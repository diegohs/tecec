package tecec.ui.control;

import java.util.ArrayList;
import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.reader.IAreaReader;
import tecec.contract.reporting.IAccountReporter;
import tecec.contract.reporting.IAreaReporter;
import tecec.contract.writer.IAreaWriter;
import tecec.dto.Area;
import tecec.dto.record.AreaRecord;
import tecec.ui.contract.control.IAreaViewerController;
import tecec.ui.contract.view.INewAreaUI;
import tecec.ui.contract.view.IUpdateAreaUI;

public class AreaViewerController extends BaseViewerController implements
		IAreaViewerController {

	String nameFilter;
	AreaRecord selectedArea;

	IAreaWriter areaWriter;
	IAreaReader areaReader;
	IAreaReporter areaReporter;

	public AreaViewerController(IAreaWriter areaWriter, IAreaReader areaReader,
			INewAreaUI newAreaUI, IUpdateAreaUI updateAreaUI, IAreaReporter reporter) {
		super(reporter);
		
		this.areaWriter = areaWriter;
		this.areaReader = areaReader;
		this.newAreaUI = newAreaUI;
		this.updateAreaUI = updateAreaUI;
		this.areaReporter = reporter;
	}

	INewAreaUI newAreaUI;
	IUpdateAreaUI updateAreaUI;

	@Override
	public void setNameFilter(String nameFilter) {
		this.nameFilter = nameFilter;
		
		super.notifyOfPropertyChange("nameFilter");
		super.notifyOfPropertyChange("areas");
	}

	@Override
	public String getNameFilter() {
		return this.nameFilter;
	}

	@Override
	public void showNewAreaUI() {
		this.newAreaUI.refresh();
		this.newAreaUI.setVisible(true);

		super.notifyOfPropertyChange("areas");
	}

	@Override
	public void showNewSubAreaUI() {
		this.newAreaUI.refresh();
		this.newAreaUI.setVisible(true);

		super.notifyOfPropertyChange("areas");
	}

	@Override
	public void showUpdateAreaUI() {
		this.updateAreaUI.setPKArea(this.selectedArea.getArea().getpKArea());
		this.updateAreaUI.setVisible(true);

		super.notifyOfPropertyChange("areas");
	}

	@Override
	public RuleViolation getDeletionViolation() {
		return this.areaWriter.getDeletionViolation(this.selectedArea.getArea()
				.getpKArea());
	}

	@Override
	public void deleteArea() throws RuleViolationException {
		this.areaWriter.deleteArea(this.selectedArea.getArea().getpKArea());

		super.notifyOfPropertyChange("areas");
	}

	@Override
	public AreaRecord getSelectedArea() {
		return this.selectedArea;
	}

	@Override
	public void setSelectedArea(AreaRecord area) {
		this.selectedArea = area;

		super.notifyOfPropertyChange("selectedArea");
		super.notifyOfPropertyChange("canCreateNewSubArea");
		super.notifyOfPropertyChange("canUpdateArea");
		super.notifyOfPropertyChange("canDeleteArea");
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

	@Override
	protected List<String[]> getExportSource() {
		return this.areaReporter.format(this.getAreas());
	}
}
