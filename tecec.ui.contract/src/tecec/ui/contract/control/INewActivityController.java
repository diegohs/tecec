package tecec.ui.contract.control;

import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.dto.Stage;

public interface INewActivityController extends IRefreshable  {
	void setActivityTitle(String title);

	String getActivityTitle();

	void setActivityDescription(String description);

	String getActivityDescription();

	void setActivityDueDate(String dueDate);

	String getActivityDueDate();
	
	List<Stage> getStages();

	Stage getSelectedStage();

	void setSelectedStage(Stage stage);

	void setSelectedStageIndex(int i);

	int getSelectedStageIndex();

	RuleViolation getInsertViolation();

	boolean getCanInsert();

	void insertActivity() throws RuleViolationException;
}
