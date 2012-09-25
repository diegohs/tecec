package tecec.ui.control;

import java.util.ArrayList;
import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.reader.ICourseReader;
import tecec.contract.reader.IStudentReader;
import tecec.contract.reporting.ICourseReporter;
import tecec.contract.writer.ICourseWriter;
import tecec.dto.Course;
import tecec.dto.Student;
import tecec.dto.record.CourseStudentRecord;
import tecec.ui.contract.control.ICourseViewerController;
import tecec.ui.contract.view.INewCourseUI;
import tecec.ui.contract.view.IUpdateCourseUI;

public class CourseViewerController extends BaseViewerController implements ICourseViewerController {

	private String nameFilter;
	private Course selectedCourse;
	private ICourseReader courseReader;
	private IStudentReader studentReader;
	private ICourseWriter courseWriter;
	
	private INewCourseUI newCourseUI;
	private IUpdateCourseUI updateCourseUI;
	private ICourseReporter reporter;

	public CourseViewerController(ICourseReader courseReader, IStudentReader studentReader, ICourseWriter courseWriter, INewCourseUI newCourseUI, IUpdateCourseUI updateCourseUI, ICourseReporter reporter) {
		super(reporter);
		
		this.courseReader = courseReader;
		this.studentReader = studentReader;
		this.courseWriter = courseWriter;
		this.newCourseUI = newCourseUI;
		this.updateCourseUI = updateCourseUI;
		this.reporter = reporter;
	}
	
	@Override
	public void setNameFilter(String nameFilter) {		
		this.nameFilter = nameFilter;
		
		super.notifyOfPropertyChange("nameFilter");
		super.notifyOfPropertyChange("courses");
	}

	@Override
	public String getNameFilter() {
		return this.nameFilter;
	}

	@Override
	public void setSelectedCourse(Course course) {		
		this.selectedCourse = course;

		super.notifyOfPropertyChange("selectedCourse");
		super.notifyOfPropertyChange("canUpdateCourse");
		super.notifyOfPropertyChange("canDeleteCourse");
	}

	@Override
	public Course getSelectedCourse() {
		return this.selectedCourse;
	}

	@Override
	public List<Course> getCourses() {
		List<Course> courses = this.courseReader.getCourses(this.nameFilter);
		
		return courses;
	}

	@Override
	public void showNewCourseUI() {
		this.newCourseUI.refresh();
		this.newCourseUI.setVisible(true);
		
		super.notifyOfPropertyChange("courses");
	}

	@Override
	public void showUpdateCourseUI() {
		this.updateCourseUI.setpKCourse(this.selectedCourse.getPKCourse());
		this.updateCourseUI.setVisible(true);
		
		super.notifyOfPropertyChange("courses");
	}

	@Override
	public boolean getCanUpdateCourse() {
		return this.selectedCourse != null;
	}

	@Override
	public boolean getCanDeleteCourse() {
		return this.selectedCourse != null;
	}

	@Override
	public void deleteCourse() throws RuleViolationException {
		RuleViolation violation = getDeletionViolation();
		
		if (violation != null) {
			throw new RuleViolationException(violation);
		}
		
		this.courseWriter.deleteCourse(this.selectedCourse.getPKCourse());
		
		super.notifyOfPropertyChange("courses");
	}

	@Override
	public void refresh() {
		setNameFilter("");
	}

	@Override
	public RuleViolation getDeletionViolation() {
		boolean hasStudents = courseReader.doesCourseHaveStudents(this.selectedCourse.getPKCourse());
		
		if (hasStudents) {
			return new RuleViolation("Não é possível excluir um curso que possui estudantes cadastrados.");
		}
		
		return null;
	}

	@Override
	protected List<String[]> getExportSource() {
		List<Course> courses = this.getCourses();
		
		List<CourseStudentRecord> records = new ArrayList<CourseStudentRecord>();
		
		for (Course course : courses) {
			List<Student> students = studentReader.getStudentsByCourse(course.getPKCourse());
					
			for (Student student : students) {
				CourseStudentRecord record = new CourseStudentRecord();
				
				record.setCourse(course);
				record.setStudent(student);
				
				records.add(record);
			}
		}
		
		return this.reporter.format(records);
	}
}
