package tecec.business.writer;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.repository.IMonographRepository;
import tecec.contract.writer.IMonographWriter;
import tecec.dto.Monograph;

public class MonographWriter implements IMonographWriter {
	
	private IMonographRepository monograhRepository;

	public MonographWriter(IMonographRepository monographRepository){
		if(monographRepository == null)
			throw new IllegalArgumentException("monographRepository");
		this.monograhRepository = monographRepository;
	}
	
	/*Rules*/
	
	@Override
	public RuleViolation getCreationViolation(Monograph newMonograph) {
		if(newMonograph.getTitle() == null || newMonograph.getTitle().trim().isEmpty()){
			return new RuleViolation ("O título da monografia deve ser preenchido.");
		} else {
			if(newMonograph.getTitle().length() > 512)
				return new RuleViolation ("O título da monografia deve ser menor que 512 caracteres.");			
		}
		
		if (newMonograph.getfKAdvisor() == null) {
			return new RuleViolation("Um orientador deve ser selecionado para a monografia.");
		}

		if (newMonograph.getfKArea() == null) {
			return new RuleViolation("Uma �rea deve ser selecionada para a monografia.");
		}

		if (newMonograph.getfKCourse() == null) {
			return new RuleViolation("Um curso deve ser selecionado para a monografia.");
		}
		
		if (newMonograph.getfKStudent() == null) {
			return new RuleViolation("Um aluno deve ser selecionado para a monografia.");
		}
		
		if (newMonograph.getfKStatus() == null) {
			return new RuleViolation("Um status deve ser selecionado para a monografia.");
		}
		
		Monograph monograph = monograhRepository.getMonographByTitle(newMonograph.getTitle());
		
		if (monograph != null)
			return new RuleViolation ("Já existe outra monografia cadastrada com a mesma descrição.");
		
		return null;
	}

	@Override
	public RuleViolation getUpdateViolation(Monograph newMonograph) {
		Monograph monograph = this.monograhRepository.getMonographByPK(newMonograph.getpKMonograph());
		
		if (monograph == null)
			return new RuleViolation ("A monografia selecionada não existe no banco de dados.");
		
		if (newMonograph.getTitle() == null || newMonograph.getTitle().trim().isEmpty())
			return new RuleViolation ("O novo título da monografia deve ser preenchida.");
		
		monograph = this.monograhRepository.getMonographByTitle(newMonograph.getTitle());
		
		if(monograph != null){
			if(!monograph.getpKMonograph().equals(newMonograph.getpKMonograph()))
				return new RuleViolation ("Já existe outra monografia cadastrada com esta descrição.");
		}
		
		return null;
	}
	
	/*End of rules*/
	
	/*Data settings methods*/

	@Override
	public void createMonograph(Monograph newMonograph) throws RuleViolationException {
		RuleViolation violation = getCreationViolation(newMonograph);
		
		if(violation != null)
			throw new RuleViolationException(violation);
		
		Monograph monograph = new Monograph();
		monograph.setTitle(newMonograph.getTitle());
		monograph.setfKAdvisor(newMonograph.getfKAdvisor());
		monograph.setfKArea(newMonograph.getfKArea());
		monograph.setfKCoadvisor(newMonograph.getfKCoadvisor());
		monograph.setfKCourse(newMonograph.getfKCourse());
		monograph.setfKStatus(newMonograph.getfKStatus());
		monograph.setfKStudent(newMonograph.getfKStudent());
		this.monograhRepository.insertMonograph(monograph);

	}

	@Override
	public void updateMonograph(Monograph newMonograph) throws RuleViolationException {
		RuleViolation violation = getUpdateViolation(newMonograph);
		
		if(violation != null)
			throw new RuleViolationException(violation);
		
		Monograph monograph = new Monograph();
		monograph.setpKMonograph(newMonograph.getpKMonograph());
		monograph.setTitle(newMonograph.getTitle());
		monograph.setfKAdvisor(newMonograph.getfKAdvisor());
		monograph.setfKArea(newMonograph.getfKArea());
		monograph.setfKCoadvisor(newMonograph.getfKCoadvisor());
		monograph.setfKCourse(newMonograph.getfKCourse());
		monograph.setfKStatus(newMonograph.getfKStatus());
		monograph.setfKStudent(newMonograph.getfKStudent());
		this.monograhRepository.updateMonograph(monograph);
	}

	@Override
	public void deleteMonograph(String pKMonograph) {
		this.monograhRepository.deleteMonograph(pKMonograph);
	}

	@Override
	public void insertMonographStage(String pKMonography, String pKStage) {
		this.monograhRepository.insertMonographStage(pKMonography, pKStage);
	}

	@Override
	public void deleteMonographStage(String pKMonography, String pKStage) {
		this.monograhRepository.deleteMonographStage(pKMonography, pKStage);
	}
}
