package tecec.repository.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import tecec.dto.Profile;
import tecec.repository.mysql.base.MySqlConnectionConfig;
import tecec.repository.mysql.base.MySqlRepository;

public class MySqlProfileRepository extends MySqlRepository implements
		tecec.contract.repository.IProfileRepository {

	public MySqlProfileRepository(MySqlConnectionConfig connectionConfig) {
		super(connectionConfig);
	}

	private void validateProfile(Profile profile) {
		if (profile.getName() == null || profile.getName().trim().isEmpty()) {
			throw new IllegalArgumentException(
					"O campo 'nome' do perfil não pode ser nulo.");
		}

		if (profile.getpKProfile() == null
				|| profile.getpKProfile().trim().isEmpty()) {
			profile.setpKProfile(UUID.randomUUID().toString());
		} else {
			if (profile.getpKProfile().length() != 36) {
				throw new IllegalArgumentException(
						"A chave primária do perfil deve ser um UUID.");
			}
		}
	}

	@Override
	public void insertProfile(Profile profile) {
		validateProfile(profile);

		String command = " INSERT INTO Profile(PKProfile, Name) VALUES(:pKProfile, :name);";

		SqlParameterSource namedParameter = new BeanPropertySqlParameterSource(
				profile);

		jdbcTemplate.update(command, namedParameter);
	}

	@Override
	public void deleteProfile(String pKProfile) {
		String command = " DELETE FROM Profile WHERE PKProfile = :pKProfile;";

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"pKProfile", pKProfile);

		jdbcTemplate.update(command, namedParameter);
	}

	@Override
	public Profile getProfileByName(String name) {
		String query = " SELECT * FROM Profile WHERE Name = :name;";

		SqlParameterSource parameters = new MapSqlParameterSource("name", name);

		List<Profile> result = jdbcTemplate.query(query, parameters,
				new RowMapper<Profile>() {
					@Override
					public Profile mapRow(ResultSet arg0, int arg1)
							throws SQLException {
						Profile profile = new Profile();

						profile.setName(arg0.getString("Name"));
						profile.setpKProfile(arg0.getString("PKProfile"));

						return profile;
					}
				});

		if (result.isEmpty()) {
			return null;
		} else {
			return result.get(0);
		}
	}

	@Override
	public Profile getProfileByPK(String pKProfile) {
		String query = " SELECT * FROM Profile WHERE PKProfile = :pKProfile;";

		SqlParameterSource parameters = new MapSqlParameterSource("pKProfile",
				pKProfile);

		List<Profile> result = jdbcTemplate.query(query, parameters,
				new RowMapper<Profile>() {
					@Override
					public Profile mapRow(ResultSet arg0, int arg1)
							throws SQLException {
						Profile profile = new Profile();

						profile.setName(arg0.getString("Name"));
						profile.setpKProfile(arg0.getString("PKProfile"));

						return profile;
					}
				});

		if (result.isEmpty()) {
			return null;
		} else {
			return result.get(0);
		}
	}

	@Override
	public void updateProfile(Profile profile) {
		String query = "UPDATE Profile SET Name = :name WHERE PKProfile = :pKProfile;";

		SqlParameterSource parameters = new BeanPropertySqlParameterSource(
				profile);

		jdbcTemplate.update(query, parameters);
	}

	@Override
	public List<Profile> getProfiles(String nameFilter) {
		String query = " SELECT * FROM Profile WHERE Name LIKE :nameFilter;";

		if (nameFilter == null) {
			nameFilter = "";
		}

		SqlParameterSource parameters = new MapSqlParameterSource("nameFilter",
				"%" + nameFilter + "%");

		List<Profile> result = jdbcTemplate.query(query, parameters,
				new RowMapper<Profile>() {
					@Override
					public Profile mapRow(ResultSet arg0, int arg1)
							throws SQLException {
						Profile profile = new Profile();

						profile.setName(arg0.getString("Name"));
						profile.setpKProfile(arg0.getString("PKProfile"));

						return profile;
					}
				});

		return result;
	}

}
