package tecec.ui.control;

import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.reader.IAdvisorReader;
import tecec.contract.reader.IAreaReader;
import tecec.contract.reader.ICourseReader;
import tecec.contract.reader.IStatusReader;
import tecec.contract.reader.IStudentReader;
import tecec.contract.writer.IMonographWriter;
import tecec.dto.Advisor;
import tecec.dto.Area;
import tecec.dto.Course;
import tecec.dto.Monograph;
import tecec.dto.Status;
import tecec.dto.Student;

import tecec.ui.contract.control.INewMonographController;

public class NewMonographController extends BaseController implements INewMonographController{
	
	/*Monograph*/
	private IMonographWriter monographWriter;
	private String title;
	
	/*Course*/
	private ICourseReader courseReader;
	private Course selectedCourse;
	private int selectedCourseIndex;
	
	/*Area*/
	private IAreaReader areaReader;
	private Area selectedArea;
	private int selectedAreaIndex;
	
	/*Student*/
	private IStudentReader studentReader;
	private Student selectedStudent;
	private int selectedStudentIndex;
	
	/*Advisor*/
	private IAdvisorReader advisorReader;
	private Advisor selectedAdvisor;
	private int selectedAdvisorIndex;
	
	/*Coadvisor*/
	private IAdvisorReader coadvisorReader;
	private Advisor selectedCoadvisor;
	private int selectedCoadvisorIndex;
	
	/*Status*/
	private IStatusReader statusReader;
	private Status selectedStatus;
	private int selectedStatusIndex;
	
	/*Constructor*/
	
	public NewMonographController(IMonographWriter monographWriter, ICourseReader courseReader, IAreaReader areaReader,
			IStudentReader studentReader, IAdvisorReader advisorReader, IStatusReader statusReader, IAdvisorReader coadvisorReader){
		if(monographWriter == null)
			throw new IllegalArgumentException("monographWriter");
		this.monographWriter = monographWriter;
		this.courseReader = courseReader;
		this.areaReader = areaReader;
		this.studentReader = studentReader;
		this.advisorReader = advisorReader;
		this.statusReader = statusReader;
		this.coadvisorReader = coadvisorReader;
	}
	
	@Override
	public RuleViolation getCreationViolation() {
		return this.monographWriter.getCreationViolation(getMonograph());
	}
	
	/*Monograph*/
	
	private Monograph getMonograph(){
		Monograph newMonograph = new Monograph();
		
		newMonograph.setTitle(this.title);
		
		if(this.selectedCourse != null)
			newMonograph.setfKCourse(this.selectedCourse.getPKCourse());
		
		if(this.selectedArea != null)
			newMonograph.setfKArea(this.selectedArea.getpKArea());
		
		if(this.selectedAdvisor != null)
			newMonograph.setfKAdvisor(this.selectedAdvisor.getPKAdvisor());
		
		return null;
	}

	@Override
	public String getMonographTitle() {
		return this.title;
	}

	@Override
	public void setMonographTitle(String title) {
		String oldValue = getMonographTitle();
		
		this.title = title;
		
		notifyOfPropertyChange("monographTitle", oldValue, title);
	}

	@Override
	public void createMonograph() throws RuleViolationException {
		RuleViolation violation = getCreationViolation ();
		
		if (violation != null)
			throw new RuleViolationException (violation);
		
		monographWriter.createMonograph(getMonograph());
		setMonographTitle("");
	}
	
	/*End of Monograph*/
	
	/*Course*/

	@Override
	public List<Course> getCourses() {
		List<Course> courses = this.courseReader.getCourses("");
		Course emptyCourse = new Course();
		
		emptyCourse.setName(" ");
		courses.add(0,emptyCourse);
		
		return courses;
	}

	@Override
	public Course getSelectedCourse() {
		return this.selectedCourse;
	}

	@Override
	public void setSelectedCourse(Course course) {
		this.selectedCourse = course;
	}

	@Override
	public void setSelectedCourseIndex(int i) {
		this.selectedCourseIndex = i;
	}

	@Override
	public int getSelecteCourseIndex() {
		return this.selectedCourseIndex;
	}
	/*End of Course*/
	
	/*Area*/

	@Override
	public List<Area> getAreas() {
		List<Area> areas = this.areaReader.getAreas("");
		Area emptyArea = new Area();
		
		emptyArea.setName(" ");
		areas.add(0, emptyArea);
		
		return areas;
	}

	@Override
	public Area getSelectedArea() {
		return this.selectedArea;
	}

	@Override
	public void setSelectedArea(Area area) {
		this.selectedArea = area;
	}

	@Override
	public void setSelectecAreaIndex(int i) {
		this.selectedAreaIndex = i;
	}

	@Override
	public int getSelectedAreaIndex() {
		return this.selectedAreaIndex;
	}
	/*End of Area*/
	
	/*Student*/

	@Override
	public List<Student> getStudents() {
		List<Student> students = this.studentReader.getStudents("");
		Student emptyStudent = new Student();
		
		emptyStudent.setName("");
		students.add(0, emptyStudent);
		
		return students;
	}

	@Override
	public Student getSelectedStudent() {
		return this.selectedStudent;
	}

	@Override
	public void setSelectedStudent(Student student) {
		this.selectedStudent = student;
	}

	@Override
	public void setSelectedStudentIndex(int i) {
		this.selectedStudentIndex = i;
	}

	@Override
	public int getSelectedStudentIndex() {
		return this.selectedStudentIndex;
	}
	
	/*End of Student*/
	
	/*Advisor*/

	@Override
	public List<Advisor> getAdvisors() {
		List<Advisor> advisors = this.advisorReader.getAdvisors("");
		Advisor emptyAdvisor = new Advisor();
		
		emptyAdvisor.setName(" ");
		advisors.add(0, emptyAdvisor);
		
		return advisors;
	}

	@Override
	public Advisor getSelectedAdvisor() {
		return this.selectedAdvisor;
	}

	@Override
	public void setSelectedAdvisor(Advisor advisor) {
		this.selectedAdvisor = advisor;
	}

	@Override
	public void setSelectedAdvisorIndex(int i) {
		this.selectedAdvisorIndex = i;
	}

	@Override
	public int getSelectedAdvisorIndex() {
		return this.selectedAdvisorIndex;
	}
	
	/*End of Advisor*/
	
	/*Status*/

	@Override
	public List<Status> getStatus() {
		List<Status> statuses = this.statusReader.getStatus("");
		Status emptyStatus = new Status();
		
		emptyStatus.setDescription(" ");
		statuses.add(0, emptyStatus);
		
		return statuses;
	}

	@Override
	public Status getSelectedStatus() {
		return this.selectedStatus;
	}

	@Override
	public void setSeletectedStatus(Status status) {
		this.selectedStatus = status;
	}

	@Override
	public void setSelectedStatusIndex(int i) {
		this.selectedStatusIndex = i;
	}

	@Override
	public int getSelectedStatusIndex() {
		return this.selectedStatusIndex;
	}
	
	/*End of Status*/
}
