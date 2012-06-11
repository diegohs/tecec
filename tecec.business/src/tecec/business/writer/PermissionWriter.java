package tecec.business.writer;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.repository.IPermissionRepository;
import tecec.contract.writer.IPermissionWriter;
import tecec.dto.Permission;

public class PermissionWriter implements IPermissionWriter {
	
	private IPermissionRepository permissionRepository;
	
	public PermissionWriter (IPermissionRepository permissionRepository) {
		if (permissionRepository == null)
			throw new IllegalArgumentException ("permissionRepository");
		this.permissionRepository = permissionRepository;
	}

	@Override
	public RuleViolation getCreationViolation(String description) {
		if (description == null || description.trim().isEmpty()) {
			return new RuleViolation ("A descrição da permissão deve ser preenchida.");
		} else {
			if (description.length() > 128)
				return new RuleViolation ("A descrição da permissão deve ser menor que 128 caracteres.");
		}
		
		Permission permission = permissionRepository.getPermissionByDescription(description);
		
		if (permission != null)
			return new RuleViolation ("Já existe outra permissão cadastrada com a mesma descrição.");
				
		return null;		
	}

	@Override
	public RuleViolation getUpdateViolation(String pKPermission,
			String newDescription) {
		Permission permission = this.permissionRepository.getPermissionByPK(pKPermission);
		
		if (permission == null) {
			return new RuleViolation ("A permissão selecionada não existe no banco de dados.");
		} 
		 
		
		if (newDescription == null || newDescription.trim().isEmpty()) {
			return new RuleViolation ("A descrição da permissão deve ser preenchida.");
		}
		
		permission = this.permissionRepository.getPermissionByDescription(newDescription);
		
		if (permission != null) {
			if (!permission.getpKPermission().equals(pKPermission)) {
				return new RuleViolation ("Já existe outra permissão cadastrada com esta descrição.");
			}
		}
		
		return null;
	}

	@Override
	public void createPermission(String description) throws RuleViolationException {
		RuleViolation violation = getCreationViolation(description);

		if (violation != null) {
			throw new RuleViolationException(violation);
		}
		
		Permission permission = new Permission ();
		permission.setDescription(description);
		this.permissionRepository.insertPermission(permission);
	}

	@Override
	public void updatePermission(String pKPermission, String newDescription)
			throws RuleViolationException {
		
		RuleViolation violation = getUpdateViolation(pKPermission, newDescription);
		
		if (violation != null) {
			throw new RuleViolationException(violation);
		}
		
		Permission permission = new Permission ();
		permission.setpKPermission(pKPermission);
		permission.setDescription(newDescription);
		
		this.permissionRepository.updatePermission(permission);	
	}

	@Override
	public void deletePermission(String pKPermission) {
		this.permissionRepository.deletePermission(pKPermission);
		
	}
}
