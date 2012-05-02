package tecec.ui.control;

import java.util.List;

import tecec.contract.reader.ICourseReader;
import tecec.contract.writer.ICourseWriter;
import tecec.dto.Course;
import tecec.ui.contract.control.ICourseViewerController;
import tecec.ui.contract.view.INewCourseUI;
import tecec.ui.contract.view.IUpdateCourseUI;

public class CourseViewerController extends BaseController implements ICourseViewerController {

	private String nameFilter;
	private Course selectedCourse;
	private ICourseReader courseReader;
	private ICourseWriter courseWriter;
	
	private INewCourseUI newCourseUI;
	private IUpdateCourseUI updateCourseUI;

	public CourseViewerController(ICourseReader courseReader, ICourseWriter courseWriter, INewCourseUI newCourseUI, IUpdateCourseUI updateCourseUI) {
		this.courseReader = courseReader;
		this.courseWriter = courseWriter;
		this.newCourseUI = newCourseUI;
		this.updateCourseUI = updateCourseUI;
	}
	
	@Override
	public void setNameFilter(String nameFilter) {
		String old = this.nameFilter;
		
		this.nameFilter = nameFilter;
		
		super.notifyOfPropertyChange("nameFilter", old, nameFilter);
		super.notifyOfPropertyChange("courses", null, getCourses());
	}

	@Override
	public String getNameFilter() {
		return this.nameFilter;
	}

	@Override
	public void setSelectedCourse(Course course) {
		Course old = this.selectedCourse;
		
		this.selectedCourse = course;

		super.notifyOfPropertyChange("selectedCourse", old, course);
		super.notifyOfPropertyChange("canUpdateCourse", old, course);
		super.notifyOfPropertyChange("canDeleteCourse", old, course);
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
		this.newCourseUI.setVisible(true);
		
		super.notifyOfPropertyChange("courses", null, getCourses());
	}

	@Override
	public void showUpdateCourseUI() {
		this.updateCourseUI.setpKCourse(this.selectedCourse.getPKCourse());
		this.updateCourseUI.setVisible(true);
		
		super.notifyOfPropertyChange("courses", null, getCourses());
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
	public void deleteCourse() {
		this.courseWriter.deleteCourse(this.selectedCourse.getPKCourse());
		
		super.notifyOfPropertyChange("courses", null, getCourses());
	}
}
