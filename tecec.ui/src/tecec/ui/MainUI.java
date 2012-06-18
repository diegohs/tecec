package tecec.ui;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JOptionPane;

import tecec.ui.contract.control.IMainUIController;
import tecec.ui.contract.view.IMainUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainUI implements IMainUI {

	private JFrame frame;

	private void backupImmediate() {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Efetuar backup da base de dados");

		int variableChooser = fileChooser.showSaveDialog(null);

		if (variableChooser != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "Backup nÃ£o realizado", "Janela finalizada", JOptionPane.CANCEL_OPTION);
		} else {

			String commandDump = "";

			/*if (System.getProperty("os.name").toUpperCase().compareTo("LINUX") == 0) {
				commandDump = "/usr/bin/mysqldump";
			} else {
				 Em casa não deu certo assim no Windows só passando o caminho c://program files etc.
				commandDump = "MYSQL_PATH\\bin\\mysqldump.exe";
			}*/

			commandDump = "mysqldump";

			String location = fileChooser.getSelectedFile() + ".sql";
			ProcessBuilder pb = new ProcessBuilder(commandDump, "--user=root",
					"--password=lester", "tecec", "--result-file=" + location);
			try {
				pb.start();
				JOptionPane.showMessageDialog(null, "Backup efetuado com sucesso");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

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
				JOptionPane.showMessageDialog(null, "Backup restaurado com sucesso! Aplicação será encerrada...");
				System.exit(0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}

	private tecec.ui.contract.control.IMainUIController mainUIController;

	public MainUI(IMainUIController mainUIController) {
		this.mainUIController = mainUIController;

		initialize();
	}

	private void showStudentUI() {
		mainUIController.showStudentViewerUI();
	}

	private void showCourseUI() {
		mainUIController.showCouseViewerUI();
	}

	private void showAreaUI() {
		mainUIController.showAreaViewerUI();
	}

	private void showActivityUI() {
		mainUIController.showActivityViewerUI();
	}

	private void showAdvisorUI() {
		mainUIController.showAdvisorViewerUI();
	}

	private void showStatusUI() {
		mainUIController.showStatusViewerUI();
	}

	private void showMonographUI() {
		mainUIController.showMonographViewerUI();
	}

	private void showProfileUI() {
		mainUIController.showProfileViewerUI();
	}

	private void showStageUI() {
		mainUIController.showStageViewerUI();
	}

	private void showPermissionUI() {
		mainUIController.showPermissionViewerUI();
	}

	private void initialize() {
		JFrame.setDefaultLookAndFeelDecorated(true);
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

		JMenuItem mntmProfile = new JMenuItem("Perfil");
		mntmProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showProfileUI();
			}
		});
		mnCadastro.add(mntmProfile);

		JMenuItem mntmStage = new JMenuItem("EstÃ¡gio");
		mntmStage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showStageUI();
			}
		});

		mnCadastro.add(mntmStage);

		JMenuItem mntmPermission = new JMenuItem("PermissÃ£o");
		mntmPermission.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPermissionUI();
			}
		});
		mnCadastro.add(mntmPermission);

		JMenu mnBackup = new JMenu("Backup");
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
		mntmSobre.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Tecec: Software para gerenciamento de Tccs");

			}

		});
		mnAjuda.add(mntmSobre);

	}

}
