package tecec.ui.contract;

import java.util.List;

import tecec.dto.Course;

public interface ICourseViewerController {
	void setNameFilter(String nameFilter);
	String getNameFilter();
	
	void setSelectedCourse(Course course);
	Course getSelectedCourse();
	List<Course> getCourses();
}
