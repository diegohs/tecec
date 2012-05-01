package tecec.contract.writer;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface ICourseWriter {
	RuleViolation getCreationViolation(String name);
	RuleViolation getUpdateViolation(String pKCourse, String newName);
	void createCourse(String name) throws RuleViolationException;
	void updateCourse(String pKCourse, String newName) throws RuleViolationException;
}
