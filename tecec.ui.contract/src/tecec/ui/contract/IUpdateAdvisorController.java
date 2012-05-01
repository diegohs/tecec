package tecec.ui.contract;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface IUpdateAdvisorController {
	void setPKAdvisor(String pkAdvisor);

	void setAdvisorName(String name);

	void setAdvisorEmail(String email);

	String getAdvisorName();

	String getAdvisorEmail();

	void updateCourse() throws RuleViolationException;

	RuleViolation getUpdateViolation();

}
