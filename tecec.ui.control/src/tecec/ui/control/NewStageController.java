package tecec.ui.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.writer.IStageWriter;

import tecec.ui.contract.control.INewStageController;

public class NewStageController extends BaseController implements INewStageController {
	
	private IStageWriter stageWriter;
	private String stageName;
	private Integer stageYear;
	
	public NewStageController (IStageWriter stageWriter) {
		if (stageWriter == null)
			throw new IllegalArgumentException ("stageWriter");
		this.stageWriter = stageWriter;
		
	}

	@Override
	public String getStageName() {
		return this.stageName;
	}

	@Override
	public void setStageName(String name) {
		String oldValue = getStageName ();
		
		this.stageName = name;
		
		notifyOfPropertyChange("stageName", oldValue, name);
		
	}

	@Override
	public Integer getStageYear() {
		return this.stageYear;
	}

	@Override
	public void setStageYear(Integer year) {
		Integer old = getStageYear ();
		
		this.stageYear = year;
		
		notifyOfPropertyChange("stageYear",old,year);
		
	}

	@Override
	public void createStage() throws RuleViolationException {
	RuleViolation violation = getCreationViolation ();
		
		if (violation != null)
			throw new RuleViolationException (violation);
		
		stageWriter.createStage(this.stageName, this.stageYear);		
		setStageName ("");
		setStageYear (null);
		
	}

	@Override
	public RuleViolation getCreationViolation() {
		return this.stageWriter.getCreationViolation(this.stageName, this.stageYear);
	}

}
