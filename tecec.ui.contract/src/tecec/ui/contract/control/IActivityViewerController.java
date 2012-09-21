package tecec.ui.contract.control;

import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.dto.Activity;

public interface IActivityViewerController extends IViewerController {
	void setTitleFilter(String filter);
	String getTitleFilter();
	
	boolean getCanUpdate();
	boolean getCanDelete();
	
	void showNewActivityUI();
	void showUpdateActivityUI();
	
	void deleteActivity() throws RuleViolationException;
	
	RuleViolation getDeletionViolation();
	
	List<Activity> getActivities();

	void setSelectedActivity(Activity activity);
	Activity getSelectedActivity();
}
