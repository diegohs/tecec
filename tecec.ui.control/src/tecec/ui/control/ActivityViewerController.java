package tecec.ui.control;

import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.reader.IActivityReader;
import tecec.contract.writer.IActivityWriter;
import tecec.dto.Activity;
import tecec.ui.contract.control.IActivityViewerController;
import tecec.ui.contract.view.INewActivityUI;
import tecec.ui.contract.view.IUpdateActivityUI;

public class ActivityViewerController extends BaseController implements
		IActivityViewerController {

	private String title;
	private Activity selectedActivity;

	private tecec.contract.writer.IActivityWriter activityWriter;
	private tecec.contract.reader.IActivityReader activityReader;
	private tecec.ui.contract.view.INewActivityUI newActivityUI;
	private tecec.ui.contract.view.IUpdateActivityUI updateActivityUI;

	public ActivityViewerController(INewActivityUI newActivityUI,
			IUpdateActivityUI updateActivityUI, IActivityWriter activityWriter, IActivityReader activityReader) {
		this.newActivityUI = newActivityUI;
		this.updateActivityUI = updateActivityUI;
		this.activityWriter = activityWriter;
		this.activityReader = activityReader;
	}

	@Override
	public void setTitleFilter(String filter) {
		this.title = filter;

		super.notifyOfPropertyChange("activities", null, getActivities());
	}

	@Override
	public String getTitleFilter() {
		return this.title;
	}

	@Override
	public boolean getCanUpdate() {
		return this.selectedActivity != null;
	}

	@Override
	public boolean getCanDelete() {
		return this.selectedActivity != null;
	}

	@Override
	public void showNewActivityUI() {
		this.newActivityUI.setVisible(true);

		super.notifyOfPropertyChange("activities", null, getActivities());
	}

	@Override
	public void showUpdateActivityUI() {
		this.updateActivityUI.setPKActivity(this.selectedActivity.getpKActivity());
		this.updateActivityUI.setVisible(true);

		super.notifyOfPropertyChange("activities", null, getActivities());
	}

	@Override
	public void deleteActivity() throws RuleViolationException {
		RuleViolation violation = getDeletionViolation();

		if (violation != null) {
			throw new RuleViolationException(violation);
		}
		
		this.activityWriter.deleteActivity(this.selectedActivity.getpKActivity());

		super.notifyOfPropertyChange("activities", null, getActivities());
	}

	@Override
	public RuleViolation getDeletionViolation() {
		return null;
	}

	@Override
	public List<Activity> getActivities() {
		if (this.title == null) {
			this.title = "";
		}
		
		return this.activityReader.getActivities(this.title);
	}

	@Override
	public void setSelectedActivity(Activity activity) {
		this.selectedActivity = activity;
		
		super.notifyOfPropertyChange("canUpdate", null, getCanUpdate());
		super.notifyOfPropertyChange("canDelete", null, getCanDelete());
	}

	@Override
	public Activity getSelectedActivity() {
		return this.selectedActivity;
	}

}
