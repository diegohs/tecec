package tecec.root.config;

public class TececMySqlConnectionConfig extends tecec.repository.mysql.base.MySqlConnectionConfig {

	public TececMySqlConnectionConfig() {
		//super("jdbc:mysql://localhost:3306/tecec", "root", "lester");
		super("jdbc:mysql://codd:3306/tecec", "alunobd", "alunobd");
		//super("jdbc:mysql://mysql-t.sourceforge.net:3306/t747309_tecec", "t747309_admin", "lester");
	}
}
