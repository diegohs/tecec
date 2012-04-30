package tecec.ui.control;

import tecec.dto.Course;
import tecec.contract.repository.CourseRepository;

public class DefaultNewCourseController implements tecec.ui.contract.NewCourseController {	
	private CourseRepository courseRepository;	
	private Course course;
	
	public DefaultNewCourseController(CourseRepository courseRepository) {
		if (courseRepository == null) {
			throw new IllegalArgumentException("courseRepository");
		}
		
		this.courseRepository = courseRepository;
		
		this.course = new Course();
	}

	@Override
	public Course getCourse() {
		return this.course;
	}

	@Override
	public void storeCourse(){
		String errorMessage = getInvalidFieldsMessage();
		
		if (errorMessage != null) {
			throw new RuntimeException(errorMessage);
		}
		
		courseRepository.insertCourse(this.course);
	}

	@Override
	public String getInvalidFieldsMessage() {
		if (this.course.getName() == null || this.course.getName().trim().isEmpty()) {
			return "O nome do curso deve ser preenchido.";
		}
		
		if (this.course.getName().length() > 128) {
			return "O nome do curso deve ser menor que 128 caracteres.";
		}
		
		return null;
	}
	
}
