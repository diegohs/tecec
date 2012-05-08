package tecec.ui.control;

import java.util.List;

import tecec.contract.reader.IAdvisorReader;
import tecec.contract.writer.IAdvisorWriter;
import tecec.dto.Advisor;
import tecec.ui.contract.control.IAdvisorViewerController;
import tecec.ui.contract.view.INewAdvisorUI;
import tecec.ui.contract.view.IUpdateAdvisorUI;

public class AdvisorViewerController  extends BaseController implements IAdvisorViewerController{

	private String nameFilter;
	private Advisor selectedAdvisor;
	
	private IAdvisorReader advisorReader;
	private IAdvisorWriter advisorWriter;	
	private INewAdvisorUI newAdvisorUI;
	private IUpdateAdvisorUI updateAdvisorUI;
	
	public AdvisorViewerController (IAdvisorReader advisorReader, IAdvisorWriter advisorWriter, 
			INewAdvisorUI newAdvisorUI, IUpdateAdvisorUI updateAdviorUI) {
		this.advisorReader = advisorReader;
		this.advisorWriter = advisorWriter;
		this.newAdvisorUI = newAdvisorUI;
		this.updateAdvisorUI = updateAdviorUI;
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
		super.notifyOfPropertyChange("canUpdateAdvisor", old, advisor);
		super.notifyOfPropertyChange("canDeleteAdvisor", old, advisor);		
	}

	@Override
	public Advisor getSelectedAdvisor() {
		return this.selectedAdvisor;
	}

	@Override
	public List<Advisor> getAdvisors() {
		List<Advisor> advisors = this.advisorReader.getAdvisors(this.nameFilter);		
		return advisors;
	}

	@Override
	public void deleteAdvisor() {
		this.advisorWriter.deleteAdvisor(this.selectedAdvisor.getPKAdvisor());			
		super.notifyOfPropertyChange("advisors", null, getAdvisors());
		
	}

	@Override
	public boolean getCanUpdateAdvisor() {
		return this.selectedAdvisor != null;
	}

	@Override
	public boolean getCanDeleteAdvisor() {
		return this.selectedAdvisor != null;
	}

	@Override
	public void showNewAdvisorUI() {
		this.newAdvisorUI.setVisible(true);		
		super.notifyOfPropertyChange("advisors", null, getAdvisors());			
	}

	@Override
	public void showUpdateAdvisorUI() {
		this.updateAdvisorUI.setpkAdvisor(this.selectedAdvisor.getPKAdvisor());
		this.updateAdvisorUI.setVisible(true);		
		super.notifyOfPropertyChange("advisors", null, getAdvisors());		
	}

}
