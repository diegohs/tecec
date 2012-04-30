package tecec.contract.writer;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface ICourseWriter {
	RuleViolation getCreationViolation(String name);
	void createCourse(String name) throws RuleViolationException;
	void updateCourse(String pKCourse, String newName) throws RuleViolationException;
}
