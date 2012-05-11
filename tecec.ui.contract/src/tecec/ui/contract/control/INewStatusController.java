package tecec.ui.contract.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface INewStatusController {
	String getStatusDescription ();
	void setStatusDescription (String description);
	
	void createStatus () throws RuleViolationException;
	RuleViolation getCreationViolation();
}
