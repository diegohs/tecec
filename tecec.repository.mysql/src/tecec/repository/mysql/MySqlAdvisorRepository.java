package tecec.repository.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import tecec.contract.repository.IAdvisorRepository;

import tecec.dto.Advisor;
import tecec.repository.mysql.base.MySqlConnectionConfig;
import tecec.repository.mysql.base.MySqlRepository;

public class MySqlAdvisorRepository extends MySqlRepository implements
		IAdvisorRepository {

	public MySqlAdvisorRepository(MySqlConnectionConfig connectionConfig) {
		super(connectionConfig);
	}

	private void validateAdvisor(Advisor advisor) {
		if (advisor.getName() == null || advisor.getName().trim().isEmpty()) {
			throw new IllegalArgumentException(
					"O campo 'nome' do orientador não pode ser nulo.");
		}

		if (advisor.getEmail() == null || advisor.getEmail().trim().isEmpty()) {
			throw new IllegalArgumentException(
					"O campo 'e-mail' do orientador não pode ser nulo.");
		}

		if (advisor.getPKAdvisor() == null
				|| advisor.getPKAdvisor().trim().isEmpty()) {
			advisor.setPKAdvisor(UUID.randomUUID().toString());
		} else {
			if (advisor.getPKAdvisor().length() != 36) {
				throw new IllegalArgumentException(
						"A chave primária do orientador deve ser um UUID.");
			}
		}
	}

	@Override
	public void insertAdvisor(Advisor advisor) {
		validateAdvisor(advisor);
		String command = "INSERT INTO Advisor(PKAdvisor, Name, Email) VALUES (:pKAdvisor, :name, :email);";
		SqlParameterSource namedParameter = new BeanPropertySqlParameterSource(
				advisor);
		jdbcTemplate.update(command, namedParameter);
	}

	@Override
	public void updateAdvisor(Advisor advisor) {
		String query = "UPDATE Advisor SET Name = :name, Email = :email WHERE PKAdvisor = :pKAdvisor;";
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(
				advisor);
		jdbcTemplate.update(query, parameters);
	}

	@Override
	public Advisor getAdvisorByName(String name) {
		String query = "SELECT * FROM Advisor WHERE Name = :name;";
		SqlParameterSource parameters = new MapSqlParameterSource("name", name);

		List<Advisor> result = jdbcTemplate.query(query, parameters,
				new RowMapper<Advisor>() {
					@Override
					public Advisor mapRow(ResultSet arg0, int arg1)
							throws SQLException {
						Advisor advisor = new Advisor();
						advisor.setName(arg0.getString("Name"));
						advisor.setEmail(arg0.getString("Email"));
						advisor.setPKAdvisor(arg0.getString("PKAdvisor"));

						return advisor;
					}
				});

		if (result.isEmpty()) {
			return null;
		} else {
			return result.get(0);
		}
	}

	@Override
	public Advisor getAdvisorByPk(String pKAdvisor) {
		String query = "SELECT * FROM Advisor WHERE PKAdvisor = :pKAdvisor;";
		SqlParameterSource parameters = new MapSqlParameterSource("pKAdvisor",
				pKAdvisor);

		List<Advisor> result = jdbcTemplate.query(query, parameters,
				new RowMapper<Advisor>() {
					@Override
					public Advisor mapRow(ResultSet arg0, int arg1)
							throws SQLException {
						Advisor advisor = new Advisor();
						advisor.setName(arg0.getString("Name"));
						advisor.setEmail(arg0.getString("Email"));
						advisor.setPKAdvisor(arg0.getString("PKAdvisor"));

						return advisor;
					}
				});

		if (result.isEmpty()) {
			return null;
		} else {
			return result.get(0);
		}
	}

	@Override
	public Advisor getAdvisorByEmail(String email) {
		String query = "SELECT * FROM Advisor WHERE Email = :email;";
		SqlParameterSource parameters = new MapSqlParameterSource("email",
				email);

		List<Advisor> result = jdbcTemplate.query(query, parameters,
				new RowMapper<Advisor>() {
					@Override
					public Advisor mapRow(ResultSet arg0, int arg1)
							throws SQLException {
						Advisor advisor = new Advisor();
						advisor.setName(arg0.getString("Name"));
						advisor.setEmail(arg0.getString("Email"));
						advisor.setPKAdvisor(arg0.getString("PKAdvisor"));

						return advisor;
					}
				});

		if (result.isEmpty()) {
			return null;
		} else {
			return result.get(0);
		}
	}

	@Override
	public List<Advisor> getAdvisors(String nameFilter) {
		String query = "SELECT * FROM Advisor WHERE Name Like :nameFilter;";

		if (nameFilter == null)
			nameFilter = "";

		SqlParameterSource parameters = new MapSqlParameterSource("nameFilter",
				"%" + nameFilter + "%");

		List<Advisor> result = jdbcTemplate.query(query, parameters,
				new RowMapper<Advisor>() {
					@Override
					public Advisor mapRow(ResultSet arg0, int arg1)
							throws SQLException {
						Advisor advisor = new Advisor();

						advisor.setName(arg0.getString("Name"));
						advisor.setEmail(arg0.getString("Email"));
						advisor.setPKAdvisor(arg0.getString("PKAdvisor"));

						return advisor;
					}
				});
		return result;
	}

	@Override
	public void deleteAdvisor(String pKAdvisor) {
		String command = " DELETE FROM Advisor WHERE PKAdvisor = :pKAdvisor;";
		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"pKAdvisor", pKAdvisor);
		jdbcTemplate.update(command, namedParameter);		
	}

	@Override
	public boolean doesAdviseAnyMonograph(String pKAdvisor) {
		String query = " SELECT COUNT(*) " + 
					   " FROM Advisor a " + 
					   " INNER JOIN Monograph m ON a.PKAdvisor = m.FKAdvisor " + 
					   " WHERE a.PKAdvisor = :pKAdvisor";
		
		SqlParameterSource parameters = new MapSqlParameterSource("pKAdvisor", pKAdvisor);
		
		if (jdbcTemplate.queryForInt(query, parameters) > 0) {
			return true;
		}
		
		query = " SELECT COUNT(*) " + 
			    " FROM Advisor a " + 
			    " INNER JOIN Monograph m ON a.PKAdvisor = m.FKCoadvisor " + 
			    " WHERE a.PKAdvisor = :pKAdvisor";
		

		if (jdbcTemplate.queryForInt(query, parameters) > 0) {
			return true;
		}
		
		return false;
	}
}
