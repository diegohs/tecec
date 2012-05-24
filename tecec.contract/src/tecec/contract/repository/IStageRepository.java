package tecec.contract.repository;

import java.util.List;

import tecec.dto.Stage;

public interface IStageRepository {
	void insertStage (Stage stage);
	void updateStage (Stage stage);
	void deleteStage (String pKStage);
	Stage getStageByName (String name);
	Stage getStageByPK (String pKStage);
	Stage getStageByYear (Integer year);	
	List <Stage> getStages (String nameFilter);
}
