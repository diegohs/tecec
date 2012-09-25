package tecec.ui;


import javax.swing.JDialog;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import tecec.contract.RuleViolation;
import tecec.ui.contract.control.INewAccountController;
import tecec.ui.contract.view.INewAccountUI;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

public class NewAccountUI extends JDialog implements INewAccountUI {
	/**
	 *
	 */
	private static final long serialVersionUID = -4727931413656355731L;
	private JTextField txtID;
	private JTextField txtName;

	INewAccountController controller;

	private void insertAccount(){
		RuleViolation violation = controller.getInsertViolation();

		if (violation != null) {
			JOptionPane.showMessageDialog(this, violation.getDescription(),
					"ERRO", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				this.controller.insert();

				this.setVisible(false);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e, "ERRO",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public NewAccountUI(INewAccountController controller) {
		this.controller = controller;

		setDefaultLookAndFeelDecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/tecec/ui/files/icone_tecec.png")));

		setModal(true);
		setResizable(false);
		setBounds(100, 100, 419, 195);
		getContentPane().setLayout(new MigLayout("", "[][][grow][]", "[][][][][][][]"));

		JLabel lblNewLabel = new JLabel("ID:");
		getContentPane().add(lblNewLabel, "flowx,cell 1 2");

		txtID = new JTextField();
		getContentPane().add(txtID, "cell 2 2,growx");
		txtID.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Nome:");
		getContentPane().add(lblNewLabel_3, "cell 1 3,alignx left");

		txtName = new JTextField();
		getContentPane().add(txtName, "cell 2 3,growx");
		txtName.setColumns(10);

		JButton btnInsert = new JButton("Cadastrar");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				insertAccount();
			}
		});
		getContentPane().add(btnInsert, "cell 1 5 2 1,alignx center");
		initDataBindings();

	}

	@Override
	public void refresh() {
		this.controller.refresh();
	}
	protected void initDataBindings() {
		BeanProperty<INewAccountController, String> iNewAccountControllerBeanProperty = BeanProperty.create("userName");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<INewAccountController, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, controller, iNewAccountControllerBeanProperty, txtName, jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<INewAccountController, String> iNewAccountControllerBeanProperty_1 = BeanProperty.create("ID");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_1 = BeanProperty.create("text");
		AutoBinding<INewAccountController, String, JTextField, String> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, controller, iNewAccountControllerBeanProperty_1, txtID, jTextFieldBeanProperty_1);
		autoBinding_1.bind();
	}
}
