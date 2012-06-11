package tecec.ui.contract.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface INewPermissionController {
	String getPermissionDescription ();
	void setPermissionDescription (String description);
	
	void createPermission () throws RuleViolationException;
	RuleViolation getCreationViolation();
}
