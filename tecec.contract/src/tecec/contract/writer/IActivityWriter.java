package tecec.contract.writer;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.dto.Activity;

public interface IActivityWriter {
	void insertActivity(Activity activity) throws RuleViolationException;
	void updateActivity(Activity activity) throws RuleViolationException;
	void deleteActivity(String pKActivity);
	
	RuleViolation getInsertViolation(Activity activity);
	RuleViolation getUpdateViolation(Activity activity);
}
