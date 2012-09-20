package tecec.ui.control;

import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.encryption.IPasswordHasher;
import tecec.contract.reader.IAccountReader;
import tecec.contract.reader.IProfileReader;
import tecec.contract.writer.IAccountWriter;
import tecec.dto.Account;
import tecec.dto.Profile;
import tecec.ui.contract.control.IUpdateAccountController;

public class UpdateAccountController extends BaseController implements
		IUpdateAccountController {

	String id;
	String userName;
	boolean resetPassword;
	Profile selectedProfile;
	int selectedProfileIndex;

	IProfileReader profileReader;
	IAccountWriter accountWriter;
	IAccountReader accountReader;
	IPasswordHasher passwordHasher;

	public UpdateAccountController(IProfileReader profileReader, IPasswordHasher hasher,
			IAccountWriter accountWriter, IAccountReader accountReader) {
		this.profileReader = profileReader;
		this.accountWriter = accountWriter;
		this.accountReader = accountReader;
		this.passwordHasher = hasher;

		setSelectedProfileIndex(-1);
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
	public List<Profile> getProfiles() {
		return this.profileReader.getProfiles("");
	}

	@Override
	public Profile getSelectedProfile() {
		return this.selectedProfile;
	}

	@Override
	public void setSelectedProfile(Profile profile) {
		this.selectedProfile = profile;

		super.notifyOfPropertyChange("selectedProfile");
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

	@Override
	public void setSelectedProfileIndex(int i) {
		this.selectedProfileIndex = i;

		super.notifyOfPropertyChange("selectedProfileIndex");
	}

	@Override
	public int getSelectedProfileIndex() {
		return this.selectedProfileIndex;
	}

	private Account getAccount() {
		Account account = this.accountReader.getAccount(id);

		account.setUserName(userName);

		if (this.selectedProfile != null) {
			account.setfKProfile(this.selectedProfile.getpKProfile());
		} else {
			account.setfKProfile(null);
		}

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

		List<Profile> profiles = getProfiles();

		super.notifyOfPropertyChange("profiles");

		setSelectedProfile(null);
		setSelectedProfileIndex(-1);

		for (int i = 0; i < profiles.size(); i++) {
			if (profiles.get(i).getpKProfile().equals(account.getfKProfile())) {
				setSelectedProfileIndex(i);
				break;
			}
		}
	}
}
