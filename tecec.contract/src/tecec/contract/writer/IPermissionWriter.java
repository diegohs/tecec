package tecec.contract.writer;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface IPermissionWriter {
	RuleViolation getCreationViolation (String description);
	
	RuleViolation getUpdateViolation (String pKPermission, String newDescription);
	
	void createPermission (String description) throws RuleViolationException;
	
	void updatePermission (String pKPermission, String newDescription)
		throws RuleViolationException;
	
	void deletePermission (String pKPermission);	
}