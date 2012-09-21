package tecec.ui.contract.control;

import java.util.List;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.ui.contract.record.AreaRecord;

public interface IAreaViewerController extends IViewerController  {
	void setNameFilter(String nameFilter);

	String getNameFilter();

	void showNewAreaUI();
	
	void showNewSubAreaUI();

	void showUpdateAreaUI();

	RuleViolation getDeletionViolation();

	void deleteArea() throws RuleViolationException;

	AreaRecord getSelectedArea();

	void setSelectedArea(AreaRecord area);

	boolean getCanCreateNewSubArea();

	boolean getCanUpdateArea();

	boolean getCanDeleteArea();

	List<AreaRecord> getAreas();
}