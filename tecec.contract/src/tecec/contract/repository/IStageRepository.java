package tecec.contract.repository;

import java.util.List;


import tecec.dto.Stage;

public interface IStageRepository {
	
	void insertStage (Stage stage);
	void updateStage (Stage stage);
	
	void deleteStage (String pKStage);
	
	Stage getStageByNameAndYear (String name, String year);
	Stage getStageByPK (String pKStage);

	List <Stage> getStages (String nameFilter);
	
	List<Stage> getStagesByMonograph(String pKMonograph);
}
