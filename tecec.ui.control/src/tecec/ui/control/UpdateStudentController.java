package tecec.ui.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.reader.IStudentReader;
import tecec.contract.writer.IStudentWriter;
import tecec.dto.Student;
import tecec.ui.contract.control.IUpdateStudentController;

public class UpdateStudentController extends BaseController implements
		IUpdateStudentController {

	private String pkStudent;
	private String studentName;
	private String studentEmail;
	private IStudentWriter studentWriter;
	private IStudentReader studentReader;

	public UpdateStudentController(IStudentWriter studentWriter,
			IStudentReader studentReader) {
		this.studentWriter = studentWriter;
		this.studentReader = studentReader;
	}

	@Override
	public void setPKStudent(String pkStudent) {
		this.pkStudent = pkStudent;
		Student student = this.studentReader.getStudentByPk(pkStudent);
		if (student != null)
			this.setStudentName(student.getName());
	}

	@Override
	public void setStudentName(String name) {
		String old = this.studentName;

		this.studentName = name;

		super.notifyOfPropertyChange("studentName", old, name);

	}

	@Override
	public void setStudentEmail(String email) {
		String old = this.studentEmail;
		this.studentEmail = email;
		super.notifyOfPropertyChange("studentEmail", old, email);
	}

	@Override
	public String getStudentName() {
		return this.studentName;
	}

	@Override
	public String getStudentEmail() {
		return this.studentEmail;
	}

	@Override
	public void updateStudent() throws RuleViolationException {
		RuleViolation violation = getUpdateViolation();

		if (violation != null) {
			throw new RuleViolationException(violation);
		}
		this.studentWriter.updateStudent(this.pkStudent, this.studentName,
				this.studentEmail);

	}

	@Override
	public RuleViolation getUpdateViolation() {
		return this.studentWriter.getUpdateViolation(this.pkStudent,
				this.studentName, this.studentEmail);
	}
}