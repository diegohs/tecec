package tecec.contract.writer;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface IMonographWriter {
	RuleViolation getCreationViolation(String name);

	RuleViolation getUpdateViolation(String PKMonograph, String newName);

	void createMonograph(String name) throws RuleViolationException;

	void updateMonograph(String PKMonograph, String newName)
			throws RuleViolationException;
	void deleteMonograph(String PKMonograph);
}
