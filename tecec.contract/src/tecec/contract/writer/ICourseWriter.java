package tecec.contract.writer;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.dto.Course;

public interface ICourseWriter {
	
	RuleViolation getCreationViolation(String name, String turn, String year);

	RuleViolation getUpdateViolation(Course newCourse);

	void createCourse(String name, String turn, String year) throws RuleViolationException;

	void updateCourse(String pKCourse, String newName, String newTurn, String newYear)
			throws RuleViolationException;
	void deleteCourse(String pKCourse);
}
