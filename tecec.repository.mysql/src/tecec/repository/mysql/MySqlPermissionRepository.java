package tecec.repository.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import tecec.contract.repository.IPermissionRepository;
import tecec.dto.Permission;
import tecec.repository.mysql.base.MySqlConnectionConfig;
import tecec.repository.mysql.base.MySqlRepository;

public class MySqlPermissionRepository extends MySqlRepository implements IPermissionRepository
{

	public MySqlPermissionRepository(MySqlConnectionConfig connectionConfig) {
		super(connectionConfig);
	}
	
	private void validatePermission (Permission permission) {
		if (permission.getDescription() == null || permission.getDescription().trim().isEmpty()) {
			throw new IllegalArgumentException ("O campo 'descrição' da permissão não pode ser nulo.");
		}
		
		if (permission.getpKPermission() == null || permission.getpKPermission().trim().isEmpty()) {
			permission.setpKPermission(UUID.randomUUID().toString());
		} else {
			if (permission.getpKPermission().length() != 36) {
				throw new IllegalArgumentException ("A chave primária da permissão deve ser um UUID.");
			}
		}
		
	}
	

	@Override
	public void insertPermission(Permission permission) {
		validatePermission(permission);
		String command = "INSERT INTO Permission (PKPermission, Description) VALUES (:pKPermission, :description);";
		SqlParameterSource namedParameter = new BeanPropertySqlParameterSource
				(permission);
		jdbcTemplate.update(command, namedParameter);
	}

	@Override
	public void updatePermission(Permission permission) {
		String query = "UPDATE Permission SET Description = :description WHERE PKPermission = :pKPermission;";
		SqlParameterSource parameters = new BeanPropertySqlParameterSource (permission);
		jdbcTemplate.update(query, parameters);
	}

	@Override
	public void deletePermission(String pKPermission) {
		String command = "DELETE FROM Permission WHERE PKPermission = :pKPermission;";
		SqlParameterSource namedParameter = new MapSqlParameterSource
				("pKPermission", pKPermission);
		jdbcTemplate.update(command, namedParameter);		
	}

	@Override
	public Permission getPermissionByDescription(String description) {
		String query = "SELECT * FROM Permission WHERE Description = :description;";
		SqlParameterSource parameters = new MapSqlParameterSource ("description", description);
		
		List <Permission> result = jdbcTemplate.query(query, parameters, 
				new RowMapper <Permission> () {
			@Override
			public Permission mapRow (ResultSet arg0, int arg1) throws SQLException {
				Permission permission = new Permission ();
				permission.setDescription(arg0.getString("Description"));
				permission.setpKPermission(arg0.getString("PKPermission"));
				return permission;
			}
		});
		
		if (result.isEmpty())
			return null;
		else 
			return result.get(0);	
	}

	@Override
	public Permission getPermissionByPK(String pKPermission) {
		String query = "SELECT * FROM Permission WHERE PKPermission = :pKPermission;";
		SqlParameterSource parameters = new MapSqlParameterSource ("pKPermission", pKPermission);
		
		List <Permission> result = jdbcTemplate.query(query, parameters, 
				new RowMapper <Permission> () {
			@Override
			public Permission mapRow (ResultSet arg0, int arg1) throws SQLException {
				Permission permission = new Permission ();
				permission.setDescription(arg0.getString("Description"));
				permission.setpKPermission(arg0.getString("PKPermission"));
				return permission;
			}
		});
		
		if (result.isEmpty())
			return null;
		else 
			return result.get(0);
	}

	@Override
	public List<Permission> getPermission(String nameFilter) {
		String query = "SELECT * FROM Permission WHERE Description LIKE :nameFilter;";
		if (nameFilter == null)
			nameFilter = "";
		
		SqlParameterSource parameters = new MapSqlParameterSource ("nameFilter",
				"%" + nameFilter + "%");
		
		List <Permission> result = jdbcTemplate.query(query, parameters,
				new RowMapper <Permission> () {
			@Override
			public Permission mapRow (ResultSet arg0, int arg1)
				throws SQLException {
				Permission permission = new Permission ();
				permission.setDescription(arg0.getString("Description"));
				permission.setpKPermission(arg0.getString("PKPermission"));
				
				return permission;
			}
		});
		
		return result;		
	}

}
