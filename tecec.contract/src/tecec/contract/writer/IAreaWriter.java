package tecec.contract.writer;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface IAreaWriter {
	RuleViolation getCreationViolation(tecec.dto.Area pNewArea);

	RuleViolation getUpdateViolation(tecec.dto.Area pArea);

	RuleViolation getDeletionViolation(String pKArea);

	void createArea(tecec.dto.Area pNewArea)
			throws RuleViolationException;

	void updateArea(tecec.dto.Area pArea)
			throws RuleViolationException;

	void deleteArea(String pKArea) throws RuleViolationException;
}
