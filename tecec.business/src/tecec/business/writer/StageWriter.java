package tecec.business.writer;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.repository.IStageRepository;
import tecec.contract.writer.IStageWriter;

import tecec.dto.Stage;

public class StageWriter implements IStageWriter {

	private IStageRepository stageRepository;

	public StageWriter(IStageRepository stageRepository) {
		if (stageRepository == null)
			throw new IllegalArgumentException("stageRepository");

		this.stageRepository = stageRepository;
	}

	@Override
	public RuleViolation getCreationViolation(String name, String year) {

		if (name == null || name.trim().isEmpty()) {
			return new RuleViolation("O nome do estágio deve ser preenchido.");
		} else {
			if (name.length() > 128) {
				return new RuleViolation(
						"O nome do estágio deve ser menor que 128 caracteres.");
			}
		}

		if (year == null || year.trim().isEmpty()) {
			return new RuleViolation("O ano do estágio deve ser preenchido.");
		} else {
			if (year.length() != 4) {
				return new RuleViolation(
						"O ano do estágio deve ter 4 caracteres.");
			}
		}

		Stage stage = stageRepository.getStageByNameAndYear(name, year);

		if (stage != null)
			return new RuleViolation(
					"Já existe outro estágio cadastrado com este nome e ano.");

		return null;
	}
	
	@Override
	public RuleViolation getUpdateViolation(Stage newStage) {

		Stage stage = this.stageRepository.getStageByPK(newStage.getpKStage());

		if (stage == null)
			return new RuleViolation(
					"O estágio selecionado não existe no banco de dados.");

		if (newStage.getName() == null || newStage.getName().trim().isEmpty())
			return new RuleViolation("O nome do estágio deve ser preenchido.");

		if (newStage.getYear() == null || newStage.getYear().trim().isEmpty()
				|| newStage.getYear().length() != 4) {
			return new RuleViolation("O ano do estágio é inválido.");
		}

		stage = this.stageRepository.getStageByNameAndYear(newStage.getName(),
				newStage.getYear());

		if (stage != null) {
			if (!stage.getpKStage().equals(newStage.getpKStage())) {
				return new RuleViolation(
						"Já existe outro estágio cadastrado com tais dados.");
			}
		}

		return null;
	}

	@Override
	public void createStage(String name, String year)
			throws RuleViolationException {

		RuleViolation violation = getCreationViolation(name, year);

		if (violation != null)
			throw new RuleViolationException(violation);

		Stage stage = new Stage();
		stage.setName(name);
		stage.setYear(year);
		this.stageRepository.insertStage(stage);
	}

	@Override
	public void updateStage(String pKStage, String newName, String newYear)
			throws RuleViolationException {

		RuleViolation violation = getCreationViolation(newName, newYear);
		if (violation != null)
			throw new RuleViolationException(violation);


		Stage stage = new Stage ();
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
