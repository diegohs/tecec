package tecec.ui.contract.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface IUpdateDocumentationController {
	
	void setPKDocumentation (String pKDocumentation);
	void setDocumentationFileName (String fileName);
	void setDocumentationData (byte[] data);
	
	String getDocumentationFileName ();
	byte[] getDocumentationData ();
	
	boolean getCanUpdate ();
	void updateDocumentation () throws RuleViolationException;
	
	RuleViolation getUpdateViolation ();


}
