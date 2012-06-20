package tecec.business.writer;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.encryption.IPasswordHasher;
import tecec.contract.repository.IAccountRepository;
import tecec.contract.writer.IAccountWriter;
import tecec.dto.Account;

public class AccountWriter implements IAccountWriter {

	IPasswordHasher hasher;
	IAccountRepository accountRepository;

	public AccountWriter(IPasswordHasher hasher,
			IAccountRepository accountRepository) {
		this.hasher = hasher;
		this.accountRepository = accountRepository;
	}

	@Override
	public void insertAccount(String id, String plainPassword, String userName,
			String fKProfile, String fKStudent) {
		try {
			Account account = new Account();

			account.setId(id);
			account.setUserName(userName);
			account.setfKProfile(fKProfile);
			account.setFKStudent(fKStudent);

			String hashedPassword = this.hasher.hash(plainPassword);

			account.setPassword(hashedPassword);

			this.accountRepository.insertAccount(account);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateAccount(Account account) throws RuleViolationException {
		RuleViolation violation = this.getUpdateViolation(account);
		
		if (violation != null) {
			throw new RuleViolationException(violation);
		}
		
		this.accountRepository.updateAccount(account);
	}

	@Override
	public void deleteAccount(String id) throws RuleViolationException {
		RuleViolation violation = this.getDeletionViolation(id);
		
		if (violation != null) {
			throw new RuleViolationException(violation);
		}
		
		this.accountRepository.deleteAccount(id);
	}

	@Override
	public RuleViolation getUpdateViolation(Account account) {
		return null;
	}

	@Override
	public RuleViolation getDeletionViolation(String id) {
		Account account = this.accountRepository.getAccount(id);
		
		if (account.getFKStudent() != null && !account.getFKStudent().isEmpty()) {
			return new RuleViolation("Não é possível excluir uma conta vinculada a um aluno. Excluia o aluno para que sua conta também seja excluída.");
		}
		
		return null;
	}

}
