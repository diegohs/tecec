package tecec.ui.control;

import java.util.List;

import tecec.contract.reader.IProfileReader;
import tecec.contract.reporting.IAccountReporter;
import tecec.contract.writer.IProfileWriter;
import tecec.dto.Profile;
import tecec.ui.contract.control.IProfileViewerController;
import tecec.ui.contract.view.INewProfileUI;
import tecec.ui.contract.view.IUpdateProfileUI;

public class ProfileViewerController extends BaseViewerController implements IProfileViewerController {

	private String nameFilter;
	private Profile selectedProfile;
	private IProfileReader profileReader;
	private IProfileWriter profileWriter;
	
	private INewProfileUI newProfileUI;
	private IUpdateProfileUI updateProfileUI;

	public ProfileViewerController(IProfileReader profileReader, IProfileWriter profileWriter, INewProfileUI newProfileUI, IUpdateProfileUI updateProfileUI, IAccountReporter reporter) {
		super(reporter);
		
		this.profileReader = profileReader;
		this.profileWriter = profileWriter;
		this.newProfileUI = newProfileUI;
		this.updateProfileUI = updateProfileUI;
	}
	
	@Override
	public void setNameFilter(String nameFilter) {		
		this.nameFilter = nameFilter;
		
		super.notifyOfPropertyChange("nameFilter");
		super.notifyOfPropertyChange("profiles");
	}

	@Override
	public String getNameFilter() {
		return this.nameFilter;
	}

	@Override
	public void setSelectedProfile(Profile profile) {		
		this.selectedProfile = profile;

		super.notifyOfPropertyChange("selectedProfile");
		super.notifyOfPropertyChange("canUpdateProfile");
		super.notifyOfPropertyChange("canDeleteProfile");
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
		this.newProfileUI.refresh();
		this.newProfileUI.setVisible(true);
		
		super.notifyOfPropertyChange("profiles");
	}

	@Override
	public void showUpdateProfileUI() {
		this.updateProfileUI.setpKProfile(this.selectedProfile.getpKProfile());
		this.updateProfileUI.setVisible(true);
		
		super.notifyOfPropertyChange("profiles");
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
		
		super.notifyOfPropertyChange("profiles");
	}

	@Override
	public void refresh() {
		setNameFilter("");
	}

	@Override
	protected List<String[]> getExportSource() {
		// TODO Auto-generated method stub
		return null;
	}
}
