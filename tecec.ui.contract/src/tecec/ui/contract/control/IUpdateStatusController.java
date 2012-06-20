package tecec.ui.contract.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface IUpdateStatusController extends IRefreshable  {
	void setPKStatus (String pKStatus);
	void setStatusDescription (String description);
	String getStatusDescription ();
	
	void updateStatus () throws RuleViolationException;
	RuleViolation getUpdateViolation ();
}
