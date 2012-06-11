package tecec.ui.control;



import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.writer.IPermissionWriter;

import tecec.ui.contract.control.INewPermissionController;


public class NewPermissionController extends BaseController implements INewPermissionController{

	private IPermissionWriter permissionWriter;
	private String permissionDescription;
	
	public NewPermissionController (IPermissionWriter permissionWriter) {
		if (permissionWriter == null)
			throw new IllegalArgumentException ("permissionWriter");
		this.permissionWriter = permissionWriter;
	}	

	@Override
	public String getPermissionDescription() {
		return this.permissionDescription;
	}

	@Override
	public void setPermissionDescription(String description) {
		String oldValue = getPermissionDescription ();
		
		this.permissionDescription = description;
		
		notifyOfPropertyChange("permissionDescription", oldValue, description);
	}
	
	@Override
	public void createPermission() throws RuleViolationException {
		RuleViolation violation = getCreationViolation ();
		
		if (violation != null)
			throw new RuleViolationException (violation);
		
		permissionWriter.createPermission(this.permissionDescription);
		
		setPermissionDescription ("");
	}

	@Override
	public RuleViolation getCreationViolation() {
		return permissionWriter.getCreationViolation(this.getPermissionDescription());	
	}	
}
