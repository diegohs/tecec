package tecec.ui.contract;
import java.util.List;
import tecec.dto.Student;

public interface IStudentViewerController {

	void setNameFilter(String nameFilter);

	String getNameFilter();

	void setSelectedStudent(Student student);

	Student getSelectedStudent();

	List<Student> getStudents();
}