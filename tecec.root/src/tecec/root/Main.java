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
					NewCourseUI window = container.getComponent(NewCourseUI.class);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
