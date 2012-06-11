package tecec.ui.contract.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface IUpdatePermissionController {
	void setPKPermission (String pKPermission);
	void setPermissionDescription (String description);
	String getPermissionDescription ();
	
	void updatePermission () throws RuleViolationException;
	RuleViolation getUpdateViolation ();
}
