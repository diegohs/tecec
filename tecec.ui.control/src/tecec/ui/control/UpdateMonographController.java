package tecec.ui.control;

import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

import tecec.contract.reader.IMonographReader;

import tecec.contract.writer.IMonographWriter;

import tecec.dto.Advisor;
import tecec.dto.Area;
import tecec.dto.Course;
import tecec.dto.Monograph;
import tecec.dto.Status;
import tecec.dto.Student;

import tecec.ui.contract.control.IUpdateMonographController;

public class UpdateMonographController extends BaseController implements IUpdateMonographController {
	
	private String pKMonograph;
	private String monographTitle;
	private IMonographWriter monographWriter;
	private IMonographReader monographReader;	
	
	/* Construtor */
	public UpdateMonographController (IMonographWriter monographWriter, IMonographReader monographReader) {
		this.monographWriter = monographWriter;
		this.monographReader = monographReader;
	}
	

	@Override
	public RuleViolation getUpdateViolation() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*Monograph*/
	
	private Monograph getMonograph(){
		return null;
	}

	@Override
	public void updateMonograph() throws RuleViolationException {
		RuleViolation violation = getUpdateViolation();
		if (violation != null) {
			throw new RuleViolationException(violation);
		}
		this.monographWriter.updateMonograph(getMonograph());
				
	}
	
	/*End of Monograph*/
	
	/*Courses*/

	@Override
	public List<Course> getCourses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Course getSelectedCourse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSelectedCourse(Course course) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSelectedCourseIndex(int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getSelecteCourseIndex() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/*End of Courses*/
	
	/*Area*/

	@Override
	public List<Area> getAreas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Area getSelectedArea() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSelectedArea(Area area) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSelectecAreaIndex(int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getSelectedAreaIndex() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/*End of Area*/
	
	/*Student*/

	@Override
	public List<Student> getStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student getSelectedStudent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSelectedStudent(Student student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSelectedStudentIndex(int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getSelectedStudentIndex() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/*End of Student*/
	
	/*Advisor*/

	@Override
	public List<Advisor> getAdvisors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Advisor getSelectedAdvisor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSelectedAdvisor(Advisor advisor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSelectedAdvisorIndex(int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getSelectedAdvisorIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Advisor> getCoadvisors() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*End of Advisor*/
	
	/*Coadvisor*/

	@Override
	public Advisor getSelectedCoadvisor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSelectedCoadvisor(Advisor coadvisor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSelectedCoadvisorIndex(int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getSelectedCoadvisorIndex() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/*End of Coadvisor*/
	
	/*Status*/

	@Override
	public List<Status> getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Status getSelectedStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSeletectedStatus(Status status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSelectedStatusIndex(int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getSelectedStatusIndex() {
		// TODO Auto-generated method stub
		return 0;
	}
}
