package tecec.contract.repository;

import java.util.List;
import tecec.dto.Permission;

public interface IPermissionRepository {
	void insertPermission (Permission permission);
	void updatePermission (Permission permission);
	void deletePermission (String pKPermission);
	Permission getPermissionByDescription (String description);
	Permission getPermissionByPK (String pKPermission);
	List <Permission> getPermission (String nameFilter);
}
