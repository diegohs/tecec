package tecec.ui.contract.control;

import java.util.List;

import tecec.ui.contract.record.MonographRecord;

public interface IMonographViewerController extends IRefreshable  {

	void setNameFilter (String nameFilter);
	String getNameFilter ();
	
	List<MonographRecord> getMonographs();
	MonographRecord getSelectedMonograph();
	void setSelectedMonograph(MonographRecord monograph);
	
	void showNewMonographUI();
	void showUpdateMonographUI();	
	
	boolean getCanUpdateMonograph();
	boolean getCanDeleteMonograph ();
	
	void deleteMonograph ();
}
