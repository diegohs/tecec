package tecec.contract.writer;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

import tecec.dto.Documentation;

public interface IDocumentationWriter {
	
	RuleViolation getCreationViolation (String fileName, byte[] data);
	
	RuleViolation getUpdateViolation (Documentation newDocumentation);
	
	void createDocumentation (String fileName, byte[] data)  throws RuleViolationException;
	
	void updateDocumentation (String pKDocumentation, String newFileName, byte[] newData) 
			throws RuleViolationException;
	
	void deleteDocumentation (String pKDocumentation);

}
