package tecec.ui.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.writer.ICourseWriter;

public class NewCourseController extends BaseController implements
		tecec.ui.contract.control.INewCourseController {
	
	private ICourseWriter courseWriter;
	private String courseName;
	private String courseTurn;
	private String courseYear;

	public NewCourseController(ICourseWriter courseWriter) {
		if (courseWriter == null) {
			throw new IllegalArgumentException("courseWriter");
		}

		this.courseWriter = courseWriter;
	}

	@Override
	public String getCourseName() {
		return this.courseName;
	}

	@Override
	public void setCourseName(String name) {		
		this.courseName = name;
		notifyOfPropertyChange("courseName");		

	}

	@Override
	public String getCourseTurn() {
		return this.courseTurn;
	}

	@Override
	public void setCourseTurn(String turn) {
		this.courseTurn = turn;
		notifyOfPropertyChange("courseTurn");
		
	}

	@Override
	public String getCourseYear() {
		return this.courseYear;
	}

	@Override
	public void setCourseYear(String year) {
		this.courseYear = year;
		notifyOfPropertyChange("courseYear");
		
	}

	@Override
	public void createCourse() throws RuleViolationException {
		RuleViolation violation = getCreationViolation ();
		if (violation != null)
			throw new RuleViolationException (violation);
		
		courseWriter.createCourse(this.courseName, this.courseTurn, this.courseYear);
			
		refresh();
	}

	@Override
	public RuleViolation getCreationViolation() {
		return courseWriter.getCreationViolation(this.courseName, this.courseTurn, this.courseYear);
	}

	@Override
	public void refresh() {
		setCourseName ("");
		setCourseYear ("");
		setCourseTurn ("");	
	}
	
}
