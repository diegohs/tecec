package tecec.ui.contract.control;

import java.util.List;
import tecec.dto.Monograph;

public interface IMonographViewerController {

	void setNameFilter (String nameFilter);
	String getNameFilter ();
	
	void setSelectedMonograph (Monograph monograph);
	Monograph getSelectedMonograph();
	List <Monograph> getMonograph ();
	
	void deleteMonograph ();	
	
	boolean getCanUpdateMonograph ();
	boolean getCanDeleteMonograph ();	
	
	void showNewMonographUI();
	void showUpdateMonographUI();
}
