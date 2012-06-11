package tecec.ui.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

import tecec.contract.reader.IPermissionReader;

import tecec.contract.writer.IPermissionWriter;

import tecec.dto.Permission;

import tecec.ui.contract.control.IUpdatePermissionController;

public class UpdatePermissionController extends BaseController implements 
	IUpdatePermissionController {
	
	private String pKPermission;
	private String permissionDescription;
	private IPermissionWriter permissionWriter;
	private IPermissionReader permissionReader;	
	
	/* Construtor */
	public UpdatePermissionController (IPermissionWriter permissionWriter, IPermissionReader permissionReader) {
		this.permissionWriter = permissionWriter;
		this.permissionReader = permissionReader;
	}

	@Override
	public void setPKPermission(String pKPermission) {
		this.pKPermission = pKPermission;
		
		Permission permission = this.permissionReader.getPermissionByPK(pKPermission);
		
		if (permission != null)
			this.setPermissionDescription(permission.getDescription());
	}

	@Override
	public void setPermissionDescription(String description) {
		String old = this.permissionDescription;
		this.permissionDescription = description;		
		super.notifyOfPropertyChange("permissionDescription", old, description);
		
	}

	@Override
	public String getPermissionDescription() {
		return this.permissionDescription;
	}

	@Override
	public void updatePermission() throws RuleViolationException {
		RuleViolation violation = getUpdateViolation();
		if (violation != null) {
			throw new RuleViolationException(violation);
		}
		this.permissionWriter.updatePermission(this.pKPermission, this.permissionDescription);
				
	}

	@Override
	public RuleViolation getUpdateViolation() {
		return this.permissionWriter.getUpdateViolation(this.pKPermission,this.permissionDescription);
	}
}
