package tecec.ui.control;

import java.util.ArrayList;
import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.reader.IActivityReader;
import tecec.contract.reader.ICourseReader;
import tecec.contract.reader.IDocumentationReader;
import tecec.contract.reader.IHandInReader;
import tecec.contract.reader.IMonographReader;
import tecec.contract.reader.IStageReader;
import tecec.contract.reader.IStudentReader;
import tecec.contract.reporting.IActivityRecordReporter;
import tecec.contract.writer.IHandInWriter;
import tecec.dto.Activity;
import tecec.dto.Course;
import tecec.dto.Documentation;
import tecec.dto.HandIn;
import tecec.dto.Monograph;
import tecec.dto.Stage;
import tecec.dto.Student;
import tecec.dto.record.ActivityRecord;
import tecec.ui.contract.control.ICoordinatorPageController;

public class CoordinatorPageController extends BaseViewerController implements
		ICoordinatorPageController {

	ActivityRecord selectedActivity;

	String filter;
	boolean filterByCourse, filterByStudent, filterByTitle;
	boolean showLate, showOnTime;

	IActivityReader activityReader;
	ICourseReader courseReader;
	IStageReader stageReader;
	IHandInReader handInReader;
	IMonographReader monographReader;
	IStudentReader studentReader;
	IHandInWriter handInWriter;
	IDocumentationReader documentationReader;
	IActivityRecordReporter reporter;

	public CoordinatorPageController(IActivityReader activityReader,
			ICourseReader courseReader, IStageReader stageReader,
			IHandInReader handInReader, IMonographReader monographReader,
			IStudentReader studentReader, IHandInWriter handInWriter,
			IDocumentationReader documentationReader,
			IActivityRecordReporter reporter) {
		super(reporter);

		this.activityReader = activityReader;
		this.courseReader = courseReader;
		this.stageReader = stageReader;
		this.handInReader = handInReader;
		this.monographReader = monographReader;
		this.studentReader = studentReader;
		this.handInWriter = handInWriter;
		this.documentationReader = documentationReader;
		this.reporter = reporter;
		
		this.filter = "";
	}

	@Override
	public List<ActivityRecord> getActivities() {
		List<ActivityRecord> result = new ArrayList<ActivityRecord>();

		List<Course> courses = this.courseReader
				.getCourses(filterByCourse ? this.filter : "");

		for (Course course : courses) {
			List<Monograph> monographies = this.monographReader
					.getMonographiesByCourse(course.getPKCourse());

			for (Monograph monograph : monographies) {
				List<Stage> stages = this.stageReader
						.getStagesByMonograph(monograph.getpKMonograph());

				for (Stage stage : stages) {
					List<Activity> activities = this.activityReader
							.getActivitiesByStage(stage.getpKStage(), filterByTitle ? this.filter : "");

					for (Activity activity : activities) {
						HandIn handIn = this.handInReader
								.getHandInByActivityAndMonograph(
										activity.getpKActivity(),
										monograph.getpKMonograph(),
										this.showOnTime, this.showLate);

						if (handIn != null) {
							Student student = this.studentReader
									.getStudentByPk(monograph.getfKStudent(),
											filterByStudent ? this.filter : "");

							ActivityRecord record = new ActivityRecord();

							record.setCourse(course);
							record.setMonograph(monograph);
							record.setStage(stage);
							record.setActivity(activity);
							record.setHandIn(handIn);
							record.setStudent(student);

							result.add(record);
						}
					}
				}
			}
		}

		return result;
	}

	@Override
	public ActivityRecord getSelectedActivity() {
		return this.selectedActivity;
	}

	@Override
	public void setSelectedActivity(ActivityRecord activity) {
		this.selectedActivity = activity;

		super.notifyOfPropertyChange("selectedActivity");
		super.notifyOfPropertyChange("canUpdate");
		super.notifyOfPropertyChange("canDelete");
		super.notifyOfPropertyChange("canDownloadFile");

		if (activity != null) {
			super.notifyOfPropertyChange("selectedActivity.handIn.grade");
			super.notifyOfPropertyChange("selectedActivity.handIn.remark");
		} else {
			super.notifyOfPropertyChange("selectedActivity.handIn.grade");
			super.notifyOfPropertyChange("selectedActivity.handIn.remark");
		}

	}

	@Override
	public void update() throws RuleViolationException {
		RuleViolation violation = getUpdateViolation();

		if (violation != null) {
			throw new RuleViolationException(violation);
		}

		this.handInWriter.updateHandIn(this.selectedActivity.getHandIn()
				.getPKHandIn(), this.selectedActivity.getHandIn().getGrade(),
				this.selectedActivity.getHandIn().getRemark());

		super.notifyOfPropertyChange("activities");
	}

	@Override
	public boolean getCanUpdate() {
		return this.selectedActivity != null;
	}

	@Override
	public boolean getCanDownloadFile() {
		return this.selectedActivity != null;
	}

	@Override
	public Documentation getSelectedHandInFile() {
		String pkDocumentation = this.getSelectedActivity().getHandIn()
				.getFKDocumentation();

		Documentation doc = this.documentationReader
				.getDocumentationByPK(pkDocumentation);

		return doc;
	}

	@Override
	public RuleViolation getUpdateViolation() {
		if (this.selectedActivity == null) {
			return new RuleViolation("Nenhuma entrega foi selecionada.");
		}

		return null;
	}

	@Override
	public void refresh() {
		super.notifyOfPropertyChange("activities");
	}

	@Override
	public RuleViolation getDeletionViolation() {
		if (this.selectedActivity == null) {
			return new RuleViolation("Nenhuma entrega foi selecionada.");
		}

		return null;
	}

	@Override
	public void delete() throws RuleViolationException {
		RuleViolation violation = getDeletionViolation();

		if (violation != null) {
			throw new RuleViolationException(violation);
		}

		this.handInWriter.deleteHandIn(this.selectedActivity.getHandIn()
				.getPKHandIn());

		super.notifyOfPropertyChange("activities");
	}

	@Override
	public boolean getCanDelete() {
		return this.selectedActivity != null;
	}

	@Override
	protected List<String[]> getExportSource() {
		return this.reporter.format(this.getActivities());
	}

	public boolean getShowLate() {
		return showLate;
	}

	public void setShowLate(boolean showLate) {
		this.showLate = showLate;
	}

	public boolean getShowOnTime() {
		return showOnTime;
	}

	public void setShowOnTime(boolean showOnTime) {
		this.showOnTime = showOnTime;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;

		super.notifyOfPropertyChange("activities");
	}

	public boolean getFilterByCourse() {
		return filterByCourse;
	}

	public void setFilterByCourse(boolean filterByCourse) {
		this.filterByCourse = filterByCourse;

		super.notifyOfPropertyChange("filterByCourse");
		super.notifyOfPropertyChange("activities");
	}

	public boolean getFilterByStudent() {
		return filterByStudent;
	}

	public void setFilterByStudent(boolean filterByStudent) {
		this.filterByStudent = filterByStudent;

		super.notifyOfPropertyChange("filterByStudent");
		super.notifyOfPropertyChange("activities");
	}

	public boolean getFilterByTitle() {
		return filterByTitle;
	}

	public void setFilterByTitle(boolean filterByTitle) {
		this.filterByTitle = filterByTitle;

		super.notifyOfPropertyChange("filterByTitle");
		super.notifyOfPropertyChange("activities");
	}

}
