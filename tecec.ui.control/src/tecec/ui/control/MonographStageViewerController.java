package tecec.ui.control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.reader.IMonographReader;
import tecec.contract.reader.IStageReader;
import tecec.contract.writer.IMonographWriter;
import tecec.dto.Monograph;
import tecec.dto.Stage;
import tecec.ui.contract.control.IMonographStageViewerController;

public class MonographStageViewerController extends BaseController implements
		IMonographStageViewerController {

	Monograph monograph;

	IStageReader stageReader;
	IMonographReader monographReader;
	IMonographWriter monographWriter;

	Stage selectedCorrelatedStage;
	Stage selectedStage;

	List<Stage> stages;
	List<Stage> correlatedStages;

	public MonographStageViewerController(IStageReader stageReader,
			IMonographReader monographReader,
			IMonographWriter monographWriter) {
		super();
		this.stageReader = stageReader;
		this.monographReader = monographReader;
		this.monographWriter = monographWriter;
	}

	@Override
	public void setMonograph(String pKMonograph) {
		this.monograph = this.monographReader.getMonographByPK(pKMonograph);

		this.loadCorrelatedStages();
		this.loadStages();
	}

	@Override
	public List<Stage> getCorrelatedStages() {
		return this.correlatedStages;
	}

	@Override
	public List<Stage> getStages() {
		return this.stages;
	}

	@Override
	public Stage getSelectedStage() {
		return this.selectedStage;
	}

	@Override
	public RuleViolation getInsertViolation() {
		return null;
	}

	@Override
	public RuleViolation getDeleteViolation() {
		boolean hasHandIns = this.monographReader.doesMonographHaveHandIns(
				this.monograph.getpKMonograph(),
				this.selectedCorrelatedStage.getpKStage());

		if (hasHandIns) {
			return new RuleViolation(
					"Não é possível excluir uma etapa de uma monografia que já recebeu atividades.");
		}

		return null;
	}

	@Override
	public void insertMonographStage() throws RuleViolationException {
		RuleViolation violation = getInsertViolation();

		if (violation != null) {
			throw new RuleViolationException(violation);
		}

		List<Stage> newStages = new ArrayList<Stage>();
		List<Stage> newCorrelatedStages = new ArrayList<Stage>();
		
		newStages.addAll(this.stages);
		newCorrelatedStages.addAll(this.correlatedStages);
		
		newStages.remove(this.selectedStage);
		newCorrelatedStages.add(this.selectedStage);
		
		this.stages = newStages;
		this.correlatedStages = newCorrelatedStages;
		
		super.notifyOfPropertyChange("stages", null, this.stages);
		super.notifyOfPropertyChange("correlatedStages", null, this.correlatedStages);
	}

	@Override
	public void deleteMonographStage() throws RuleViolationException {
		RuleViolation violation = getDeleteViolation();

		if (violation != null) {
			throw new RuleViolationException(violation);
		}

		List<Stage> newStages = new ArrayList<Stage>();
		List<Stage> newCorrelatedStages = new ArrayList<Stage>();
		
		newStages.addAll(this.stages);
		newCorrelatedStages.addAll(this.correlatedStages);

		newCorrelatedStages.remove(this.selectedCorrelatedStage);
		newStages.add(this.selectedCorrelatedStage);
		
		this.stages = newStages;
		this.correlatedStages = newCorrelatedStages;
		
		super.notifyOfPropertyChange("stages", null, this.stages);
		super.notifyOfPropertyChange("correlatedStages", null, this.correlatedStages);
	}

	@Override
	public Stage getSelectedCorrelatedStage() {
		return this.selectedCorrelatedStage;
	}

	private void loadStages() {
		List<Stage> stages = this.stageReader.getStages("");

		for (Stage stage : this.correlatedStages) {
			for (int i = 0; i < stages.size(); i++) {
				if (stages.get(i).getName().equals(stage.getName())) {
					stages.remove(i);
					i--;
				}
			}
		}

		this.stages = stages;

		super.notifyOfPropertyChange("stages", null, this.stages);
		super.notifyOfPropertyChange("canInsert", null, this.getCanInsert());
	}

	private void loadCorrelatedStages() {
		this.correlatedStages = stageReader.getStagesByMonograph(this.monograph
				.getpKMonograph());

		super.notifyOfPropertyChange("correlatedStages", null,
				this.correlatedStages);
		super.notifyOfPropertyChange("canDelete", null, this.getCanDelete());
	}

	@Override
	public void setSelectedStage(Stage stage) {
		this.selectedStage = stage;

		super.notifyOfPropertyChange("selectedStage", null, this.selectedStage);
		super.notifyOfPropertyChange("canInsert", null, this.getCanInsert());
	}

	@Override
	public void setSelectedCorrelatedStage(Stage stage) {
		this.selectedCorrelatedStage = stage;

		super.notifyOfPropertyChange("selectedCorrelatedStage", null, this.selectedCorrelatedStage);
		super.notifyOfPropertyChange("canDelete", null, this.getCanDelete());
	}

	@Override
	public boolean getCanInsert() {
		return this.selectedStage != null;
	}

	@Override
	public boolean getCanDelete() {
		return this.selectedCorrelatedStage != null;
	}

	@Override
	public void commit() throws RuleViolationException {
		List<Stage> oldCorrelatedStages = this.stageReader.getStagesByMonograph(this.monograph.getpKMonograph());
		
		for(Stage oldStage : oldCorrelatedStages){
			if (!this.correlatedStages.contains(oldStage)) {
				this.monographWriter.deleteMonographStage(this.monograph.getpKMonograph(), oldStage.getpKStage());
			}
		}
		
		for(Stage newStage : this.correlatedStages){
			if (!oldCorrelatedStages.contains(newStage)) {
				this.monographWriter.insertMonographStage(this.monograph.getpKMonograph(), newStage.getpKStage());
			}
		}
	}

}
