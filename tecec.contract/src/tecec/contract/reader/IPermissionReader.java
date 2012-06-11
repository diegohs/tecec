package tecec.contract.reader;

import java.util.List;


import tecec.dto.Permission;

public interface IPermissionReader {
	List <Permission> getPermission (String nameFilter);
	Permission getPermissionByPK (String pKPermission);
}
