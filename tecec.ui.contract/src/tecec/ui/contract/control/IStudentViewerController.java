package tecec.ui.contract.control;

import java.util.List;

import tecec.dto.Student;

public interface IStudentViewerController {
	void setNameFilter (String nameFilter);
	String getNameFilter ();
	
	void setSelectedStudent (Student student);
	Student getSelectedStudent ();
	List <Student> getStudents();
	
	void deleteStudent();
	
	boolean getCanUpdateStudent(); 
	boolean getCanDeleteStudent();
	
	void showNewStudentUI();
	void showUpdateStudentUI();


}
