package tecec.ui.contract.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface ILoginController extends IRefreshable  {
	String getID();
	void setID(String id);
	
	String getPassword();
	void setPassword(String password);
	
	String getConfirmation();
	void setConfirmation(String confirmation);
	
	boolean getIsLoginEnabled();
	boolean getIsConfirmationVisible();
	
	RuleViolation getLoginViolation(char[] password, char[] confirmation);
	void login(char[] password, char[] confirmation) throws RuleViolationException;
}
