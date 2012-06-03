package tecec.root;

import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;

public class InjectorFactory {
	public MutablePicoContainer CreateInjector() {
		MutablePicoContainer container = new DefaultPicoContainer();

		RegisterModel(container);
		RegisterUI(container);
		RegisterConfig(container);
		RegisterRepositores(container);
		RegisterControllers(container);

		return container;
	}

	private void RegisterModel(MutablePicoContainer container) {

		/* Course */

		container.addComponent(tecec.contract.writer.ICourseWriter.class,
				tecec.business.writer.CourseWriter.class);

		container.addComponent(tecec.contract.reader.ICourseReader.class,
				tecec.business.reader.CourseReader.class);
		
		/* Advisor */

		container.addComponent(tecec.contract.writer.IAdvisorWriter.class,
				tecec.business.writer.AdvisorWriter.class);

		container.addComponent(tecec.contract.reader.IAdvisorReader.class,
				tecec.business.reader.AdvisorReader.class);
		
		/* Area */

		container.addComponent(tecec.contract.writer.IAreaWriter.class,
				tecec.business.writer.AreaWriter.class);

		container.addComponent(tecec.contract.reader.IAreaReader.class,
				tecec.business.reader.AreaReader.class);

		/* Activity */
		
		container.addComponent(tecec.contract.writer.IActivityWriter.class,
				tecec.business.writer.ActivityWriter.class);

		container.addComponent(tecec.contract.reader.IActivityReader.class,
				tecec.business.reader.ActivityReader.class);
		
		/* Student */

		container.addComponent(tecec.contract.writer.IStudentWriter.class,
				tecec.business.writer.StudentWriter.class);

		container.addComponent(tecec.contract.reader.IStudentReader.class,
				tecec.business.reader.StudentReader.class);
		
		/* Status */

		container.addComponent(tecec.contract.writer.IStatusWriter.class,
				tecec.business.writer.StatusWriter.class);

		container.addComponent(tecec.contract.reader.IStatusReader.class,
				tecec.business.reader.StatusReader.class);
		
		/* Monograph */
		container.addComponent(tecec.contract.writer.IMonographWriter.class,
				tecec.business.writer.MonographWriter.class);
		
		container.addComponent(tecec.contract.reader.IMonographReader.class,
				tecec.business.reader.MonographReader.class);
		
		/* Perfil */

		container.addComponent(tecec.contract.writer.IProfileWriter.class,
				tecec.business.writer.ProfileWriter.class);

		container.addComponent(tecec.contract.reader.IProfileReader.class,
				tecec.business.reader.ProfileReader.class);
		
		/* Stage */

		container.addComponent(tecec.contract.writer.IStageWriter.class,
				tecec.business.writer.StageWriter.class);

		container.addComponent(tecec.contract.reader.IStageReader.class,
				tecec.business.reader.StageReader.class);
	}

