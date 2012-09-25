package tecec.ui.control;

import java.util.List;

import tecec.contract.reader.IAdvisorReader;
import tecec.contract.reporting.IAdvisorReporter;
import tecec.contract.writer.IAdvisorWriter;
import tecec.dto.Advisor;
import tecec.ui.contract.control.IAdvisorViewerController;
import tecec.ui.contract.view.INewAdvisorUI;
import tecec.ui.contract.view.IUpdateAdvisorUI;

public class AdvisorViewerController  extends BaseViewerController implements IAdvisorViewerController{

	private String nameFilter;
	private Advisor selectedAdvisor;
	
	private IAdvisorReader advisorReader;
	private IAdvisorWriter advisorWriter;	
	private INewAdvisorUI newAdvisorUI;
	private IUpdateAdvisorUI updateAdvisorUI;
	private IAdvisorReporter reporter;
	
	public AdvisorViewerController (IAdvisorReader advisorReader, IAdvisorWriter advisorWriter, 
			INewAdvisorUI newAdvisorUI, IUpdateAdvisorUI updateAdviorUI, IAdvisorReporter reporter) {
		super(reporter);		
		
		this.advisorReader = advisorReader;
		this.advisorWriter = advisorWriter;
		this.newAdvisorUI = newAdvisorUI;
		this.updateAdvisorUI = updateAdviorUI;
		this.reporter = reporter;
	}
	
	@Override
	public void setNameFilter(String nameFilter) {
		this.nameFilter = nameFilter;
		super.notifyOfPropertyChange("nameFilter");		
		super.notifyOfPropertyChange("advisors");		
	}

	@Override
	public String getNameFilter() {
		return this.nameFilter;
	}

	@Override
	public void setSelectedAdvisor(Advisor advisor) {
		this.selectedAdvisor = advisor;
		
		super.notifyOfPropertyChange("selectedAdvisor");
		super.notifyOfPropertyChange("canUpdateAdvisor");
		super.notifyOfPropertyChange("canDeleteAdvisor");		
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
		super.notifyOfPropertyChange("advisors");
		
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
		this.newAdvisorUI.refresh();
		this.newAdvisorUI.setVisible(true);		
		super.notifyOfPropertyChange("advisors");			
	}

	@Override
	public void showUpdateAdvisorUI() {
		this.updateAdvisorUI.setpkAdvisor(this.selectedAdvisor.getPKAdvisor());
		this.updateAdvisorUI.setVisible(true);		
		super.notifyOfPropertyChange("advisors");		
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub		
	}

	@Override
	protected List<String[]> getExportSource() {
		return this.reporter.format(this.getAdvisors());
	}

}
