package tecec.ui.contract.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface IUpdateTurnController {
	void setPKTurn(String pKTurn);
	void setTurnName(String name);
	String getTurnName();
	
	void updateTurn() throws RuleViolationException;
	RuleViolation getUpdateViolation();
}
