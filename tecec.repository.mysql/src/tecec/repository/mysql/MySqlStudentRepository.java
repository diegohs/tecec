package tecec.repository.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import tecec.contract.repository.IStudentRepository;

import tecec.dto.Student;
import tecec.repository.mysql.base.MySqlConnectionConfig;
import tecec.repository.mysql.base.MySqlRepository;

public class MySqlStudentRepository extends MySqlRepository implements
		IStudentRepository {

	public MySqlStudentRepository(MySqlConnectionConfig connectionConfig) {
		super(connectionConfig);
	}

	private void validateStudent(Student student) {
		if (student.getName() == null || student.getName().trim().isEmpty()) {
			throw new IllegalArgumentException(
					"O campo 'nome' do estudante não pode ser nulo.");
		}

		if (student.getEmail() == null || student.getEmail().trim().isEmpty()) {
			throw new IllegalArgumentException(
					"O campo 'e-mail' do estudante não pode ser nulo.");
		}

		if (student.getPKStudent() == null
				|| student.getPKStudent().trim().isEmpty()) {
			student.setPKStudent(UUID.randomUUID().toString());
		} else {
			if (student.getPKStudent().length() != 36) {
				throw new IllegalArgumentException(
						"A chave primária do estudante deve ser um UUID.");
			}
		}
	}

	@Override
	public void insertStudent(Student student) {
		validateStudent(student);
		String command = "INSERT INTO Student(PKStudent, Name, Email) VALUES (:pKStudent, :name, :email);";
		SqlParameterSource namedParameter = new BeanPropertySqlParameterSource(
				student);
		jdbcTemplate.update(command, namedParameter);
	}

	@Override
	public void updateStudent(Student student) {
		String query = "UPDATE Student SET Name = :name, Email = :email WHERE PKStudent = :pKStudent;";
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(
				student);
		jdbcTemplate.update(query, parameters);
	}

	@Override
	public Student getStudentByName(String name) {
		String query = "SELECT * FROM Student WHERE Name = :name;";
		SqlParameterSource parameters = new MapSqlParameterSource("name", name);

		List<Student> result = jdbcTemplate.query(query, parameters,
				new RowMapper<Student>() {
					@Override
					public Student mapRow(ResultSet arg0, int arg1)
							throws SQLException {
						Student student = new Student();
						student.setName(arg0.getString("Name"));
						student.setEmail(arg0.getString("Email"));
						student.setPKStudent(arg0.getString("PKStudent"));

						return student;
					}
				});

		if (result.isEmpty()) {
			return null;
		} else {
			return result.get(0);
		}
	}

	@Override
	public Student getStudentByPk(String pKStudent) {
		String query = "SELECT * FROM Student WHERE PKStudent = :pKStudent;";
		SqlParameterSource parameters = new MapSqlParameterSource("pKStudent",
				pKStudent);

		List<Student> result = jdbcTemplate.query(query, parameters,
				new RowMapper<Student>() {
					@Override
					public Student mapRow(ResultSet arg0, int arg1)
							throws SQLException {
						Student student = new Student();
						student.setName(arg0.getString("Name"));
						student.setEmail(arg0.getString("Email"));
						student.setPKStudent(arg0.getString("PKStudent"));

						return student;
					}
				});

		if (result.isEmpty()) {
			return null;
		} else {
			return result.get(0);
		}
	}

	@Override
	public Student getStudentByEmail(String email) {
		String query = "SELECT * FROM Student WHERE Email = :email;";
		SqlParameterSource parameters = new MapSqlParameterSource("email",
				email);

		List<Student> result = jdbcTemplate.query(query, parameters,
				new RowMapper<Student>() {
					@Override
					public Student mapRow(ResultSet arg0, int arg1)
							throws SQLException {
						Student student = new Student();
						student.setName(arg0.getString("Name"));
						student.setEmail(arg0.getString("Email"));
						student.setPKStudent(arg0.getString("PKStudent"));

						return student;
					}
				});

		if (result.isEmpty()) {
			return null;
		} else {
			return result.get(0);
		}
	}
	
	private List<Student> getStudents(String query, SqlParameterSource parameters){
		List<Student> result = jdbcTemplate.query(query, parameters,
				new RowMapper<Student>() {
					@Override
					public Student mapRow(ResultSet arg0, int arg1)
							throws SQLException {
						Student student = new Student();

						student.setName(arg0.getString("Name"));
						student.setEmail(arg0.getString("Email"));
						student.setPKStudent(arg0.getString("PKStudent"));

						return student;
					}
				});
		return result;
	}

	@Override
	public List<Student> getStudents(String nameFilter) {
		String query = "SELECT * FROM Student WHERE Name Like :nameFilter;";

		if (nameFilter == null)
			nameFilter = "";

		SqlParameterSource parameters = new MapSqlParameterSource("nameFilter",
				"%" + nameFilter + "%");

		return getStudents(query, parameters);
	}

	@Override
	public void deleteStudent(String pKStudent) {
		String command = " DELETE FROM Student WHERE PKStudent = :pKStudent;";
		SqlParameterSource namedParameter = new MapSqlParameterSource(
				"pKStudent", pKStudent);
		jdbcTemplate.update(command, namedParameter);
	}

	@Override
	public void insertStudentCourse(String pKStudent, String pKCourse) {
		String command = " INSERT INTO StudentCourse(FKStudent, FKCourse) "
				+ " VALUES (:fKStudent, :fKCourse); ";

		Hashtable<String, Object> map = new Hashtable<String, Object>();

		map.put("fKStudent", pKStudent);
		map.put("fKCourse", pKCourse);

		SqlParameterSource parameters = new MapSqlParameterSource(map);

		jdbcTemplate.update(command, parameters);
	}

	@Override
	public void deleteStudentCourse(String pKStudent, String pKCourse) {
		String command = " DELETE FROM StudentCourse "
				+ " WHERE FKStudent = :fKStudent "
				+ "   AND FKCourse = :fKCourse;";

		Hashtable<String, Object> map = new Hashtable<String, Object>();

		map.put("fKStudent", pKStudent);
		map.put("fKCourse", pKCourse);

		SqlParameterSource parameters = new MapSqlParameterSource(map);

		jdbcTemplate.update(command, parameters);

	}

	@Override
	public boolean doesStudentHaveMonographiesInCourse(String pKStudent,
			String pKCourse) {
		String query = " SELECT COUNT(*) " + " FROM Monograph m "
				+ " INNER JOIN Student s ON m.FKStudent = s.PKStudent "
				+ " INNER JOIN StudentCourse sc ON s.PKStudent = sc.FKStudent "
				+ " INNER JOIN Course c ON sc.FKCourse = c.PKCourse AND m.FKCourse = c.PKCourse "
				+ " WHERE s.PKStudent = :pKStudent "
				+ "   AND c.PKCourse = :pKCourse";

		Hashtable<String, Object> map = new Hashtable<String, Object>();

		map.put("pKStudent", pKStudent);
		map.put("pKCourse", pKCourse);

		SqlParameterSource parameters = new MapSqlParameterSource(map);

		return jdbcTemplate.queryForInt(query, parameters) > 0;
	}

	@Override
	public List<Student> getStudentByCourse(String pKCourse) {
		String query = " SELECT * FROM Student " + 
					   " WHERE PKStudent IN (SELECT FKStudent " + 
					   " 					 FROM StudentCourse " + 
					   " 					 WHERE FKCourse = :fKCourse);";
		
		SqlParameterSource parameters = new MapSqlParameterSource("fKCourse", pKCourse);
		
		return getStudents(query, parameters);
	}

	@Override
	public boolean doesStudentHaveMonographies(String pKStudent) {
		String query = " SELECT COUNT(*) " + 
					   " FROM Monograph m " +
					   " INNER JOIN Student s ON m.FKStudent = s.PKStudent " +
					   " WHERE s.PKStudent = :pKStudent ";

		SqlParameterSource parameters = new MapSqlParameterSource("pKStudent", pKStudent);

		return jdbcTemplate.queryForInt(query, parameters) > 0;
	}
}