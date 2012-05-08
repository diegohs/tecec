package tecec.ui.control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.writer.IActivityWriter;
import tecec.dto.Activity;
import tecec.ui.contract.control.INewActivityController;

public class NewActivityController extends BaseController implements
		INewActivityController {

	private String title;
	private String description;
	private String dueDate;

	private tecec.contract.writer.IActivityWriter activityWriter;

	public NewActivityController(IActivityWriter activityWriter) {
		this.activityWriter = activityWriter;
	}

	@Override
	public void setActivityTitle(String title) {
		this.title = title;
		
		super.notifyOfPropertyChange("activityTitle", null, title);
		super.notifyOfPropertyChange("canInsert", null, getCanInsert());
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
		super.notifyOfPropertyChange("canInsert", null, getCanInsert());
	}

	@Override
	public String getActivityDueDate() {
		return this.dueDate;
	}

	@Override
	public RuleViolation getInsertViolation() {
		Activity activity;

		try {
			activity = getActivity();
		} catch (ParseException e) {
			return new RuleViolation(
					"A data de entrega deve ser uma data válida.");
		}

		return this.activityWriter.getInsertViolation(activity);
	}

	@Override
	public boolean getCanInsert() {
		return this.title != null && !this.title.isEmpty() && this.dueDate != null && !this.dueDate.isEmpty();
	}

	@Override
	public void insertActivity() throws RuleViolationException {
		RuleViolation violation = getInsertViolation();

		if (violation != null) {
			throw new RuleViolationException(violation);
		}

		Activity activity;
		
		try {
			activity = getActivity();

		} catch (ParseException e) {
			throw new RuleViolationException(new RuleViolation("A data de entrega deve ser uma data válida"));
		}

		this.activityWriter.insertActivity(activity);
		
		this.setActivityDescription("");
		this.setActivityDueDate("");
		this.setActivityTitle("");
	}

	private Activity getActivity() throws ParseException {
		Activity activity = new Activity();

		activity.setTitle(this.title);
		activity.setDescription(this.description);

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		format.setLenient(false);
		
		Date date = format.parse(this.dueDate);

		activity.setDueDate(date);

		return activity;
	}

}
