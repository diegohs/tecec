package tecec.repository.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import tecec.contract.repository.IHandInRepository;
import tecec.dto.HandIn;
import tecec.repository.mysql.base.MySqlConnectionConfig;
import tecec.repository.mysql.base.MySqlRepository;

public class MySqlHandInRepository extends MySqlRepository implements
		IHandInRepository {

	public MySqlHandInRepository(MySqlConnectionConfig connectionConfig) {
		super(connectionConfig);
	}

	@Override
	public void insertHandIn(HandIn handIn) {
		if (handIn.getPKHandIn() == null
				|| handIn.getPKHandIn().trim().isEmpty()) {
			handIn.setPKHandIn(UUID.randomUUID().toString());
		} else {
			if (handIn.getPKHandIn().length() != 36) {
				throw new IllegalArgumentException(
						"A chave primária da entrega deve ser um UUID.");
			}
		}

		if (handIn.getFKActivity() == null || handIn.getFKActivity().isEmpty()) {
			throw new IllegalArgumentException(
					"A entrega deve possuir uma atividade associada.");
		}

		if (handIn.getFKDocumentation() == null
				|| handIn.getFKActivity().isEmpty()) {
			throw new IllegalArgumentException(
					"A entrega deve possuir uma atividade associada.");
		}

		if (handIn.getFKMonograph() == null
				|| handIn.getFKMonograph().isEmpty()) {
			throw new IllegalArgumentException(
					"A entrega deve possuir uma monografia associada.");
		}

		String command = " INSERT INTO HandIn (PKHandIn, FKMonograph, FKActivity, FKDocumentation, HandedOn) "
				+ " VALUES (:pKHandIn, :fKMonograph, :fKActivity, :fKDocumentation, now()); ";

		SqlParameterSource parameters = new BeanPropertySqlParameterSource(
				handIn);

		jdbcTemplate.update(command, parameters);
	}

	@Override
	public void updateHandIn(String pKHandIn, String grade, String remark) {
		String command = " UPDATE HandIn SET " + " 		Grade = :grade, "
				+ " 		Remark = :remark " + " WHERE PKHandIn = :pKHandIn; ";
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("grade", grade);
		map.put("remark", remark);
		map.put("pKHandIn", pKHandIn);

		SqlParameterSource parameters = new MapSqlParameterSource(map);

		jdbcTemplate.update(command, parameters);
	}

	@Override
	public void deleteHandIn(String pKHandIn) {
		String command1 = " DELETE FROM Documentation WHERE PKDocumentation = (SELECT FKDocumentation FROM HandIn WHERE PKHandIn = :pKHandIn);";
		String command2 = " DELETE FROM HandIn WHERE PKHandIn = :pKHandIn; ";

		SqlParameterSource parameters = new MapSqlParameterSource("pKHandIn", pKHandIn);

		jdbcTemplate.update(command1, parameters);
		jdbcTemplate.update(command2, parameters);
	}

	@Override
	public HandIn getHandInByActivityAndMonograph(String pKActivity,
			String pKMonograph, boolean showOnTime, boolean showLate) {
		String query = " SELECT * FROM HandIn h " + 
					   " INNER JOIN Activity a ON h.FKActivity = a.PKActivity " + 
					   " WHERE FKActivity = :fKActivity " + 
					   "   AND FKMonograph = :fKMonograph ";
		
		String firstWhere = " AND h.HandedOn > a.DueDate ";
		String secondWhere = " h.HandedOn < a.DueDate ";
		
		if (showLate) {
			query += firstWhere;
			
			if (showOnTime) {
				query += "OR" + secondWhere;
			}
		}
		else if(showOnTime){
			query += "AND" + secondWhere;
		}
		else
		{
			return null;
		}

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("fKActivity", pKActivity);
		map.put("fKMonograph", pKMonograph);

		SqlParameterSource parameters = new MapSqlParameterSource(map);

		List<HandIn> handIns = getHandIns(query, parameters);

		if (handIns.size() > 0) {
			return handIns.get(0);
		} else
			return null;
	}

	private List<HandIn> getHandIns(String query, SqlParameterSource parameters) {
		List<HandIn> handIns = jdbcTemplate.query(query, parameters,
				new RowMapper<HandIn>() {
					@Override
					public HandIn mapRow(ResultSet arg0, int arg1)
							throws SQLException {
						HandIn handIn = new HandIn();

						handIn.setPKHandIn(arg0.getString("PKHandIn"));
						handIn.setFKActivity(arg0.getString("FKActivity"));
						handIn.setFKMonograph(arg0.getString("FKMonograph"));
						handIn.setFKDocumentation(arg0
								.getString("FKDocumentation"));
						handIn.setHandedOn(arg0.getDate("HandedOn"));
						handIn.setGrade(arg0.getString("Grade"));
						handIn.setRemark(arg0.getString("Remark"));

						return handIn;
					}
				});

		return handIns;
	}
}
