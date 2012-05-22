package tecec.ui.contract.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface IUpdateProfileController {
	void setPKProfile(String pKProfile);
	void setProfileName(String name);
	String getProfileName();
	
	void updateProfile() throws RuleViolationException;
	RuleViolation getUpdateViolation();

}
