package tecec.contract.writer;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface IStatusWriter {
	RuleViolation getCreationViolation (String description);
	
	RuleViolation getUpdateViolation (String pKStatus, String newDescription);
	
	void createStatus (String description) throws RuleViolationException;
	
	void updateStatus (String pKStatus, String newDescription)
		throws RuleViolationException;
	
	void deleteStatus (String pKStatus);	
}
