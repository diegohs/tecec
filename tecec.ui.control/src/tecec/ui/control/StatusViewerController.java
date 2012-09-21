package tecec.ui.control;

import java.util.List;


import tecec.contract.reader.IStatusReader;

import tecec.contract.writer.IStatusWriter;

import tecec.dto.Status;

import tecec.ui.contract.control.IStatusViewerController;

import tecec.ui.contract.view.INewStatusUI;

import tecec.ui.contract.view.IUpdateStatusUI;

public class StatusViewerController extends BaseViewerController implements
	IStatusViewerController {
	
	private String nameFilter;
	private Status selectedStatus;
	private IStatusReader statusReader;
	private IStatusWriter statusWriter;
	
	private INewStatusUI newStatusUI;
	private IUpdateStatusUI updateStatusUI;
	
	public StatusViewerController (IStatusReader statusReader, IStatusWriter statusWriter, INewStatusUI newStatusUI, IUpdateStatusUI updateStatusUI) {
		this.statusReader = statusReader;
		this.statusWriter = statusWriter;
		this.newStatusUI = newStatusUI;
		this.updateStatusUI = updateStatusUI;
	}

	@Override
	public void setNameFilter(String nameFilter) {
		this.nameFilter = nameFilter;
		
		super.notifyOfPropertyChange("nameFilter");
		super.notifyOfPropertyChange("status");
	}

	@Override
	public String getNameFilter() {
		return this.nameFilter;
	}

	@Override
	public void setSelectedStatus(Status status) {
		this.selectedStatus = status;
		
		super.notifyOfPropertyChange("selectedStatus");
		super.notifyOfPropertyChange("canUpdateStatus");
		super.notifyOfPropertyChange("canDeleteStatus");
		
	}

	@Override
	public Status getSelectedStatus() {
		return this.selectedStatus;
	}

	@Override
	public List<Status> getStatus() {
		List <Status> status = this.statusReader.getStatus(this.nameFilter);
		return status;	
	}

	@Override
	public void deleteStatus() {
		this.statusWriter.deleteStatus(this.selectedStatus.getpKStatus());
		super.notifyOfPropertyChange("status");	
	}

	@Override
	public boolean getCanUpdateStatus() {
		return this.selectedStatus != null;	
	}

	@Override
	public boolean getCanDeleteStatus() {
		return this.selectedStatus != null;
	}

	@Override
	public void showNewStatusUI() {
		this.newStatusUI.refresh();
		this.newStatusUI.setVisible(true);
		
		super.notifyOfPropertyChange("status");
	}	

	@Override
	public void showUpdateStatusUI() {
		this.updateStatusUI.setpKStatus(this.selectedStatus.getpKStatus());
		this.updateStatusUI.setVisible(true);		
		super.notifyOfPropertyChange("status");
	}

	@Override
	public void refresh() {
		this.setNameFilter("");
	}

	@Override
	protected List<String[]> getExportSource() {
		// TODO Auto-generated method stub
		return null;
	}
}
