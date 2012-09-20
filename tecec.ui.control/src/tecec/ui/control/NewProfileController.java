package tecec.ui.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.writer.IProfileWriter;

public class NewProfileController extends BaseController implements
		tecec.ui.contract.control.INewProfileController {
	
	private IProfileWriter profileWriter;
	private String profileName;

	public NewProfileController(IProfileWriter profileWriter) {
		if (profileWriter == null) {
			throw new IllegalArgumentException("profileWriter");
		}

		this.profileWriter = profileWriter;
	}

	@Override
	public void createProfile() throws RuleViolationException {
		RuleViolation violation = getCreationViolation();

		if (violation != null) {
			throw new RuleViolationException(violation);
		}

		profileWriter.createProfile(this.profileName);

		refresh();
	}

	@Override
	public String getProfileName() {
		return this.profileName;
	}

	@Override
	public void setProfileName(String name) {
		String oldValue = getProfileName();

		this.profileName = name;

		notifyOfPropertyChange("profileName");
	}

	@Override
	public RuleViolation getCreationViolation() {
		return profileWriter.getCreationViolation(this.getProfileName());
	}

	@Override
	public void refresh() {
		setProfileName("");
	}

}
