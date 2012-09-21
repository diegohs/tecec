package tecec.ui.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;

import tecec.contract.reader.IStageReader;

import tecec.contract.writer.IStageWriter;

import tecec.dto.Stage;

import tecec.ui.contract.control.IUpdateStageController;

public class UpdateStageController extends BaseController implements IUpdateStageController {
	
	private String pKStage;
	private String stageName;
	private String stageYear;
	
	private IStageWriter stageWriter;
	private IStageReader stageReader;
	
	public UpdateStageController (IStageWriter stageWriter, IStageReader stageReader) {
		this.stageWriter = stageWriter;
		this.stageReader = stageReader;
	}	

	@Override
	public void setPKStage(String pKStage) {		
		this.pKStage = pKStage;
		Stage stage = this.stageReader.getStageByPK(pKStage);
		
		this.setStageName(stage.getName());
		this.setStageYear(stage.getYear());		
	}

	@Override
	public void setStageName(String name) {
		this.stageName = name;
		
		super.notifyOfPropertyChange("stageName");
		super.notifyOfPropertyChange("canUpdate");

	}

	@Override
	public void setStageYear(String year) {
		this.stageYear = year;
		
		super.notifyOfPropertyChange("stageYear");
		super.notifyOfPropertyChange("canUpdate");		
	}

	@Override
	public String getStageName() {
		return this.stageName;
	}

	@Override
	public String getStageYear() {
		return this.stageYear;
	}

	@Override
	public boolean getCanUpdate() {
		return this.stageYear != null && this.stageName != null &&
				!this.stageYear.trim().isEmpty() && !this.stageName.trim().isEmpty();
		
	}

	@Override
	public void updateStage() throws RuleViolationException {
		RuleViolation violation = getUpdateViolation ();
		
		if (violation != null)
			throw new RuleViolationException (violation);
		
		this.stageWriter.updateStage(this.pKStage, this.stageName, this.stageYear);
		
		refresh();
	}

	@Override
	public RuleViolation getUpdateViolation() {
		Stage stage = new Stage();
		stage.setpKStage(this.pKStage);
		stage.setName(this.stageName);
		stage.setYear (this.stageYear);
		return this.stageWriter.getUpdateViolation(stage);
	}

	@Override
	public void refresh() {
		setPKStage(this.pKStage);
	}
	
	
}