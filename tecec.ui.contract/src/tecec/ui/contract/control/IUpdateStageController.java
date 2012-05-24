package tecec.ui.contract.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface IUpdateStageController {

	void setPKStage(String pKStage);
	void setStageName(String name);
	String getStageName();
	
	void setStageYear (Integer year);
	Integer getStageYear ();
	
	void updateStage() throws RuleViolationException;
	RuleViolation getUpdateViolation();
}
