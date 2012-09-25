package tecec.contract.writer;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.dto.Account;

public interface IAccountWriter {
	void insertAccount(String id, String plainPassword, String userName, String fKStudent);
	void updateAccount(Account account) throws RuleViolationException;
	void deleteAccount(String id) throws RuleViolationException;
	
	RuleViolation getUpdateViolation(Account account);
	RuleViolation getDeletionViolation(String id);
}
