package tecec.ui;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import tecec.contract.RuleViolation;
import tecec.contract.RuleViolationException;
import tecec.ui.contract.control.ILoginController;
import tecec.ui.contract.view.ILoginUI;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginUI extends JDialog implements ILoginUI {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	ILoginController controller;

	private void login() {
		RuleViolation violation = this.controller.getLoginViolation(
				txtPassword.getPassword(), txtConfirmation.getPassword());

		if (violation != null) {
			JOptionPane.showMessageDialog(this, violation.getDescription(),
					"ERRO", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				this.setVisible(false);

				try {
					controller.login(txtPassword.getPassword(),
							txtConfirmation.getPassword());
				} catch (RuleViolationException e) {
					JOptionPane.showMessageDialog(this, e.getMessage(), "ERRO",
							JOptionPane.ERROR_MESSAGE);

					this.setVisible(true);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e, "ERRO",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private final JPanel contentPanel = new JPanel();
	private JTextField txtLogin;
	private JPasswordField txtPassword;
	private JPasswordField txtConfirmation;
	private JLabel lblConfirmation;

	public LoginUI(ILoginController controller) {
		this.controller = controller;

		setDefaultLookAndFeelDecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/tecec/ui/files/icone_tecec.png")));

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 458, 229);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("",
				"[grow][][200px:n,grow][grow]", "[grow][][][][][][grow]"));
		{
			JLabel lblLogin = new JLabel("Login:");
			contentPanel.add(lblLogin, "cell 1 1");
		}
		{
			txtLogin = new JTextField();
			contentPanel.add(txtLogin, "cell 2 1,growx");
			txtLogin.setColumns(10);
		}
		{
			JButton btnConfirm = new JButton("Confirmar");
			btnConfirm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					login();
				}
			});
			{
				JLabel lblSenha = new JLabel("Senha:");
				contentPanel.add(lblSenha, "cell 1 2");
			}
			{
				lblConfirmation = new JLabel("Confirma\u00E7\u00E3o:");
				contentPanel.add(lblConfirmation, "cell 1 3,alignx trailing");
			}
			{
				txtConfirmation = new JPasswordField();
				contentPanel.add(txtConfirmation, "cell 2 3,growx");
			}
			contentPanel.add(btnConfirm, "flowx,cell 2 5,alignx center");
		}
		{
			JButton btnCancel = new JButton("Cancelar");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
			contentPanel.add(btnCancel, "cell 2 5,alignx center");
		}
		{
			txtPassword = new JPasswordField();
			contentPanel.add(txtPassword, "cell 2 2,growx");
		}
		initDataBindings();
	}

	@Override
	public void refresh() {
		controller.refresh();
	}

	protected void initDataBindings() {
		BeanProperty<ILoginController, String> iLoginControllerBeanProperty = BeanProperty
				.create("ID");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty
				.create("text");
		AutoBinding<ILoginController, String, JTextField, String> autoBinding = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, controller,
						iLoginControllerBeanProperty, txtLogin,
						jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<ILoginController, Boolean> iLoginControllerBeanProperty_1 = BeanProperty
				.create("isLoginEnabled");
		BeanProperty<JTextField, Boolean> jTextFieldBeanProperty_1 = BeanProperty
				.create("enabled");
		AutoBinding<ILoginController, Boolean, JTextField, Boolean> autoBinding_1 = Bindings
				.createAutoBinding(UpdateStrategy.READ, controller,
						iLoginControllerBeanProperty_1, txtLogin,
						jTextFieldBeanProperty_1);
		autoBinding_1.bind();
		//
		BeanProperty<ILoginController, String> iLoginControllerBeanProperty_2 = BeanProperty
				.create("confirmation");
		BeanProperty<JPasswordField, String> jPasswordFieldBeanProperty = BeanProperty
				.create("text");
		AutoBinding<ILoginController, String, JPasswordField, String> autoBinding_2 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, controller,
						iLoginControllerBeanProperty_2, txtConfirmation,
						jPasswordFieldBeanProperty);
		autoBinding_2.bind();
		//
		BeanProperty<ILoginController, String> iLoginControllerBeanProperty_3 = BeanProperty
				.create("password");
		BeanProperty<JPasswordField, String> jPasswordFieldBeanProperty_1 = BeanProperty
				.create("text");
		AutoBinding<ILoginController, String, JPasswordField, String> autoBinding_3 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, controller,
						iLoginControllerBeanProperty_3, txtPassword,
						jPasswordFieldBeanProperty_1);
		autoBinding_3.bind();
		//
		BeanProperty<ILoginController, Boolean> iLoginControllerBeanProperty_4 = BeanProperty
				.create("isConfirmationVisible");
		BeanProperty<JLabel, Boolean> jLabelBeanProperty = BeanProperty
				.create("visible");
		AutoBinding<ILoginController, Boolean, JLabel, Boolean> autoBinding_4 = Bindings
				.createAutoBinding(UpdateStrategy.READ, controller,
						iLoginControllerBeanProperty_4, lblConfirmation,
						jLabelBeanProperty);
		autoBinding_4.bind();
		//
		BeanProperty<JPasswordField, Boolean> jPasswordFieldBeanProperty_2 = BeanProperty
				.create("visible");
		AutoBinding<ILoginController, Boolean, JPasswordField, Boolean> autoBinding_5 = Bindings
				.createAutoBinding(UpdateStrategy.READ, controller,
						iLoginControllerBeanProperty_4, txtConfirmation,
						jPasswordFieldBeanProperty_2);
		autoBinding_5.bind();
	}
}
