package tecec.ui;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tecec.ui.contract.control.IUpdateAccountController;
import tecec.ui.contract.view.IUpdateAccountUI;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.jdesktop.beansbinding.BeanProperty;
import java.util.List;

import tecec.contract.RuleViolation;
import tecec.dto.Profile;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.SwingBindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;

public class UpdateAccountUI extends JDialog implements IUpdateAccountUI {

	/**
	 *
	 */
	private static final long serialVersionUID = -8048654131713852396L;

	private final JPanel contentPanel = new JPanel();

	IUpdateAccountController controller;
	private JTextField txtId;
	private JTextField txtUserName;
	private JComboBox cboProfile;
	private JCheckBox chckbxNewCheckBox;

	private void update(){
		RuleViolation violation = this.controller.getUpdateViolation();

		if (violation != null) {
			JOptionPane.showMessageDialog(this, violation.getDescription(),
					"ERRO", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				this.controller.update();

				this.setVisible(false);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e, "ERRO",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public UpdateAccountUI(IUpdateAccountController controller) {
		this.controller = controller;

		setDefaultLookAndFeelDecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/tecec/ui/files/icone_tecec.png")));

		setModal(true);
		setBounds(100, 100, 457, 240);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][][grow][]", "[][][][][][][][]"));
		{
			JLabel lblNewLabel = new JLabel("Perfil:");
			contentPanel.add(lblNewLabel, "cell 1 1,alignx left");
		}
		{
			cboProfile = new JComboBox();
			contentPanel.add(cboProfile, "cell 2 1,growx");
		}
		{
			JLabel lblNewLabel_1 = new JLabel("ID:");
			contentPanel.add(lblNewLabel_1, "cell 1 2,alignx left");
		}
		{
			txtId = new JTextField();
			txtId.setEditable(false);
			txtId.setEnabled(false);
			contentPanel.add(txtId, "cell 2 2,growx");
			txtId.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Nome:");
			contentPanel.add(lblNewLabel_2, "cell 1 3,alignx left");
		}
		{
			txtUserName = new JTextField();
			contentPanel.add(txtUserName, "cell 2 3,growx");
			txtUserName.setColumns(10);
		}
		{
			chckbxNewCheckBox = new JCheckBox("Resetar senha");
			contentPanel.add(chckbxNewCheckBox, "cell 2 4,alignx right");
		}
		{
			JButton btnUpdate = new JButton("Atualizar");
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					update();
				}
			});
			contentPanel.add(btnUpdate, "cell 2 6,alignx right");
		}
		initDataBindings();
	}



	@Override
	public void refresh() {
		this.controller.refresh();
	}

	@Override
	public void setAccountID(String id) {
		this.controller.setAccountID(id);
	}
	protected void initDataBindings() {
		BeanProperty<IUpdateAccountController, List<Profile>> iUpdateAccountControllerBeanProperty = BeanProperty.create("profiles");
		JComboBoxBinding<Profile, IUpdateAccountController, JComboBox> jComboBinding = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ, controller, iUpdateAccountControllerBeanProperty, cboProfile);
		jComboBinding.bind();
		//
		BeanProperty<IUpdateAccountController, Integer> iUpdateAccountControllerBeanProperty_1 = BeanProperty.create("selectedProfileIndex");
		BeanProperty<JComboBox, Integer> jComboBoxBeanProperty = BeanProperty.create("selectedIndex");
		AutoBinding<IUpdateAccountController, Integer, JComboBox, Integer> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, controller, iUpdateAccountControllerBeanProperty_1, cboProfile, jComboBoxBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<IUpdateAccountController, Profile> iUpdateAccountControllerBeanProperty_2 = BeanProperty.create("selectedProfile");
		BeanProperty<JComboBox, Object> jComboBoxBeanProperty_1 = BeanProperty.create("selectedItem");
		AutoBinding<IUpdateAccountController, Profile, JComboBox, Object> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, controller, iUpdateAccountControllerBeanProperty_2, cboProfile, jComboBoxBeanProperty_1);
		autoBinding_1.bind();
		//
		BeanProperty<IUpdateAccountController, String> iUpdateAccountControllerBeanProperty_3 = BeanProperty.create("ID");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<IUpdateAccountController, String, JTextField, String> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ, controller, iUpdateAccountControllerBeanProperty_3, txtId, jTextFieldBeanProperty);
		autoBinding_2.bind();
		//
		BeanProperty<IUpdateAccountController, String> iUpdateAccountControllerBeanProperty_4 = BeanProperty.create("userName");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_1 = BeanProperty.create("text");
		AutoBinding<IUpdateAccountController, String, JTextField, String> autoBinding_3 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, controller, iUpdateAccountControllerBeanProperty_4, txtUserName, jTextFieldBeanProperty_1);
		autoBinding_3.bind();
		//
		BeanProperty<IUpdateAccountController, Boolean> iUpdateAccountControllerBeanProperty_5 = BeanProperty.create("resetPassword");
		BeanProperty<JCheckBox, Boolean> jCheckBoxBeanProperty = BeanProperty.create("selected");
		AutoBinding<IUpdateAccountController, Boolean, JCheckBox, Boolean> autoBinding_4 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, controller, iUpdateAccountControllerBeanProperty_5, chckbxNewCheckBox, jCheckBoxBeanProperty);
		autoBinding_4.bind();
	}
}