	private void RegisterUI(MutablePicoContainer container) {

		/* Course */
		
		container.addComponent(tecec.ui.contract.view.INewCourseUI.class,
				tecec.ui.NewCourseUI.class);

		container.addComponent(tecec.ui.contract.view.ICourseViewerUI.class,
				tecec.ui.CourseViewerUI.class);

		container.addComponent(tecec.ui.contract.view.IUpdateCourseUI.class,
				tecec.ui.UpdateCourseUI.class);
		
		/* Advisor */

		container.addComponent(tecec.ui.contract.view.INewAdvisorUI.class,
				tecec.ui.NewAdvisorUI.class);

		container.addComponent(tecec.ui.contract.view.IAdvisorViewerUI.class,
				tecec.ui.AdvisorViewerUI.class);

		container.addComponent(tecec.ui.contract.view.IUpdateAdvisorUI.class,
				tecec.ui.UpdateAdvisorUI.class);
		
		/* Area */

		container.addComponent(tecec.ui.contract.view.INewAreaUI.class,
				tecec.ui.NewAreaUI.class);

		container.addComponent(tecec.ui.contract.view.IAreaViewerUI.class,
				tecec.ui.AreaViewerUI.class);

		container.addComponent(tecec.ui.contract.view.IUpdateAreaUI.class,
				tecec.ui.UpdateAreaUI.class);

		/* Activity */
		
		container.addComponent(tecec.ui.contract.view.INewActivityUI.class,
				tecec.ui.NewActivityUI.class);

		container.addComponent(tecec.ui.contract.view.IActivityViewerUI.class,
				tecec.ui.ActivityViewerUI.class);

		container.addComponent(tecec.ui.contract.view.IUpdateActivityUI.class,
				tecec.ui.UpdateActivityUI.class);
		
		/* Main */

		container.addComponent(tecec.ui.contract.view.IMainUI.class,
				tecec.ui.MainUI.class);
		
		/* Student */

		container.addComponent(tecec.ui.contract.view.IStudentViewerUI.class,
				tecec.ui.StudentViewerUI.class);

		container.addComponent(tecec.ui.contract.view.INewStudentUI.class,
				tecec.ui.NewStudentUI.class);

		container.addComponent(tecec.ui.contract.view.IUpdateStudentUI.class,
				tecec.ui.UpdateStudentUI.class);
		
		/* Status */
		
		container.addComponent(tecec.ui.contract.view.INewStatusUI.class,
				tecec.ui.NewStatusUI.class);

		container.addComponent(tecec.ui.contract.view.IStatusViewerUI.class,
				tecec.ui.StatusViewerUI.class);

		container.addComponent(tecec.ui.contract.view.IUpdateStatusUI.class,
				tecec.ui.UpdateStatusUI.class);
		
		/* Monograph */
		
		container.addComponent(tecec.ui.contract.view.INewMonographUI.class,
				tecec.ui.NewMonographUI.class);
		
		container.addComponent(tecec.ui.contract.view.IMonographViewerUI.class,
				tecec.ui.MonographViewerUI.class);
		
		container.addComponent(tecec.ui.contract.view.IUpdateMonographUI.class,
				tecec.ui.UpdateMonographUI.class);
		
		/* Course */
		
		container.addComponent(tecec.ui.contract.view.INewProfileUI.class,
				tecec.ui.NewProfileUI.class);

		container.addComponent(tecec.ui.contract.view.IProfileViewerUI.class,
				tecec.ui.ProfileViewerUI.class);

		container.addComponent(tecec.ui.contract.view.IUpdateProfileUI.class,
				tecec.ui.UpdateProfileUI.class);
		
	
		
		/* Stage */
		
		container.addComponent(tecec.ui.contract.view.INewStageUI.class,
				tecec.ui.NewStageUI.class);

		container.addComponent(tecec.ui.contract.view.IStageViewerUI.class,
				tecec.ui.StageViewerUI.class);

		container.addComponent(tecec.ui.contract.view.IUpdateStageUI.class,
				tecec.ui.UpdateStageUI.class);
		
		
	}

