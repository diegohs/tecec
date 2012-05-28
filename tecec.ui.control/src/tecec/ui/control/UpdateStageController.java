package tecec.ui.control;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.reader.IStageReader;
import tecec.contract.writer.IStageWriter;
import tecec.dto.Stage;

import tecec.ui.contract.control.IUpdateStageController;

public class UpdateStageController extends BaseController implements IUpdateStageController {
	
	private String stageName;
	private Integer stageYear;
	private String pKStage;
	private IStageReader stageReader;
	private IStageWriter stageWriter;
	
	public UpdateStageController (IStageReader stageReader, IStageWriter stageWriter) {
		this.stageReader = stageReader;
		this.stageWriter = stageWriter;
	}

	@Override
	public void setPKStage(String pKStage) {
		this.pKStage = pKStage;
		Stage stage = this.stageReader.getStageByPK(pKStage);
		
		if (stage != null) {
			this.setStageName(stage.getName());
			this.setStageYear(stage.getYear());
		}
	}

	@Override
	public void setStageName(String name) {
		String old = this.stageName;
		this.stageName = name;		
		super.notifyOfPropertyChange("stageName", old, name);
		
	}

	@Override
	public String getStageName() {
		return this.stageName;
	}

	@Override
	public void setStageYear(Integer year) {
		Integer old = this.stageYear;
		this.stageYear = year;
		super.notifyOfPropertyChange("stageYear", old, year);
		
	}

	@Override
	public Integer getStageYear() {
		return this.stageYear;
	}

	@Override
	public void updateStage() throws RuleViolationException {
		RuleViolation violation = getUpdateViolation ();
		if (violation != null)
			throw new RuleViolationException (violation);
		
		this.stageWriter.updateStage(this.pKStage, this.stageName, this.stageYear);
		
	}

	@Override
	public RuleViolation getUpdateViolation() {
		return this.stageWriter.getUpdateViolation(this.pKStage, this.stageName, this.stageYear);
	}
}
