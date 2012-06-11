package tecec.business.reader;

import java.util.List;

import tecec.contract.reader.IPermissionReader;
import tecec.contract.repository.IPermissionRepository;
import tecec.dto.Permission;

public class PermissionReader implements IPermissionReader {
	
	private IPermissionRepository permissionRepository;
	
	public PermissionReader (IPermissionRepository permissionRepository) {
		this.permissionRepository = permissionRepository;
	}
	

	@Override
	public List<Permission> getPermission(String nameFilter) {
		return permissionRepository.getPermission(nameFilter);
	}

	@Override
	public Permission getPermissionByPK(String pKPermission) {
		return permissionRepository.getPermissionByPK(pKPermission);
	}	
}
