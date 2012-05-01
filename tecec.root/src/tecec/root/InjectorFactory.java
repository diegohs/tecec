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
	}

	private void RegisterUI(MutablePicoContainer container) {
		container.addComponent(tecec.ui.NewCourseUI.class);
		container.addComponent(tecec.ui.CourseViewerUI.class);
		container.addComponent(tecec.ui.UpdateCourseUI.class);
	}

	private void RegisterControllers(MutablePicoContainer container) {
		container.addComponent(tecec.ui.contract.INewCourseController.class,
				tecec.ui.control.NewCourseController.class);

		container.addComponent(tecec.ui.contract.ICourseViewerController.class,
				tecec.ui.control.CourseViewerController.class);

		container.addComponent(tecec.ui.contract.IUpdateCourseController.class,
				tecec.ui.control.UpdateCourseController.class);
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
	}
}
