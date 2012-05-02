package tecec.ui.contract.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface INewAdvisorController {
	String getAdvisorName ();
	void setAdvisorName (String name);
	
	String getAdvisorEmail ();
	void setAdvisorEmail (String email);
	
	void createAdvisor () throws RuleViolationException;
	RuleViolation getCreationViolation();
}

