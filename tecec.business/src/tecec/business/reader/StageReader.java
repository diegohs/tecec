package tecec.business.reader;

import java.util.List;

import tecec.contract.reader.IStageReader;

import tecec.contract.repository.IStageRepository;

import tecec.dto.Stage;

public class StageReader implements IStageReader {
	
	private IStageRepository stageRepository;
	
	public StageReader (IStageRepository stageRepository) {
		this.stageRepository = stageRepository;
	}

	@Override
	public List<Stage> getStages(String nameFilter) {
		return stageRepository.getStages(nameFilter);
	}

	@Override
	public Stage getStageByPK(String pKStage) {
		return this.stageRepository.getStageByPK(pKStage);
	}

	@Override
	public List<Stage> getStagesByMonograph(String pKMonograph) {
		return this.stageRepository.getStagesByMonograph(pKMonograph);
	}

}
