package tecec.ui.control;

import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.reader.IAreaReader;
import tecec.contract.writer.IAreaWriter;
import tecec.dto.Area;
import tecec.ui.contract.control.INewAreaController;

public class NewAreaController extends BaseController implements
		INewAreaController {

	private String areaName;
	private String description;
	private Area selectedArea;
	private int selectedAreaIndex;

	private tecec.contract.reader.IAreaReader areaReader;
	private tecec.contract.writer.IAreaWriter areaWriter;

	public NewAreaController(IAreaReader areaReader, IAreaWriter areaWriter) {
		this.areaReader = areaReader;
		this.areaWriter = areaWriter;
	}

	@Override
	public void setAreaName(String name) {
		String old = this.areaName;

		this.areaName = name;

		super.notifyOfPropertyChange("areaName", null, name);
		super.notifyOfPropertyChange("canCreateArea", old, name);
	}

	@Override
	public String getAreaName() {
		return this.areaName;
	}

	@Override
	public void setDescription(String description) {
		String old = this.description;

		this.description = description;

		super.notifyOfPropertyChange("description", old, description);
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public RuleViolation getCreationViolation() {
		return this.areaWriter.getCreationViolation(getArea());
	}

	@Override
	public void createArea() throws RuleViolationException {
		this.areaWriter.createArea(getArea());

		refresh();
	}

	private Area getArea() {
		Area newArea = new Area();

		newArea.setDescription(this.description);

		if (this.selectedArea != null) {
			newArea.setfKMainArea(this.selectedArea.getpKArea());
		}

		newArea.setName(this.areaName);

		return newArea;
	}

	@Override
	public boolean getCanCreateArea() {
		if (this.areaName == null || this.areaName.isEmpty()) {
			return false;
		}

		return true;
	}

	@Override
	public List<Area> getAreas() {
		List<Area> areas = this.areaReader.getAreas("");

		Area emtpyArea = new Area();

		emtpyArea.setName(" ");

		areas.add(0, emtpyArea);

		return areas;
	}

	@Override
	public Area getSelectedArea() {
		return this.selectedArea;
	}

	@Override
	public void setSelectedArea(Area area) {
		this.selectedArea = area;
	}

	@Override
	public void setSelectedAreaIndex(int i) {
		this.selectedAreaIndex = i;
	}

	@Override
	public int getSelectedAreaIndex() {
		return this.selectedAreaIndex;
	}

	@Override
	public void refresh() {		
		this.setAreaName("");
		this.setDescription("");
		
		super.notifyOfPropertyChange("areas", null, getAreas());
		
		setSelectedArea(null);
		setSelectedAreaIndex(-1);
	}
}
