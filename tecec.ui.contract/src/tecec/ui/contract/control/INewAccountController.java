package tecec.ui.contract.control;

import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.dto.Profile;

public interface INewAccountController extends IRefreshable {
	void setID(String id);
	String getID();
	
	void setUserName(String userName);
	String getUserName();
	
	RuleViolation getInsertViolation();
	void insert() throws RuleViolationException;
	
	List<Profile> getProfiles();
	Profile getSelectedProfile();
	void setSelectedProfile(Profile profile);
	void setSelectedProfileIndex(int i);
}
