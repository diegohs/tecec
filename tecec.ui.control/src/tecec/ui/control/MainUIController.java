package tecec.ui.control;

import tecec.ui.contract.control.IMainUIController;
import tecec.ui.contract.view.*;;

public class MainUIController extends BaseController implements IMainUIController {

	private IAreaViewerUI areaViewerUI;
	private IAdvisorViewerUI advisorViewerUI;
	private IStudentViewerUI studentViewerUI;
	private IActivityViewerUI activityViewerUI;
	private ICourseViewerUI courseViewerUI;
	private IStatusViewerUI statusViewerUI;
	private IMonographViewerUI monographViewerUI;
	private IProfileViewerUI profileViewerUI;	
	private IStageViewerUI stageViewerUI;
	private IAccountViewerUI accountViewerUI;

	public MainUIController(IAreaViewerUI areaViewerUI,
			IAdvisorViewerUI advisorViewerUI, IStudentViewerUI studentViewerUI,
			IActivityViewerUI activityViewerUI, ICourseViewerUI courseViewerUI,
			IStatusViewerUI statusViewerUI,
			IMonographViewerUI monographViewerUI,
			IProfileViewerUI profileViewerUI, IStageViewerUI stageViewerUI,
			IAccountViewerUI accountViewerUI) {
		this.areaViewerUI = areaViewerUI;
		this.advisorViewerUI = advisorViewerUI;
		this.studentViewerUI = studentViewerUI;
		this.activityViewerUI = activityViewerUI;
		this.courseViewerUI = courseViewerUI;
		this.statusViewerUI = statusViewerUI;
		this.monographViewerUI = monographViewerUI;
		this.profileViewerUI = profileViewerUI;
		this.stageViewerUI = stageViewerUI;
		this.accountViewerUI = accountViewerUI;
	}

	@Override
	public void showStudentViewerUI() {
		this.studentViewerUI.refresh();
		this.studentViewerUI.setVisible(true);
	}

	@Override
	public void showCouseViewerUI() {
		this.courseViewerUI.refresh();
		this.courseViewerUI.setVisible(true);
	}

	@Override
	public void showAdvisorViewerUI() {
		this.advisorViewerUI.refresh();
		this.advisorViewerUI.setVisible(true);
	}

	@Override
	public void showAreaViewerUI() {
		this.areaViewerUI.refresh();
		this.areaViewerUI.setVisible(true);
	}

	@Override
	public void showActivityViewerUI() {
		this.activityViewerUI.refresh();
		this.activityViewerUI.setVisible(true);
	}

	@Override
	public void showStatusViewerUI(){
		this.statusViewerUI.refresh();
		this.statusViewerUI.setVisible(true);
	}

	@Override
	public void showMonographViewerUI() {
		this.monographViewerUI.refresh();
		this.monographViewerUI.setVisible(true);		
	}

	@Override
	public void showProfileViewerUI() {
		this.profileViewerUI.refresh();
		this.profileViewerUI.setVisible(true);		
	}
	
	@Override
	public void showStageViewerUI () {
		this.stageViewerUI.refresh();
		this.stageViewerUI.setVisible(true);
	}

	@Override
	public void showAccountViewerUI() {
		this.accountViewerUI.refresh();
		this.accountViewerUI.setVisible(true);
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
}
