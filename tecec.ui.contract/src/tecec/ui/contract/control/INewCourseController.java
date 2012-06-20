package tecec.ui.contract.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface INewCourseController extends IRefreshable  {	
	String getCourseName();
	void setCourseName(String name);
	
	String getCourseTurn ();
	void setCourseTurn (String turn);
	
	String getCourseYear () ;
	void setCourseYear (String year);
	
	
	void createCourse() throws RuleViolationException;
	RuleViolation getCreationViolation();

}
