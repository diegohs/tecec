package tecec.ui.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.writer.IStudentWriter;

public class NewStudentController extends BaseController implements
	tecec.ui.contract.control.INewStudentController {
	
	private IStudentWriter studentWriter;
	private String studentName;
	private String studentEmail;
	
	public NewStudentController(IStudentWriter studentWriter) {
		if (studentWriter == null) {
			throw new IllegalArgumentException("studentWriter");
		}

		this.studentWriter = studentWriter;
	}

	@Override
	public String getStudentName() {
		return this.studentName;
	}

	@Override
	public void setStudentName(String name) {
		String oldValue = getStudentName();
		this.studentName = name;
		notifyOfPropertyChange("studentName", oldValue, name);
		
	}

	@Override
	public String getStudentEmail() {
		return this.studentEmail;
	}

	@Override
	public void setStudentEmail(String email) {
		String oldValue = getStudentEmail();
		this.studentEmail = email;
		notifyOfPropertyChange("studentEmail", oldValue, email);		
	}

	@Override
	public void createStudent() throws RuleViolationException {
		RuleViolation violation = getCreationViolation();

		if (violation != null) {
			throw new RuleViolationException(violation);
		}

		studentWriter.createStudent(this.studentName, this.studentEmail);

		setStudentName("");
		setStudentEmail("");
		
	}

	@Override
	public RuleViolation getCreationViolation() {
		return studentWriter.getCreationViolation(this.getStudentName(), this.getStudentEmail());
	}

}