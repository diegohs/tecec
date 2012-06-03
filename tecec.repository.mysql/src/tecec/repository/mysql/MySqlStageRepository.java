package tecec.repository.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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

public class MySqlStageRepository extends MySqlRepository implements IStageRepository {
	
	private void validateStage (Stage stage) {
		
		if (stage.getName() == null || stage.getName().trim().isEmpty()) {
			throw new IllegalArgumentException ("O campo nome do estágio não pode ser nulo.");
		}
		
		if (stage.getYear() == null || stage.getYear().trim().isEmpty() || stage.getYear().length()!=4) {
			throw new IllegalArgumentException ("O campo ano do estágio deve ser válido.");
		}
		
		Stage search = this.getStageByNameAndYear(stage.getName(), stage.getYear());
		
		if (search != null)
			throw new IllegalArgumentException ("Já existe outro estágio cadastrado com estas descrições.");
		
		
		if (stage.getpKStage() == null || stage.getpKStage().trim().isEmpty()) {
			stage.setpKStage(UUID.randomUUID().toString());
		} else {
			if (stage.getpKStage().length() != 36) {
				throw new IllegalArgumentException ("A chave primária do estágio deve ser um UUID.");
			}
		}	
	}

	public MySqlStageRepository(MySqlConnectionConfig connectionConfig) {
		super(connectionConfig);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insertStage(Stage stage) {
		validateStage (stage);
		
		String command = "INSERT INTO Stage (PKStage, Name, Year) VALUES (:pKStage, :name, :year);";
		
		SqlParameterSource parameter = new BeanPropertySqlParameterSource (stage);
		
		jdbcTemplate.update(command, parameter);
	}

	@Override
	public void updateStage(Stage stage) {
		Stage search = this.getStageByNameAndYear(stage.getName(), stage.getYear());
		
		if (search != null)
			throw new IllegalArgumentException ("Já existe outro estágio cadastrado com estas descrições.");

		String query = "UPDATE Stage Set Name = :name, Year = :year WHERE PKStage = :pKStage;";
		SqlParameterSource parameters = new BeanPropertySqlParameterSource (stage);
		jdbcTemplate.update(query, parameters);
		
	}

	@Override
	public void deleteStage(String pKStage) {
		String command = "DELETE FROM Stage WHERE PKStage = :pKStage;";
		SqlParameterSource namedParameter = new MapSqlParameterSource("pKStage",pKStage);
		jdbcTemplate.update(command, namedParameter);
	}

	@Override
	public Stage getStageByNameAndYear(String name, String year) {
		if (name == null || year == null || year.length()!=4)
			return null;
		
		
		String query = "SELECT *FROM Stage WHERE Name=:name AND Year = :year;";
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("name", name);
		map.put("year", year);
		
		SqlParameterSource parameters = new MapSqlParameterSource (map);
		
		List <Stage> stages = getStages (query, parameters);
		
		if (stages.size() > 0)
			return stages.get(0);
		else 
			return null;
	}

	@Override
	public Stage getStageByPK(String pKStage) {
		String query = "SELECT *FROM Stage WHERE PKStage = :pKStage;";
		
		SqlParameterSource parameters = new MapSqlParameterSource("pKStage", pKStage);
		
		List <Stage> stages = getStages (query, parameters);
		
		if (stages.size() > 0)
			return stages.get(0);
		else 
			return null;

	}

	@Override
	public List<Stage> getStages(String nameFilter) {
		if (nameFilter == null)
			nameFilter = "";
		
		String query = "SELECT *FROM Stage WHERE Name LIKE :nameFilter;";
		SqlParameterSource parameters = new MapSqlParameterSource ("nameFilter", "%" + nameFilter + "%");
		return getStages (query, parameters);
		
	}
	
	private List <Stage> getStages (String query, SqlParameterSource parameters) {
		List <Stage> stages = jdbcTemplate.query(query, parameters, 
				new RowMapper <Stage> () {
					@Override
					public Stage mapRow(ResultSet arg0, int arg1)
							throws SQLException {
						Stage stage = new Stage ();
						stage.setpKStage(arg0.getString("PKStage"));
						stage.setName(arg0.getString("Name"));
						stage.setYear(arg0.getString("Year"));
						
						return stage;
					}
			
		});
		
		return stages;
	}
	
}
