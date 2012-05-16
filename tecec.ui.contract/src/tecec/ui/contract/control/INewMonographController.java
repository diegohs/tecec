package tecec.ui.contract.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface INewMonographController {
	String getMonographTitle ();
	void setMonographTitle (String title);
	
	void createMonograph () throws RuleViolationException;
	RuleViolation getCreationViolation();
}
