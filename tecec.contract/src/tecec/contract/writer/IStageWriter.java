package tecec.contract.writer;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface IStageWriter {

	RuleViolation getCreationViolation(String name, Integer year);

	RuleViolation getUpdateViolation(String pKStage, String newName, Integer newYear);

	void createStage(String name, Integer year) throws RuleViolationException;

	void updateStage(String pKStage, String newName, Integer newYear) throws RuleViolationException;

	void deleteStage(String pKStage);

}
