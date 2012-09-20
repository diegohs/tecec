package tecec.ui.control;

import java.util.ArrayList;
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
import tecec.ui.contract.view.IMonographStageViewerUI;

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
	private Advisor selectedCoadvisor;
	private int selectedCoadvisorIndex;
	
	/*Status*/
	private IStatusReader statusReader;
	private Status selectedStatus;
	private int selectedStatusIndex;
	
	private IMonographStageViewerUI monographStageUI;
	
	/*Constructor*/

	public UpdateMonographController (IMonographReader monographReader, IMonographWriter monographWriter, ICourseReader courseReader, IAreaReader areaReader,
			IStudentReader studentReader, IAdvisorReader advisorReader, IStatusReader statusReader, IMonographStageViewerUI monographViewerUI) {
		this.monographWriter = monographWriter;
		this.monographReader = monographReader;
		this.courseReader = courseReader;
		this.areaReader = areaReader;
		this.studentReader = studentReader;
		this.advisorReader = advisorReader;
		this.statusReader = statusReader;
		this.monographStageUI = monographViewerUI;
		
		setSelectedAdvisorIndex(-1);
		setSelectedAreaIndex(-1);
		setSelectedCoadvisorIndex(-1);
		setSelectedCourseIndex(-1);
		setSelectedStatusIndex(-1);
		setSelectedStudentIndex(-1);
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
		
		super.notifyOfPropertyChange("monographTitle");
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
		
		List<Advisor> advisors = getAdvisors();
		List<Area> areas = getAreas();
		List<Advisor> coadvisors = getCoadvisors();		
		List<Course> courses = getCourses();
		List<Status> statuses = getStatus();		
		List<Student> students = getStudents();
		
		super.notifyOfPropertyChange("areas");
		super.notifyOfPropertyChange("advisors");
		super.notifyOfPropertyChange("coadvisors");
		super.notifyOfPropertyChange("statuses");
		super.notifyOfPropertyChange("students");
		super.notifyOfPropertyChange("courses");	
		
		setSelectedAdvisor(null);
		setSelectedArea(null);
		setSelectedCoadvisor(null);
		setSelectedCourse(null);
		setSelectedStatus(null);
		setSelectedStudent(null);

		setSelectedAdvisorIndex(-1);
		setSelectedAreaIndex(-1);
		setSelectedCoadvisorIndex(-1);
		setSelectedCourseIndex(-1);
		setSelectedStatusIndex(-1);
		setSelectedStudentIndex(-1);
		
		if(monograph.getfKAdvisor() != null && !monograph.getfKAdvisor().isEmpty()){
			for(int i=0;i<advisors.size();i++){
				if(monograph.getfKAdvisor().equals(advisors.get(i).getPKAdvisor())){
					setSelectedAdvisorIndex(i);
					break;
				}
			}
		}		
		
		if(monograph.getfKArea() != null && !monograph.getfKArea().isEmpty()){
			for(int i=0;i<areas.size();i++){
				if(monograph.getfKArea().equals(areas.get(i).getpKArea())){
					setSelectedAreaIndex(i);
					break;
				}
			}
		}		
		
		if(monograph.getfKCoadvisor() != null && !monograph.getfKCoadvisor().isEmpty()){
			for(int i=0;i<coadvisors.size();i++){
				if(monograph.getfKCoadvisor().equals(coadvisors.get(i).getPKAdvisor())){
					setSelectedCoadvisorIndex(i);
					break;
				}
			}
		}
		
		if(monograph.getfKCourse() != null && !monograph.getfKCourse().isEmpty()){
			for(int i=0;i<courses.size();i++){
				if(monograph.getfKCourse().equals(courses.get(i).getPKCourse())){
					setSelectedCourseIndex(i);
					break;
				}
			}
		}		
		
		if(monograph.getfKStatus() != null && !monograph.getfKStatus().isEmpty()){
			for(int i=0;i<statuses.size();i++){
				if(monograph.getfKStatus().equals(statuses.get(i).getpKStatus())){
					setSelectedStatusIndex(i);
					break;
				}
			}
		}
		
		if(monograph.getfKStudent() != null && !monograph.getfKStudent().isEmpty()){
			for(int i=0;i<students.size();i++){
				if(monograph.getfKStudent().equals(students.get(i).getPKStudent())){
					this.setSelectedStudentIndex(i);
					break;
				}
			}
		}
	}
	
	private Monograph getMonograph(){
		Monograph monograph = new Monograph();
		
		monograph.setpKMonograph(this.pKMonograph);		
		monograph.setTitle(this.monographTitle);
		
		if(this.selectedAdvisor != null)
			monograph.setfKAdvisor(this.selectedAdvisor.getPKAdvisor());
		
		if(this.selectedArea != null)
			monograph.setfKArea(this.selectedArea.getpKArea());
		
		if(this.selectedCoadvisor != null && this.selectedAdvisor.getPKAdvisor() != null)
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
		
		refresh();
	}
	
	/*End of Monograph*/
	
	/*Courses*/

	@Override
	public List<Course> getCourses() {
		List<Course> courses = this.courseReader.getCourses("");
		
		return courses;
	}

	@Override
	public Course getSelectedCourse() {
		return this.selectedCourse;
	}

	@Override
	public void setSelectedCourse(Course course) {
		this.selectedCourse = course;

		super.notifyOfPropertyChange("selectedCourse");
		super.notifyOfPropertyChange("canSelectStudent");
		super.notifyOfPropertyChange("students");
	}

	@Override
	public void setSelectedCourseIndex(int i) {
		this.selectedCourseIndex = i;
		
		super.notifyOfPropertyChange("selectedCourseIndex");
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
		
		return areas;
	}

	@Override
	public Area getSelectedArea() {
		return this.selectedArea;
	}

	@Override
	public void setSelectedArea(Area area) {
		this.selectedArea = area;
		
		super.notifyOfPropertyChange("selectedArea");
	}

	@Override
	public void setSelectedAreaIndex(int i) {
		this.selectedAreaIndex = i;
		
		super.notifyOfPropertyChange("selectedAreaIndex");
	}

	@Override
	public int getSelectedAreaIndex() {
		return this.selectedAreaIndex;
	}
	
	/*End of Area*/
	
	/*Student*/

	@Override
	public List<Student> getStudents() {
		if (this.selectedCourse != null) {
			List<Student> students = this.studentReader.getStudentsByCourse(this.selectedCourse.getPKCourse());

			return students;
		}
		else
		{
			return new ArrayList<Student>();
		}
	}

	@Override
	public Student getSelectedStudent() {
		return this.selectedStudent;
	}

	@Override
	public void setSelectedStudent(Student student) {
		this.selectedStudent = student;
		
		super.notifyOfPropertyChange("selectedStudent");
	}

	@Override
	public void setSelectedStudentIndex(int i) {
		this.selectedStudentIndex = i;
		
		super.notifyOfPropertyChange("selectedStudentIndex");
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
		
		return advisors;
	}

	@Override
	public Advisor getSelectedAdvisor() {
		return this.selectedAdvisor;
	}

	@Override
	public void setSelectedAdvisor(Advisor advisor) {
		this.selectedAdvisor = advisor;
		
		super.notifyOfPropertyChange("selectedAdvisor");
	}

	@Override
	public void setSelectedAdvisorIndex(int i) {
		this.selectedAdvisorIndex = i;
		
		super.notifyOfPropertyChange("selectedAdvisorIndex");
	}

	@Override
	public int getSelectedAdvisorIndex() {
		return this.selectedAdvisorIndex;
	}
	
	/*End of Advisor*/
	
	/*Coadvisor*/

	@Override
	public List<Advisor> getCoadvisors() {
		List<Advisor> coadvisors = this.advisorReader.getAdvisors("");
		
		Advisor emptyAdvisor = new Advisor();
		
		emptyAdvisor.setName(" ");
		
		coadvisors.add(0, emptyAdvisor);
		
		return coadvisors;
	}

	@Override
	public Advisor getSelectedCoadvisor() {
		return this.selectedCoadvisor;
	}

	@Override
	public void setSelectedCoadvisor(Advisor coadvisor) {
		this.selectedCoadvisor = coadvisor;
		
		super.notifyOfPropertyChange("selectedCoadvisor");
	}

	@Override
	public void setSelectedCoadvisorIndex(int i) {
		this.selectedCoadvisorIndex = i;

		super.notifyOfPropertyChange("selectedCoadvisorIndex");
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
		
		return statuses;
	}

	@Override
	public Status getSelectedStatus() {
		return this.selectedStatus;
	}

	@Override
	public void setSelectedStatus(Status status) {
		this.selectedStatus = status;
		
		super.notifyOfPropertyChange("selectedStatus");
	}

	@Override
	public void setSelectedStatusIndex(int i) {
		this.selectedStatusIndex = i;

		super.notifyOfPropertyChange("selectedStatusIndex");
	}

	@Override
	public int getSelectedStatusIndex() {
		return this.selectedStatusIndex;
	}

	@Override
	public void showMonographStageUI() {
		this.monographStageUI.setPKMonograph(this.pKMonograph);
		this.monographStageUI.setVisible(true);
	}

	@Override
	public boolean getCanSelectStudent() {
		if (this.selectedCourse != null) {
			return this.studentReader.getStudentsByCourse(
					this.selectedCourse.getPKCourse()).size() > 0;
		}

		return false;
	}
	
	/*End of Status*/
	
	@Override
	public void refresh(){
		setPKMonograph(this.pKMonograph);
	}
}
