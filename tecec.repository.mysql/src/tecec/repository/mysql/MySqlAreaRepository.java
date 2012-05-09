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

import tecec.dto.Area;
import tecec.repository.mysql.base.MySqlConnectionConfig;
import tecec.repository.mysql.base.MySqlRepository;

public class MySqlAreaRepository extends MySqlRepository implements
		tecec.contract.repository.IAreaRepository {

	public MySqlAreaRepository(MySqlConnectionConfig connectionConfig) {
		super(connectionConfig);
	}

	@Override
	public void insertArea(Area area) {
		if (area.getName() == null || area.getName().trim().isEmpty()) {
			throw new IllegalArgumentException(
					"O campo 'nome' não pode ser nulo.");
		}

		if (area.getpKArea() == null || area.getpKArea().trim().isEmpty()) {
			area.setpKArea(UUID.randomUUID().toString());
		} else {
			if (area.getpKArea().length() != 36) {
				throw new IllegalArgumentException(
						"A chave primária do curso deve ser um UUID.");
			}
		}

		String command = " INSERT INTO Area(PKArea, FKMainArea, Name, Description) VALUES(:pKArea, :fKMainArea, :name, :description); ";

		SqlParameterSource parameters = new BeanPropertySqlParameterSource(area);

		jdbcTemplate.update(command, parameters);
	}

	@Override
	public void updateArea(Area area) {
		if (area.getName() == null || area.getName().trim().isEmpty()) {
			throw new IllegalArgumentException(
					"O campo 'nome' não pode ser nulo.");
		}
		
		String command = " UPDATE Area SET Name = :name, Description = :description ";
		
		if (area.getfKMainArea() != null && !area.getfKMainArea().isEmpty()) {
			command += " , FKMainArea = :fKMainArea ";
		}
		else{
			command += " , FKMainArea = NULL ";
		}
			
		
		command += " WHERE PKArea = :pKArea;";
		
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(area);
		
		jdbcTemplate.update(command, parameters);
	}

	@Override
	public void deleteArea(String pKArea) {
		String command = " DELETE FROM Area WHERE PKArea = :pKArea";
		
		SqlParameterSource parameters = new MapSqlParameterSource("pKArea", pKArea);
		
		jdbcTemplate.update(command, parameters);
	}

	@Override
	public Area getAreaByNameAndMainArea(String name, String fKMainArea) {
		String query = " SELECT * FROM Area WHERE Name = :name ";
		
		if (fKMainArea != null && !fKMainArea.isEmpty()) {
			query += " AND FKMainArea = :fKMainArea ";
		}
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("name", name);
		map.put("fKMainArea", fKMainArea);

		SqlParameterSource parameters = new MapSqlParameterSource(map);

		List<Area> areas = getAreas(query, parameters);

		if (areas.size() > 0) {
			return areas.get(0);
		}

		return null;
	}

	@Override
	public Area getAreaByPK(String pKArea) {
		String query = " SELECT * FROM Area WHERE PKArea = :pKArea;";

		SqlParameterSource parameters = new MapSqlParameterSource("pKArea",
				pKArea);

		List<Area> areas = getAreas(query, parameters);

		if (areas.size() > 0) {
			return areas.get(0);
		}

		return null;
	}

	@Override
	public List<Area> getAreas(String nameFilter) {
		if (nameFilter == null) {
			nameFilter = "";
		}

		String query = " SELECT * FROM Area WHERE Name LIKE :nameFilter ORDER BY FKMainArea;";

		SqlParameterSource parameters = new MapSqlParameterSource("nameFilter",
				"%" + nameFilter + "%");

		return getAreas(query, parameters);
	}

	private List<Area> getAreas(String query, SqlParameterSource parameters) {
		List<Area> areas = jdbcTemplate.query(query, parameters,
				new RowMapper<Area>() {
					@Override
					public Area mapRow(ResultSet arg0, int arg1)
							throws SQLException {
						Area area = new Area();

						area.setpKArea(arg0.getString("PKArea"));
						area.setfKMainArea(arg0.getString("FKMainArea"));
						area.setName(arg0.getString("Name"));
						area.setDescription(arg0.getString("Description"));

						return area;
					}
				});

		return areas;
	}

	@Override
	public List<Area> getSubAreas(String pKArea) {
		String query = " SELECT * FROM Area WHERE FKMainArea = :fKMainArea; ";
		
		SqlParameterSource parameters = new MapSqlParameterSource("fKMainArea", pKArea);
		
		return getAreas(query, parameters);
	}

}
