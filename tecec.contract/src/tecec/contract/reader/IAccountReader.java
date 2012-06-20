package tecec.contract.reader;

import java.util.List;

import tecec.dto.Account;

public interface IAccountReader {
	boolean areCredentialsValid(String id, String plainPassword);
	boolean isPasswordReset(String id);
	Account getAccount(String id);
	List<Account> getAccounts(String filter);	
}
