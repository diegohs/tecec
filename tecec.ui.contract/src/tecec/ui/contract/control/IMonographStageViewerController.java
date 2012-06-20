package tecec.ui.contract.control;

import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.dto.Stage;

public interface IMonographStageViewerController extends IRefreshable  {
	void setMonograph(String pKMonograph);
		
	List<Stage> getCorrelatedStages();
	List<Stage> getStages();
	
	Stage getSelectedStage();
	void setSelectedStage(Stage stage);
	Stage getSelectedCorrelatedStage();
	void setSelectedCorrelatedStage(Stage stage);
	
	RuleViolation getInsertViolation();
	RuleViolation getDeleteViolation();
	
	boolean getCanInsert();
	boolean getCanDelete();
	
	void insertMonographStage() throws RuleViolationException;
	void deleteMonographStage() throws RuleViolationException;
	
	void commit() throws RuleViolationException;
}
