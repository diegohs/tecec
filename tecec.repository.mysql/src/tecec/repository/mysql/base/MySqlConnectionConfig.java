package tecec.repository.mysql.base;

public abstract class MySqlConnectionConfig {
	protected String host;
	protected String user;
	protected String password;
	
	public String getHost() {
		return host;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}
	
	public MySqlConnectionConfig(String host, String user, String password) {
		this.host = host;
		this.user = user;
		this.password = password;
	}
}
