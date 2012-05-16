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
	
	@Override
	public RuleViolation getCreationViolation(String title) {
		if(title == null || title.trim().isEmpty()){
			return new RuleViolation ("O título da monografia deve ser preenchido.");
		} else {
			if(title.length() > 512)
				return new RuleViolation ("O título da monografia deve ser menor que 512 caracteres.");			
		}
		
		Monograph monograph = monograhRepository.getMonographByTitle(title);
		
		if (monograph != null)
			return new RuleViolation ("Já existe outra monografia cadastrada com a mesma descrição.");
		
		return null;
	}

	@Override
	public RuleViolation getUpdateViolation(String pKMonograph, String newTitle) {
		Monograph monograph = this.monograhRepository.getMonographByPK(pKMonograph);
		
		if (monograph == null)
			return new RuleViolation ("A monografia selecionada não existe no banco de dados.");
		
		if (newTitle == null || newTitle.trim().isEmpty())
			return new RuleViolation ("O novo título da monografia deve ser preenchida.");
		
		monograph = this.monograhRepository.getMonographByTitle(newTitle);
		
		if(monograph != null){
			if(!monograph.getpKMonograph().equals(pKMonograph))
				return new RuleViolation ("Já existe outra monografia cadastrada com esta descrição.");
		}
		
		return null;
	}

	@Override
	public void createMonograph(String title)
			throws RuleViolationException {
		RuleViolation violation = getCreationViolation(title);
		
		if(violation != null)
			throw new RuleViolationException(violation);
		
		Monograph monograph = new Monograph();
		monograph.setTitle(title);
		this.monograhRepository.insertMonograph(monograph);

	}

	@Override
	public void updateMonograph(String pKMonograph, String newTitle)
			throws RuleViolationException {
		RuleViolation violation = getUpdateViolation(pKMonograph, newTitle);
		
		if(violation != null)
			throw new RuleViolationException(violation);
		
		Monograph monograph = new Monograph();
		monograph.setpKMonograph(pKMonograph);
		monograph.setTitle(newTitle);
		
		this.monograhRepository.updateMonograph(monograph);
	}

	@Override
	public void deleteMonograph(String pKMonograph) {
		this.monograhRepository.deleteMonograph(pKMonograph);
	}

}
