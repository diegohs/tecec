package tecec.repository.mysql;

import java.util.UUID;

import tecec.dto.Student;
import tecec.repository.mysql.base.MySqlConnectionConfig;
import tecec.repository.mysql.base.MySqlRepository;

public class MySqlStudentRepository extends MySqlRepository implements
		tecec.contract.repository.StudentRepository {

	public MySqlStudentRepository(MySqlConnectionConfig connectionConfig) {
		super(connectionConfig);
	}

	@Override
	public void insertStudent(Student student) {
		try {
			if (student.getName() == null || student.getName().trim().isEmpty()) {
				throw new IllegalArgumentException(
						"O campo 'nome' do estudante não pode ser nulo.");
			}

			if (student.getPkStudent() == null
					|| student.getPkStudent().trim().isEmpty()) {
				student.setPkStudent(UUID.randomUUID().toString());
			} else {
				if (student.getPkStudent().length() != 36) {
					throw new IllegalArgumentException(
							"A chave primária do estudante deve ser um UUID.");
				}
			}			

			if (student.getEmail() == null
					|| student.getEmail().trim().isEmpty()) {
				throw new IllegalArgumentException(
						"O campo 'e-mail' não pode ser nulo.");
			}

			String command = " INSERT INTO Student (PkStudent, Name, Email) VALUES ('%1s, '%2s','%3s')";
			command = String.format(command, student.getPkStudent(),
					student.getName(), student.getEmail());
			
			this.jdbcTemplate.getJdbcOperations().update(command);
		} catch (Exception e) {
			throw new RuntimeException(
					"Ocorreu um erro durante a inserção de um novo estudante: "
							+ e.getMessage(), e);
		}
	}
}