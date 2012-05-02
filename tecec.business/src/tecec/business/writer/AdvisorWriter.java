package tecec.business.writer;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.repository.IAdvisorRepository;
import tecec.contract.writer.IAdvisorWriter;
import tecec.dto.Advisor;

public class AdvisorWriter implements IAdvisorWriter {

	private IAdvisorRepository advisorRepository;

	public AdvisorWriter(IAdvisorRepository advisorRepository) {
		if (advisorRepository == null)
			throw new IllegalArgumentException("advisorRepository");
		this.advisorRepository = advisorRepository;
	}

	@Override
	public RuleViolation getUpdateViolation(String pkAdvisor, String newName,
			String email) {
		Advisor advisor = this.advisorRepository.getAdvisorByPk(pkAdvisor);
		
		if (advisor == null)
			return new RuleViolation ("O orientador selecionado não existe no banco de dados.");
		
		if (newName == null || newName.trim().isEmpty()) 
			return new RuleViolation("O nome do orientador deve ser preenchido.");
		
		if (email == null || email.trim().isEmpty())
			return new RuleViolation("O e-mail do orientador deve ser preenchido.");
		
		advisor = this.advisorRepository.getAdvisorByEmail(email);
		
		if (advisor != null) {
			if (!advisor.getPKAdvisor().equals(pkAdvisor)) {
				return new RuleViolation ("Já existe outro orientador cadastrado com este e-mail.");
			}
		}
		
		return null;
	}

	@Override
	public void updateAdvisor(String pkAdvisor, String newName, String email)
			throws RuleViolationException {
		
		RuleViolation violation = getUpdateViolation(pkAdvisor, newName,email);
		if (violation != null)
			throw new RuleViolationException (violation);
		
		Advisor advisor = new Advisor ();
		advisor.setPKAdvisor(pkAdvisor);
		advisor.setName(newName);
		advisor.setEmail(email);
		
		this.advisorRepository.updateAdvisor(advisor);
	}
	

	@Override
	public void createAdvisor(String name, String email)
			throws RuleViolationException {
		RuleViolation violation = getCreationViolation(name, email);

		if (violation != null)
			throw new RuleViolationException(violation);
		Advisor advisor = new Advisor();
		advisor.setName(name);
		advisor.setEmail(email);
		this.advisorRepository.insertAdvisor(advisor);
	}

	@Override
	public RuleViolation getCreationViolation(String name, String email) {

		if (name == null || name.trim().isEmpty()) {
			return new RuleViolation(
					"O nome do orientador deve ser preenchido.");
		} else {
			if (name.length() > 128) {
				return new RuleViolation(
						"O nome do orientador deve ser menor que 128 caracteres.");
			}
		}

		if (email == null || email.trim().isEmpty()) {
			return new RuleViolation(
					"O e-mail do orientador deve ser preenchido.");
		} else {
			if (email.length() > 128) {
				return new RuleViolation(
						"O e-mail do orientador deve ser menor que 128 caracteres.");
			}
		}

		Advisor advisorName = advisorRepository.getAdvisorByName(name);
		Advisor advisorEmail = advisorRepository.getAdvisorByEmail(email);

		if (advisorName != null && advisorEmail != null) {
			return new RuleViolation(
					"Já existe outro orientador cadastrado com o mesmo nome e e-mail.");
		}

		/* Nao pode ter duas pessoas com o mesmo e-mail, acho que devemos permitir nome
		 * pois eu já estudei com pessoas com nomes idênticos, acontece*/
		if (advisorEmail != null) {
			return new RuleViolation(
					"Já existe outro orientador cadastrado com o mesmo e-mail.");
		}

		return null;
	}

}
