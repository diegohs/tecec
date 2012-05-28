package tecec.repository.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import tecec.dto.Turn;
import tecec.repository.mysql.base.MySqlConnectionConfig;
import tecec.repository.mysql.base.MySqlRepository;

public class MySqlTurnRepository extends MySqlRepository implements
		tecec.contract.repository.ITurnRepository {

	public MySqlTurnRepository(MySqlConnectionConfig connectionConfig) {
		super(connectionConfig);
	}

	private void validateTurn(Turn turn) {
		if (turn.getName() == null || turn.getName().trim().isEmpty()) {
			throw new IllegalArgumentException(
					"O campo 'nome' do turno não pode ser nulo.");
		}

		if (turn.getPKTurn() == null
				|| turn.getPKTurn().trim().isEmpty()) {
			turn.setPKTurn(UUID.randomUUID().toString());
		} else {
			if (turn.getPKTurn().length() != 36) {
				throw new IllegalArgumentException(
						"A chave primária do turno deve ser um UUID.");
			}
		}
	}

	@Override
	public void insertTurn(Turn turn) {
		validateTurn(turn);

		String command = " INSERT INTO Turn(PKTurn, Name) VALUES(:pKTurn, :name);";

		SqlParameterSource namedParameter = new BeanPropertySqlParameterSource(
				turn);

		jdbcTemplate.update(command, namedParameter);
	}

	@Override
	public void deleteTurn(String pKTurn) {
		String command = " DELETE FROM Turn WHERE PKTurn = :pKTurn;";

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"pKTurn", pKTurn);

		jdbcTemplate.update(command, namedParameter);
	}

	@Override
	public Turn getTurnByName(String name) {
		String query = " SELECT * FROM Turn WHERE Name = :name;";

		SqlParameterSource parameters = new MapSqlParameterSource("name", name);

		List<Turn> result = jdbcTemplate.query(query, parameters,
				new RowMapper<Turn>() {
					@Override
					public Turn mapRow(ResultSet arg0, int arg1)
							throws SQLException {
						Turn turn = new Turn();

						turn.setName(arg0.getString("Name"));
						turn.setPKTurn(arg0.getString("PKTurn"));

						return turn;
					}
				});

		if (result.isEmpty()) {
			return null;
		} else {
			return result.get(0);
		}
	}

	@Override
	public Turn getTurnByPK(String pKTurn) {
		String query = " SELECT * FROM Turn WHERE PKTurn = :pKTurn;";

		SqlParameterSource parameters = new MapSqlParameterSource("pKTurn",
				pKTurn);

		List<Turn> result = jdbcTemplate.query(query, parameters,
				new RowMapper<Turn>() {
					@Override
					public Turn mapRow(ResultSet arg0, int arg1)
							throws SQLException {
						Turn turn = new Turn();

						turn.setName(arg0.getString("Name"));
						turn.setPKTurn(arg0.getString("PKTurn"));

						return turn;
					}
				});

		if (result.isEmpty()) {
			return null;
		} else {
			return result.get(0);
		}
	}

	@Override
	public void updateTurn(Turn turn) {
		String query = "UPDATE Turn SET Name = :name WHERE PKTurn = :pKTurn;";

		SqlParameterSource parameters = new BeanPropertySqlParameterSource(
				turn);

		jdbcTemplate.update(query, parameters);
	}

	@Override
	public List<Turn> getTurns(String nameFilter) {
		String query = " SELECT * FROM Turn WHERE Name LIKE :nameFilter;";

		if (nameFilter == null) {
			nameFilter = "";
		}

		SqlParameterSource parameters = new MapSqlParameterSource("nameFilter",
				"%" + nameFilter + "%");

		List<Turn> result = jdbcTemplate.query(query, parameters,
				new RowMapper<Turn>() {
					@Override
					public Turn mapRow(ResultSet arg0, int arg1)
							throws SQLException {
						Turn turn = new Turn();

						turn.setName(arg0.getString("Name"));
						turn.setPKTurn(arg0.getString("PKTurn"));

						return turn;
					}
				});

		return result;
	}

}
