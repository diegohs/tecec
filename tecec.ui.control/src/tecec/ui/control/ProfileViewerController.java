package tecec.ui.control;

import java.util.List;

import tecec.contract.reader.IProfileReader;
import tecec.contract.writer.IProfileWriter;
import tecec.dto.Profile;
import tecec.ui.contract.control.IProfileViewerController;
import tecec.ui.contract.view.INewProfileUI;
import tecec.ui.contract.view.IUpdateProfileUI;

public class ProfileViewerController extends BaseController implements IProfileViewerController {

	private String nameFilter;
	private Profile selectedProfile;
	private IProfileReader profileReader;
	private IProfileWriter profileWriter;
	
	private INewProfileUI newProfileUI;
	private IUpdateProfileUI updateProfileUI;

	public ProfileViewerController(IProfileReader profileReader, IProfileWriter profileWriter, INewProfileUI newProfileUI, IUpdateProfileUI updateProfileUI) {
		this.profileReader = profileReader;
		this.profileWriter = profileWriter;
		this.newProfileUI = newProfileUI;
		this.updateProfileUI = updateProfileUI;
	}
	
	@Override
	public void setNameFilter(String nameFilter) {
		String old = this.nameFilter;
		
		this.nameFilter = nameFilter;
		
		super.notifyOfPropertyChange("nameFilter", old, nameFilter);
		super.notifyOfPropertyChange("profiles", null, getProfiles());
	}

	@Override
	public String getNameFilter() {
		return this.nameFilter;
	}

	@Override
	public void setSelectedProfile(Profile profile) {
		Profile old = this.selectedProfile;
		
		this.selectedProfile = profile;

		super.notifyOfPropertyChange("selectedProfile", old, profile);
		super.notifyOfPropertyChange("canUpdateProfile", old, profile);
		super.notifyOfPropertyChange("canDeleteProfile", old, profile);
	}
	
	

	@Override
	public Profile getSelectedProfile() {
		return this.selectedProfile;
	}

	@Override
	public List<Profile> getProfiles() {
		List<Profile> profiles = this.profileReader.getProfiles(this.nameFilter);
		
		return profiles;
	}

	@Override
	public void showNewProfileUI() {
		this.newProfileUI.setVisible(true);
		
		super.notifyOfPropertyChange("profiles", null, getProfiles());
	}

	@Override
	public void showUpdateProfileUI() {
		this.updateProfileUI.setpKProfile(this.selectedProfile.getpKProfile());
		this.updateProfileUI.setVisible(true);
		
		super.notifyOfPropertyChange("profiles", null, getProfiles());
	}

	@Override
	public boolean getCanUpdateProfile() {
		return this.selectedProfile != null;
	}

	@Override
	public boolean getCanDeleteProfile() {
		return this.selectedProfile != null;
	}

	@Override
	public void deleteProfile() {
		this.profileWriter.deleteProfile(this.selectedProfile.getpKProfile());
		
		super.notifyOfPropertyChange("profiles", null, getProfiles());
	}
}
