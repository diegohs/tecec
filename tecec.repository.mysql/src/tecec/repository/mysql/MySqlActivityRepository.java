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

import tecec.contract.repository.IActivityRepository;
import tecec.dto.Activity;
import tecec.repository.mysql.base.MySqlConnectionConfig;
import tecec.repository.mysql.base.MySqlRepository;

public class MySqlActivityRepository extends MySqlRepository implements
		IActivityRepository {

	public MySqlActivityRepository(MySqlConnectionConfig connectionConfig) {
		super(connectionConfig);
	}

	@Override
	public void insertActivity(Activity activity) {
		if (activity.getTitle() == null || activity.getTitle().isEmpty()) {
			throw new IllegalArgumentException(
					"O título da atividade deve ser preenchido.");
		}

		if (activity.getpKActivity() == null
				|| activity.getpKActivity().trim().isEmpty()) {
			activity.setpKActivity(UUID.randomUUID().toString());
		} else {
			if (activity.getpKActivity().length() != 36) {
				throw new IllegalArgumentException(
						"A chave primÃ¡ria do estudante deve ser um UUID.");
			}
		}

		if (activity.getFKStage() == null || activity.getFKStage().isEmpty()) {
			throw new IllegalArgumentException(
					"A atividade deve possuir uma etapa associada.");
		}

		if (activity.getDueDate() == null) {
			throw new IllegalArgumentException(
					"A data de entrega da atividade deve ser uma data válida.");
		}

		String command = " INSERT INTO Activity(PKActivity, Title, Description, DueDate, FKStage) "
				+ " VALUES (:pKActivity, :title, :description, :dueDate, :fKStage);";

		SqlParameterSource parameters = new BeanPropertySqlParameterSource(
				activity);

		jdbcTemplate.update(command, parameters);
	}

	@Override
	public void updateActivity(Activity activity) {
		String command = " UPDATE Activity SET Title = :title, Description = :description, DueDate = :dueDate "
				+ " WHERE PKActivity = :pKActivity; ";

		SqlParameterSource parameters = new BeanPropertySqlParameterSource(
				activity);

		jdbcTemplate.update(command, parameters);
	}

	@Override
	public void deleteActivity(String pKActivity) {
		String command = " DELETE FROM Activity WHERE PKActivity = :pKActivity";

		SqlParameterSource parameters = new MapSqlParameterSource("pKActivity",
				pKActivity);

		jdbcTemplate.update(command, parameters);
	}

	@Override
	public List<Activity> getActivities(String titleFilter) {
		String query = " SELECT * FROM Activity WHERE Title LIKE :titleFilter";

		SqlParameterSource parameters = new MapSqlParameterSource(
				"titleFilter", "%" + titleFilter + "%");

		return getActivites(query, parameters);
	}

	private List<Activity> getActivites(String query,
			SqlParameterSource parameters) {
		return jdbcTemplate.query(query, parameters, new RowMapper<Activity>() {

			@Override
			public Activity mapRow(ResultSet arg0, int arg1)
					throws SQLException {
				Activity activity = new Activity();

				activity.setpKActivity(arg0.getString("PKActivity"));
				activity.setTitle(arg0.getString("Title"));
				activity.setDescription(arg0.getString("Description"));
				activity.setDueDate(arg0.getDate("DueDate"));
				activity.setFKStage(arg0.getString("FKStage"));

				return activity;
			}
		});
	}

	@Override
	public Activity getActivityByPK(String pKActivity) {
		String query = " SELECT * FROM Activity WHERE PKActivity = :pKActivity;";

		SqlParameterSource parameters = new MapSqlParameterSource("pKActivity",
				pKActivity);

		List<Activity> activities = getActivites(query, parameters);

		if (activities.size() > 0) {
			return activities.get(0);
		}

		return null;
	}

	@Override
	public List<Activity> getActivitiesByStage(String pKStage, String filter) {
		String query = " SELECT * FROM Activity WHERE FKStage = :fKStage AND Title LIKE :title";

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("fKStage", pKStage);
		map.put("title", "%" + filter + "%");

		SqlParameterSource parameters = new MapSqlParameterSource(map);

		return getActivites(query, parameters);
	}

}
