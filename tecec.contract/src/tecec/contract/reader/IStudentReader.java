package tecec.contract.reader;

import java.util.List;

import tecec.dto.Student;

public interface IStudentReader {
	List<Student> getStudents (String nameFilter);

}
