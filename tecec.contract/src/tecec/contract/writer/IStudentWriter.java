package tecec.contract.writer;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface IStudentWriter {
	RuleViolation getCreationViolation(String name, String email);

	RuleViolation getUpdateViolation(String pkStudent, String newName,
			String email);

	void createStudent(String name, String email) throws RuleViolationException;

	void updateStudent(String pkStudent, String newName, String email)
			throws RuleViolationException;

}
