package tecec.business.reader;


import java.util.List;

import tecec.contract.encryption.IPasswordHasher;
import tecec.contract.reader.IAccountReader;
import tecec.contract.repository.IAccountRepository;
import tecec.dto.Account;

public class AccountReader implements IAccountReader {

	IPasswordHasher hasher;
	IAccountRepository accountRepository;

	public AccountReader(IPasswordHasher hasher,
			IAccountRepository accountRepository) {
		this.hasher = hasher;
		this.accountRepository = accountRepository;
	}

	@Override
	public boolean areCredentialsValid(String id, String plainPassword) {
		try {
			Account account = this.accountRepository.getAccount(id);
			
			if (account != null) {
				return this.hasher.arePasswordsEqual(plainPassword, account.getPassword());	
			}
			
			return false;
			
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public Account getAccount(String id) {
		return this.accountRepository.getAccount(id);
	}

	@Override
	public List<Account> getAccounts(String filter) {
		return this.accountRepository.getAccounts(filter);
	}

	@Override
	public boolean isPasswordReset(String id) {
		try {
			Account account = this.accountRepository.getAccount(id);
			
			if (account != null) {
				return this.hasher.arePasswordsEqual(id, account.getPassword());	
			}
			
			return false;
			
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

}
