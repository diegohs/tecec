package tecec.ui.control;

import java.util.List;


import tecec.contract.reader.IPermissionReader;

import tecec.contract.writer.IPermissionWriter;

import tecec.dto.Permission;

import tecec.ui.contract.control.IPermissionViewerController;

import tecec.ui.contract.view.INewPermissionUI;

import tecec.ui.contract.view.IUpdatePermissionUI;

public class PermissionViewerController extends BaseController implements
	IPermissionViewerController {
	
	private String nameFilter;
	private Permission selectedPermission;
	private IPermissionReader permissionReader;
	private IPermissionWriter permissionWriter;
	
	private INewPermissionUI newPermissionUI;
	private IUpdatePermissionUI updatePermissionUI;
	
	public PermissionViewerController (IPermissionReader permissionReader, IPermissionWriter permissionWriter, INewPermissionUI newPermissionUI, IUpdatePermissionUI updatePermissionUI) {
		this.permissionReader = permissionReader;
		this.permissionWriter = permissionWriter;
		this.newPermissionUI = newPermissionUI;
		this.updatePermissionUI = updatePermissionUI;
	}

	@Override
	public void setNameFilter(String nameFilter) {
		String old = this.nameFilter;
		this.nameFilter = nameFilter;
		
		super.notifyOfPropertyChange("nameFilter", old, nameFilter);
		super.notifyOfPropertyChange("permission", null, getPermission());
	}

	@Override
	public String getNameFilter() {
		return this.nameFilter;
	}

	@Override
	public void setSelectedPermission(Permission permission) {
		Permission old = this.selectedPermission;
		this.selectedPermission = permission;
		
		super.notifyOfPropertyChange("selectedPermission", old, permission);
		super.notifyOfPropertyChange("canUpdatePermission", old, permission);
		super.notifyOfPropertyChange("canDeletePermission", old, permission);
		
	}

	@Override
	public Permission getSelectedPermission() {
		return this.selectedPermission;
	}

	@Override
	public List<Permission> getPermission() {
		List <Permission> permission = this.permissionReader.getPermission(this.nameFilter);
		return permission;	
	}

	@Override
	public void deletePermission() {
		this.permissionWriter.deletePermission(this.selectedPermission.getpKPermission());
		super.notifyOfPropertyChange("permission", null, getPermission());	
	}

	@Override
	public boolean getCanUpdatePermission() {
		return this.selectedPermission != null;	
	}

	@Override
	public boolean getCanDeletePermission() {
		return this.selectedPermission != null;
	}

	@Override
	public void showNewPermissionUI() {
		this.newPermissionUI.setVisible(true);
		super.notifyOfPropertyChange("permission", null, getPermission());
	}	

	@Override
	public void showUpdatePermissionUI() {
		this.updatePermissionUI.setpKPermission(this.selectedPermission.getpKPermission());
		this.updatePermissionUI.setVisible(true);		
		super.notifyOfPropertyChange("permission", null, getPermission());
	}
}
