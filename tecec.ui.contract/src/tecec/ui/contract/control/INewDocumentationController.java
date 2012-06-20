package tecec.ui.contract.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface INewDocumentationController extends IRefreshable  {
	
	String getDocumentationFileName ();
	void setDocumentationFileName (String fileName);
	
	byte[] getDocumentationData ();
	void setDocumentationData (byte[] data);
	
	void createDocumentation () throws RuleViolationException;
	RuleViolation getCreationViolation ();


}
