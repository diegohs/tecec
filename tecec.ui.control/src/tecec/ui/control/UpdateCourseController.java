package tecec.ui.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.reader.ICourseReader;
import tecec.contract.writer.ICourseWriter;
import tecec.dto.Course;
import tecec.ui.contract.control.IUpdateCourseController;

public class UpdateCourseController extends BaseController implements IUpdateCourseController {

	private String pKCourse;
	private String courseName;
	private ICourseWriter courseWriter;
	private ICourseReader courseReader;
	
	public UpdateCourseController(ICourseWriter courseWriter, ICourseReader courseReader) {
		this.courseWriter = courseWriter;
		this.courseReader = courseReader;
	}
	
	@Override
	public void setPKCourse(String pKCourse) {
		this.pKCourse = pKCourse;
		
		Course course = this.courseReader.getCourseByPK(pKCourse);
		
		if (course != null) {
			this.setCourseName(course.getName());
		}
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
