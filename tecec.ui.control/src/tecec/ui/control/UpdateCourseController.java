package tecec.ui.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.writer.ICourseWriter;
import tecec.ui.contract.IUpdateCourseController;

public class UpdateCourseController extends BaseController implements IUpdateCourseController {

	private String pKCourse;
	private String courseName;
	private ICourseWriter courseWriter;
	
	public UpdateCourseController(ICourseWriter courseWriter) {
		this.courseWriter = courseWriter;
	}
	
	@Override
	public void setPKCourse(String pKCourse) {
		this.pKCourse = pKCourse;
	}

	@Override
	public void setCourseName(String name) {
		String old = this.courseName;
		
		this.courseName = name;
		
		super.notifyOfPropertyChange("courseName", old, name);
	}

	@Override
	public String getCourseName() {
		return this.courseName;
	}

	@Override
	public void updateCourse() throws RuleViolationException {
		RuleViolation violation = getUpdateViolation();
		
		if (violation != null) {
			throw new RuleViolationException(violation);
		}
		
		this.courseWriter.updateCourse(this.pKCourse, this.courseName);
	}

	@Override
	public RuleViolation getUpdateViolation() {
		return this.courseWriter.getUpdateViolation(this.pKCourse, this.courseName);
	}

}
