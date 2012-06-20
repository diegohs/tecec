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

import tecec.dto.Course;

import tecec.repository.mysql.base.MySqlConnectionConfig;
import tecec.repository.mysql.base.MySqlRepository;

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
		
		if (course.getTurn() == null || course.getTurn().trim().isEmpty()) {
			throw new IllegalArgumentException ("O campo turno do curso não pode ser nulo.");
		}
		
		if (course.getYear() == null || course.getYear().trim().isEmpty() || course.getYear().length()!=4) {
			throw new IllegalArgumentException ("O campo ano do curso deve ser válido.");
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

		String command = " INSERT INTO Course(PKCourse, Name, Turn, Year) VALUES(:pKCourse, :name, :turn, :year);";

		SqlParameterSource namedParameter = new BeanPropertySqlParameterSource(
				course);

		jdbcTemplate.update(command, namedParameter);
	}

	@Override
	public void deleteCourse(String pKCourse) {
		String command = " DELETE FROM Course WHERE PKCourse = :pKCourse;";

		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"pKCourse", pKCourse);

		jdbcTemplate.update(command, namedParameter);
	}

	

	@Override
	public Course getCourseByPK(String pKCourse) {
		String query = " SELECT * FROM Course WHERE PKCourse = :pKCourse;";

		SqlParameterSource parameters = new MapSqlParameterSource("pKCourse",
				pKCourse);

		List<Course> result = jdbcTemplate.query(query, parameters,
				new RowMapper<Course>() {
					@Override
					public Course mapRow(ResultSet arg0, int arg1)
							throws SQLException {
						Course course = new Course();

						course.setName(arg0.getString("Name"));
						course.setPKCourse(arg0.getString("PKCourse"));
						course.setTurn(arg0.getString("Turn"));
						course.setYear(arg0.getString("Year"));

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
	public List<Course> getCourses(String nameFilter) {
		String query = " SELECT * FROM Course WHERE Name LIKE :nameFilter;";

		if (nameFilter == null) {
			nameFilter = "";
		}

		SqlParameterSource parameters = new MapSqlParameterSource("nameFilter",
				"%" + nameFilter + "%");

		List<Course> result = jdbcTemplate.query(query, parameters,
				new RowMapper<Course>() {
					@Override
					public Course mapRow(ResultSet arg0, int arg1)
							throws SQLException {
						Course course = new Course();

						course.setName(arg0.getString("Name"));
						course.setPKCourse(arg0.getString("PKCourse"));
						course.setTurn(arg0.getString("Turn"));
						course.setYear(arg0.getString("Year"));

						return course;
					}
				});

		return result;
	}

	@Override
	public void updateCourse(Course course) {
		
		Course search = this.getCourseByNameAndTurnAndYear(course.getName(), course.getTurn(), course.getYear());
		
		if (search != null)
			throw new IllegalArgumentException ("Já existe outro curso cadastrado com estas descrições.");
		
		String query = "UPDATE Course SET Name =:name, Turn = :turn, Year = :year WHERE PKCourse = :pKCourse;";
		SqlParameterSource parameters = new BeanPropertySqlParameterSource (course);
		
		jdbcTemplate.update(query, parameters);
	}

	@Override
	public Course getCourseByNameAndTurnAndYear(String name, String turn,
			String year) {
		
		if (name == null || year == null || year.length()!= 4 || turn == null)
			return null;
		
		String query = "SELECT *FROM Course WHERE Name=:name AND TURN = :turn AND YEAR=:year;";
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("turn", turn);
		map.put("year", year);
		
		SqlParameterSource parameters = new MapSqlParameterSource (map);
		
		List <Course> courses = getCourses (query, parameters);
		
		if (courses.size() > 0)
			return courses.get(0);
		else return null;
	
	}	
	
	
	private List <Course> getCourses (String query, SqlParameterSource parameters) {
		List <Course> courses = jdbcTemplate.query(query, parameters, 
				new RowMapper <Course> () {
					@Override
					public Course mapRow(ResultSet arg0, int arg1)
							throws SQLException {
						Course course = new Course ();
						course.setPKCourse(arg0.getString("PKCourse"));
						course.setName(arg0.getString("Name"));
						course.setYear(arg0.getString("Year"));
						course.setTurn(arg0.getString("Turn"));
						
						return course;
					}
			
		});
		
		return courses;
	}

	@Override
	public List<Course> getStudentCourses(String pKStudent) {
		String query = " SELECT * " + 
					   " FROM Course c " + 
					   " INNER JOIN StudentCourse sc ON c.PKCourse = sc.FKCourse " + 
					   " WHERE sc.FKStudent = :fKStudent;";
		
		SqlParameterSource parameters = new MapSqlParameterSource("fKStudent", pKStudent);
		
		return getCourses(query, parameters);
	}

	@Override
	public boolean doesCourseHaveStudents(String pKCourse) {
		String query = " SELECT COUNT(*) " + 
					   " FROM Course c " + 
					   " INNER JOIN StudentCourse sc ON c.PKCourse = sc.FKCourse " + 
					   " WHERE c.PKCourse = :pKCourse; ";
		
		SqlParameterSource parameters = new MapSqlParameterSource("pKCourse", pKCourse);
		
		return jdbcTemplate.queryForInt(query, parameters) > 0;
	}
}
