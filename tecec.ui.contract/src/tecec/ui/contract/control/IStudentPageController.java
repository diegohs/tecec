package tecec.ui.contract.control;

import java.util.List;

import tecec.dto.record.ActivityRecord;

public interface IStudentPageController extends IViewerController {

	void setPKStudent(String pKStudent);

	ActivityRecord getSelectedActivityRecord();

	void setSelectedActivityRecord(ActivityRecord activityRecord);

	List<ActivityRecord> getActivityRecords();

	boolean getCanHandIn();

	String getSelectedActivityDescription();

	String getSelectedHandInRemark();

	void handIn(String file) throws java.io.IOException;
}
