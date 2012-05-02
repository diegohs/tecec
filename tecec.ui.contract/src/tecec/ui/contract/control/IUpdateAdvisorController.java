package tecec.ui.contract.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface IUpdateAdvisorController {
	
	void setPKAdvisor (String pkAdvisor);
	void setAdvisorName (String name);
	void setAdvisorEmail (String email);
	
	String getAdvisorName ();
	String getAdvisorEmail ();
	
	void updateAdvisor () throws RuleViolationException;
	RuleViolation getUpdateViolation();

}
