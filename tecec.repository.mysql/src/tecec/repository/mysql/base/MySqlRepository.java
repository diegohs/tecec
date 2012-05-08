package tecec.repository.mysql.base;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.*;


public abstract class MySqlRepository {
    protected static NamedParameterJdbcTemplate jdbcTemplate;

	public MySqlRepository(MySqlConnectionConfig connectionConfig) {		
		if (jdbcTemplate == null) {
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			
			dataSource.setDriverClassName("com.mysql.jdbc.Driver");
			dataSource.setUrl(connectionConfig.getHost());
			dataSource.setUsername(connectionConfig.getUser());
			dataSource.setPassword(connectionConfig.getPassword());
			
			jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		}		
	}
}
