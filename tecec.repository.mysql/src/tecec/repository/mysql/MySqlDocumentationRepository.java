package tecec.repository.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;

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
			throw new IllegalArgumentException(
					"O nome do arquivo é inválido.");

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

		final DefaultLobHandler lobHandler = new DefaultLobHandler();

		List<Documentation> docs = jdbcTemplate.query(query, parameters,
				new RowMapper<Documentation>() {

					@Override
					public Documentation mapRow(ResultSet arg0, int arg1)
							throws SQLException {

						Documentation doc = new Documentation();

						doc.setFileName(arg0.getString("FileName"));
						doc.setpKDocumentation(arg0
								.getString("PKDocumentation"));

						doc.setData(lobHandler.getBlobAsBytes(arg0, "Data"));

						return doc;
					}

				});

		return docs;
	}

	@Override
	public void insertDocumentation(Documentation documentation) {
		validateDocumentation(documentation);

		String command = " INSERT INTO Documentation (PKDocumentation, FileName, Data) "
				+ " VALUES (?, ?, ?);";

		final Documentation persistentDoc = documentation;

		jdbcTemplate.getJdbcOperations().execute(
				command,
				new AbstractLobCreatingPreparedStatementCallback(
						new DefaultLobHandler()) {
					@Override
					protected void setValues(
							java.sql.PreparedStatement preparedStatement,
							LobCreator lobCreator) throws SQLException,
							DataAccessException {
						preparedStatement.setString(1,
								persistentDoc.getpKDocumentation());
						preparedStatement.setString(2,
								persistentDoc.getFileName());
						lobCreator.setBlobAsBytes(preparedStatement, 3,
								persistentDoc.getData());
					}
				});
	}

	@Override
	public void updateDocumentation(Documentation documentation) {
		String command = " UPDATE Documentation SET FileName = ?, Data = ? "
				+ " WHERE PKDocumentation =?; ";

		final Documentation persistentDoc = documentation;

		jdbcTemplate.getJdbcOperations().execute(
				command,
				new AbstractLobCreatingPreparedStatementCallback(
						new DefaultLobHandler()) {
					@Override
					protected void setValues(
							java.sql.PreparedStatement preparedStatement,
							LobCreator lobCreator) throws SQLException,
							DataAccessException {
						preparedStatement.setString(3,
								persistentDoc.getpKDocumentation());
						preparedStatement.setString(1,
								persistentDoc.getFileName());
						lobCreator.setBlobAsBytes(preparedStatement, 2,
								persistentDoc.getData());
					}
				});
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

		List<Documentation> result = getDocumentations(query, parameters);

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
						Documentation doc = new Documentation();
						doc.setData(arg0.getBytes("Data"));
						doc.setFileName(arg0.getString("FileName"));
						doc.setpKDocumentation(arg0
								.getString("PKDocumentation"));

						return doc;
					}
				});

		return result;
	}

}
