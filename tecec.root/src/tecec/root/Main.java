package tecec.root;

import java.awt.EventQueue;

import org.picocontainer.MutablePicoContainer;

import tecec.ui.contract.view.ICourseViewerUI;

public class Main {
	
	private static MutablePicoContainer container;

	public static void main(String[] args) {
		container = new InjectorFactory().CreateInjector();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ICourseViewerUI window = container.getComponent(ICourseViewerUI.class);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
