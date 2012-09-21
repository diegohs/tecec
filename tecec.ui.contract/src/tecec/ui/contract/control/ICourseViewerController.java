package tecec.ui.contract.control;

import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.dto.Course;

public interface ICourseViewerController extends IViewerController  {
	void setNameFilter(String nameFilter);
	String getNameFilter();
	
	void setSelectedCourse(Course course);
	Course getSelectedCourse();
	List<Course> getCourses();
	
	RuleViolation getDeletionViolation();
	
	void deleteCourse() throws RuleViolationException;
	
	boolean getCanUpdateCourse();
	boolean getCanDeleteCourse();
	
	void showNewCourseUI();
	void showUpdateCourseUI();
	

}
