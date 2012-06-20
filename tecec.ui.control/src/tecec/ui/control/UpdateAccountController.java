package tecec.ui.control;

import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
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

	public UpdateAccountController(IProfileReader profileReader,
			IAccountWriter accountWriter, IAccountReader accountReader) {
		this.profileReader = profileReader;
		this.accountWriter = accountWriter;
		this.accountReader = accountReader;
		
		setSelectedProfileIndex(-1);
	}

	@Override
	public void setAccountID(String id) {
		this.id = id;
		
		super.notifyOfPropertyChange("ID", null, id);
		
		refresh();
	}

	@Override
	public String getID() {
		return this.id;
	}

	@Override
	public void setUserName(String userName) {
		this.userName = userName;
		
		super.notifyOfPropertyChange("userName", null, userName);
	}

	@Override
	public String getUserName() {
		return this.userName;
	}

	@Override
	public void setResetPassword(boolean reset) {
		this.resetPassword = reset;
		
		super.notifyOfPropertyChange("resetPassword", null, reset);
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
		
		super.notifyOfPropertyChange("selectedProfile", null, selectedProfile);
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
		
		super.notifyOfPropertyChange("selectedProfileIndex", null, i);
	}

	@Override
	public int getSelectedProfileIndex() {
		return this.selectedProfileIndex;
	}

	private Account getAccount() {
		Account account = this.accountReader.getAccount(id);

		account.setUserName(userName);
		account.setfKProfile(this.selectedProfile.getpKProfile());

		if (resetPassword) {
			account.setPassword(account.getId());
		}

		return account;
	}

	@Override
	public void refresh() {		
		Account account = this.accountReader.getAccount(this.id);
		
		setUserName(account.getUserName());
		setResetPassword(false);			

		List<Profile> profiles = getProfiles();
		
		super.notifyOfPropertyChange("profiles", null, profiles);

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
