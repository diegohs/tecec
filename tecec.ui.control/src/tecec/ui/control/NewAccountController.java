package tecec.ui.control;

import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.reader.IProfileReader;
import tecec.contract.writer.IAccountWriter;
import tecec.dto.Profile;
import tecec.ui.contract.control.INewAccountController;

public class NewAccountController extends BaseController implements INewAccountController {

	String id;
	String userName;
	
	Profile selectedProfile;
	int selectedProfileIndex;
	
	IAccountWriter accountWriter;
	IProfileReader profileReader;	
	
	public NewAccountController(IAccountWriter accountWriter,
			IProfileReader profileReader) {
		this.accountWriter = accountWriter;
		this.profileReader = profileReader;
	}

	@Override
	public void refresh() {
		setID("");
		
		super.notifyOfPropertyChange("profiles");
		
		setSelectedProfile(null);
		setSelectedProfileIndex(-1);
	}

	@Override
	public void setID(String id) {
		this.id = id;
		
		super.notifyOfPropertyChange("id");
	}

	@Override
	public String getID() {
		return this.id;
	}

	@Override
	public void setUserName(String userName) {
		this.userName = userName;
		
		super.notifyOfPropertyChange("userName");
	}

	@Override
	public String getUserName() {
		return this.userName;
	}

	@Override
	public RuleViolation getInsertViolation() {
		if (this.selectedProfile == null) {
			return new RuleViolation("Um perfil deve ser selecionado para o usuário.");
		}
		
		if (this.id == null || this.id.isEmpty()) {
			return new RuleViolation("O ID do usuário deve ser preenchido.");
		}
		if (this.userName == null || this.userName.isEmpty()) {
			return new RuleViolation("O nome do usuário deve ser preenchido.");
		}
		
		return null;
	}

	@Override
	public void insert()
			throws RuleViolationException {
		RuleViolation violation = getInsertViolation();
		
		if (violation != null) {
			throw new RuleViolationException(violation);
		}
		
		this.accountWriter.insertAccount(this.id, this.id, userName, this.selectedProfile.getpKProfile(), null);
		
		refresh();
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
		
		super.notifyOfPropertyChange("selectedProfile");
	}

	@Override
	public void setSelectedProfileIndex(int i) {
		this.selectedProfileIndex = i;
		
		super.notifyOfPropertyChange("selectedProfileIndex");
	}

}
