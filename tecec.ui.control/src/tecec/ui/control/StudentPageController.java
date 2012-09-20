package tecec.ui.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tecec.contract.reader.*;
import tecec.contract.writer.IHandInWriter;
import tecec.dto.*;
import tecec.ui.contract.control.IStudentPageController;
import tecec.ui.contract.record.ActivityRecord;

public class StudentPageController extends BaseController implements
		IStudentPageController {

	String pKStudent;
	ActivityRecord selectedActivityRecord;

	IMonographReader monographReader;
	IActivityReader activityReader;
	ICourseReader courseReader;
	IHandInReader handInReader;
	IHandInWriter handInWriter;
	IStageReader stageReader;

	public StudentPageController(IMonographReader monographReader,
			IActivityReader activityReader, ICourseReader courseReader,
			IHandInReader handInReader, IStageReader stageReader, IHandInWriter handInWriter) {
		this.monographReader = monographReader;
		this.activityReader = activityReader;
		this.courseReader = courseReader;
		this.handInReader = handInReader;
		this.handInWriter = handInWriter;
		this.stageReader = stageReader;
	}

	@Override
	public void setPKStudent(String pKStudent) {
		this.pKStudent = pKStudent;

		super.notifyOfPropertyChange("activityRecords");
	}

	@Override
	public ActivityRecord getSelectedActivityRecord() {
		return this.selectedActivityRecord;
	}

	@Override
	public void setSelectedActivityRecord(ActivityRecord activityRecord) {
		this.selectedActivityRecord = activityRecord;

		super.notifyOfPropertyChange("canHandIn");
		super.notifyOfPropertyChange("selectedActivityDescription");
		super.notifyOfPropertyChange("selectedHandInRemark");
	}

	@Override
	public List<ActivityRecord> getActivityRecords() {
		List<ActivityRecord> result = new ArrayList<ActivityRecord>();

		if (this.pKStudent == null) {
			return result;
		}

		List<Course> courses = this.courseReader
				.getStudentCourses(this.pKStudent);

		for (Course course : courses) {
			Monograph monograph = this.monographReader
					.getMonographByStudentAndCourse(this.pKStudent,
							course.getPKCourse());

			if (monograph != null) {
				List<Stage> stages = this.stageReader
						.getStagesByMonograph(monograph.getpKMonograph());

				for (Stage stage : stages) {
					List<Activity> activities = this.activityReader
							.getActivitiesByStage(stage.getpKStage());

					for (Activity activity : activities) {
						HandIn handIn = this.handInReader
								.getHandInByActivityAndMonograph(
										activity.getpKActivity(),
										monograph.getpKMonograph());

						ActivityRecord record = new ActivityRecord();

						record.setCourse(course);
						record.setMonograph(monograph);
						record.setStage(stage);
						record.setActivity(activity);
						record.setHandIn(handIn);

						result.add(record);
					}
				}
			}
		}

		return result;
	}

	@Override
	public boolean getCanHandIn() {
		return this.selectedActivityRecord != null;
	}

	@Override
	public String getSelectedActivityDescription() {
		if (this.selectedActivityRecord != null) {
			return this.selectedActivityRecord.getActivity().getDescription();
		}

		return "";
	}

	@Override
	public String getSelectedHandInRemark() {
		if (this.selectedActivityRecord != null) {
			if (this.selectedActivityRecord.getHandIn() != null) {
				return this.selectedActivityRecord.getHandIn().getRemark();
			}
		}

		return "";
	}

	@Override
	public void refresh() {
		super.notifyOfPropertyChange("activityRecords");
	}

	@Override
	public void handIn(String file) throws IOException  {
		this.handInWriter.handIn(this.getSelectedActivityRecord().getMonograph().getpKMonograph(), 
				this.getSelectedActivityRecord().getActivity().getpKActivity(), 
				file);
		
		super.notifyOfPropertyChange("activityRecords");
	}

}
