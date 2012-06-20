package tecec.contract.repository;

import java.util.List;

import tecec.dto.Account;

public interface IAccountRepository {
	void insertAccount(Account account);
	Account getAccount(String id);
	List<Account> getAccounts(String filter);
	void deleteAccount(String id);
	void updateAccount(Account account);
}
