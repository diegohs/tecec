package tecec.contract.writer;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface IMonographWriter {
	
	RuleViolation getCreationViolation(String name);

	RuleViolation getUpdateViolation(String pKMonograph, String newName);

	void createMonograph(String name) throws RuleViolationException;

	void updateMonograph(String pKMonograph, String newName)
			throws RuleViolationException;
	void deleteMonograph(String pKMonograph);
}
