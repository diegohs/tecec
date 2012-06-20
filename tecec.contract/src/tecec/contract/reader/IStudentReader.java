package tecec.contract.reader;

import java.util.List;

import tecec.dto.Student;

public interface IStudentReader {
	List<Student> getStudents(String nameFilter);
	List<Student> getStudentsByCourse(String pKCourse);

	Student getStudentByPk(String pkStudent);

	public boolean doesUserHaveMonographiesInCourse(String pKStudent,
			String pKCourse);
}
