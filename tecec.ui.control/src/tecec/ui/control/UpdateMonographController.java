package tecec.ui.control;

import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

import tecec.contract.reader.IAdvisorReader;
import tecec.contract.reader.IAreaReader;
import tecec.contract.reader.ICourseReader;
import tecec.contract.reader.IMonographReader;
import tecec.contract.reader.IStatusReader;
import tecec.contract.reader.IStudentReader;

import tecec.contract.writer.IMonographWriter;

import tecec.dto.Advisor;
import tecec.dto.Area;
import tecec.dto.Course;
import tecec.dto.Monograph;
import tecec.dto.Status;
import tecec.dto.Student;

import tecec.ui.contract.control.IUpdateMonographController;

public class UpdateMonographController extends BaseController implements IUpdateMonographController {
	
	/*Monograph*/
	private IMonographWriter monographWriter;
	private IMonographReader monographReader;
	private String pKMonograph;
	private String monographTitle;
	
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

	public UpdateMonographController (IMonographReader monographReader, IMonographWriter monographWriter, ICourseReader courseReader, IAreaReader areaReader,
			IStudentReader studentReader, IAdvisorReader advisorReader, IStatusReader statusReader, IAdvisorReader coadvisorReader) {
		this.monographWriter = monographWriter;
		this.monographReader = monographReader;
		this.courseReader = courseReader;
		this.areaReader = areaReader;
		this.studentReader = studentReader;
		this.advisorReader = advisorReader;
		this.statusReader = statusReader;
		this.coadvisorReader = coadvisorReader;
	}
	

	@Override
	public RuleViolation getUpdateViolation() {
		Monograph monograph = getMonograph();
		
		return this.monographWriter.getUpdateViolation(monograph);
	}
	
	/*Monograph*/

	@Override
	public void setMonographTitle(String monographTitle) {
		this.monographTitle = monographTitle;
		
		super.notifyOfPropertyChange("monographTitle", null, monographTitle);
	}


	@Override
	public String getMonographTitle() {
		return this.monographTitle;
	}
	
	@Override
	public void setPKMonograph(String pKMonograph){
		this.pKMonograph = pKMonograph;
		
		Monograph monograph = this.monographReader.getMonographByPK(this.pKMonograph);
		
		this.setMonographTitle(monograph.getTitle());
		
		List<Monograph> monographs = getMonographs();
		
		if(monograph.getfKAdvisor() != null && !monograph.getfKAdvisor().isEmpty()){
			for(int i=0;i<monographs.size();i++){
				if(monograph.getfKAdvisor().equals(monographs.get(i).getfKAdvisor())){
					this.selectedAdvisorIndex = i;
					break;
				}
			}
		}
		
		if(monograph.getfKArea() != null && !monograph.getfKArea().isEmpty()){
			for(int i=0;i<monographs.size();i++){
				if(monograph.getfKArea().equals(monographs.get(i).getfKArea())){
					this.selectedAreaIndex = i;
					break;
				}
			}
		}
		
		if(monograph.getfKCoadvisor() != null && !monograph.getfKCoadvisor().isEmpty()){
			for(int i=0;i<monographs.size();i++){
				if(monograph.getfKCoadvisor().equals(monographs.get(i).getfKCoadvisor())){
					this.selectedCoadvisorIndex = i;
					break;
				}
			}
		}
		
		if(monograph.getfKCourse() != null && !monograph.getfKCourse().isEmpty()){
			for(int i=0;i<monographs.size();i++){
				if(monograph.getfKCourse().equals(monographs.get(i).getfKCourse())){
					this.selectedCourseIndex = i;
					break;
				}
			}
		}
		
		if(monograph.getfKStatus() != null && !monograph.getfKStatus().isEmpty()){
			for(int i=0;i<monographs.size();i++){
				if(monograph.getfKStatus().equals(monographs.get(i).getfKStatus())){
					this.selectedStatusIndex = i;
					break;
				}
			}
		}
		
		if(monograph.getfKStudent() != null && !monograph.getfKStudent().isEmpty()){
			for(int i=0;i<monographs.size();i++){
				if(monograph.getfKStudent().equals(monographs.get(i).getfKStudent())){
					this.selectedStudentIndex = i;
					break;
				}
			}
		}
		
		super.notifyOfPropertyChange("monographs", null, monographs);
		super.notifyOfPropertyChange("selectedAdvisorIndex", null,	this.selectedAdvisorIndex);
		super.notifyOfPropertyChange("selectedAreaIndex", null,	this.selectedAreaIndex);
		super.notifyOfPropertyChange("selectedCoadvisorIndex", null,	this.selectedCoadvisorIndex);
		super.notifyOfPropertyChange("selectedCourseIndex", null,	this.selectedCourseIndex);
		super.notifyOfPropertyChange("selectedStatusIndex", null,	this.selectedStatusIndex);
		super.notifyOfPropertyChange("selectedStudentIndex", null,	this.selectedStudentIndex);
		
	}
	
