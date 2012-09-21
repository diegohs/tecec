package tecec.ui.contract.control;

import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.dto.Stage;

public interface IStageViewerController extends IViewerController  {
	
	void setNameFilter (String nameFilter);
	String getNameFilter ();
	
	void setSelectedStage (Stage stage);
	Stage getSelectedStage ();
	
	List <Stage> getStages ();
	
	void deleteStage() throws RuleViolationException;
	
	boolean getCanUpdateStage ();
	boolean getCanDeleteStage ();
	
	void showNewStageUI ();
	void newUpdateStageUI ();
	
	RuleViolation getDeletionViolation();

}
