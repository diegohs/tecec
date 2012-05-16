package tecec.ui;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

import tecec.ui.contract.control.IMainUIController;
import tecec.ui.contract.view.IMainUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainUI implements IMainUI {

	private JFrame frame;

	@Override
	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}

	private tecec.ui.contract.control.IMainUIController mainUIController;

	public MainUI(IMainUIController mainUIController) {
		this.mainUIController = mainUIController;

		initialize();
	}

	private void showStudentUI(){
		mainUIController.showStudentViewerUI();
	}

	private void showCourseUI(){
		mainUIController.showCouseViewerUI();
	}

	private void showAreaUI(){
		mainUIController.showAreaViewerUI();
	}

	private void showActivityUI(){
		mainUIController.showActivityViewerUI();
	}

	private void showAdvisorUI(){
		mainUIController.showAdvisorViewerUI();
	}

	private void showStatusUI(){
		mainUIController.showStatusViewerUI();
	}
	
	private void showMonographUI(){
		mainUIController.showMonographViewerUI();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 558, 352);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);

		JMenuItem mntmrea = new JMenuItem("\u00C1rea");
		mntmrea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAreaUI();
			}
		});
		mnCadastro.add(mntmrea);

		JMenuItem mntmNewMenuItem = new JMenuItem("Atividade");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showActivityUI();
			}
		});
		mnCadastro.add(mntmNewMenuItem);

		JMenuItem mntmAluno = new JMenuItem("Aluno");
		mntmAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showStudentUI();
			}
		});
		mnCadastro.add(mntmAluno);

		JMenuItem mntmCurso = new JMenuItem("Curso");
		mntmCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showCourseUI();
			}
		});
		mnCadastro.add(mntmCurso);

		JMenuItem mntmOrientador = new JMenuItem("Orientador");
		mntmOrientador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAdvisorUI();
			}
		});
		mnCadastro.add(mntmOrientador);
		
		JMenuItem mntmMonografia = new JMenuItem("Monografia");
		mntmMonografia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showMonographUI();
			}
		});
		mnCadastro.add(mntmMonografia);

		JMenuItem mntmStatus = new JMenuItem("Status");
		mntmStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showStatusUI();
			}
		});
		mnCadastro.add(mntmStatus);
	}

}
