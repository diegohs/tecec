package tecec.ui.control;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.reader.IActivityReader;
import tecec.contract.writer.IActivityWriter;
import tecec.dto.Activity;
import tecec.ui.contract.control.IUpdateActivityController;

public class UpdateActivityController extends BaseController implements IUpdateActivityController {

	private String pKActivity;
	private String title;
	private String description;
	private String dueDate;
	
	public UpdateActivityController(IActivityReader activityReader,
			IActivityWriter activityWriter) {
		super();
		this.activityReader = activityReader;
		this.activityWriter = activityWriter;
	}

	private tecec.contract.reader.IActivityReader activityReader;
	private tecec.contract.writer.IActivityWriter activityWriter;
	
	
	@Override
	public void setPKActivity(String pKActivity) {
		this.pKActivity = pKActivity;
		
		Activity activity = this.activityReader.getActivityByPK(pKActivity);
		
		this.setActivityTitle(activity.getTitle());
		this.setActivityDescription(activity.getDescription());			
		this.setActivityDueDate(new SimpleDateFormat("dd/MM/yyyy").format(activity.getDueDate()));
	}

	@Override
	public void setActivityTitle(String title) {
		this.title = title;

		super.notifyOfPropertyChange("activityTitle", null, title);
		super.notifyOfPropertyChange("canUpdate", null, getCanUpdate());
	}

	@Override
	public String getActivityTitle() {
		return this.title;
	}

	@Override
	public void setActivityDescription(String description) {
		this.description = description;

		super.notifyOfPropertyChange("activityDescription", null, description);
	}

	@Override
	public String getActivityDescription() {
		return this.description;
	}

	@Override
	public void setActivityDueDate(String dueDate) {
		this.dueDate = dueDate;

		super.notifyOfPropertyChange("activityDueDate", null, dueDate);
		super.notifyOfPropertyChange("canUpdate", null, getCanUpdate());
	}

	@Override
	public String getActivityDueDate() {
		return this.dueDate;
	}

	@Override
	public RuleViolation getUpdateViolation() {
		Activity activity;
		
		try {
			activity = getActivity();
		} catch (ParseException e) {
			return new RuleViolation("A data de entrega deve ser uma data válida");
		}
		
		return this.activityWriter.getUpdateViolation(activity);
	}

	@Override
	public boolean getCanUpdate() {
		return this.title != null && !this.title.isEmpty() && this.dueDate != null && !this.dueDate.isEmpty();
	}

	@Override
	public void updateActivity() throws RuleViolationException {
		RuleViolation violation = getUpdateViolation();
		
		if (violation != null) {
			throw new RuleViolationException(violation);
		}
		
		Activity activity;
		
		try {
			activity = getActivity();
		} catch (ParseException e) {
			throw new RuleViolationException(new RuleViolation("A data de entrega deve ser uma data válida"));
		}
		
		this.activityWriter.updateActivity(activity);
		
		this.setActivityDescription("");
		this.setActivityDueDate("");
		this.setActivityTitle("");
	}
	
	private Activity getActivity() throws ParseException {
		Activity activity = new Activity();
		
		activity.setpKActivity(this.pKActivity);
		activity.setTitle(this.title);
		activity.setDescription(this.description);
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		format.setLenient(false);		
		
		activity.setDueDate(format.parse(this.dueDate));
		
		return activity;
	}

}
