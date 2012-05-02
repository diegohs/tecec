package tecec.contract.writer;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface IAdvisorWriter {
	RuleViolation getCreationViolation(String name, String email);

	RuleViolation getUpdateViolation(String pkAdvisor, String newName,
			String email);

	void createAdvisor(String name, String email) throws RuleViolationException;

	void updateAdvisor(String pkAdvisor, String newName, String email)
			throws RuleViolationException;
	
	void deleteAdvisor (String pkAdvisor);	

}
