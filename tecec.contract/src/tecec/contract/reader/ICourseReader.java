package tecec.contract.reader;

import java.util.List;

import tecec.dto.Course;

public interface ICourseReader {
	List<Course> getCourses(String nameFilter);
	Course getCourseByPK(String pKCourse);
}
