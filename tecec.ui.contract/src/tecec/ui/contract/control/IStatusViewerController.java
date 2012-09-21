package tecec.ui.contract.control;

import java.util.List;
import tecec.dto.Status;

public interface IStatusViewerController extends IViewerController  {
	
	void setNameFilter (String nameFilter);
	String getNameFilter ();
	
	void setSelectedStatus (Status status);
	Status getSelectedStatus();
	List <Status> getStatus ();
	
	void deleteStatus ();	
	
	boolean getCanUpdateStatus ();
	boolean getCanDeleteStatus ();	
	
	void showNewStatusUI();
	void showUpdateStatusUI();
}
