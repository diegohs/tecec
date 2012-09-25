package tecec.dto.record;

import tecec.dto.*;

public class CourseStudentRecord {
	private Course course;
	private Student student;
		
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
}
