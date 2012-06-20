package tecec.business.session;

import tecec.contract.session.ISessionPool;
import tecec.dto.Account;

public class SessionPool implements ISessionPool {
	
	Account loggedAccount;

	@Override
	public Account getLoggedAccount() {
		return this.loggedAccount;
	}

	@Override
	public void setLoggedAccount(Account loggedAccount) {
		this.loggedAccount = loggedAccount;
	}
	
}
