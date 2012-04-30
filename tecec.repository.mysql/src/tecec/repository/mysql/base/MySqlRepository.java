package tecec.repository.mysql.base;

import java.sql.*;

public abstract class MySqlRepository {
	private MySqlConnectionConfig connectionConfig;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MySqlRepository(MySqlConnectionConfig connectionConfig) {
		this.connectionConfig = connectionConfig;
	}

	public ResultSet select(String query) throws Exception {
		Connection connection = null;
		Statement statement = null;

		try {
			connection = DriverManager.getConnection(
					this.connectionConfig.getHost(),
					this.connectionConfig.getUser(),
					this.connectionConfig.getPassword());

			statement = connection.createStatement();

			return statement.executeQuery(query);
		} catch (Exception e) {
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}

			if (statement != null) {
				statement.close();
			}
		}
	}

	public int execute(String command) throws Exception {
		Connection connection = null;
		Statement statement = null;

		try {
			connection = DriverManager.getConnection(
					this.connectionConfig.getHost(),
					this.connectionConfig.getUser(),
					this.connectionConfig.getPassword());

			statement = connection.createStatement();

			return statement.executeUpdate(command);
		} catch (Exception e) {
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}

			if (statement != null) {
				statement.close();
			}
		}
	}
}
