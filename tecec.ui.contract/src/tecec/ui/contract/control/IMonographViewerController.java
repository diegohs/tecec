package tecec.ui.contract.control;

import java.util.List;
import tecec.dto.Monograph;

public interface IMonographViewerController {

	void setNameFilter (String nameFilter);
	String getNameFilter ();
	
	List<Monograph> getMonographs();
	Monograph getSelectedMonograph();
	void setSelectedMonograph(Monograph monograph);
	
	void showNewMonographUI();
	void showUpdateMonographUI();	
	
	boolean getCanCreateNewMonograph();
	boolean getCanUpdateMonograph();
	boolean getCanDeleteMonograph ();
	
	void deleteMonograph ();
}