	private Monograph getMonograph(){
		Monograph monograph = new Monograph();
		
		monograph.setpKMonograph(this.pKMonograph);
		
		monograph.setTitle(this.monographTitle);
		
		if(this.selectedAdvisor != null)
			monograph.setfKAdvisor(this.selectedAdvisor.getPKAdvisor());
		
		if(this.selectedArea != null)
			monograph.setfKArea(this.selectedArea.getpKArea());
		
		if(this.selectedCoadvisor != null)
			monograph.setfKCoadvisor(this.selectedCoadvisor.getPKAdvisor());
		
		if(this.selectedCourse != null)
			monograph.setfKCourse(this.selectedCourse.getPKCourse());
		
		if(this.selectedStatus != null)
			monograph.setfKStatus(this.selectedStatus.getpKStatus());
		
		if(this.selectedStudent != null)
			monograph.setfKStudent(this.selectedStudent.getPKStudent());
		
		return monograph;
	}


	@Override
	public void updateMonograph() throws RuleViolationException {
		Monograph monograph = getMonograph();
		
		this.monographWriter.updateMonograph(monograph);
		
		this.setSelectedAdvisor(null);
		this.setSelectedArea(null);
		this.setSelectedCoadvisor(null);
		this.setSelectedCourse(null);
		this.setSelectedStatus(null);
		this.setSelectedStudent(null);
		
		this.setMonographTitle("");
	}
	
	@Override
	public List<Monograph> getMonographs() {
		List<Monograph> monographs = this.monographReader.getMonograph("");
		Monograph emptyMonograph = new Monograph();
		
		emptyMonograph.setTitle(" ");
		monographs.add(0, emptyMonograph);
		
		/*if(this.pKMonograph != null && !this.pKMonograph.isEmpty()){
			for(int i=0; i< monographs.size(); i++){
				if(this.pKMonograph.equals(monographs.get(i).getpKMonograph())){
					monographs.remove(i);
				}		
			}
		}*/
		
		return monographs;
	}
	
	/*End of Monograph*/
	
	/*Courses*/

	@Override
	public List<Course> getCourses() {
		List<Course> courses = this.courseReader.getCourses("");
		Course emptyCourse = new Course();
		
		emptyCourse.setName(" ");
		courses.add(0, emptyCourse);
		
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
	public int getSelectedCourseIndex() {
		return this.selectedCourseIndex;
	}
	
	/*End of Courses*/
	
	/*Area*/

	@Override
	public List<Area> getAreas() {
		List<Area> areas = this.areaReader.getAreas("");
		Area emptyArea = new Area();
		
		emptyArea.setDescription(" ");
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
	public void setSelectedAreaIndex(int i) {
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
		
		emptyStudent.setName(" ");
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
	
	/*Coadvisor*/

	@Override
	public List<Advisor> getCoadvisors() {
		List<Advisor> coadvisors = this.coadvisorReader.getAdvisors("");
		Advisor emptyCoadvisor = new Advisor();
		
		emptyCoadvisor.setName(" ");
		coadvisors.add(0, emptyCoadvisor);
		
		return coadvisors;
	}

	@Override
	public Advisor getSelectedCoadvisor() {
		return this.selectedCoadvisor;
	}

	@Override
	public void setSelectedCoadvisor(Advisor coadvisor) {
		this.selectedCoadvisor = coadvisor;
	}

	@Override
	public void setSelectedCoadvisorIndex(int i) {
		this.selectedCoadvisorIndex = i;
	}

	@Override
	public int getSelectedCoadvisorIndex() {
		return this.selectedCoadvisorIndex;
	}
	
	/*End of Coadvisor*/
	
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
	public void setSelectedStatus(Status status) {
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
