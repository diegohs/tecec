package tecec.ui.contract.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface IUpdateMonographController {
	void setPKMonograph (String PKMonograph);
	void setMonographTitle (String title);
	String getMonographTitle ();
	
	void updateMonograph() throws RuleViolationException;
	RuleViolation getUpdateViolation();

}
