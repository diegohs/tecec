package tecec.contract.writer;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface IProfileWriter {
	RuleViolation getCreationViolation(String name);

	RuleViolation getUpdateViolation(String pKProfile, String newName);

	void createProfile(String name) throws RuleViolationException;

	void updateProfile(String pKProfile, String newName)
			throws RuleViolationException;
	void deleteProfile(String pKProfile);
}
