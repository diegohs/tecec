package tecec.root.config;

public class TececMySqlConnectionConfig extends tecec.repository.mysql.base.MySqlConnectionConfig {

	public TececMySqlConnectionConfig() {
		super("jdbc:mysql://localhost:3306/tecec", "root", "lester");
	}

}
