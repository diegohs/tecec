package tecec.contract.repository;

import java.util.List;

import tecec.dto.Course;

public interface ICourseRepository {
	void insertCourse(Course course);
	void updateCourse(Course course);
	void deleteCourse(String pKCourse);
	
	Course getCourseByNameAndTurnAndYear(String name, String turn, String year);
	Course getCourseByPK(String pKCourse);
	
	List<Course> getCourses(String nameFilter);
	List<Course> getStudentCourses(String pKStudent);
}
