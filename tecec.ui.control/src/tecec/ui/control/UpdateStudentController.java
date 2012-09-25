package tecec.ui.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.reader.IStudentReader;
import tecec.contract.writer.IStudentWriter;
import tecec.dto.Student;
import tecec.ui.contract.control.IUpdateStudentController;
import tecec.ui.contract.view.IStudentCourseViewerUI;

public class UpdateStudentController extends BaseController implements
		IUpdateStudentController {

	private String pkStudent;
	private String studentName;
	private String studentEmail;
	private IStudentWriter studentWriter;
	private IStudentReader studentReader;
	private IStudentCourseViewerUI studentCourseViewerUI;

	public UpdateStudentController(IStudentWriter studentWriter,
			IStudentReader studentReader, IStudentCourseViewerUI studentCourseViewerUI) {
		this.studentWriter = studentWriter;
		this.studentReader = studentReader;
		this.studentCourseViewerUI = studentCourseViewerUI;
	}

	@Override
	public void setPKStudent(String pkStudent) {
		this.pkStudent = pkStudent;
		
		Student student = this.studentReader.getStudentByPk(pkStudent, "");

		this.setStudentName(student.getName());
		this.setStudentEmail(student.getEmail());
	}

	@Override
	public void setStudentName(String name) {
		this.studentName = name;

		super.notifyOfPropertyChange("studentName");
		super.notifyOfPropertyChange("canUpdate");
	}

	@Override
	public void setStudentEmail(String email) {
		this.studentEmail = email;

		super.notifyOfPropertyChange("studentEmail");
		super.notifyOfPropertyChange("canUpdate");
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

		refresh();
	}

	@Override
	public RuleViolation getUpdateViolation() {
		return this.studentWriter.getUpdateViolation(this.pkStudent,
				this.studentName, this.studentEmail);
	}

	@Override
	public boolean getCanUpdate() {
		return this.studentEmail != null && this.studentName != null && !this.studentEmail.isEmpty() && !this.studentName.isEmpty();
	}

	@Override	
	public void showStudentCourseUI() {
		this.studentCourseViewerUI.setPKStudent(this.pkStudent);
		this.studentCourseViewerUI.setVisible(true);
	}

	@Override
	public void refresh() {
		setPKStudent(this.pkStudent);
	}
}