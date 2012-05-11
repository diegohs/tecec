package tecec.repository.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import tecec.contract.repository.IStatusRepository;
import tecec.dto.Status;
import tecec.repository.mysql.base.MySqlConnectionConfig;
import tecec.repository.mysql.base.MySqlRepository;

public class MySqlStatusRepository extends MySqlRepository implements IStatusRepository
{

	public MySqlStatusRepository(MySqlConnectionConfig connectionConfig) {
		super(connectionConfig);
	}
	
	private void validateStatus (Status status) {
		if (status.getDescription() == null || status.getDescription().trim().isEmpty()) {
			throw new IllegalArgumentException ("O campo 'descrição' do status não pode ser nulo.");
		}
		
		if (status.getpKStatus() == null || status.getpKStatus().trim().isEmpty()) {
			status.setpKStatus(UUID.randomUUID().toString());
		} else {
			if (status.getpKStatus().length() != 36) {
				throw new IllegalArgumentException ("A chave primária do status deve ser um UUID.");
			}
		}
		
	}
	

	@Override
	public void insertStatus(Status status) {
		validateStatus(status);
		String command = "INSERT INTO Status (PKStatus, Description) VALUES (:pKStatus, :description);";
		SqlParameterSource namedParameter = new BeanPropertySqlParameterSource
				(status);
		jdbcTemplate.update(command, namedParameter);
	}

	@Override
	public void updateStatus(Status status) {
		String query = "UPDATE Status SET Description = :description WHERE PKStatus = :pKStatus;";
		SqlParameterSource parameters = new BeanPropertySqlParameterSource (status);
		jdbcTemplate.update(query, parameters);
	}

	@Override
	public void deleteStatus(String pKStatus) {
		String command = "DELETE FROM Status WHERE PKStatus = :pKStatus;";
		SqlParameterSource namedParameter = new MapSqlParameterSource
				("pKStatus", pKStatus);
		jdbcTemplate.update(command, namedParameter);		
	}

	@Override
	public Status getStatusByDescription(String description) {
		String query = "SELECT * FROM Status WHERE Description = :description;";
		SqlParameterSource parameters = new MapSqlParameterSource ("description", description);
		
		List <Status> result = jdbcTemplate.query(query, parameters, 
				new RowMapper <Status> () {
			@Override
			public Status mapRow (ResultSet arg0, int arg1) throws SQLException {
				Status status = new Status ();
				status.setDescription(arg0.getString("Description"));
				status.setpKStatus(arg0.getString("PKStatus"));
				return status;
			}
		});
		
		if (result.isEmpty())
			return null;
		else 
			return result.get(0);	
	}

	@Override
	public Status getStatusByPK(String pKStatus) {
		String query = "SELECT * FROM Status WHERE PKStatus = :pKStatus;";
		SqlParameterSource parameters = new MapSqlParameterSource ("pKStatus", pKStatus);
		
		List <Status> result = jdbcTemplate.query(query, parameters, 
				new RowMapper <Status> () {
			@Override
			public Status mapRow (ResultSet arg0, int arg1) throws SQLException {
				Status status = new Status ();
				status.setDescription(arg0.getString("Description"));
				status.setpKStatus(arg0.getString("PKStatus"));
				return status;
			}
		});
		
		if (result.isEmpty())
			return null;
		else 
			return result.get(0);
	}

	@Override
	public List<Status> getStatus(String nameFilter) {
		String query = "SELECT * FROM Status WHERE Description LIKE :nameFilter;";
		if (nameFilter == null)
			nameFilter = "";
		
		SqlParameterSource parameters = new MapSqlParameterSource ("nameFilter",
				"%" + nameFilter + "%");
		
		List <Status> result = jdbcTemplate.query(query, parameters,
				new RowMapper <Status> () {
			@Override
			public Status mapRow (ResultSet arg0, int arg1)
				throws SQLException {
				Status status = new Status ();
				status.setDescription(arg0.getString("Description"));
				status.setpKStatus(arg0.getString("PKStatus"));
				
				return status;
			}
		});
		
		return result;		
	}

}
