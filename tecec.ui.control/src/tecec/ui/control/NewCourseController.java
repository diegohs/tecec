package tecec.ui.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.writer.ICourseWriter;

public class NewCourseController extends BaseController implements
		tecec.ui.contract.INewCourseController {
	private ICourseWriter courseWriter;
	private String courseName;

	public NewCourseController(ICourseWriter courseWriter) {
		if (courseWriter == null) {
			throw new IllegalArgumentException("courseWriter");
		}

		this.courseWriter = courseWriter;
	}

	@Override
	public void createCourse() throws RuleViolationException {
		RuleViolation violation = getCreationViolation();

		if (violation != null) {
			throw new RuleViolationException(violation);
		}

		courseWriter.createCourse(this.courseName);

		setCourseName("");
	}

	@Override
	public String getCourseName() {
		return this.courseName;
	}

	@Override
	public void setCourseName(String name) {
		String oldValue = getCourseName();

		this.courseName = name;

		notifyOfPropertyChange("courseName", oldValue, name);
	}

	@Override
	public RuleViolation getCreationViolation() {
		return courseWriter.getCreationViolation(this.getCourseName());
	}

}
