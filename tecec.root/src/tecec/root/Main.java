package tecec.root;

import java.awt.EventQueue;

import org.picocontainer.MutablePicoContainer;

import tecec.ui.contract.view.ICouseViewerUI;

public class Main {
	
	private static MutablePicoContainer container;

	public static void main(String[] args) {
		container = new InjectorFactory().CreateInjector();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ICouseViewerUI window = container.getComponent(ICouseViewerUI.class);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
