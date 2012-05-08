package tecec.ui.contract.control;

import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.dto.Area;

public interface INewAreaController {
	void setAreaName(String name);
	String getAreaName();
	
	void setDescription(String description);
	String getDescription();
	
	RuleViolation getCreationViolation();
	
	List<Area> getAreas();

	Area getSelectedArea();

	void setSelectedArea(Area area);

	void setSelectedAreaIndex(int i);

	int getSelectedAreaIndex();
	
	void createArea() throws RuleViolationException;
	
	boolean getCanCreateArea();
}
