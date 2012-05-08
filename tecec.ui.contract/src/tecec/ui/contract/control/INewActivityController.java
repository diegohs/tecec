package tecec.ui.contract.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface INewActivityController {
	void setActivityTitle(String title);

	String getActivityTitle();

	void setActivityDescription(String description);

	String getActivityDescription();

	void setActivityDueDate(String dueDate);

	String getActivityDueDate();

	RuleViolation getInsertViolation();

	boolean getCanInsert();

	void insertActivity() throws RuleViolationException;
}
