package tecec.ui.contract.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface IUpdateCourseController extends IRefreshable  {
	void setPKCourse(String pKCourse);
	void setCourseName(String name);
	void setCourseTurn (String turn);
	void setCourseYear (String year);
	
	String getCourseName();
	String getCourseTurn ();
	String getCourseYear ();
	
	boolean getCanUpdate();
	
	void updateCourse() throws RuleViolationException;
	RuleViolation getUpdateViolation();
	


}
