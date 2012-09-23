package tecec.ui.contract.control;

import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.dto.record.AccountRecord;

public interface IAccountViewerController extends IViewerController {	
	void setFilter(String filter);
	String getFilter();
	
	List<AccountRecord> getAccounts();
	AccountRecord getSelectedAccount();
	void setSelectedAccount(AccountRecord account);
	
	boolean getCanUpdate();
	boolean getCanDelete();
	
	void showUpdateAccountUI();
	void showNewAccountUI();
	void delete() throws RuleViolationException;
	
	RuleViolation getDeletionViolation();
}
