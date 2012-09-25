package tecec.ui.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.encryption.IPasswordHasher;
import tecec.contract.reader.IAccountReader;
import tecec.contract.writer.IAccountWriter;
import tecec.dto.Account;
import tecec.ui.contract.control.IUpdateAccountController;

public class UpdateAccountController extends BaseController implements
		IUpdateAccountController {

	String id;
	String userName;
	boolean resetPassword;

	IAccountWriter accountWriter;
	IAccountReader accountReader;
	IPasswordHasher passwordHasher;

	public UpdateAccountController(IPasswordHasher hasher,
			IAccountWriter accountWriter, IAccountReader accountReader) {
		this.accountWriter = accountWriter;
		this.accountReader = accountReader;
		this.passwordHasher = hasher;
	}

	@Override
	public void setAccountID(String id) {
		this.id = id;

		super.notifyOfPropertyChange("ID");

		refresh();
	}

	@Override
	public String getID() {
		return this.id;
	}

	@Override
	public void setUserName(String userName) {
		this.userName = userName;

		super.notifyOfPropertyChange("userName");
	}

	@Override
	public String getUserName() {
		return this.userName;
	}

	@Override
	public void setResetPassword(boolean reset) {
		this.resetPassword = reset;

		super.notifyOfPropertyChange("resetPassword");
	}

	@Override
	public boolean getResetPassword() {
		return this.resetPassword;
	}

	@Override
	public RuleViolation getUpdateViolation() {
		return this.accountWriter.getUpdateViolation(getAccount());
	}

	@Override
	public void update() throws RuleViolationException {
		RuleViolation violation = getUpdateViolation();

		if (violation != null) {
			throw new RuleViolationException(violation);
		}

		this.accountWriter.updateAccount(getAccount());
	}

	private Account getAccount() {
		Account account = this.accountReader.getAccount(id);

		account.setUserName(userName);

		if (resetPassword) {
			try {
				account.setPassword(passwordHasher.hash(account.getId()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return account;
	}

	@Override
	public void refresh() {
		Account account = this.accountReader.getAccount(this.id);

		setUserName(account.getUserName());
		setResetPassword(false);
	}
}
