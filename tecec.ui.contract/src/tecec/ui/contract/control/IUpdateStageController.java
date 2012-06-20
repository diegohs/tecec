package tecec.ui.contract.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface IUpdateStageController extends IRefreshable  {
	
	void setPKStage (String pKStage);
	void setStageName (String name);
	void setStageYear (String year);
	
	String getStageName ();
	String getStageYear ();
	
	boolean getCanUpdate ();
	
	void updateStage () throws RuleViolationException;
	RuleViolation getUpdateViolation ();

}
