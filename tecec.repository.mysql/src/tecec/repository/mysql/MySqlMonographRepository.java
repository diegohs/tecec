package tecec.repository.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import tecec.contract.repository.IMonographRepository;
import tecec.repository.mysql.base.MySqlConnectionConfig;
import tecec.repository.mysql.base.MySqlRepository;
import tecec.dto.Monograph;

public class MySqlMonographRepository extends MySqlRepository implements
		IMonographRepository {

	public MySqlMonographRepository (MySqlConnectionConfig connectionConfig){
		super(connectionConfig);
	}

	@Override
	public void insertMonograph(Monograph monograph) {

		if(monograph.getTitle() == null || monograph.getTitle().trim().isEmpty()){
			throw new IllegalArgumentException(
					"O campo 'Título' não pode ser nulo");
		}

		if (monograph.getpKMonograph() == null || monograph.getpKMonograph().trim().isEmpty()){
			monograph.setpKMonograph(UUID.randomUUID().toString());
		} else {
			if (monograph.getpKMonograph().length() != 36){
				throw new IllegalArgumentException(
						"A chave primária da monografia deve ser um UUID");
			}
		}

		String command = "INSERT INTO Monograph(PKMonograph, FKArea, FKStudent, FKCourse, FKAdvisor, FKCoadvisor, FKStatus, Title) VALUES(:pKMonograph, :fKArea, :fKStudent, :fKCourse, :fKAdvisor, :fKCoadvisor, :fKStatus, :title);";
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(monograph);
		jdbcTemplate.update(command, parameters);
	}

	@Override
	public void updateMonograph(Monograph monograph) {
		if(monograph.getTitle() == null || monograph.getTitle().trim().isEmpty()){
			throw new IllegalArgumentException(
					"O campo 'Título' não pode ser nulo");
		}

		String command = "UPDATE Monograph SET FKArea = :fKArea, FKStudent = :fKStudent, FKCourse = :fKCourse, FKAdvisor = :fKAdvisor, FKCoadvisor = :fKCoadvisor, FKStatus = :fKStatus, Title= :title WHERE PKMonograph = :pKMonograph;";
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(monograph);
		jdbcTemplate.update(command, parameters);

	}

	@Override
	public void deleteMonograph(String pKMonograph) {
		String command = " DELETE FROM Monograph WHERE PKMonograph = :pKMonograph;";
		
		SqlParameterSource parameters = new MapSqlParameterSource("pKMonograph",pKMonograph);
		jdbcTemplate.update(command, parameters);
	}

	@Override
	public Monograph getMonographByTitle(String title) {
		String query = "SELECT * FROM Monograph WHERE Title = :title;";
		SqlParameterSource parameters = new MapSqlParameterSource ("title", title);

		List<Monograph> result = jdbcTemplate.query(query, parameters,
				new RowMapper<Monograph>() {
			@Override
			public Monograph mapRow(ResultSet arg0, int arg1) throws SQLException {
				Monograph monograph = new Monograph();
				monograph.setTitle(arg0.getString("Title"));
				monograph.setpKMonograph(arg0.getString("PKMonograph"));
				monograph.setfKAdvisor(arg0.getString("FKAdvisor"));
				monograph.setfKArea(arg0.getString("FKArea"));
				monograph.setfKCoadvisor(arg0.getString("FKCoadvisor"));
				monograph.setfKCourse(arg0.getString("FKCourse"));
				monograph.setfKStatus(arg0.getString("FKStatus"));
				monograph.setfKStudent(arg0.getString("FKStudent"));
				return monograph;
			}
		});

		if(result.isEmpty())
			return null;
		else
			return result.get(0);
	}

	@Override
	public Monograph getMonographByPK(String pKMonograph) {
		String query = "SELECT * FROM Monograph WHERE PKMonograph = :pKMonograph;";
		SqlParameterSource parameters = new MapSqlParameterSource("pKMonograph",pKMonograph);

		List<Monograph> result = jdbcTemplate.query(query, parameters,
				new RowMapper<Monograph>() {
			@Override
			public Monograph mapRow(ResultSet arg0, int arg1) throws SQLException {
				Monograph monograph = new Monograph();
				monograph.setTitle(arg0.getString("Title"));
				monograph.setpKMonograph(arg0.getString("PKMonograph"));
				monograph.setfKAdvisor(arg0.getString("FKAdvisor"));
				monograph.setfKArea(arg0.getString("FKArea"));
				monograph.setfKCoadvisor(arg0.getString("FKCoadvisor"));
				monograph.setfKCourse(arg0.getString("FKCourse"));
				monograph.setfKStatus(arg0.getString("FKStatus"));
				monograph.setfKStudent(arg0.getString("FKStudent"));
				return monograph;
			}
		});

		if(result.isEmpty())
			return null;
		else
			return result.get(0);
	}

	@Override
	public List<Monograph> getMonograph(String nameFilter) {
		if (nameFilter == null)
			nameFilter = "";
		
		String query = " SELECT * FROM Monograph WHERE Title LIKE :nameFilter ORDER BY FKStudent;";
		
		SqlParameterSource parameters = new MapSqlParameterSource("nameFilter",
				"%" + nameFilter + "%");

		return getMonograph(query, parameters);		
	}
	
	private List<Monograph> getMonograph(String query, SqlParameterSource parameters){
		List<Monograph> result = jdbcTemplate.query(query, parameters,
				new RowMapper<Monograph>() {
			@Override
			public Monograph mapRow(ResultSet arg0, int arg1) throws SQLException {
				Monograph monograph = new Monograph();
				monograph.setTitle(arg0.getString("Title"));
				monograph.setpKMonograph(arg0.getString("PKMonograph"));
				monograph.setfKAdvisor(arg0.getString("FKAdvisor"));
				monograph.setfKArea(arg0.getString("FKArea"));
				monograph.setfKCoadvisor(arg0.getString("FKCoadvisor"));
				monograph.setfKCourse(arg0.getString("FKCourse"));
				monograph.setfKStatus(arg0.getString("FKStatus"));
				monograph.setfKStudent(arg0.getString("FKStudent"));
				return monograph;
			}
		});
		
		return result;
	}

}
