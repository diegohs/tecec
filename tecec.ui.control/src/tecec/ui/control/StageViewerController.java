package tecec.ui.control;

import java.util.List;


import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.reader.IStageReader;

import tecec.contract.writer.IStageWriter;

import tecec.dto.Stage;

import tecec.ui.contract.control.IStageViewerController;

import tecec.ui.contract.view.INewStageUI;

import tecec.ui.contract.view.IUpdateStageUI;

public class StageViewerController extends BaseController implements IStageViewerController {

	private String nameFilter;
	private Stage selectedStage;
	
	private IStageReader stageReader;
	private IStageWriter stageWriter;
	private INewStageUI newStageUI;
	private IUpdateStageUI updateStageUI;
	
	public StageViewerController (IStageReader stageReader, IStageWriter stageWriter,
			INewStageUI newStageUI, IUpdateStageUI updateStageUI) {
		this.stageReader = stageReader;
		this.stageWriter = stageWriter;
		this.newStageUI = newStageUI;
		this.updateStageUI = updateStageUI;

	}

	@Override
	public void setNameFilter(String nameFilter) {
		
		String old = this.nameFilter;
		this.nameFilter = nameFilter;
		
		this.notifyOfPropertyChange("nameFilter");
		this.notifyOfPropertyChange("stages");


	}

	@Override
	public String getNameFilter() {
		return this.nameFilter;
	}

	@Override
	public void setSelectedStage(Stage stage) {
		Stage old = this.selectedStage;
		this.selectedStage = stage;
		
		super.notifyOfPropertyChange("selectedStage");
		super.notifyOfPropertyChange("canUpdateStage");
		super.notifyOfPropertyChange("canDeleteStage");
	
	}

	@Override
	public Stage getSelectedStage() {
		return this.selectedStage;
	}

	@Override
	public List<Stage> getStages() {
		List <Stage> stages = this.stageReader.getStages(this.nameFilter);
		return stages;

	}

	@Override
	public void deleteStage() throws RuleViolationException {
		this.stageWriter.deleteStage(this.selectedStage.getpKStage());
		
		super.notifyOfPropertyChange("stages");

	}

	@Override
	public boolean getCanUpdateStage() {
		return this.selectedStage != null;
	}

	@Override
	public boolean getCanDeleteStage() {
		return this.selectedStage != null;

	}

	@Override
	public void showNewStageUI() {
		this.newStageUI.refresh();
		this.newStageUI.setVisible(true);
		
		super.notifyOfPropertyChange("stages");		
		
	}

	@Override
	public void newUpdateStageUI() {
		this.updateStageUI.setpKStage(this.selectedStage.getpKStage());
		this.updateStageUI.setVisible(true);
		
		super.notifyOfPropertyChange("stages");
	}

	@Override
	public void refresh() {
		setNameFilter("");
	}

	@Override
	public RuleViolation getDeletionViolation() {
		return this.stageWriter.getDeletionViolation(this.selectedStage.getpKStage());
	}
}
