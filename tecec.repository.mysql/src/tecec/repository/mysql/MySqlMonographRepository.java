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
import tecec.dto.Course;
import tecec.dto.Monograph;
import tecec.dto.Student;
import tecec.repository.mysql.base.MySqlConnectionConfig;
import tecec.repository.mysql.base.MySqlRepository;

public class MySqlMonographRepository extends MySqlRepository implements
		IMonographRepository {

	/* Verifiquem a classe toda, não sei se está certo refiz. Por: Bruno */

	/* Construtor */
	public MySqlMonographRepository(MySqlConnectionConfig connectionConfig) {
		super(connectionConfig);
	}

	/* Verificar não sei se está certo. Por: Bruno */
	private void validateMonograph(Monograph monograph) {
		/*
		 * Obs: não é necessário um "coorientador", isso é particular, varia de
		 * caso para caso
		 */
		if (monograph.getPKMonograph() == null
				|| monograph.getPKMonograph().trim().isEmpty()) {
			monograph.setPKMonograph(UUID.randomUUID().toString());
		} else {
			if (monograph.getPKMonograph().length() != 36) {
				throw new IllegalArgumentException(
						"A chave primária da monografia deve ser um UUID.");
			}
		}

		if (monograph.getFKArea() == null
				|| monograph.getFKArea().trim().isEmpty()) {
			throw new IllegalArgumentException(
					"A monografia deve ser associada a alguma 'área'.");
		}

		if (monograph.getFKStudent() == null
				|| monograph.getFKStudent().trim().isEmpty()) {
			throw new IllegalArgumentException(
					"A monografia deve ser associada a algum 'estudante'.");
		}

		if (monograph.getFKCourse() == null
				|| monograph.getFKCourse().trim().isEmpty()) {
			throw new IllegalArgumentException(
					"A monografia deve ser associada a algum 'curso'.");
		}

		if (monograph.getFKAdvisor() == null
				|| monograph.getFKAdvisor().trim().isEmpty()) {
			throw new IllegalArgumentException(
					"A monografia deve ser associada a algum 'orientador'.");
		}

		if (monograph.getFKStatus() == null
				|| monograph.getFKStatus().trim().isEmpty()) {
			throw new IllegalArgumentException(
					"A monografia deve possuir um 'status' de andamento.");
		}

		if (monograph.getTitle() == null
				|| monograph.getTitle().trim().isEmpty()) {
			throw new IllegalArgumentException(
					"A monografia deve possuir um 'título'.");
		}
	}

	@Override
	public void insertMonograph(Monograph monograph) {

		validateMonograph(monograph);
		String command = "INSERT INTO Monograph (PKMonograph, FKStudent, FKCourse, FKAdvisor, FKCoadvisor, FKStatus, Title)"
				+ " VALUES (:pKMonograph, :fKArea, :fKStudent, :fKCourse, :fKAdvisor, fKCoadvisor,"
				+ " :fKStatus, :title);";

		SqlParameterSource nameParameter = new BeanPropertySqlParameterSource(
				monograph);
		jdbcTemplate.update(command, nameParameter);
	}

	@Override
	public void updateMonograph(Monograph monograph) {

		String query = "";

		/* Possui coorientador if (true) */
		if (monograph.getFKCoadvisor() != null
				&& !monograph.getFKCoadvisor().isEmpty()) {
			query = "UPDATE Monograph SET FKStudent = :fKStudent, FKCourse = :fKCourse, FKAdvisor = :fKAdvisor, "
					+ "FKCoadvisor = :fKCoadvisor, FKStatus = :fKStatus, Title = :title WHERE PKMonograph = :pKMonograph;";
		} else {
			query = "UPDATE Monograph SET FKStudent = :fKStudent, FKCourse = :fKCourse, FKAdvisor = :fKAdvisor, "
					+ "FKCoadvisor = NULL, FKStatus = :fKStatus, Title = :title WHERE PKMonograph = :pKMonograph;";
		}

		SqlParameterSource parameters = new BeanPropertySqlParameterSource(
				monograph);
		jdbcTemplate.update(query, parameters);
	}	

	@Override
	public void deleteMonograph(String pKMonograph) {
		String command = "DELETE FROM Monograph WHERE PKMonograph = :pkMonograph;";
		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"pKMonograph", pKMonograph);
		jdbcTemplate.update(command, namedParameter);
	}
	
	@Override
	public Monograph getMonographByPK(String pKMonograph) {
		String query = "SELECT * FROM Monograph WHERE PKMonograph = :pKMonograph;";
		SqlParameterSource parameters = new MapSqlParameterSource ("pKMonograph", pKMonograph);
		
		List <Monograph> result = jdbcTemplate.query(query, parameters,
				new RowMapper<Monograph>() {
			@Override
			public Monograph mapRow(ResultSet arg0, int arg1)
					throws SQLException {
				Monograph monograph = new Monograph();
				
				monograph.setFKAdvisor(arg0.getString("FKAdvisor"));
				monograph.setFKArea(arg0.getString("FKArea"));
				monograph.setFKCoadvisor(arg0.getString("FKCoadvisor"));
				monograph.setFKCourse(arg0.getString("FKCourse"));
				monograph.setFKStatus(arg0.getString("FKStatus"));
				monograph.setFKStudent(arg0.getString("FKStudent"));
				monograph.setPKMonograph(arg0.getString("PKMonograph"));
				monograph.setTitle(arg0.getString("Title"));
				
				return monograph;
			}
		});
		
		if (result.isEmpty())
			return null;
		else return result.get(0);
	}
	

	@Override
	public List<Monograph> getMonograph(String titleFilter) {
		// TODO Auto-generated method stub
		return null;
	}
}
