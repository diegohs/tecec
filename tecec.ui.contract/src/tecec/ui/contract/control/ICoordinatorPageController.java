package tecec.ui.contract.control;

import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.dto.Documentation;
import tecec.dto.record.ActivityRecord;

public interface ICoordinatorPageController extends IViewerController {	
	List<ActivityRecord> getActivities();
	ActivityRecord getSelectedActivity();
	void setSelectedActivity(ActivityRecord activity);
	
	RuleViolation getUpdateViolation();
	void update() throws RuleViolationException;
	RuleViolation getDeletionViolation();
	void delete() throws RuleViolationException;
	
	boolean getCanUpdate();
	boolean getCanDelete();
	boolean getCanDownloadFile();
	Documentation getSelectedHandInFile();
}
