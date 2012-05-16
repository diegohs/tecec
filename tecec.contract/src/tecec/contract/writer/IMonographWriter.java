package tecec.contract.writer;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface IMonographWriter {
	RuleViolation getCreationViolation (String title);
	
	RuleViolation getUpdateViolation (String pKMonograph, String newTitle);
	
	void createMonograph (String title) throws RuleViolationException;
	
	void updateMonograph (String pKMonograph, String newTitle)
		throws RuleViolationException;
	
	void deleteMonograph (String pKMonograph);
}
