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
		container.addComponent(tecec.contract.writer.ICourseWriter.class,
				tecec.business.writer.CourseWriter.class);

		container.addComponent(tecec.contract.reader.ICourseReader.class,
				tecec.business.reader.CourseReader.class);

		container.addComponent(tecec.contract.writer.IAdvisorWriter.class,
				tecec.business.writer.AdvisorWriter.class);

		container.addComponent(tecec.contract.reader.IAdvisorReader.class,
				tecec.business.reader.AdvisorReader.class);

		container.addComponent(tecec.contract.writer.IStudentWriter.class,
				tecec.business.writer.StudentWriter.class);

		container.addComponent(tecec.contract.reader.IStudentReader.class,
				tecec.business.reader.StudentReader.class);

	}

	private void RegisterUI(MutablePicoContainer container) {
		container.addComponent(tecec.ui.contract.view.INewCourseUI.class,
				tecec.ui.NewCourseUI.class);

		container.addComponent(tecec.ui.contract.view.ICourseViewerUI.class,
				tecec.ui.CourseViewerUI.class);

		container.addComponent(tecec.ui.contract.view.IUpdateCourseUI.class,
				tecec.ui.UpdateCourseUI.class);

		container.addComponent(tecec.ui.contract.view.INewAdvisorUI.class,
				tecec.ui.NewAdvisorUI.class);

		container.addComponent(tecec.ui.contract.view.IAdvisorViewerUI.class,
				tecec.ui.AdvisorViewerUI.class);

		container.addComponent(tecec.ui.contract.view.IUpdateAdvisorUI.class,
				tecec.ui.UpdateAdvisorUI.class);
		
		container.addComponent(tecec.ui.contract.view.INewStudentUI.class,
				tecec.ui.NewStudentUI.class);

		container.addComponent(tecec.ui.contract.view.IStudentViewerUI.class,
				tecec.ui.StudentViewerUI.class);

		container.addComponent(tecec.ui.contract.view.IUpdateStudentUI.class,
				tecec.ui.UpdateStudentUI.class);

		
	}

	private void RegisterControllers(MutablePicoContainer container) {
		container.addComponent(
				tecec.ui.contract.control.INewCourseController.class,
				tecec.ui.control.NewCourseController.class);

		container.addComponent(
				tecec.ui.contract.control.ICourseViewerController.class,
				tecec.ui.control.CourseViewerController.class);

		container.addComponent(
				tecec.ui.contract.control.IUpdateCourseController.class,
				tecec.ui.control.UpdateCourseController.class);

		container.addComponent(
				tecec.ui.contract.control.INewAdvisorController.class,
				tecec.ui.control.NewAdvisorController.class);

		container.addComponent(
				tecec.ui.contract.control.IAdvisorViewerController.class,
				tecec.ui.control.AdvisorViewerController.class);
		
		container.addComponent(
				tecec.ui.contract.control.INewStudentController.class,
				tecec.ui.control.NewStudentController.class);

		container.addComponent(
				tecec.ui.contract.control.IStudentViewerController.class,
				tecec.ui.control.StudentViewerController.class);

	}

	private void RegisterConfig(MutablePicoContainer container) {
		container.addComponent(
				tecec.repository.mysql.base.MySqlConnectionConfig.class,
				tecec.root.config.TececMySqlConnectionConfig.class);
	}

	private void RegisterRepositores(MutablePicoContainer container) {
		container.addComponent(
				tecec.contract.repository.ICourseRepository.class,
				tecec.repository.mysql.MySqlCourseRepository.class);

		container.addComponent(
				tecec.contract.repository.IAdvisorRepository.class,
				tecec.repository.mysql.MySqlAdvisorRepository.class);
		
		container.addComponent(
				tecec.contract.repository.IStudentRepository.class,
				tecec.repository.mysql.MySqlStudentRepository.class);
	}
}
