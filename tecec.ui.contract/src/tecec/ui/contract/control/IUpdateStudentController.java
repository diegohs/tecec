package tecec.ui.contract.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface IUpdateStudentController {
	
	void setPKStudent (String pkStudent);
	void setStudentName (String name);
	void setStudentEmail (String email);
	
	String getStudentName ();
	String getStudentEmail ();
	
	void updateStudent () throws RuleViolationException;
	RuleViolation getUpdateViolation();

}
