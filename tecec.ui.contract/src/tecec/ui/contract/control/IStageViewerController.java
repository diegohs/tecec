package tecec.ui.contract.control;

import java.util.List;

import tecec.dto.Stage;

public interface IStageViewerController {
	
	void setNameFilter (String nameFilter);
	String getNameFilter ();
	
	void setSelectedStage (Stage stage);
	Stage getSelectedStage ();
	
	List <Stage> getStages ();
	
	void deleteStage ();
	
	boolean getCanUpdateStage ();
	boolean getCanDeleteStage ();
	
	void showNewStageUI ();
	void newUpdateStageUI ();

}
