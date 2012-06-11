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

import tecec.ui.contract.control.IUpdatePermissionController;
import tecec.ui.contract.view.IUpdatePermissionUI;

public class UpdatePermissionUI extends JDialog implements IUpdatePermissionUI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private IUpdatePermissionController updatePermissionController;

	@Override
	public void setpKPermission(String pKPermission) {		
		this.updatePermissionController.setPKPermission(pKPermission);
	}
	
	@Override
	public void setVisible(boolean visible){
		super.setVisible(visible);
	}
	
	private void storePermission() {
		try {
			RuleViolation violation = this.updatePermissionController
					.getUpdateViolation();

			if (violation != null) {
				JOptionPane.showMessageDialog(this, violation.getDescription(),
						"Erro", JOptionPane.ERROR_MESSAGE);
			} else {
				this.updatePermissionController.updatePermission();
				
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
	private JButton btnUpdatePermission;

	/**
	 * Create the frame.
	 */
	public UpdatePermissionUI(IUpdatePermissionController updatePermissionController) {
		this.updatePermissionController = updatePermissionController;
		
		setDefaultLookAndFeelDecorated(true);

		setModal(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 436, 245);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow][grow]",
				"[grow][][56.00][29.00][grow]"));

		JLabel lblNewLabel = new JLabel("Atualizar Permissão");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblNewLabel, "cell 1 1,alignx center");

		JLabel lblNome = new JLabel("Descrição:");
		contentPane.add(lblNome, "flowx,cell 1 2");

		txtPermissionDescription = new JTextField();
		contentPane.add(txtPermissionDescription, "cell 1 2,growx");
		txtPermissionDescription.setColumns(10);
		
		btnUpdatePermission = new JButton("Atualizar");
		btnUpdatePermission.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				storePermission();
			}
		});
		contentPane.add(btnUpdatePermission, "cell 1 3,alignx center,growy");
		initDataBindings();
	}
	protected void initDataBindings() {
		BeanProperty<IUpdatePermissionController, String> iUpdatePermissionControllerBeanProperty = BeanProperty.create("permissionDescription");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<IUpdatePermissionController, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, updatePermissionController, iUpdatePermissionControllerBeanProperty, txtPermissionDescription, jTextFieldBeanProperty);
		autoBinding.bind();
	}
}
