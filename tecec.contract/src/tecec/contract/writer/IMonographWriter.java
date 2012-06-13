package tecec.contract.writer;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.dto.Monograph;

public interface IMonographWriter {
	RuleViolation getCreationViolation (Monograph newMonograph);
	
	RuleViolation getUpdateViolation (Monograph newMonograph);
	
	void createMonograph (Monograph newMonograph) throws RuleViolationException;
	
	void updateMonograph (Monograph newMonograph)	throws RuleViolationException;
	
	void insertMonographStage(String pKMonography, String pKStage);
	void deleteMonographStage(String pKMonography, String pKStage);
	
	void deleteMonograph (String pKMonograph);
}
