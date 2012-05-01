package tecec.root;

import java.awt.EventQueue;

import org.picocontainer.*;
import tecec.ui.*;

public class Main {
	
	private static MutablePicoContainer container;

	public static void main(String[] args) {
		container = new InjectorFactory().CreateInjector();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CourseViewerUI window = container.getComponent(CourseViewerUI.class);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
