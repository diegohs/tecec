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
import javax.swing.border.TitledBorder;

public class UpdateProfileUI extends JDialog implements IUpdateProfileUI {
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
	private JPanel panel;

	/**
	 * Create the frame.
	 */
	public UpdateProfileUI(IUpdateProfileController updateProfileController) {
		this.updateProfileController = updateProfileController;
		
		setDefaultLookAndFeelDecorated(true);

		setModal(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 198);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Atualizar Perfil:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 412, 142);
		contentPane.add(panel);
		panel.setLayout(null);
		
				JLabel lblNome = new JLabel("Nome:");
				lblNome.setBounds(10, 45, 81, 14);
				panel.add(lblNome);
				
						txtProfileName = new JTextField();
						txtProfileName.setBounds(74, 42, 330, 20);
						panel.add(txtProfileName);
						txtProfileName.setColumns(10);
						
								btnUpdateProfile = new JButton("Atualizar");
								btnUpdateProfile.setBounds(327, 102, 75, 29);
								panel.add(btnUpdateProfile);
								btnUpdateProfile.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {
										storeProfile();
									}
								});
		initDataBindings();
	}
	protected void initDataBindings() {
		BeanProperty<IUpdateProfileController, String> iUpdateProfileControllerBeanProperty = BeanProperty.create("profileName");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<IUpdateProfileController, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, updateProfileController, iUpdateProfileControllerBeanProperty, txtProfileName, jTextFieldBeanProperty);
		autoBinding.bind();
	}

	@Override
	public void refresh() {
		updateProfileController.refresh();
	}
}
