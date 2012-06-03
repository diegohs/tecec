package tecec.ui.contract.control;

import java.util.List;

import tecec.dto.Course;
import tecec.dto.Stage;

public interface ICourseViewerController {
	void setNameFilter(String nameFilter);
	String getNameFilter();
	
	void setSelectedCourse(Course course);
	Course getSelectedCourse();
	List<Course> getCourses();
	
	void deleteCourse();
	
	boolean getCanUpdateCourse();
	boolean getCanDeleteCourse();
	
	void showNewCourseUI();
	void showUpdateCourseUI();
	

}
