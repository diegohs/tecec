package tecec.ui.control;

import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.contract.reader.IAreaReader;
import tecec.contract.writer.IAreaWriter;
import tecec.dto.Area;
import tecec.ui.contract.control.IUpdateAreaController;

public class UpdateAreaController extends BaseController implements
		IUpdateAreaController {

	private IAreaWriter areaWriter;
	private IAreaReader areaReader;

	public UpdateAreaController(IAreaWriter areaWriter, IAreaReader areaReader) {
		this.areaWriter = areaWriter;
		this.areaReader = areaReader;
		
		setSelectedAreaIndex(-1);
	}

	private String pKArea;
	private String areaName;
	private String areaDescription;
	private Area selectedArea;
	private int selectedAreaIndex;

	@Override
	public void setPKArea(String pKArea) {		
		this.pKArea = pKArea;

		Area area = this.areaReader.getAreaByPK(this.pKArea);

		this.setAreaDescription(area.getDescription());
		this.setAreaName(area.getName());

		List<Area> areas = getAreas();

		super.notifyOfPropertyChange("areas", null, areas);		

		if (area.getfKMainArea() != null && !area.getfKMainArea().isEmpty()) {
			setSelectedArea(null);
			setSelectedAreaIndex(-1);
			
			for (int i = 0; i < areas.size(); i++) {
				if (area.getfKMainArea().equals(areas.get(i).getpKArea())) {
					setSelectedAreaIndex(i);

					break;
				}
			}
		}
	}

	@Override
	public void setSelectedAreaIndex(int i) {
		this.selectedAreaIndex = i;
		
		super.notifyOfPropertyChange("selectedAreaIndex", null, i);
	}

	@Override
	public int getSelectedAreaIndex() {
		return this.selectedAreaIndex;
	}

	@Override
	public void setAreaName(String name) {
		this.areaName = name;

		super.notifyOfPropertyChange("areaName", null, name);
	}

	@Override
	public String getAreaName() {
		return this.areaName;
	}

	@Override
	public void setAreaDescription(String description) {
		this.areaDescription = description;

		super.notifyOfPropertyChange("areaDescription", null, description);
	}

	@Override
	public String getAreaDescription() {
		return this.areaDescription;
	}

	@Override
	public RuleViolation getUpdateViolation() {
		Area area = getArea();

		return this.areaWriter.getUpdateViolation(area);
	}

	private Area getArea() {
		Area area = new Area();

		area.setpKArea(this.pKArea);
		area.setName(this.areaName);
		area.setDescription(this.areaDescription);

		if (this.getSelectedArea() != null) {
			area.setfKMainArea(this.getSelectedArea().getpKArea());
		}

		return area;
	}

	@Override
	public List<Area> getAreas() {
		List<Area> areas = this.areaReader.getAreas("");

		Area emtpyArea = new Area();
		
		emtpyArea.setName(" ");

		areas.add(0, emtpyArea);
		
		if (this.pKArea != null && !this.pKArea.isEmpty()) {
			for (int i = 0; i < areas.size(); i++) {
				if (this.pKArea.equals(areas.get(i).getpKArea())) {
					areas.remove(i);

					break;
				}
			}
		}

		return areas;
	}

	@Override
	public Area getSelectedArea() {
		return this.selectedArea;
	}

	@Override
	public void setSelectedArea(Area area) {
		this.selectedArea = area;
		
		super.notifyOfPropertyChange("selectedArea", null, area);
	}

	@Override
	public void updateArea() throws RuleViolationException {
		Area area = getArea();

		this.areaWriter.updateArea(area);
	}

	@Override
	public void refresh() {
		setPKArea(this.pKArea);
	}

}
