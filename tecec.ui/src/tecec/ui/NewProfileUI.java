package tecec.ui;


import javax.swing.JDialog;import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tecec.ui.contract.control.INewProfileController;
import tecec.ui.contract.view.INewProfileUI;
import tecec.contract.RuleViolation;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import org.jdesktop.beansbinding.*;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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

		setLocationByPlatform(true);
		setModal(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 436, 245);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow][grow]",
				"[grow][][56.00][29.00][grow]"));

		JLabel lblNewLabel = new JLabel("Cadastrar Novo Perfil");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblNewLabel, "cell 1 1,alignx center");

		JLabel lblNome = new JLabel("Nome:");
		contentPane.add(lblNome, "flowx,cell 1 2");

		txtProfileName = new JTextField();
		contentPane.add(txtProfileName, "cell 1 2,growx");
		txtProfileName.setColumns(10);

		btnCreateProfile = new JButton("Cadastrar");
		btnCreateProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				storeProfile();
			}
		});
		contentPane.add(btnCreateProfile, "cell 1 3,alignx center,growy");
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
}
