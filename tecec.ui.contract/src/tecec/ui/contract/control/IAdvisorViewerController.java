package tecec.ui.contract.control;

import java.util.List;

import tecec.dto.Advisor;

public interface IAdvisorViewerController extends IViewerController  {
	void setNameFilter (String nameFilter);
	String getNameFilter ();
	
	void setSelectedAdvisor (Advisor advisor);
	Advisor getSelectedAdvisor ();
	List <Advisor> getAdvisors();
	
	void deleteAdvisor();
	
	boolean getCanUpdateAdvisor(); 
	boolean getCanDeleteAdvisor();
	
	void showNewAdvisorUI();
	void showUpdateAdvisorUI();


}
