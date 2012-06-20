package tecec.ui.contract.control;

import java.util.List;
import tecec.dto.Profile;

public interface IProfileViewerController extends IRefreshable  {
	void setNameFilter(String nameFilter);
	String getNameFilter();
	
	void setSelectedProfile(Profile profile);
	Profile getSelectedProfile();
	List<Profile> getProfiles();
	
	void deleteProfile();
	
	boolean getCanUpdateProfile();
	boolean getCanDeleteProfile();
	
	void showNewProfileUI();
	void showUpdateProfileUI();

}
