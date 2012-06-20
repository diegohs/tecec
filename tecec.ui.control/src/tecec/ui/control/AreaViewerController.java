package tecec.ui.control;

import java.util.ArrayList;
import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.reader.IAreaReader;
import tecec.contract.writer.IAreaWriter;
import tecec.dto.Area;
import tecec.ui.contract.control.IAreaViewerController;
import tecec.ui.contract.record.AreaRecord;
import tecec.ui.contract.view.INewAreaUI;
import tecec.ui.contract.view.IUpdateAreaUI;

public class AreaViewerController extends BaseController implements
		IAreaViewerController {

	String nameFilter;
	AreaRecord selectedArea;

	IAreaWriter areaWriter;
	IAreaReader areaReader;

	public AreaViewerController(IAreaWriter areaWriter, IAreaReader areaReader,
			INewAreaUI newAreaUI, IUpdateAreaUI updateAreaUI) {
		this.areaWriter = areaWriter;
		this.areaReader = areaReader;
		this.newAreaUI = newAreaUI;
		this.updateAreaUI = updateAreaUI;
	}

	INewAreaUI newAreaUI;
	IUpdateAreaUI updateAreaUI;

	@Override
	public void setNameFilter(String nameFilter) {
		this.nameFilter = nameFilter;
		
		super.notifyOfPropertyChange("nameFilter", null, nameFilter);
		super.notifyOfPropertyChange("areas", null, getAreas());
	}

	@Override
	public String getNameFilter() {
		return this.nameFilter;
	}

	@Override
	public void showNewAreaUI() {
		this.newAreaUI.refresh();
		this.newAreaUI.setVisible(true);

		super.notifyOfPropertyChange("areas", null, getAreas());
	}

	@Override
	public void showNewSubAreaUI() {
		this.newAreaUI.refresh();
		this.newAreaUI.setVisible(true);

		super.notifyOfPropertyChange("areas", null, getAreas());
	}

	@Override
	public void showUpdateAreaUI() {
		this.updateAreaUI.setPKArea(this.selectedArea.getArea().getpKArea());
		this.updateAreaUI.setVisible(true);

		super.notifyOfPropertyChange("areas", null, getAreas());
	}

	@Override
	public RuleViolation getDeletionViolation() {
		return this.areaWriter.getDeletionViolation(this.selectedArea.getArea()
				.getpKArea());
	}

	@Override
	public void deleteArea() throws RuleViolationException {
		this.areaWriter.deleteArea(this.selectedArea.getArea().getpKArea());

		super.notifyOfPropertyChange("areas", null, getAreas());
	}

	@Override
	public AreaRecord getSelectedArea() {
		return this.selectedArea;
	}

	@Override
	public void setSelectedArea(AreaRecord area) {
		AreaRecord old = this.selectedArea;

		this.selectedArea = area;

		super.notifyOfPropertyChange("selectedArea", old, area);
		super.notifyOfPropertyChange("canCreateNewSubArea", null,
				getCanCreateNewSubArea());
		super.notifyOfPropertyChange("canUpdateArea", null, getCanUpdateArea());
		super.notifyOfPropertyChange("canDeleteArea", null, getCanDeleteArea());
	}

	@Override
	public boolean getCanCreateNewSubArea() {
		return this.selectedArea != null;
	}

	@Override
	public boolean getCanUpdateArea() {
		return this.selectedArea != null;
	}

	@Override
	public boolean getCanDeleteArea() {
		return this.selectedArea != null;
	}

	@Override
	public List<AreaRecord> getAreas() {
		List<Area> areas = this.areaReader.getAreas(this.nameFilter);

		List<AreaRecord> areaRecords = new ArrayList<AreaRecord>();

		for (Area area : areas) {
			AreaRecord record = new AreaRecord();

			record.setArea(area);

			Area mainArea = this.areaReader.getAreaByPK(area.getfKMainArea());

			if (mainArea != null) {
				record.setMainAreaName(mainArea.getName());
			}

			areaRecords.add(record);
		}

		return areaRecords;
	}

	@Override
	public void refresh() {
		setNameFilter("");
	}
}
