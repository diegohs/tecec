package tecec.ui.contract.control;

import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.dto.Student;

public interface IStudentViewerController extends IViewerController  {
	void setNameFilter (String nameFilter);
	String getNameFilter ();
	
	void setSelectedStudent (Student student);
	Student getSelectedStudent ();
	List <Student> getStudents();
	
	RuleViolation getDeletionViolation();
	void deleteStudent() throws RuleViolationException;
	
	boolean getCanUpdateStudent(); 
	boolean getCanDeleteStudent();
	
	void showNewStudentUI();
	void showUpdateStudentUI();


}
