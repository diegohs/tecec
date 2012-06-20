package tecec.ui.control;

import java.util.List;


import tecec.contract.reader.IStatusReader;

import tecec.contract.writer.IStatusWriter;

import tecec.dto.Status;

import tecec.ui.contract.control.IStatusViewerController;

import tecec.ui.contract.view.INewStatusUI;

import tecec.ui.contract.view.IUpdateStatusUI;

public class StatusViewerController extends BaseController implements
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
		String old = this.nameFilter;
		this.nameFilter = nameFilter;
		
		super.notifyOfPropertyChange("nameFilter", old, nameFilter);
		super.notifyOfPropertyChange("status", null, getStatus());
	}

	@Override
	public String getNameFilter() {
		return this.nameFilter;
	}

	@Override
	public void setSelectedStatus(Status status) {
		Status old = this.selectedStatus;
		this.selectedStatus = status;
		
		super.notifyOfPropertyChange("selectedStatus", old, status);
		super.notifyOfPropertyChange("canUpdateStatus", old, status);
		super.notifyOfPropertyChange("canDeleteStatus", old, status);
		
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
		super.notifyOfPropertyChange("status", null, getStatus());	
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
		
		super.notifyOfPropertyChange("status", null, getStatus());
	}	

	@Override
	public void showUpdateStatusUI() {
		this.updateStatusUI.setpKStatus(this.selectedStatus.getpKStatus());
		this.updateStatusUI.setVisible(true);		
		super.notifyOfPropertyChange("status", null, getStatus());
	}

	@Override
	public void refresh() {
		this.setNameFilter("");
	}
}
