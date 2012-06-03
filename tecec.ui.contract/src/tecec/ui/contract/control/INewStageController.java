package tecec.ui.contract.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface INewStageController {
	
	String getStageName ();
	void setStageName (String name);
	
	String getStageYear ();
	void setStageYear (String year);
	
	void createStage () throws RuleViolationException;
	RuleViolation getCreationViolation ();

}
