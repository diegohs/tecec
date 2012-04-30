package tecec.repository.mysql;

import java.util.UUID;

import tecec.dto.Course;
import tecec.repository.mysql.base.*;

public class MySqlCourseRepository extends MySqlRepository implements
		tecec.contract.repository.CourseRepository {

	public MySqlCourseRepository(MySqlConnectionConfig connectionConfig) {
		super(connectionConfig);
	}

	@Override
	public void insertCourse(Course course) {
		try {
			if (course.getName() == null || course.getName().trim().isEmpty()) {
				throw new IllegalArgumentException(
						"O campo 'nome' do curso n�o pode ser nulo.");
			}

			if (course.getPKCourse() == null
					|| course.getPKCourse().trim().isEmpty()) {
				course.setPKCourse(UUID.randomUUID().toString());
			} else {
				if (course.getPKCourse().length() != 36) {
					throw new IllegalArgumentException(
							"A chave prim�ria do curso deve ser um UUID.");
				}
			}

			String command = " INSERT INTO Course(PKCourse, Name) VALUES('%1s','%2s')";

			command = String.format(command, course.getPKCourse(),
					course.getName());

			super.Execute(command);
		} catch (Exception e) {
			throw new RuntimeException(
					"Ocorreu um erro durante a inser��o de um novo curso: "
							+ e.getMessage(), e);
		}
	}

}
