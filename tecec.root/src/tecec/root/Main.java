package tecec.root;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.picocontainer.MutablePicoContainer;

import tecec.ui.contract.view.*;

public class Main {

	private static MutablePicoContainer container;

	public static void main(String[] args) {
		container = new InjectorFactory().CreateInjector();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					try {
						// Usar tema Default do SO
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

						// Cross-platform (Esse é o default)
						//UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());


	                } catch (UnsupportedLookAndFeelException ex) {
	                     ex.printStackTrace();
	                } catch (IllegalAccessException ex) {
	                     ex.printStackTrace();
	                } catch (InstantiationException ex) {
	                     ex.printStackTrace();
	                } catch (ClassNotFoundException ex) {
	                     ex.printStackTrace();
	                }

					ILoginUI loginUI = container.getComponent(ILoginUI.class);
					loginUI.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
