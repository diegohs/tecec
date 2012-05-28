package tecec.ui.contract.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface INewTurnController {	
	String getTurnName();
	void setTurnName(String name);
	
	void createTurn() throws RuleViolationException;
	RuleViolation getCreationViolation();
}
