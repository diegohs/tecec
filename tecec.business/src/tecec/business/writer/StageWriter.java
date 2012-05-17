package tecec.business.writer;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.repository.IStageRepository;
import tecec.contract.writer.IStageWriter;
import tecec.dto.Stage;


public class StageWriter implements IStageWriter {

	private IStageRepository stageRepository;
	
	public StageWriter (IStageRepository stageRepository) {
		if (stageRepository == null)
			throw new IllegalArgumentException("stageRepository");
		this.stageRepository = stageRepository;
	}
	
	@Override
	public RuleViolation getCreationViolation(String name, int year) {
		if (name == null || name.trim().isEmpty()) {
			return new RuleViolation ("O nome do estágio deve ser preenchido.");
		} else {
			if (name.length() > 128)
				return new RuleViolation ("O nome do estágio deve ser menor que 128 caracteres.");
		}
		
		/* Eu acho que pode ter status iguais, mas não pode ter no mesmo ano
		 * 
		 *  Por: Bruno
		 *  */
		Stage stage = stageRepository.getStageByName(name);		
		if (stage != null && stage.getYear()==year) {
			return new RuleViolation ("Já existe outro estágio cadastrado com os mesmos dados: nome e ano.");
		}
		
		return null;
	}

	@Override
	public RuleViolation getUpdateViolation(String pKStage, String newName,
			int newYear) {
		
		Stage stage = this.stageRepository.getStageByPK(pKStage);
		
		if (stage == null)
			return new RuleViolation ("O estágio selecionado não existe no banco de dados.");
		
		if (newName == null || newName.trim().isEmpty())
			return new RuleViolation ("O nome do estágio deve ser preenchido.");
		
		
		stage = this.stageRepository.getStageByName(newName);
		
		if (stage!=null && stage.getYear()!=newYear) {
			if (!stage.getpKStage().equals(pKStage)) {
				return new RuleViolation ("Já existe outro estágio cadastrado com este nome e ano.");
			}		
		}
		
		return null;		
	}

	@Override
	public void createStage(String name, int year)
			throws RuleViolationException {
		RuleViolation violation = getCreationViolation (name,year);
		if (violation != null)
			throw new RuleViolationException (violation);
		Stage stage = new Stage();
		stage.setName(name);
		stage.setYear(year);
		this.stageRepository.insertStage(stage);
		
	}

	@Override
	public void updateStage(String pKStage, String newName, int newYear) throws RuleViolationException {
			RuleViolation violation = getUpdateViolation (pKStage, newName, newYear);
			if (violation != null)
				throw new RuleViolationException (violation);
			
			Stage stage = new Stage();
			stage.setpKStage(pKStage);
			stage.setName(newName);
			stage.setYear(newYear);
			
			this.stageRepository.updateStage(stage);
	}

	@Override
	public void deleteStage(String pKStage) {
		this.stageRepository.deleteStage(pKStage);
		
	}
}
