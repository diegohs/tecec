package tecec.ui.contract.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface INewAccountController extends IRefreshable {
	void setID(String id);
	String getID();
	
	void setUserName(String userName);
	String getUserName();
	
	RuleViolation getInsertViolation();
	void insert() throws RuleViolationException;
}
