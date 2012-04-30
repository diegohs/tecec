package tecec.ui.control;

import tecec.contract.repository.AdvisorRepository;
import tecec.dto.Advisor;
import tecec.ui.contract.NewAdvisorController;

public class DefaultNewAdvisorController implements NewAdvisorController {
	private AdvisorRepository advisorRepository;
	private Advisor advisor;
	
	public DefaultNewAdvisorController (AdvisorRepository advisorRepository) {
		if (advisorRepository == null ) {
			throw new IllegalArgumentException ("advisorRepository");
		}
		this.advisorRepository = advisorRepository;
		this.advisor = new Advisor();
	}

	@Override
	public Advisor getAdvisor() {
		return this.advisor;
	}

	@Override
	public void storeAdvisor() {
		String errorMessage = getInvalidFieldsMessage();
		
		if (errorMessage != null) {
			throw new RuntimeException(errorMessage);
		}		
		advisorRepository.insertAdvisor(this.advisor);
	}

	@Override
	public String getInvalidFieldsMessage() {
		if (this.advisor.getName() == null
				|| this.advisor.getName().trim().isEmpty()) {
			return "O nome do coordenador deve ser preenchido.";
		}

		if (this.advisor.getName().length() > 128) {
			return "O nome do coordenador deve ser menor que 128 caracteres.";
		}

		if (this.advisor.getEmail() == null
				|| this.advisor.getEmail().trim().isEmpty()) {
			return "O e-mail do coordenador deve ser preenchido";
		}

		if (this.advisor.getEmail().length() > 128) {
			return "O e-mail do coordenador deve ser menor que 128 caracteres.";
		}
		return null;
	}

}
