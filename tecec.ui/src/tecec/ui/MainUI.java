package tecec.ui;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JOptionPane;

import tecec.contract.session.ISessionPool;
import tecec.ui.contract.control.IMainUIController;
import tecec.ui.contract.view.ICoordinatorPageUI;
import tecec.ui.contract.view.IMainUI;
import tecec.ui.contract.view.IStudentPageUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.IOException;
<<<<<<< OURS
import java.io.InputStream;
=======
import java.beans.PropertyVetoException;
>>>>>>> THEIRS

public class MainUI extends JFrame implements IMainUI {

<<<<<<< OURS
	private JFrame frame;
=======
	/**
	 * 
	 */
	private static final long serialVersionUID = 6660132350079500798L;
>>>>>>> THEIRS

	private void backupImmediate() {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Efetuar backup da base de dados");

		int variableChooser = fileChooser.showSaveDialog(null);

		if (variableChooser != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "Backup n√£o realizado",
					"Janela finalizada", JOptionPane.CANCEL_OPTION);
		} else {

			String commandDump = "";

			/*if (System.getProperty("os.name").toUpperCase().compareTo("LINUX") == 0) {
				commandDump = "/usr/bin/mysqldump";
			} else {
<<<<<<< OURS
				 Em casa n„o deu certo assim no Windows sÛ passando o caminho c://program files etc.
=======
>>>>>>> THEIRS
				commandDump = "MYSQL_PATH\\bin\\mysqldump.exe";
			}*/

			commandDump = "mysqldump";

			String location = fileChooser.getSelectedFile() + ".sql";
			ProcessBuilder pb = new ProcessBuilder(commandDump, "--user=root",
					"--password=lester", "tecec", "--result-file=" + location);
			try {
				pb.start();
				JOptionPane.showMessageDialog(null,
						"Backup efetuado com sucesso");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString(), "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

<<<<<<< OURS
	private void restoreBackup () throws IOException {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Selecionar arquivo de backup");

		int variableChooser = fileChooser.showOpenDialog(null);

		if(variableChooser == JFileChooser.APPROVE_OPTION){

			String cmd = null;

			if (System.getProperty("os.name").toUpperCase().contains("LINUX")) {
				cmd = "/bin/sh -c mysql --user=root --password=lester --host=localhost tecec < \"" + fileChooser.getSelectedFile().getAbsolutePath() + "\"";
			} else {
				if (System.getProperty("os.name").toUpperCase().contains("WINDOWS")) {
					cmd = "cmd.exe /c mysql --user=root --password=lester --host=localhost tecec < \"" + fileChooser.getSelectedFile().getAbsolutePath() + "\"";
				}
			}

			try {
				Runtime.getRuntime().exec(cmd);
				JOptionPane.showMessageDialog(null, "Backup restaurado com sucesso! AplicaÁ„o ser· encerrada...");
				System.exit(0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
=======
	private void restoreBackup() {
		// implementar nao consegui: Bruno
>>>>>>> THEIRS
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(true);
		this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		
		JInternalFrame internalFrame;
		
		if (this.sessionPool.getLoggedAccount().getFKStudent() != null) {
			mnBackup.setVisible(false);
			mnCadastro.setVisible(false);
			
			this.studentPageUI.setPKStudent(this.sessionPool.getLoggedAccount()
					.getFKStudent());
			
			internalFrame = (JInternalFrame)studentPageUI;
		}
		else{
			internalFrame = (JInternalFrame)coordinatorPageUI;
		}
		
		internalFrame.setBorder(null);
		
		this.getContentPane().add(internalFrame);

		internalFrame.setVisible(true);
		
		try {
			internalFrame.setMaximum(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private tecec.ui.contract.control.IMainUIController mainUIController;
	private IStudentPageUI studentPageUI;
	private ICoordinatorPageUI coordinatorPageUI;
	private ISessionPool sessionPool;

	public MainUI(IMainUIController mainUIController, ICoordinatorPageUI coordinatorPageUI,
			IStudentPageUI studentPageUI, ISessionPool sessionPool) {
		this.mainUIController = mainUIController;
		this.studentPageUI = studentPageUI;
		this.coordinatorPageUI = coordinatorPageUI;
		this.sessionPool = sessionPool;	
		

		initialize();
	}

	private void showStudentUI() {
		mainUIController.showStudentViewerUI();
		refresh();
	}

	private void showCourseUI() {
		mainUIController.showCouseViewerUI();
		refresh();
	}

	private void showAreaUI() {
		mainUIController.showAreaViewerUI();
		refresh();
	}

	private void showActivityUI() {
		mainUIController.showActivityViewerUI();
		refresh();
	}

	private void showAdvisorUI() {
		mainUIController.showAdvisorViewerUI();
		refresh();
	}

	private void showStatusUI() {
		mainUIController.showStatusViewerUI();
		refresh();
	}

	private void showMonographUI() {
		mainUIController.showMonographViewerUI();
		refresh();
	}

	private void showProfileUI() {
		mainUIController.showProfileViewerUI();
		refresh();
	}

	private void showStageUI() {
		mainUIController.showStageViewerUI();
		refresh();
	}
	
	private void showAccountViewerUI(){
		mainUIController.showAccountViewerUI();
	}

	@Override
	public void refresh() {
		mainUIController.refresh();
		
		if (this.sessionPool.getLoggedAccount().getFKStudent() != null) {			
			studentPageUI.refresh();
		}
		else{
			coordinatorPageUI.refresh();
		}
	}
	
	final JMenuBar menuBar = new JMenuBar();;
	final JMenu mnCadastro = new JMenu("Cadastro");
	final JMenu mnBackup = new JMenu("Backup");

	private void initialize() {
		JDialog.setDefaultLookAndFeelDecorated(true);
		
		this.setBounds(100, 100, 558, 352);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(menuBar);

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

		JMenuItem mntmProfile = new JMenuItem("Perfil");
		mntmProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showProfileUI();
			}
		});
		mnCadastro.add(mntmProfile);

		JMenuItem mntmStage = new JMenuItem("Est√°gio");
		mntmStage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showStageUI();
			}
		});

		mnCadastro.add(mntmStage);
		
		JMenuItem mntmConta = new JMenuItem("Conta");
		mntmConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAccountViewerUI();
			}
		});
		mnCadastro.add(mntmConta);

		menuBar.add(mnBackup);

		/* Efetua o backup no local desejado */

		JMenuItem mntmEfetuar = new JMenuItem("Efetuar");
		mntmEfetuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* Backup aqui */
				backupImmediate();
			}
		});
		mnBackup.add(mntmEfetuar);

<<<<<<< OURS

=======
>>>>>>> THEIRS
		JMenuItem mntmRestaurar = new JMenuItem("Restaurar");
		mntmRestaurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* Restaurar aqui */
				try {
					restoreBackup();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		mnBackup.add(mntmRestaurar);

		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);

		JMenuItem mntmSobre = new JMenuItem("Sobre");
		mntmSobre.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
<<<<<<< OURS
				JOptionPane.showMessageDialog(null, "Tecec: Software para gerenciamento de Tccs");
=======
				JOptionPane.showMessageDialog(null,
						"Tecec: Software para gerenciamento de Tccs");
>>>>>>> THEIRS

			}

		});
		mnAjuda.add(mntmSobre);

	}

}
