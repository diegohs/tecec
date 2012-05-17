package tecec.repository.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import tecec.contract.repository.IStageRepository;
import tecec.dto.Stage;
import tecec.repository.mysql.base.MySqlConnectionConfig;
import tecec.repository.mysql.base.MySqlRepository;

public class MysqlStageRepository extends MySqlRepository implements IStageRepository {

	public MysqlStageRepository(MySqlConnectionConfig connectionConfig) {
		super(connectionConfig);
		// TODO Auto-generated constructor stub
	}
	
	private void validateStage (Stage stage) {
		if (stage.getName() == null || stage.getName().trim().isEmpty())
			throw new IllegalArgumentException ("O campo 'nome' da descrição não pode ser nulo.");
		
		if (stage.getpKStage() == null || stage.getpKStage().trim().isEmpty())
			stage.setpKStage(UUID.randomUUID().toString());
		else {
			if (stage.getpKStage().length() != 36)
				throw new IllegalArgumentException ("A chave primária do estágio deve ser um UUID.");
		}
	}
	

	@Override
	public void insertStage(Stage stage) {
		validateStage (stage);
		
		String command = "INSERT INTO Stage (PKStage, Name, Year) VALUES (:pKStage, :name, :year);";
		SqlParameterSource namedParameter= new BeanPropertySqlParameterSource (stage);
		jdbcTemplate.update(command, namedParameter);		
	}

	@Override
	public void updateStage(Stage stage) {
		String query = "UPDATE Stage SET Name = :name, Year = :year WHERE PKStage = :pKStage;";
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(stage);
		jdbcTemplate.update(query, parameters);		
	}

	@Override
	public void deleteStage(String pKStage) {
		String command = "DELETE FROM Stage WHERE PKStage  = :pKStage;";
		SqlParameterSource namedParameter = new MapSqlParameterSource("pKStage",pKStage);
		jdbcTemplate.update(command, namedParameter);	
	}

	@Override
	public Stage getStageByName(String name) {
		String query = "SELECT * FROM Stage WHERE Name = :name;";
		SqlParameterSource parameters = new MapSqlParameterSource ("name", name);
		
		List <Stage> result = jdbcTemplate.query(query, parameters, new RowMapper <Stage> () {
			@Override
			public Stage mapRow (ResultSet arg0, int arg1) throws SQLException {
				Stage stage = new Stage ();
				stage.setName(arg0.getString("Name"));
				stage.setYear(arg0.getInt("Year"));
				stage.setpKStage(arg0.getString("PKStage"));
				return stage;
			}
		});
		
		if (result.isEmpty())
			return null;
		else return result.get(0);
		
	}

	@Override
	public Stage getStageByPK(String pKStage) {
		String query = "SELECT * FROM Stage WHERE PKStage = :pKStage;";
		SqlParameterSource parameters = new MapSqlParameterSource ("pKStage", pKStage);
		
		List <Stage> result = jdbcTemplate.query(query, parameters, new RowMapper <Stage> () {
			@Override
			public Stage mapRow (ResultSet arg0, int arg1) throws SQLException {
				Stage stage = new Stage ();
				stage.setName(arg0.getString("Name"));
				stage.setYear(arg0.getInt("Year"));
				stage.setpKStage(arg0.getString("PKStage"));
				return stage;
			}
		});
		
		if (result.isEmpty())
			return null;
		else return result.get(0);
	}

	@Override
	public Stage getStageByYear(int year) {
		String query = "SELECT * FROM Stage WHERE Year = :year;";
		SqlParameterSource parameters = new MapSqlParameterSource ("year", year);
		
		List <Stage> result = jdbcTemplate.query(query, parameters, new RowMapper <Stage> () {
			@Override
			public Stage mapRow (ResultSet arg0, int arg1) throws SQLException {
				Stage stage = new Stage ();
				stage.setName(arg0.getString("Name"));
				stage.setYear(arg0.getInt("Year"));
				stage.setpKStage(arg0.getString("PKStage"));
				return stage;
			}
		});
		
		if (result.isEmpty())
			return null;
		else return result.get(0);
	}

	@Override
	public List<Stage> getStages(String nameFilter) {
		String query = "SELECT * FROM Stage WHERE Name LIKE: nameFilter;";
		
		if (nameFilter == null)
			nameFilter = "";
		SqlParameterSource parameters = new MapSqlParameterSource ("nameFilter",
				"%" + nameFilter + "%");
		
		List <Stage> result = jdbcTemplate.query(query, parameters,
				new RowMapper <Stage> () {
			@Override
			public Stage mapRow (ResultSet arg0, int arg1) throws SQLException{
				Stage stage = new Stage ();
				stage.setName(arg0.getString("Name"));
				stage.setpKStage(arg0.getString("PKStage"));
				stage.setYear(arg0.getInt("Year"));
				return stage;
				
			}
		});
		
		return result;
	}

}
