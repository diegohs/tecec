package tecec.contract.writer;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.dto.Stage;

public interface IStageWriter {
	
	RuleViolation getCreationViolation(String name, String year);

	RuleViolation getUpdateViolation(Stage newStage);

	void createStage(String name, String year) throws RuleViolationException;

	void updateStage(String pKStage, String newName, String newYear)
			throws RuleViolationException;
	
	void deleteStage(String pKStage);	
}
