package tecec.repository.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import tecec.contract.repository.IMonographRepository;
import tecec.dto.Monograph;
import tecec.repository.mysql.base.MySqlConnectionConfig;
import tecec.repository.mysql.base.MySqlRepository;

public class MySqlMonographRepository extends MySqlRepository implements
		IMonographRepository {
	
	public MySqlMonographRepository(MySqlConnectionConfig connectionConfig){
		super(connectionConfig);
	}

	@Override
	public void insertMonograph(Monograph monograph) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateMonograph(Monograph monograph) {
		String command = " UPDATE Monograph SET FKArea = :fKArea, FKStudent = :fKStudent, " +
				"FKCourse = :fKCourse, FKAdvisor = :fKAdvisor, FKCoadvisor = :fKCoadvisor, " +
				"FKStatus = :fKStatus, Title = :title "
				+ " WHERE PKMonograph = :pKMonograph; ";

		SqlParameterSource parameters = new BeanPropertySqlParameterSource(
				monograph);

		jdbcTemplate.update(command, parameters);
	}

	@Override
	public void deleteMonograph(String pKMonograph) {
		String command = " DELETE FROM Monograph WHERE PKMonograph = :pKMonograph";

		SqlParameterSource parameters = new MapSqlParameterSource("pKMonograph",
				pKMonograph);

		jdbcTemplate.update(command, parameters);
	}

	@Override
	public List<Monograph> getMonograph(String titleFilter) {
		String query = " SELECT * FROM Monograph WHERE Title LIKE :titleFilter";

		SqlParameterSource parameters = new MapSqlParameterSource(
				"titleFilter", "%" + titleFilter + "%");

		return getMonograph(query, parameters);
	}
	
	private List<Monograph> getMonograph(String query,
			SqlParameterSource parameters){
		return jdbcTemplate.query(query, parameters, new RowMapper<Monograph>() {

			@Override
			public Monograph mapRow(ResultSet arg0, int arg1)
					throws SQLException {
				Monograph monograph = new Monograph();

				monograph.setPKMonograph(arg0.getString("PKMonograph"));
				monograph.setFKAdvisor(arg0.getString("FKAdvisor"));
				monograph.setFKArea(arg0.getString("FKArea"));
				monograph.setFKCoadvisor(arg0.getString("FKCoadvisor"));
				monograph.setFKCourse(arg0.getString("FKCourse"));
				monograph.setFKStatus(arg0.getString("FKStatus"));
				monograph.setFKStudent(arg0.getString("FKStudent"));
				monograph.setTitle(arg0.getString("Title"));
				
				return monograph;
			}
		});
	}
	
	@Override
	public Monograph getMonographByPK(String pKMonograph) {
		String query = " SELECT * FROM Monograph WHERE PKMonograph = :pKMonograph;";

		SqlParameterSource parameters = new MapSqlParameterSource("pKMonograph",
				pKMonograph);

		List<Monograph> monograph = getMonograph(query, parameters);

		if (monograph.size() > 0) {
			return monograph.get(0);
		}

		return null;
	}
}
