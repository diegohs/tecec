package tecec.ui.contract.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface IUpdateStudentController extends IRefreshable  {
	
	void setPKStudent (String pkStudent);
	void setStudentName (String name);
	void setStudentEmail (String email);
	
	String getStudentName();
	String getStudentEmail();
	
	boolean getCanUpdate();
	
	void updateStudent () throws RuleViolationException;
	RuleViolation getUpdateViolation();

	void showStudentCourseUI();
}
