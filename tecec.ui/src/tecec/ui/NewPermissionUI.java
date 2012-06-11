package tecec.ui;

import javax.swing.JDialog;import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tecec.ui.contract.control.INewPermissionController;
import tecec.ui.contract.view.INewPermissionUI;
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

public class NewPermissionUI extends JDialog implements INewPermissionUI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private tecec.ui.contract.control.INewPermissionController newPermissionController;
		
	@Override
	public void setVisible(boolean visible){
		super.setVisible(visible);
	}
	
	private void storePermission() {
		try {
			RuleViolation violation = this.newPermissionController
					.getCreationViolation();
			if (violation != null) {
				JOptionPane.showMessageDialog(this, violation.getDescription(),
						"Erro", JOptionPane.ERROR_MESSAGE);
			} else {
				this.newPermissionController.createPermission();
				
				this.setVisible(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e, "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private JPanel contentPane;
	private JTextField txtPermissionDescription;
	private JButton btnCreatePermission;

	/**
	 * Create the frame.
	 */
	public NewPermissionUI(
			tecec.ui.contract.control.INewPermissionController newPermissionController) {
		if (newPermissionController == null) {
			throw new IllegalArgumentException("newPermissionController");
		}

		this.newPermissionController = newPermissionController;
		
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

		JLabel lblNewLabel = new JLabel("Cadastrar Nova Permissão");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblNewLabel, "cell 1 1,alignx center");

		JLabel lblNome = new JLabel("Descrição:");
		contentPane.add(lblNome, "flowx,cell 1 2");

		txtPermissionDescription = new JTextField();
		contentPane.add(txtPermissionDescription, "cell 1 2,growx");
		txtPermissionDescription.setColumns(10);

		btnCreatePermission = new JButton("Cadastrar");
		btnCreatePermission.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				storePermission();
			}
		});
		contentPane.add(btnCreatePermission, "cell 1 3,alignx center,growy");
		initDataBindings();
	}

	protected void initDataBindings() {
		BeanProperty<INewPermissionController, String> iNewPermissionControllerBeanProperty = BeanProperty
				.create("permissionDescription");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty
				.create("text");
		AutoBinding<INewPermissionController, String, JTextField, String> autoBinding = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						newPermissionController, iNewPermissionControllerBeanProperty,
						txtPermissionDescription, jTextFieldBeanProperty);
		autoBinding.bind();
	}
}
