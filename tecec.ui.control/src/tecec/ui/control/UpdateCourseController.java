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
	private String courseYear;
	private String courseTurn;
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
			this.setCourseTurn(course.getTurn());
			this.setCourseYear(course.getYear());
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
		
		this.courseWriter.updateCourse(this.pKCourse, this.courseName, this.courseTurn, this.courseYear);
	}

	@Override
	public void setCourseTurn(String turn) {
		String old = this.courseTurn;
		this.courseTurn = turn;
		
		super.notifyOfPropertyChange("courseTurn", old, turn);
		super.notifyOfPropertyChange("canUpdate", null, getCanUpdate());		
		
	}

	@Override
	public void setCourseYear(String year) {
		String old = this.courseYear;
		this.courseYear = year;
		
		super.notifyOfPropertyChange("courseYear", old, year);
		super.notifyOfPropertyChange("canUpdate", null, getCanUpdate());	
		
	}

	@Override
	public String getCourseTurn() {
		return this.courseTurn;
	}

	@Override
	public String getCourseYear() {
		return this.courseYear;
	}

	@Override
	public RuleViolation getUpdateViolation() {
		Course course = new Course ();
		course.setPKCourse(this.pKCourse);
		course.setName(this.courseName);
		course.setTurn(this.courseTurn);
		course.setYear(this.courseYear);
		return this.courseWriter.getUpdateViolation(course);
	}

	@Override
	public boolean getCanUpdate() {
		return this.courseName!= null && this.courseYear != null && this.courseTurn != null
				&& !this.courseName.trim().isEmpty() && !this.courseTurn.trim().isEmpty() &&
				!this.courseYear.trim().isEmpty();
	}
	
	
}
