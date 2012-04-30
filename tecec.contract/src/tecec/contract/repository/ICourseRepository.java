package tecec.contract.repository;

import tecec.dto.Course;

public interface ICourseRepository {
	void insertCourse(Course course);
	Course getCourse(String name);
	void updateCourse(Course course);
}
