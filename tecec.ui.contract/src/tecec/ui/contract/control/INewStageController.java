package tecec.ui.contract.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface INewStageController {
	String getStageName();
	void setStageName(String name);
	
	Integer getStageYear ();
	void setStageYear (Integer year);
	
	void createStage() throws RuleViolationException;
	RuleViolation getCreationViolation();

}
