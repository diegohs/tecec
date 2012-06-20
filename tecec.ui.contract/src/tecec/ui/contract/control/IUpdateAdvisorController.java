package tecec.ui.contract.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface IUpdateAdvisorController extends IRefreshable  {
	
	void setPKAdvisor (String pkAdvisor);
	void setAdvisorName (String name);
	void setAdvisorEmail (String email);
	
	String getAdvisorName();
	String getAdvisorEmail();
	
	boolean getCanUpdate();
	
	void updateAdvisor () throws RuleViolationException;
	RuleViolation getUpdateViolation();
}