package tecec.ui.contract.control;

import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.dto.*;

public interface IUpdateAreaController {
	void setPKArea(String pKArea);

	void setAreaName(String name);

	String getAreaName();

	void setAreaDescription(String description);

	String getAreaDescription();

	RuleViolation getUpdateViolation();

	List<Area> getAreas();

	Area getSelectedArea();

	void setSelectedArea(Area area);

	void setSelectedAreaIndex(int i);

	int getSelectedAreaIndex();

	void updateArea() throws RuleViolationException;
}
