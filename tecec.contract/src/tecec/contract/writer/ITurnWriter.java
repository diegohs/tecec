package tecec.contract.writer;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface ITurnWriter {
	RuleViolation getCreationViolation(String name);

	RuleViolation getUpdateViolation(String pKTurn, String newName);

	void createTurn(String name) throws RuleViolationException;

	void updateTurn(String pKTurn, String newName)
			throws RuleViolationException;
	void deleteTurn(String pKTurn);

}
