package tecec.ui.contract.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface IUpdateActivityController {
	void setPKActivity(String pKActivity);
	
	void setActivityTitle(String title);

	String getActivityTitle();

	void setActivityDescription(String description);

	String getActivityDescription();

	void setActivityDueDate(String dueDate);

	String getActivityDueDate();

	RuleViolation getUpdateViolation();

	boolean getCanUpdate();

	void updateActivity() throws RuleViolationException;
}
