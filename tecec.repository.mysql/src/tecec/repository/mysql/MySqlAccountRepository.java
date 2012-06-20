package tecec.repository.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import tecec.contract.repository.IAccountRepository;
import tecec.dto.Account;
import tecec.repository.mysql.base.MySqlConnectionConfig;
import tecec.repository.mysql.base.MySqlRepository;

public class MySqlAccountRepository extends MySqlRepository implements IAccountRepository {

	public MySqlAccountRepository(MySqlConnectionConfig connectionConfig) {
		super(connectionConfig);
	}

	@Override
	public void insertAccount(Account account) {
		String command = " INSERT INTO Account (ID, Password, UserName, FKProfile, FKStudent) " + 
					     " VALUES(:id, :password, :userName, :fKProfile, :fKStudent) ";
		
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(account);
		
		jdbcTemplate.update(command, parameters);
	}

	@Override
	public Account getAccount(String id) {
		String query = "SELECT * FROM Account WHERE ID = :id";		
		
		SqlParameterSource parameters = new MapSqlParameterSource("id", id);
		
		List<Account> accounts = getAccounts(query, parameters);
		
		if (accounts.size() > 0) {
			return accounts.get(0);
		}
		else
			return null;
	}
	
	private List<Account> getAccounts(String query, SqlParameterSource parameters)
	{
		return jdbcTemplate.query(query, parameters, new RowMapper<Account>() {

			@Override
			public Account mapRow(ResultSet arg0, int arg1)
					throws SQLException {
				Account account = new Account();

				account.setId(arg0.getString("ID"));
				account.setPassword(arg0.getString("Password"));
				account.setUserName(arg0.getString("UserName"));
				account.setfKProfile(arg0.getString("FKProfile"));
				account.setFKStudent(arg0.getString("FKStudent"));

				return account;
			}
		});
	}

	@Override
	public List<Account> getAccounts(String filter) {
		String query = "SELECT * FROM Account WHERE ID LIKE :id OR UserName LIKE :userName; ";
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		if (filter == null) {
			filter = "";
		}
		
		map.put("id", "%" + filter + "%");
		map.put("userName", "%" + filter + "%");
		
		SqlParameterSource parameters = new MapSqlParameterSource(map);
		
		return this.getAccounts(query, parameters);
	}

	@Override
	public void deleteAccount(String id) {
		String query = "DELETE FROM Account WHERE ID = :id;";
		
		SqlParameterSource parameters = new MapSqlParameterSource("id", id);
		
		jdbcTemplate.update(query, parameters);
	}

	@Override
	public void updateAccount(Account account) {
		String query = " UPDATE Account SET " + 
					   " 	UserName = :userName, " + 
					   " 	Password = :password, " + 
					   " 	FKStudent = :fKStudent, " + 
					   " 	FKProfile = :fKProfile " + 
					   " WHERE ID = :id;  ";
		
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(account);
		
		jdbcTemplate.update(query, parameters);
	}
}
