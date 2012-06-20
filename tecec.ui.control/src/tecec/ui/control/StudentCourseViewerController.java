package tecec.ui.control;

import java.util.ArrayList;
import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.reader.ICourseReader;
import tecec.contract.reader.IStudentReader;
import tecec.contract.writer.IStudentWriter;
import tecec.dto.Course;
import tecec.ui.contract.control.IStudentCourseViewerController;

public class StudentCourseViewerController extends BaseController implements
		IStudentCourseViewerController {

	String pKStudent;
	Course selectedCourse;
	Course selectedCorrelatedCourse;

	List<Course> courses;
	List<Course> correlatedCourses;

	IStudentReader studentReader;
	IStudentWriter studentWriter;
	ICourseReader courseReader;

	public StudentCourseViewerController(IStudentWriter studentWriter,
			ICourseReader courseReader, IStudentReader studentReader) {
		this.studentWriter = studentWriter;
		this.courseReader = courseReader;
		this.studentReader = studentReader;
	}

	@Override
	public void setPKStudent(String pKStudent) {
		this.pKStudent = pKStudent;

		loadCorrelatedCourses();
		loadCourses();
	}

	private void loadCorrelatedCourses() {
		this.correlatedCourses = this.courseReader
				.getStudentCourses(this.pKStudent);

		super.notifyOfPropertyChange("correlatedCourses", null,
				this.correlatedCourses);
		super.notifyOfPropertyChange("canDelete", null, this.getCanDelete());
	}

	private void loadCourses() {
		this.courses = this.courseReader.getCourses("");

		for (int i = 0; i < this.courses.size(); i++) {
			for (Course correlatedCourse : this.correlatedCourses) {
				if (this.courses.get(i).getPKCourse()
						.equals(correlatedCourse.getPKCourse())) {
					this.courses.remove(i);
					i--;
					break;
				}
			}
		}

		super.notifyOfPropertyChange("courses", null, this.courses);
		super.notifyOfPropertyChange("canInsert", null, this.getCanInsert());
	}

	@Override
	public RuleViolation getInsertViolation() {
		return null;
	}

	@Override
	public RuleViolation getDeletionViolation() {
		boolean hasMonographies = this.studentReader
				.doesUserHaveMonographiesInCourse(this.pKStudent,
						this.selectedCorrelatedCourse.getPKCourse());

		if (hasMonographies) {
			return new RuleViolation(
					"Não é possível desvincular um estudante de um curso em que já possui monografias.");
		}

		return null;
	}

	@Override
	public boolean getCanInsert() {
		return this.selectedCourse != null;
	}

	@Override
	public boolean getCanDelete() {
		return this.selectedCorrelatedCourse != null;
	}

	@Override
	public List<Course> getCourses() {
		return this.courses;
	}

	@Override
	public List<Course> getCorrelatedCourses() {
		return this.correlatedCourses;
	}

	@Override
	public Course getSelectedCourse() {
		return this.selectedCourse;
	}

	@Override
	public Course getSelectedCorrelatedCourse() {
		return this.selectedCorrelatedCourse;
	}

	@Override
	public void setSelectedCourse(Course course) {
		this.selectedCourse = course;

		super.notifyOfPropertyChange("selectedCourse", null,
				this.selectedCourse);
		super.notifyOfPropertyChange("canInsert", null, this.getCanInsert());
	}

	@Override
	public void setSelectedCorrelatedCourse(Course course) {
		this.selectedCorrelatedCourse = course;

		super.notifyOfPropertyChange("selectedCorrelatedCourse", null,
				this.selectedCorrelatedCourse);
		super.notifyOfPropertyChange("canDelete", null, this.getCanDelete());
	}

	@Override
	public void insertCourse() throws RuleViolationException {
		RuleViolation violation = getInsertViolation();

		if (violation != null) {
			throw new RuleViolationException(violation);
		}

		List<Course> newCourses = new ArrayList<Course>();
		List<Course> newCorrelatedCourses = new ArrayList<Course>();

		newCourses.addAll(this.courses);
		newCorrelatedCourses.addAll(this.correlatedCourses);

		newCourses.remove(this.selectedCourse);
		newCorrelatedCourses.add(this.selectedCourse);

		this.courses = newCourses;
		this.correlatedCourses = newCorrelatedCourses;

		super.notifyOfPropertyChange("courses", null, this.courses);
		super.notifyOfPropertyChange("correlatedCourses", null,
				this.correlatedCourses);
	}

	@Override
	public void deleteCourse() throws RuleViolationException {
		RuleViolation violation = getDeletionViolation();

		if (violation != null) {
			throw new RuleViolationException(violation);
		}

		List<Course> newCourses = new ArrayList<Course>();
		List<Course> newCorrelatedCourses = new ArrayList<Course>();

		newCourses.addAll(this.courses);
		newCorrelatedCourses.addAll(this.correlatedCourses);

		newCorrelatedCourses.remove(this.selectedCorrelatedCourse);
		newCourses.add(this.selectedCorrelatedCourse);

		this.courses = newCourses;
		this.correlatedCourses = newCorrelatedCourses;

		super.notifyOfPropertyChange("courses", null, this.courses);
		super.notifyOfPropertyChange("correlatedCourses", null,
				this.correlatedCourses);
	}

	@Override
	public void commit() {
		List<Course> oldCorrelatedCourses = this.courseReader
				.getStudentCourses(this.pKStudent);

		for (Course oldCourse : oldCorrelatedCourses) {
			if (!this.correlatedCourses.contains(oldCourse)) {
				this.studentWriter.deleteStudentCourse(
						this.pKStudent, oldCourse.getPKCourse());
			}
		}

		for (Course newCourse : this.correlatedCourses) {
			if (!oldCorrelatedCourses.contains(newCourse)) {
				this.studentWriter.insertStudentCourse(
						this.pKStudent, newCourse.getPKCourse());
			}
		}
	}

	@Override
	public void refresh() {
		loadCorrelatedCourses();
		loadCourses();
	}

}
