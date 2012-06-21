package tecec.ui;


import javax.swing.JDialog;import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tecec.ui.contract.control.INewProfileController;
import tecec.ui.contract.view.INewProfileUI;
import tecec.contract.RuleViolation;
import javax.swing.JLabel;

import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JButton;
import org.jdesktop.beansbinding.*;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

public class NewProfileUI extends JDialog implements INewProfileUI {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private tecec.ui.contract.control.INewProfileController newProfileController;

	@Override
	public void setVisible(boolean visible){
		super.setVisible(visible);
	}

	private void storeProfile() {
		try {
			RuleViolation violation = this.newProfileController
					.getCreationViolation();
			if (violation != null) {
				JOptionPane.showMessageDialog(this, violation.getDescription(),
						"Erro", JOptionPane.ERROR_MESSAGE);
			} else {
				this.newProfileController.createProfile();

				this.setVisible(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e, "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private JPanel contentPane;
	private JTextField txtProfileName;
	private JButton btnCreateProfile;

	/**
	 * Create the frame.
	 */
	public NewProfileUI(
			tecec.ui.contract.control.INewProfileController newProfileController) {
		if (newProfileController == null) {
			throw new IllegalArgumentException("newProfileController");
		}

		this.newProfileController = newProfileController;

		setDefaultLookAndFeelDecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/tecec/ui/files/icone_tecec.png")));

		setLocationByPlatform(true);
		setModal(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 198);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Cadastrar Novo Perfil:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 412, 142);
		contentPane.add(panel);
		panel.setLayout(null);

				txtProfileName = new JTextField();
				txtProfileName.setBounds(72, 42, 330, 20);
				panel.add(txtProfileName);
				txtProfileName.setColumns(10);

						btnCreateProfile = new JButton("Cadastrar");
						btnCreateProfile.setBounds(321, 102, 81, 29);
						panel.add(btnCreateProfile);

								JLabel lblNome = new JLabel("Nome:");
								lblNome.setBounds(10, 45, 81, 14);
								panel.add(lblNome);
						btnCreateProfile.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								storeProfile();
							}
						});
		initDataBindings();
	}

	protected void initDataBindings() {
		BeanProperty<INewProfileController, String> iNewProfileControllerBeanProperty = BeanProperty
				.create("profileName");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty
				.create("text");
		AutoBinding<INewProfileController, String, JTextField, String> autoBinding = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						newProfileController, iNewProfileControllerBeanProperty,
						txtProfileName, jTextFieldBeanProperty);
		autoBinding.bind();
	}

	@Override
	public void refresh() {
		newProfileController.refresh();
	}
}
