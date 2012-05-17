package tecec.ui.contract.control;

import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.dto.Advisor;
import tecec.dto.Area;
import tecec.dto.Course;
import tecec.dto.Status;
import tecec.dto.Student;

public interface INewMonographController {
	
	RuleViolation getCreationViolation();
	
	/*Monograph*/
	String getMonographTitle ();
	void setMonographTitle (String title);
	void createMonograph () throws RuleViolationException;
	
	/*Course*/
	List<Course> getCourses();
	Course getSelectedCourse();
	void setSelectedCourse(Course course);
	void setSelectedCourseIndex(int i);
	int getSelecteCourseIndex();	
	
	/*Area*/
	List<Area> getAreas();
	Area getSelectedArea();
	void setSelectedArea(Area area);
	void setSelectecAreaIndex(int i);
	int getSelectedAreaIndex();
	
	/*Student*/
	List<Student> getStudents();
	Student getSelectedStudent();
	void setSelectedStudent(Student student);
	void setSelectedStudentIndex(int i);
	int getSelectedStudentIndex();
	
	/*Advisor*/
	List<Advisor> getAdvisors();
	Advisor getSelectedAdvisor();
	void setSelectedAdvisor(Advisor advisor);
	void setSelectedAdvisorIndex(int i);
	int getSelectedAdvisorIndex();
	
	/*CoAdvisor*/
	List<Advisor> getCoadvisors();
	Advisor getSelectedCoadvisor();
	void setSelectedCoadvisor(Advisor coadvisor);
	void setSelectedCoadvisorIndex(int i);
	int getSelectedCoadvisorIndex();
	
	/*Status*/
	List<Status> getStatus();
	Status getSelectedStatus();
	void setSeletectedStatus(Status status);
	void setSelectedStatusIndex(int i);
	int getSelectedStatusIndex();	
}
