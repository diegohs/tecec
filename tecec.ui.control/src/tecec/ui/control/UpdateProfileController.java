package tecec.ui.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.reader.IProfileReader;
import tecec.contract.writer.IProfileWriter;
import tecec.dto.Profile;
import tecec.ui.contract.control.IUpdateProfileController;

public class UpdateProfileController extends BaseController implements
		IUpdateProfileController {

	private String pKProfile;
	private String profileName;
	private IProfileWriter profileWriter;
	private IProfileReader profileReader;

	public UpdateProfileController(IProfileWriter profileWriter,
			IProfileReader profileReader) {
		this.profileWriter = profileWriter;
		this.profileReader = profileReader;
	}

	@Override
	public void setPKProfile(String pKProfile) {
		this.pKProfile = pKProfile;

		Profile profile = this.profileReader.getProfileByPK(pKProfile);

		this.setProfileName(profile.getName());
	}

	@Override
	public void setProfileName(String name) {
		String old = this.profileName;

		this.profileName = name;

		super.notifyOfPropertyChange("profileName");
	}

	@Override
	public String getProfileName() {
		return this.profileName;
	}

	@Override
	public void updateProfile() throws RuleViolationException {
		RuleViolation violation = getUpdateViolation();

		if (violation != null) {
			throw new RuleViolationException(violation);
		}

		this.profileWriter.updateProfile(this.pKProfile, this.profileName);
		
		refresh();
	}

	@Override
	public RuleViolation getUpdateViolation() {
		return this.profileWriter.getUpdateViolation(this.pKProfile,
				this.profileName);
	}

	@Override
	public void refresh() {
		this.setPKProfile(this.pKProfile);	}

}
