package tecec.ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tecec.contract.RuleViolation;
import tecec.ui.contract.control.IUpdateAdvisorController;
import tecec.ui.contract.view.IUpdateAdvisorUI;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

public class UpdateAdvisorUI extends JDialog implements IUpdateAdvisorUI {

	private tecec.ui.contract.control.IUpdateAdvisorController updateAdvisorController;
	

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtEmail;
	private JButton btnUpdate;

	@Override
	public void setpkAdvisor(String pkAdvisor) {
		this.updateAdvisorController.setPKAdvisor(pkAdvisor);
	}
	
	private void updateAdvisor(){
		RuleViolation violation = this.updateAdvisorController.getUpdateViolation();
		
		if (violation != null) {
			JOptionPane.showMessageDialog(this, violation.getDescription(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
		else{
			try {
				this.updateAdvisorController.updateAdvisor();
				
				this.setVisible(false);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e, "ERRO", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * Create the dialog.
	 */
	public UpdateAdvisorUI(IUpdateAdvisorController updateAdvisorController) {
		this.updateAdvisorController = updateAdvisorController;
		
		setDefaultLookAndFeelDecorated(true);
		
		setModal(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow][]", "[grow][][][][][][grow]"));
		{
			JLabel lblNewLabel = new JLabel("Nome:");
			contentPanel.add(lblNewLabel, "flowx,cell 1 1");
		}
		{
			JLabel lblNewLabel_1 = new JLabel("E-Mail:");
			contentPanel.add(lblNewLabel_1, "flowx,cell 1 3");
		}
		{
			txtName = new JTextField();
			contentPanel.add(txtName, "cell 1 1,growx");
			txtName.setColumns(10);
		}
		{
			txtEmail = new JTextField();
			contentPanel.add(txtEmail, "cell 1 3,growx");
			txtEmail.setColumns(10);
		}
		{
			btnUpdate = new JButton("Atualizar");
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					updateAdvisor();
				}
			});
			contentPanel.add(btnUpdate, "cell 1 5,alignx right");
		}
		initDataBindings();
	}
	protected void initDataBindings() {
		BeanProperty<IUpdateAdvisorController, String> iUpdateAdvisorControllerBeanProperty = BeanProperty.create("advisorName");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<IUpdateAdvisorController, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, updateAdvisorController, iUpdateAdvisorControllerBeanProperty, txtName, jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<IUpdateAdvisorController, String> iUpdateAdvisorControllerBeanProperty_1 = BeanProperty.create("advisorEmail");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_1 = BeanProperty.create("text");
		AutoBinding<IUpdateAdvisorController, String, JTextField, String> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, updateAdvisorController, iUpdateAdvisorControllerBeanProperty_1, txtEmail, jTextFieldBeanProperty_1);
		autoBinding_1.bind();
		//
		BeanProperty<IUpdateAdvisorController, Boolean> iUpdateAdvisorControllerBeanProperty_2 = BeanProperty.create("canUpdate");
		BeanProperty<JButton, Boolean> jButtonBeanProperty = BeanProperty.create("enabled");
		AutoBinding<IUpdateAdvisorController, Boolean, JButton, Boolean> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ, updateAdvisorController, iUpdateAdvisorControllerBeanProperty_2, btnUpdate, jButtonBeanProperty);
		autoBinding_2.bind();
	}
}
