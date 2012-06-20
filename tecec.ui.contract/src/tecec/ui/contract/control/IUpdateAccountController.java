package tecec.ui.contract.control;

import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.dto.Profile;

public interface IUpdateAccountController extends IRefreshable {
	void setAccountID(String id);
	
	String getID();
	
	void setUserName(String userName);
	String getUserName();
	
	void setResetPassword(boolean reset);
	boolean getResetPassword();
	
	List<Profile> getProfiles();
	Profile getSelectedProfile();
	void setSelectedProfile(Profile profile);
	void setSelectedProfileIndex(int i);
	int getSelectedProfileIndex();
	
	RuleViolation getUpdateViolation();
	
	void update() throws RuleViolationException;
}
