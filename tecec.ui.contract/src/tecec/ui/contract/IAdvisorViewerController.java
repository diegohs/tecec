package tecec.ui.contract;

import java.util.List;

import tecec.dto.Advisor;

public interface IAdvisorViewerController {

	void setNameFilter(String nameFilter);

	String getNameFilter();

	void setSelectedAdvisor(Advisor advisor);

	Advisor getSelectedAdvisor();

	List<Advisor> getAdvisors();
}
