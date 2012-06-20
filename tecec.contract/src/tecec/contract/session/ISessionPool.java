package tecec.contract.session;

import tecec.dto.Account;

public interface ISessionPool {
	Account getLoggedAccount();
	void setLoggedAccount(Account loggedAccount);
}
