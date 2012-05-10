package tecec.business.writer;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.repository.IStatusRepository;
import tecec.dto.Status;

public class StatusWriter implements tecec.contract.writer.IStatusWriter {

	/* Verifiquem por favor, pode estar mega errado por causa da descrição
	 * possibilitei acho que várias descrições iguais, porém chaves diferentes
	 * Por: Bruno 
	 */
	
	private tecec.contract.repository.IStatusRepository statusRepository;

	public StatusWriter(IStatusRepository statusRepository) {
		if (statusRepository == null) {
			throw new IllegalArgumentException("statusRepository");
		}

		this.statusRepository = statusRepository;
	}

	@Override
	public void createStatus(String description) throws RuleViolationException {
		RuleViolation violation = getCreationViolation(description);

		if (violation != null) {
			throw new RuleViolationException(violation);
		}

		Status status = new Status();

		status.setDescription(description);

		this.statusRepository.insertStatus(status);
	}

	@Override
	public void updateStatus(String pKStatus, String newDescription) throws RuleViolationException {
		RuleViolation violation = getUpdateViolation(pKStatus, newDescription);
		
		if (violation != null) {
			throw new RuleViolationException(violation);
		}
		
		Status status = new Status();
		
		status.setpKStatus(pKStatus);
		status.setDescription(newDescription);
		
		this.statusRepository.updateStatus(status);
	}

	@Override
	public RuleViolation getCreationViolation(String description) {
		if (description == null || description.trim().isEmpty()) {
			return new RuleViolation("A descrição deve ser preenchida.");
		} else {
			if (description.length() > 512) {
				return new RuleViolation(
						"A descrição deve conter no máximo 512 caracteres.");
			}
		}
		return null;
	}

	@Override
	public RuleViolation getUpdateViolation(String pKStatus, String newDescription) {
		Status status = this.statusRepository.getStatusByDescription(pKStatus);

		if (status == null) {
			return new RuleViolation(
					"A descrição selecionada não existe no banco de dados.");
		}
		
		if (newDescription == null || newDescription.trim().isEmpty()) {
			return new RuleViolation("A descrição deve ser preenchida.");
		}

		status = this.statusRepository.getStatusByDescription(newDescription);

		if (status != null) {
			if (!status.getpKStatus().equals(pKStatus)) {
				return new RuleViolation(
						"Já existe outra descrição cadastrada com essa chave.");
			}
		}

		return null;
	}

	@Override
	public void deleteStatus(String pKStatus) {
		this.statusRepository.deleteStatus(pKStatus);
	}

}
