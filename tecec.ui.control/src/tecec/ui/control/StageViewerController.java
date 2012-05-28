package tecec.ui.control;

import java.util.List;

import tecec.contract.reader.IStageReader;

import tecec.contract.writer.IStageWriter;

import tecec.dto.Stage;

import tecec.ui.contract.control.IStageViewerController;
import tecec.ui.contract.view.INewStageUI;

import tecec.ui.contract.view.IUpdateStageUI;


public class StageViewerController extends BaseController implements IStageViewerController{
	
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
		
		super.notifyOfPropertyChange("nameFilter", old, nameFilter);
		super.notifyOfPropertyChange("status", null, getStages());
		
	}

	@Override
	public String getNameFilter() {
		return this.nameFilter;
	}

	@Override
	public void setSelectedStage(Stage stage) {
		Stage old = this.selectedStage;
		this.selectedStage = stage;
		
		super.notifyOfPropertyChange("selectedStage", old, stage);
		super.notifyOfPropertyChange("canUpdateStage", old, stage);
		super.notifyOfPropertyChange("canDeleteStage", old, stage);
		
	}

	@Override
	public Stage getSelectedStage() {
		return this.selectedStage;
	}

	@Override
	public List<Stage> getStages() {
		List <Stage> stage = this.stageReader.getStage(this.nameFilter);
		return stage;	
	}

	@Override
	public void deleteStage() {
		this.stageWriter.deleteStage(this.selectedStage.getpKStage());
		super.notifyOfPropertyChange("status", null, getStages());	
		
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
		this.newStageUI.setVisible(true);
		super.notifyOfPropertyChange("stage", null, getStages());
		
	}

	@Override
	public void showUpdateStageUI() {
		this.updateStageUI.setpKStage(this.selectedStage.getpKStage());
		this.updateStageUI.setVisible(true);		
		super.notifyOfPropertyChange("stage", null, getStages());
		
	}

}
