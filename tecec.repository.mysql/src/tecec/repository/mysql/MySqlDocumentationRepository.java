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

import tecec.contract.repository.IDocumentationRepository;

import tecec.dto.Documentation;

import tecec.repository.mysql.base.MySqlConnectionConfig;
import tecec.repository.mysql.base.MySqlRepository;

public class MySqlDocumentationRepository extends MySqlRepository implements
		IDocumentationRepository {

	public MySqlDocumentationRepository(MySqlConnectionConfig connectionConfig) {
		super(connectionConfig);
		// TODO Auto-generated constructor stub
	}

	private void validateDocumentation(Documentation documentation) {
		if (documentation.getFileName() == null
				|| documentation.getFileName().trim().isEmpty())
			throw new IllegalArgumentException("O nome do arquivo é inválido.");

		if (documentation.getData() == null
				|| documentation.getData().length <= 0) {
			throw new IllegalArgumentException("O arquivo é inválido.");
		}

		if (documentation.getpKDocumentation() == null
				|| documentation.getpKDocumentation().trim().isEmpty()) {
			documentation.setpKDocumentation(UUID.randomUUID().toString());
		} else {
			if (documentation.getpKDocumentation().length() != 36) {
				throw new IllegalArgumentException(
						"A chave primária da documentação deve ser um UUID.");
			}
		}

	}

	private List<Documentation> getDocumentations(String query,
			SqlParameterSource parameters) {
		List<Documentation> docs = jdbcTemplate.query(query, parameters,
				new RowMapper<Documentation>() {
					@Override
					public Documentation mapRow(ResultSet arg0, int arg1)
							throws SQLException {
						Documentation documentation = new Documentation();
						documentation.setpKDocumentation(arg0
								.getString("PKDocumentation"));
						documentation.setFileName(arg0.getString("FileName"));
						documentation.setData(arg0.getBytes("Data"));

						return documentation;
					}

				});

		return docs;
	}

	@Override
	public void insertDocumentation(Documentation documentation) {
		validateDocumentation(documentation);

		String command = "INSERT INTO Documentation (PKDocumentation, FileName, Data) VALUES (:pKDocumentation, :fileName, :data);";
		SqlParameterSource namedParameter = new BeanPropertySqlParameterSource(
				documentation);

		jdbcTemplate.update(command, namedParameter);

	}

	@Override
	public void updateDocumentation(Documentation documentation) {
		String query = "UPDATE Documentation SET FileName = :fileName, Data = :data WHERE PKDocumentation =:pKDocumentation;";
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(
				documentation);
		jdbcTemplate.update(query, parameters);
	}

	@Override
	public void deleteDocumentation(String pKDocumentation) {

		String command = "DELETE FROM Documentation PKDocumentation = :pKDocumentation;";
		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"pKDocumentation", pKDocumentation);
		jdbcTemplate.update(command, namedParameter);

	}



	@Override
	public Documentation getDocumentationByPK(String pKDocumentation) {
		String query = "SELECT * FROM Documentation WHERE PKDocumentation = :pKDocumentation;";
		SqlParameterSource parameters = new MapSqlParameterSource(
				"pKDocumentation", pKDocumentation);

		List<Documentation> result = jdbcTemplate.query(query, parameters,
				new RowMapper<Documentation>() {

					@Override
					public Documentation mapRow(ResultSet arg0, int arg1)
							throws SQLException {
						Documentation doc = new Documentation();
						doc.setData(arg0.getBytes("Data"));
						doc.setFileName(arg0.getString("FileName"));
						doc.setpKDocumentation(arg0
								.getString("PKDocumentation"));

						return doc;
					}

				});

		if (result.isEmpty())
			return null;
		else
			return result.get(0);
	}

	@Override
	public Documentation getDocumentationByFileName(String fileName) {

		if (fileName == null || fileName.trim().isEmpty()) {
			return null;
		}

		String query = "SELECT *FROM Documentation WHERE FileName = :fileName;";

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("fileName", fileName);

		SqlParameterSource parameters = new MapSqlParameterSource(map);

		List<Documentation> docs = getDocumentations(query, parameters);

		if (docs.size() > 0)
			return docs.get(0);
		else
			return null;
	}

	@Override
	public List<Documentation> getDocumentations(String nameFilter) {

		String query = "SELECT *FROM Documentation WHERE FileName LIKE :nameFilter;";

		if (nameFilter == null)
			nameFilter = "";

		SqlParameterSource parameters = new MapSqlParameterSource("nameFilter",
				"%" + nameFilter + "%");

		List<Documentation> result = jdbcTemplate.query(query, parameters,
				new RowMapper<Documentation>() {
					@Override
					public Documentation mapRow(ResultSet arg0, int arg1)
							throws SQLException {
						Documentation doc = new Documentation ();
						doc.setData(arg0.getBytes("Data"));
						doc.setFileName(arg0.getString("FileName"));
						doc.setpKDocumentation(arg0.getString("PKDocumentation"));
						
						return doc;
					}
				});
		
		return result;
	}

}
