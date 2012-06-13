package tecec.ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tecec.ui.contract.view.INewDocumentationUI;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class NewDocumentationUI extends JDialog implements INewDocumentationUI {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField fileTextField;
	private File localization; // Contem o arquivo a ser salvo no banco de dados
	byte[] fileArray; 			// Contem um array de bytes

	private void selectedFile(JButton btnUplod) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Selecione a documentação a salvar");

		int variableChooser = fileChooser.showOpenDialog(null);

		if (variableChooser != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null,
					"Nenhum arquivo foi selecionado", "Janeia finalizada",
					JOptionPane.CANCEL_OPTION);
		} else {
			localization = fileChooser.getSelectedFile();
			fileTextField.setText(localization.getPath());
			btnUplod.setEnabled(true);			
		}
	}

	private static byte[] createByteArray(File input) {
		InputStream is = null;

		/* Acho que está até 2MB checa ai */
		if (input.length() > 2000000) {
			//System.out.println ("Deu erro");
			return null;
		}
		

		byte[] array = new byte[((int) input.length())];
		try {
			is = new FileInputStream(input);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			is.read(array);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return array;

	}

	public static void main(String[] args) {
		try {
			NewDocumentationUI dialog = new NewDocumentationUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public NewDocumentationUI() {

		/* Aplica o look and feel utilizado no main */

		setDefaultLookAndFeelDecorated(true);
		setLocationByPlatform(true);
		setModal(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);

		setBounds(100, 100, 449, 203);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][][][][][]"));

		JLabel lblAdicionarNovaDocumentalai = new JLabel(
				"Adicionar nova documentação");
		lblAdicionarNovaDocumentalai.setFont(new Font("DejaVu Sans", Font.BOLD,
				12));
		contentPanel.add(lblAdicionarNovaDocumentalai,
				"cell 0 1 2 1,alignx center");

		JLabel lblLocalizao = new JLabel("Localização:");
		contentPanel.add(lblLocalizao, "cell 0 3,alignx trailing");

		fileTextField = new JTextField();
		fileTextField.setEditable(false);
		contentPanel.add(fileTextField, "cell 1 3,growx");
		fileTextField.setColumns(10);
		
		JLabel lblArquivosDeNo = new JLabel("Arquivos de no máximo 2 MB.");
		lblArquivosDeNo.setFont(new Font("DejaVu Sans", Font.PLAIN, 9));
		contentPanel.add(lblArquivosDeNo, "cell 1 4,alignx right");

		JButton btnLocalizarArquivo = new JButton("Localizar arquivo");

		contentPanel.add(btnLocalizarArquivo, "flowx,cell 1 5,alignx right");

		final JButton btnCarregarArquivo = new JButton("Carregar arquivo");		
		btnCarregarArquivo.setEnabled(false);
		contentPanel.add(btnCarregarArquivo, "cell 1 5");

		btnLocalizarArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedFile(btnCarregarArquivo);
			}
		});
		
		btnCarregarArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (localization != null && localization.length()>0) {
					System.out.println (localization.length());
					fileArray = createByteArray(localization);
				}
			}
		});
	}

}
