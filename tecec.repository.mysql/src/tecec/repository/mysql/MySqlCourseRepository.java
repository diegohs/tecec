package tecec.repository.mysql;

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
	public Course getCourseByName(String name) {
		String query = " SELECT * FROM Course WHERE Name = :name;";

		SqlParameterSource parameters = new MapSqlParameterSource("name", name);

		List<Course> result = this.jdbcTemplate.query(query, parameters,
				new RowMapper<Course>() {
					@Override
					public Course mapRow(ResultSet arg0, int arg1)
							throws SQLException {
						Course course = new Course();

						course.setName(arg0.getString("Name"));
						course.setPKCourse(arg0.getString("PKCourse"));

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
	public Course getCourseByPK(String pKCourse) {
		String query = " SELECT * FROM Course WHERE PKCourse = :pKCourse;";

		SqlParameterSource parameters = new MapSqlParameterSource("pKCourse",
				pKCourse);

		List<Course> result = this.jdbcTemplate.query(query, parameters,
				new RowMapper<Course>() {
					@Override
					public Course mapRow(ResultSet arg0, int arg1)
							throws SQLException {
						Course course = new Course();

						course.setName(arg0.getString("Name"));
						course.setPKCourse(arg0.getString("PKCourse"));

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
		String query = "UPDATE Course SET Name = :name WHERE PKCourse = :pKCourse;";

		SqlParameterSource parameters = new BeanPropertySqlParameterSource(
				course);

		this.jdbcTemplate.update(query, parameters);
	}

	@Override
	public List<Course> getCourses(String nameFilter) {
		String query = " SELECT * FROM Course WHERE Name LIKE :nameFilter;";

		if (nameFilter == null) {
			nameFilter = "";
		}

		SqlParameterSource parameters = new MapSqlParameterSource("nameFilter",
				"%" + nameFilter + "%");

		List<Course> result = this.jdbcTemplate.query(query, parameters,
				new RowMapper<Course>() {
					@Override
					public Course mapRow(ResultSet arg0, int arg1)
							throws SQLException {
						Course course = new Course();

						course.setName(arg0.getString("Name"));
						course.setPKCourse(arg0.getString("PKCourse"));

						return course;
					}
				});

		return result;
	}

}
