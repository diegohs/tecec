package tecec.ui.contract;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface INewCourseController {
	String getCourseName();

	void setCourseName(String name);

	void createCourse() throws RuleViolationException;

	RuleViolation getCreationViolation();
}
