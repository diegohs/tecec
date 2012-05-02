package tecec.ui.contract.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface IUpdateCourseController {
	void setPKCourse(String pKCourse);
	void setCourseName(String name);
	String getCourseName();
	
	void updateCourse() throws RuleViolationException;
	RuleViolation getUpdateViolation();
}
