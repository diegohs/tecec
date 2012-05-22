package tecec.ui.contract.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface INewProfileController {
	String getProfileName();
	void setProfileName(String name);
	
	void createProfile() throws RuleViolationException;
	RuleViolation getCreationViolation();

}
