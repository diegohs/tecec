package tecec.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import tecec.contract.RuleViolation;
import net.miginfocom.swing.MigLayout;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;

import tecec.ui.contract.control.IUpdateProfileController;
import tecec.ui.contract.view.IUpdateProfileUI;

public class UpdateProfileUI extends JDialog implements IUpdateProfileUI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IUpdateProfileController updateProfileController;

	@Override
	public void setpKProfile(String pKProfile) {
		this.updateProfileController.setPKProfile(pKProfile);
	}
	
	@Override
	public void setVisible(boolean visible){
		super.setVisible(visible);
	}
	
	private void storeProfile() {
		try {
			RuleViolation violation = this.updateProfileController
					.getUpdateViolation();

			if (violation != null) {
				JOptionPane.showMessageDialog(this, violation.getDescription(),
						"Erro", JOptionPane.ERROR_MESSAGE);
			} else {
				this.updateProfileController.updateProfile();
				
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
	private JButton btnUpdateProfile;

	/**
	 * Create the frame.
	 */
	public UpdateProfileUI(IUpdateProfileController updateProfileController) {
		this.updateProfileController = updateProfileController;
		
		setDefaultLookAndFeelDecorated(true);

		setModal(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 436, 245);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow][grow]",
				"[grow][][56.00][29.00][grow]"));

		JLabel lblNewLabel = new JLabel("Atualizar Perfil");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblNewLabel, "cell 1 1,alignx center");

		JLabel lblNome = new JLabel("Nome:");
		contentPane.add(lblNome, "flowx,cell 1 2");

		txtProfileName = new JTextField();
		contentPane.add(txtProfileName, "cell 1 2,growx");
		txtProfileName.setColumns(10);

		btnUpdateProfile = new JButton("Atualizar");
		btnUpdateProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				storeProfile();
			}
		});
		contentPane.add(btnUpdateProfile, "cell 1 3,alignx center,growy");
		initDataBindings();
	}
	protected void initDataBindings() {
		BeanProperty<IUpdateProfileController, String> iUpdateProfileControllerBeanProperty = BeanProperty.create("profileName");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<IUpdateProfileController, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, updateProfileController, iUpdateProfileControllerBeanProperty, txtProfileName, jTextFieldBeanProperty);
		autoBinding.bind();
	}
}
