package tecec.business.writer;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.repository.IStatusRepository;
import tecec.contract.writer.IStatusWriter;
import tecec.dto.Status;

public class StatusWriter implements IStatusWriter {
	
	private IStatusRepository statusRepository;
	
	public StatusWriter (IStatusRepository statusRepository) {
		if (statusRepository == null)
			throw new IllegalArgumentException ("statusRepository");
		this.statusRepository = statusRepository;
	}

	@Override
	public RuleViolation getCreationViolation(String description) {
		if (description == null || description.trim().isEmpty()) {
			return new RuleViolation ("A descrição do status deve ser preenchida.");
		} else {
			if (description.length() > 128)
				return new RuleViolation ("A descrição do status deve ser menor que 128 caracteres.");
		}
		
		Status status = statusRepository.getStatusByDescription(description);
		
		if (status != null)
			return new RuleViolation ("Já existe outro status cadastrado com a mesma descrição.");
				
		return null;		
	}

	@Override
	public RuleViolation getUpdateViolation(String pKStatus,
			String newDescription) {
		Status status = this.statusRepository.getStatusByPK(pKStatus);
		
		if (status == null) {
			return new RuleViolation ("O status selecionado não existe no banco de dados.");
		} 
		 
		
		if (newDescription == null || newDescription.trim().isEmpty()) {
			return new RuleViolation ("A descrição do status deve ser preenchida.");
		}
		
		status = this.statusRepository.getStatusByDescription(newDescription);
		
		if (status != null) {
			if (!status.getpKStatus().equals(pKStatus)) {
				return new RuleViolation ("Já existe outro status cadastrado com esta descrição.");
			}
		}
		
		return null;
	}

	@Override
	public void createStatus(String description) throws RuleViolationException {
		RuleViolation violation = getCreationViolation(description);

		if (violation != null) {
			throw new RuleViolationException(violation);
		}
		
		Status status = new Status ();
		status.setDescription(description);
		this.statusRepository.insertStatus(status);
	}

	@Override
	public void updateStatus(String pKStatus, String newDescription)
			throws RuleViolationException {
		
		RuleViolation violation = getUpdateViolation(pKStatus, newDescription);
		
		if (violation != null) {
			throw new RuleViolationException(violation);
		}
		
		Status status = new Status ();
		status.setpKStatus(pKStatus);
		status.setDescription(newDescription);
		
		this.statusRepository.updateStatus(status);	
	}

	@Override
	public void deleteStatus(String pKStatus) {
		this.statusRepository.deleteStatus(pKStatus);
		
	}
	

}
