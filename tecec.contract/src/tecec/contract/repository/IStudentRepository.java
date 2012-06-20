package tecec.contract.repository;

import java.util.List;

import tecec.dto.Student;

public interface IStudentRepository {
	void insertStudent(Student student);

	void updateStudent(Student student);

	void deleteStudent(String pkStudent);

	Student getStudentByName(String name);

	Student getStudentByPk(String pkStudent);

	Student getStudentByEmail(String email);

	List<Student> getStudents(String nameFilter);
	List<Student> getStudentByCourse(String pKCourse);

	void insertStudentCourse(String pKStudent, String pKCourse);
	void deleteStudentCourse(String pKStudent, String pKCourse);
	
	boolean doesStudentHaveMonographies(String pKStudent);	
	boolean doesStudentHaveMonographiesInCourse(String pKStudent, String pKCourse);
}