	private void RegisterControllers(MutablePicoContainer container) {
		/* Course */
		container.addComponent(		
				tecec.ui.contract.control.INewCourseController.class,
				tecec.ui.control.NewCourseController.class);

		container.addComponent(
				tecec.ui.contract.control.ICourseViewerController.class,
				tecec.ui.control.CourseViewerController.class);

		container.addComponent(
				tecec.ui.contract.control.IUpdateCourseController.class,
				tecec.ui.control.UpdateCourseController.class);

		/* Advisor */
		
		container.addComponent(
				tecec.ui.contract.control.INewAdvisorController.class,
				tecec.ui.control.NewAdvisorController.class);

		container.addComponent(
				tecec.ui.contract.control.IUpdateAdvisorController.class,
				tecec.ui.control.UpdateAdvisorController.class);

		container.addComponent(
				tecec.ui.contract.control.IAdvisorViewerController.class,
				tecec.ui.control.AdvisorViewerController.class);

		/* Area */
		
		container.addComponent(
				tecec.ui.contract.control.INewAreaController.class,
				tecec.ui.control.NewAreaController.class);

		container.addComponent(
				tecec.ui.contract.control.IAreaViewerController.class,
				tecec.ui.control.AreaViewerController.class);

		container.addComponent(
				tecec.ui.contract.control.IUpdateAreaController.class,
				tecec.ui.control.UpdateAreaController.class);

		/* Activity */
		
		container.addComponent(
				tecec.ui.contract.control.INewActivityController.class,
				tecec.ui.control.NewActivityController.class);

		container.addComponent(
				tecec.ui.contract.control.IActivityViewerController.class,
				tecec.ui.control.ActivityViewerController.class);

		container.addComponent(
				tecec.ui.contract.control.IUpdateActivityController.class,
				tecec.ui.control.UpdateActivityController.class);

		/* Main */
		
		container.addComponent(
				tecec.ui.contract.control.IMainUIController.class,
				tecec.ui.control.MainUIController.class);

		/* Student */
		
		container.addComponent(
				tecec.ui.contract.control.INewStudentController.class,
				tecec.ui.control.NewStudentController.class);

		container.addComponent(
				tecec.ui.contract.control.IUpdateStudentController.class,
				tecec.ui.control.UpdateStudentController.class);

		container.addComponent(
				tecec.ui.contract.control.IStudentViewerController.class,
				tecec.ui.control.StudentViewerController.class);
		
		/* Status */
		container.addComponent(		
				tecec.ui.contract.control.INewStatusController.class,
				tecec.ui.control.NewStatusController.class);

		container.addComponent(
				tecec.ui.contract.control.IStatusViewerController.class,
				tecec.ui.control.StatusViewerController.class);

		container.addComponent(
				tecec.ui.contract.control.IUpdateStatusController.class,
				tecec.ui.control.UpdateStatusController.class);
		
		/* Monograph */
		
		container.addComponent(
				tecec.ui.contract.control.INewMonographController.class,
				tecec.ui.control.NewMonographController.class);
		
		container.addComponent(
				tecec.ui.contract.control.IMonographViewerController.class,
				tecec.ui.control.MonographViewerController.class);
		
		container.addComponent(
				tecec.ui.contract.control.IUpdateMonographController.class,
				tecec.ui.control.UpdateMonographController.class);
		
		/* Profile */
		container.addComponent(		
				tecec.ui.contract.control.INewProfileController.class,
				tecec.ui.control.NewProfileController.class);

		container.addComponent(
				tecec.ui.contract.control.IProfileViewerController.class,
				tecec.ui.control.ProfileViewerController.class);

		container.addComponent(
				tecec.ui.contract.control.IUpdateProfileController.class,
				tecec.ui.control.UpdateProfileController.class);
		
	
		
		/* Stage */
		container.addComponent(		
				tecec.ui.contract.control.INewStageController.class,
				tecec.ui.control.NewStageController.class);

		container.addComponent(
				tecec.ui.contract.control.IStageViewerController.class,
				tecec.ui.control.StageViewerController.class);

		container.addComponent(
				tecec.ui.contract.control.IUpdateStageController.class,
				tecec.ui.control.UpdateStageController.class);
	}

	private void RegisterConfig(MutablePicoContainer container) {
		container.addComponent(
				tecec.repository.mysql.base.MySqlConnectionConfig.class,
				tecec.root.config.TececMySqlConnectionConfig.class);
	}

	private void RegisterRepositores(MutablePicoContainer container) {
		/* Course */
		container.addComponent(
				tecec.contract.repository.ICourseRepository.class,
				tecec.repository.mysql.MySqlCourseRepository.class);

		/* Advisor */
		container.addComponent(
				tecec.contract.repository.IAdvisorRepository.class,
				tecec.repository.mysql.MySqlAdvisorRepository.class);

		/* Area */
		container.addComponent(tecec.contract.repository.IAreaRepository.class,
				tecec.repository.mysql.MySqlAreaRepository.class);

		/* Activity */
		container.addComponent(
				tecec.contract.repository.IActivityRepository.class,
				tecec.repository.mysql.MySqlActivityRepository.class);

		/* Student */
		container.addComponent(
				tecec.contract.repository.IStudentRepository.class,
				tecec.repository.mysql.MySqlStudentRepository.class);
		
		/* Status */
		container.addComponent(
				tecec.contract.repository.IStatusRepository.class,
				tecec.repository.mysql.MySqlStatusRepository.class);
		
		/* Monograph */
		container.addComponent(
				tecec.contract.repository.IMonographRepository.class,
				tecec.repository.mysql.MySqlMonographRepository.class);
		
		/* Profile */
		container.addComponent(
				tecec.contract.repository.IProfileRepository.class,
				tecec.repository.mysql.MySqlProfileRepository.class);
		
	
		
		/* Stage */
		container.addComponent(
				tecec.contract.repository.IStageRepository.class,
				tecec.repository.mysql.MySqlStageRepository.class);
	}
}
