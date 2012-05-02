package tecec.business.reader;

import java.util.List;

import tecec.contract.repository.ICourseRepository;
import tecec.dto.Course;

public class CourseReader implements tecec.contract.reader.ICourseReader {
	private tecec.contract.repository.ICourseRepository courseRepository;

	public CourseReader(ICourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	@Override
	public List<Course> getCourses(String nameFilter) {
		return courseRepository.getCourses(nameFilter);
	}

	@Override
	public Course getCourseByPK(String pKCourse) {
		return courseRepository.getCourseByPK(pKCourse);
	}

}
