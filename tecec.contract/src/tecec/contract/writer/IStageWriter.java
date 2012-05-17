package tecec.contract.writer;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface IStageWriter {

	RuleViolation getCreationViolation(String name, int year);

	RuleViolation getUpdateViolation(String pKStage, String newName, int newYear);

	void createStage(String name, int year) throws RuleViolationException;

	void updateStage(String pKStage, String newName, int newYear) throws RuleViolationException;

	void deleteStage(String pKStage);

}
