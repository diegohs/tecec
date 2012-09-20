package tecec.ui.control;

import java.util.ArrayList;
import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.reader.*;
import tecec.contract.writer.IMonographWriter;
import tecec.dto.Advisor;
import tecec.dto.Area;
import tecec.dto.Course;
import tecec.dto.Monograph;
import tecec.dto.Status;
import tecec.dto.Student;

import tecec.ui.contract.control.INewMonographController;

public class NewMonographController extends BaseController implements
		INewMonographController {

	/* Monograph */
	private IMonographWriter monographWriter;
	private String title;

	/* Course */
	private ICourseReader courseReader;
	private Course selectedCourse;
	private int selectedCourseIndex;

	/* Area */
	private IAreaReader areaReader;
	private Area selectedArea;
	private int selectedAreaIndex;

	/* Student */
	private IStudentReader studentReader;
	private Student selectedStudent;
	private int selectedStudentIndex;

	/* Advisor */
	private IAdvisorReader advisorReader;
	private Advisor selectedAdvisor;
	private int selectedAdvisorIndex;

	/* Coadvisor */
	private Advisor selectedCoadvisor;
	private int selectedCoadvisorIndex;

	/* Status */
	private IStatusReader statusReader;
	private Status selectedStatus;
	private int selectedStatusIndex;

	/* Constructor */

	public NewMonographController(IMonographWriter monographWriter,
			ICourseReader courseReader, IAreaReader areaReader,
			IStudentReader studentReader, IAdvisorReader advisorReader,
			IStatusReader statusReader) {
		if (monographWriter == null)
			throw new IllegalArgumentException("monographWriter");
		this.monographWriter = monographWriter;
		this.courseReader = courseReader;
		this.areaReader = areaReader;
		this.studentReader = studentReader;
		this.advisorReader = advisorReader;
		this.statusReader = statusReader;
		
		setSelectecAreaIndex(-1);
		setSelectedAdvisorIndex(-1);
		setSelectedCoadvisorIndex(-1);
		setSelectedCourseIndex(-1);
		setSelectedStatusIndex(-1);
		setSelectedStudentIndex(-1);		
	}

	@Override
	public RuleViolation getCreationViolation() {
		return this.monographWriter.getCreationViolation(getMonograph());
	}

	/* Monograph */

	private Monograph getMonograph() {
		Monograph newMonograph = new Monograph();

		newMonograph.setTitle(this.title);

		if (this.selectedCourse != null)
			newMonograph.setfKCourse(this.selectedCourse.getPKCourse());

		if (this.selectedArea != null)
			newMonograph.setfKArea(this.selectedArea.getpKArea());

		if (this.selectedAdvisor != null)
			newMonograph.setfKAdvisor(this.selectedAdvisor.getPKAdvisor());

		if (this.selectedCoadvisor != null)
			newMonograph.setfKCoadvisor(this.selectedCoadvisor.getPKAdvisor());

		if (this.selectedStatus != null)
			newMonograph.setfKStatus(this.selectedStatus.getpKStatus());

		if (this.selectedStudent != null)
			newMonograph.setfKStudent(this.selectedStudent.getPKStudent());

		return newMonograph;
	}

	@Override
	public String getMonographTitle() {
		return this.title;
	}

	@Override
	public void setMonographTitle(String title) {

		this.title = title;

		super.notifyOfPropertyChange("monographTitle");
	}

	@Override
	public void createMonograph() throws RuleViolationException {
		RuleViolation violation = getCreationViolation();

		if (violation != null)
			throw new RuleViolationException(violation);

		Monograph monograph = getMonograph();

		monographWriter.createMonograph(monograph);

		refresh();
	}

	/* End of Monograph */

	/* Course */

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
	public int getSelecteCourseIndex() {
		return this.selectedCourseIndex;
	}

	/* End of Course */

	/* Area */

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
	public void setSelectecAreaIndex(int i) {
		this.selectedAreaIndex = i;
		super.notifyOfPropertyChange("selectedAreaIndex");
	}

	@Override
	public int getSelectedAreaIndex() {
		return this.selectedAreaIndex;
	}

	/* End of Area */

	/* Student */

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

	/* End of Student */

	/* Advisor */

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

	/* End of Advisor */

	/* Status */

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

	/* End of Status */

	/* Coadvisor */

	@Override
	public List<Advisor> getCoadvisors() {
		List<Advisor> coadvisors = this.advisorReader.getAdvisors("");

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

	@Override
	public boolean getCanSelectStudent() {
		if (this.selectedCourse != null) {
			return this.studentReader.getStudentsByCourse(
					this.selectedCourse.getPKCourse()).size() > 0;
		}

		return false;
	}

	/* End of Coadvisor */

	@Override
	public void refresh() {
		setMonographTitle("");

		super.notifyOfPropertyChange("areas");
		super.notifyOfPropertyChange("advisors");
		super.notifyOfPropertyChange("coadvisors");
		super.notifyOfPropertyChange("courses");
		super.notifyOfPropertyChange("status");
		
		setSelectedArea(null);
		setSelectedAdvisor(null);
		setSelectedCoadvisor(null);
		setSelectedCourse(null);
		setSelectedStatus(null);
		setSelectedStudent(null);
		
		setSelectecAreaIndex(-1);
		setSelectedAdvisorIndex(-1);
		setSelectedCoadvisorIndex(-1);
		setSelectedCourseIndex(-1);
		setSelectedStatusIndex(-1);
		setSelectedStudentIndex(-1);
	}
}
