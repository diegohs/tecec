package tecec.ui.control;

import java.util.List;

import tecec.contract.reader.ICourseReader;
import tecec.dto.Course;
import tecec.ui.contract.ICourseViewerController;

public class CourseViewerController extends BaseController implements ICourseViewerController {

	private String nameFilter;
	private Course selectedCourse;
	private tecec.contract.reader.ICourseReader courseReader;

	public CourseViewerController(ICourseReader courseReader) {
		this.courseReader = courseReader;
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
}
