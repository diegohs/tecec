package tecec.root;

import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;

public class InjectorFactory {
	public MutablePicoContainer CreateInjector() {
		MutablePicoContainer container = new DefaultPicoContainer();

		RegisterUI(container);
		RegisterConfig(container);
		RegisterRepositores(container);
		RegisterControllers(container);

		return container;
	}
	
	private void RegisterUI(MutablePicoContainer container)
	{
		container.addComponent(tecec.ui.NewCourseUI.class);
	}
	
	private void RegisterControllers(MutablePicoContainer container)
	{
		container.addComponent(tecec.ui.contract.NewCourseController.class, tecec.ui.control.DefaultNewCourseController.class);
	}

	private void RegisterConfig(MutablePicoContainer container) {
		container.addComponent(
				tecec.repository.mysql.base.MySqlConnectionConfig.class,
				tecec.root.config.TececMySqlConnectionConfig.class);
	}

	private void RegisterRepositores(MutablePicoContainer container) {
		container.addComponent(
				tecec.contract.repository.CourseRepository.class,
				tecec.repository.mysql.MySqlCourseRepository.class);
	}
}
