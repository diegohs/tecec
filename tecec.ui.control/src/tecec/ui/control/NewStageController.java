package tecec.ui.control;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

import tecec.contract.writer.IStageWriter;
import tecec.ui.contract.control.INewStageController;

public class NewStageController extends BaseController implements INewStageController {

	private IStageWriter stageWriter;
	private String stageName;
	private String stageYear;
	
	public NewStageController (IStageWriter stageWriter) {
		if ( stageWriter == null)
			throw new IllegalArgumentException ("stageWriter");
		this.stageWriter = stageWriter;
	}
	
	@Override
	public String getStageName() {
		return this.stageName;
	}

	@Override
	public void setStageName(String name) {
		String old = getStageName ();
		this.stageName = name;
		notifyOfPropertyChange("stageName", old, name);
	}

	@Override
	public String getStageYear() {
		return this.stageYear;
	}

	@Override
	public void setStageYear(String year) {
		String old = getStageYear ();
		this.stageYear = year;
		notifyOfPropertyChange("stageYear", old, year);
		
	}

	@Override
	public void createStage() throws RuleViolationException {
		RuleViolation violation = getCreationViolation ();
		if (violation != null)
			throw new RuleViolationException (violation);
		
		stageWriter.createStage(this.stageName, this.stageYear);
		
		refresh();
	}

	@Override
	public RuleViolation getCreationViolation() {
		return stageWriter.getCreationViolation(this.getStageName(), this.getStageYear());
	}

	@Override
	public void refresh() {
		setStageName("");
		setStageYear((new SimpleDateFormat("yyyy")).format(Calendar.getInstance().getTime()));
	}
	


}
