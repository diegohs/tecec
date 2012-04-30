package tecec.repository.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import tecec.dto.Course;
import tecec.repository.mysql.base.*;

public class MySqlCourseRepository extends MySqlRepository implements
		tecec.contract.repository.ICourseRepository {

	public MySqlCourseRepository(MySqlConnectionConfig connectionConfig) {
		super(connectionConfig);
	}

	private void validateCourse(Course course) {
		if (course.getName() == null || course.getName().trim().isEmpty()) {
			throw new IllegalArgumentException(
					"O campo 'nome' do curso não pode ser nulo.");
		}

		if (course.getPKCourse() == null
				|| course.getPKCourse().trim().isEmpty()) {
			course.setPKCourse(UUID.randomUUID().toString());
		} else {
			if (course.getPKCourse().length() != 36) {
				throw new IllegalArgumentException(
						"A chave primária do curso deve ser um UUID.");
			}
		}
	}

	@Override
	public void insertCourse(Course course) {
		validateCourse(course);

		String command = " INSERT INTO Course(PKCourse, Name) VALUES(:pKCourse, :name);";

		SqlParameterSource namedParameter = new BeanPropertySqlParameterSource(
				course);

		this.jdbcTemplate.update(command, namedParameter);
	}

	@Override
	public Course getCourse(String name) {
		String query = " SELECT * FROM Course WHERE Name = :name;";

		SqlParameterSource parameters = new MapSqlParameterSource("name", name);

		List<Course> result = this.jdbcTemplate.query(query, parameters,
				new RowMapper<Course>() {
					@Override
					public Course mapRow(ResultSet arg0, int arg1)
							throws SQLException {
						Course course = new Course();

						course.setName(arg0.getString("Name"));

						return course;
					}
				});

		if (result.isEmpty()) {
			return null;
		} else {
			return result.get(0);
		}
	}

	@Override
	public void updateCourse(Course course) {
		// TODO Auto-generated method stub

	}

}
