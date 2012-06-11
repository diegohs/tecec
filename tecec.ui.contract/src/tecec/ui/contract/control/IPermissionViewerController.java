package tecec.ui.contract.control;

import java.util.List;
import tecec.dto.Permission;

public interface IPermissionViewerController {
	
	void setNameFilter (String nameFilter);
	String getNameFilter ();
	
	void setSelectedPermission (Permission permission);
	Permission getSelectedPermission();
	List <Permission> getPermission ();
	
	void deletePermission ();	
	
	boolean getCanUpdatePermission ();
	boolean getCanDeletePermission ();	
	
	void showNewPermissionUI();
	void showUpdatePermissionUI();
}
