package tecec.ui.contract.control;

import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.dto.Advisor;
import tecec.dto.Area;
import tecec.dto.Course;
import tecec.dto.Monograph;
import tecec.dto.Status;
import tecec.dto.Student;

public interface IUpdateMonographController {
	
	/*Course*/
	List<Course> getCourses();
	Course getSelectedCourse();
	void setSelectedCourse(Course course);
	void setSelectedCourseIndex(int i);
	int getSelectedCourseIndex();	
	
	/*Area*/
	List<Area> getAreas();
	Area getSelectedArea();
	void setSelectedArea(Area area);
	void setSelectedAreaIndex(int i);
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
	void setSelectedStatus(Status status);
	void setSelectedStatusIndex(int i);
	int getSelectedStatusIndex();
	
	/*Monograph*/
	List<Monograph> getMonographs();
	void setPKMonograph(String pKMonograph);
	void setMonographTitle(String title);
	String getMonographTitle();
	
	
	void updateMonograph() throws RuleViolationException;
	RuleViolation getUpdateViolation();

}
