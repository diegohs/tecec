package tecec.ui.control;

import java.util.List;

import tecec.contract.reader.IAdvisorReader;
import tecec.dto.Advisor;
import tecec.ui.contract.IAdvisorViewerController;

public class AdvisorViewerController extends BaseController implements IAdvisorViewerController{

	private String nameFilter;
	private Advisor selectedAdvisor;
	private IAdvisorReader advisorReader;
	
	public AdvisorViewerController (IAdvisorReader advisorReader) {
		this.advisorReader = advisorReader;
	}
	
	@Override
	public void setNameFilter(String nameFilter) {
		String old = this.nameFilter;
		this.nameFilter = nameFilter;
		super.notifyOfPropertyChange("nameFilter", old, nameFilter);
		super.notifyOfPropertyChange("advisors", null, getAdvisors());		
	}

	@Override
	public String getNameFilter() {
		return this.nameFilter;
	}

	@Override
	public void setSelectedAdvisor(Advisor advisor) {
		Advisor old = this.selectedAdvisor;
		this.selectedAdvisor = advisor;		
		super.notifyOfPropertyChange("selectedAdvisor", old, advisor);		
	}

	@Override
	public Advisor getSelectedAdvisor() {
		return this.selectedAdvisor;
	}

	@Override
	public List<Advisor> getAdvisors() {
		List <Advisor> advisors = this.advisorReader.getAdvisors(this.nameFilter);
		return advisors;
	}
	
	
}
