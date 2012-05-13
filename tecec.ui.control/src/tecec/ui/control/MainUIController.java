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



	public MainUIController(IAreaViewerUI areaViewerUI,
			IAdvisorViewerUI advisorViewerUI, IStudentViewerUI studentViewerUI,
			IActivityViewerUI activityViewerUI, ICourseViewerUI courseViewerUI, IStatusViewerUI statusViewerUI) {
		this.areaViewerUI = areaViewerUI;
		this.advisorViewerUI = advisorViewerUI;
		this.studentViewerUI = studentViewerUI;
		this.activityViewerUI = activityViewerUI;
		this.courseViewerUI = courseViewerUI;
		this.statusViewerUI = statusViewerUI;
	}

	@Override
	public void showStudentViewerUI() {
		this.studentViewerUI.setVisible(true);
	}

	@Override
	public void showCouseViewerUI() {
		this.courseViewerUI.setVisible(true);
	}

	@Override
	public void showAdvisorViewerUI() {
		this.advisorViewerUI.setVisible(true);
	}

	@Override
	public void showAreaViewerUI() {
		this.areaViewerUI.setVisible(true);
	}

	@Override
	public void showActivityViewerUI() {
		this.activityViewerUI.setVisible(true);
	}

	@Override
	public void showStatusViewerUI(){
		this.statusViewerUI.setVisible(true);
	}

}
