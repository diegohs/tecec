package tecec.ui.contract.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

public interface INewStudentController {
	String getStudentName ();
	void setStudentName (String name);
	
	String getStudentEmail ();
	void setStudentEmail (String email);
	
	void createStudent () throws RuleViolationException;
	RuleViolation getCreationViolation();
}

