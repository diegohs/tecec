package tecec.ui.control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.reader.IStageReader;
import tecec.contract.writer.IActivityWriter;
import tecec.dto.Activity;
import tecec.dto.Stage;
import tecec.ui.contract.control.INewActivityController;

public class NewActivityController extends BaseController implements
		INewActivityController {

	private String title;
	private String description;
	private String dueDate;

	private int selectedStageIndex;
	private Stage selectedStage;

	private IActivityWriter activityWriter;
	private IStageReader stageReader;

	public NewActivityController(IActivityWriter activityWriter,
			IStageReader stageReader) {
		this.activityWriter = activityWriter;
		this.stageReader = stageReader;

		this.selectedStageIndex = -1;
	}

	@Override
	public void setActivityTitle(String title) {
		this.title = title;

		super.notifyOfPropertyChange("activityTitle");
		super.notifyOfPropertyChange("canInsert");
	}

	@Override
	public String getActivityTitle() {
		return this.title;
	}

	@Override
	public void setActivityDescription(String description) {
		this.description = description;

		super.notifyOfPropertyChange("activityDescription");
	}

	@Override
	public String getActivityDescription() {
		return this.description;
	}

	@Override
	public void setActivityDueDate(String dueDate) {
		this.dueDate = dueDate;

		super.notifyOfPropertyChange("activityDueDate");
		super.notifyOfPropertyChange("canInsert");
	}

	@Override
	public String getActivityDueDate() {
		return this.dueDate;
	}

	@Override
	public RuleViolation getInsertViolation() {
		if (this.selectedStage == null) {
			return new RuleViolation(
					"A atividade deve ser vinculada � uma etapa.");
		}

		Activity activity;

		try {
			activity = getActivity();
		} catch (ParseException e) {
			return new RuleViolation(
					"A data de entrega deve ser uma data v�lida.");
		}

		return this.activityWriter.getInsertViolation(activity);
	}

	@Override
	public boolean getCanInsert() {
		return this.title != null && !this.title.isEmpty()
				&& this.dueDate != null && !this.dueDate.isEmpty()
				&& this.selectedStage != null;
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
			throw new RuleViolationException(new RuleViolation(
					"A data de entrega deve ser uma data v�lida"));
		}

		this.activityWriter.insertActivity(activity);

		refresh();
	}

	private Activity getActivity() throws ParseException {
		Activity activity = new Activity();

		activity.setTitle(this.title);
		activity.setDescription(this.description);

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		format.setLenient(false);

		Date date = format.parse(this.dueDate);

		activity.setDueDate(date);

		activity.setFKStage(this.selectedStage.getpKStage());

		return activity;
	}

	@Override
	public List<Stage> getStages() {
		return this.stageReader.getStages("");
	}

	@Override
	public Stage getSelectedStage() {
		return this.selectedStage;
	}

	@Override
	public void setSelectedStage(Stage stage) {
		this.selectedStage = stage;

		super.notifyOfPropertyChange("canInsert");
		super.notifyOfPropertyChange("selectedStage");
	}

	@Override
	public void setSelectedStageIndex(int i) {
		this.selectedStageIndex = i;

		super.notifyOfPropertyChange("selectedStageIndex");
	}

	@Override
	public int getSelectedStageIndex() {
		return this.selectedStageIndex;
	}

	@Override
	public void refresh() {
		this.setActivityDescription("");
		this.setActivityDueDate(null);
		this.setActivityTitle("");
		this.setSelectedStage(null);
		
		super.notifyOfPropertyChange("stages");
		
		this.setSelectedStageIndex(-1);

	}

}
