package tecec.ui.contract.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface IUpdateAccountController extends IRefreshable {
	void setAccountID(String id);
	
	String getID();
	
	void setUserName(String userName);
	String getUserName();
	
	void setResetPassword(boolean reset);
	boolean getResetPassword();	
	
	RuleViolation getUpdateViolation();
	
	void update() throws RuleViolationException;
}
