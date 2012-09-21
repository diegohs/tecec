package tecec.ui.contract.control;

import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.dto.Course;

public interface IStudentCourseViewerController extends IViewerController  {
	void setPKStudent(String pKStudent);
	
	RuleViolation getInsertViolation();
	RuleViolation getDeletionViolation();
	
	boolean getCanInsert();
	boolean getCanDelete();
	
	List<Course> getCourses();
	List<Course> getCorrelatedCourses();
	
	Course getSelectedCourse();
	Course getSelectedCorrelatedCourse();
	
	void setSelectedCourse(Course course);
	void setSelectedCorrelatedCourse(Course course);
	
	void insertCourse() throws RuleViolationException;
	void deleteCourse() throws RuleViolationException;
	
	void commit();
}
