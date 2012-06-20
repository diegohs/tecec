package tecec.ui.control;

import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.encryption.IPasswordHasher;
import tecec.contract.reader.IAccountReader;
import tecec.contract.session.ISessionPool;
import tecec.contract.writer.IAccountWriter;
import tecec.dto.Account;
import tecec.ui.contract.control.ILoginController;
import tecec.ui.contract.view.IMainUI;

public class LoginController extends BaseController implements ILoginController {

	String id;
	String password;
	String confirmation;
	boolean isLoginEnabled;

	IAccountWriter accountWriter;
	IAccountReader accountReader;
	IPasswordHasher hasher;
	ISessionPool sessionPool;
	IMainUI mainUI;

	public LoginController(IAccountReader accountReader,
			IAccountWriter accountWriter, ISessionPool sessionPool, IPasswordHasher hasher,
			IMainUI mainUI) {
		this.accountWriter = accountWriter;
		this.accountReader = accountReader;
		this.sessionPool = sessionPool;
		this.hasher = hasher;
		this.mainUI = mainUI;
		setIsLoginEnabled(true);
	}

	@Override
	public String getID() {
		return this.id;
	}

	@Override
	public void setID(String id) {
		this.id = id;

		super.notifyOfPropertyChange("id", null, id);
	}

	private void setIsLoginEnabled(boolean enabled) {
		this.isLoginEnabled = enabled;

		super.notifyOfPropertyChange("isLoginEnabled", null,
				this.isLoginEnabled);
		super.notifyOfPropertyChange("isConfirmationVisible", null,
				getIsConfirmationVisible());
	}

	private boolean areCredentialsValid(String password) {
		return this.accountReader.areCredentialsValid(id, new String(password));
	}

	@Override
	public void login(char[] password, char[] confirmation)
			throws RuleViolationException {
		RuleViolation violation = getLoginViolation(password, confirmation);
		
		if (violation != null) {
			throw new RuleViolationException(violation);
		}

		Account account = this.accountReader.getAccount(this.id);

		this.sessionPool.setLoggedAccount(account);

		this.mainUI.setVisible(true);
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;

		super.notifyOfPropertyChange("password", null, confirmation);
	}

	@Override
	public String getConfirmation() {
		return this.confirmation;
	}

	@Override
	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;

		super.notifyOfPropertyChange("confirmation", null, confirmation);
	}

	@Override
	public boolean getIsLoginEnabled() {
		return this.isLoginEnabled;
	}

	@Override
	public RuleViolation getLoginViolation(char[] password, char[] confirmation) {
		List<Account> accounts = this.accountReader.getAccounts("");

		if (accounts.size() == 0) {
			this.accountWriter.insertAccount("admin", "admin", "admin", null,
					null);
		}
		
		boolean isReset = this.accountReader.isPasswordReset(this.id);

		if (isReset && this.isLoginEnabled) {
			setIsLoginEnabled(false);

			return new RuleViolation(
					"Nenhuma senha foi definida para esta conta. Defina uma nova senha antes de continuar.");
		} else {
			if (!this.isLoginEnabled) {
				if (password.length == 0) {
					return new RuleViolation(
							"O campo 'Senha' deve ser preenchido.");
				}

				if (this.id.equals(new String(password))) {
					return new RuleViolation(
							"A senha não pode ser idêntica à identificação da conta.");
				}

				if (password.length != confirmation.length) {
					return new RuleViolation(
							"Os valores dos campos 'Senha' e 'Confirmação' não são equivalentes.");
				}

				for (int i = 0; i < password.length; i++) {
					if (password[i] != confirmation[i]) {
						return new RuleViolation(
								"Os valores dos campos 'Senha' e 'Confirmação' não são equivalentes");
					}
				}
				
				Account account = this.accountReader.getAccount(this.id);

				try {
					account.setPassword(hasher.hash(new String(password)));
				} catch (Exception e1) {
					e1.printStackTrace();
					return new RuleViolation(e1.getMessage());
				}

				try {
					this.accountWriter.updateAccount(account);
				} catch (RuleViolationException e) {
					return new RuleViolation(e.getMessage());
				}
			}			

			if (!areCredentialsValid(new String(password))) {
				return new RuleViolation("Login e/ou senha incorretos.");
			}
		}

		return null;
	}

	@Override
	public boolean getIsConfirmationVisible() {
		return !this.isLoginEnabled;
	}

	@Override
	public void refresh() {
		this.setID("");
	}
}
