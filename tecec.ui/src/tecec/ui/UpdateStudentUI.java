package tecec.ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tecec.contract.RuleViolation;
import tecec.ui.contract.control.IUpdateStudentController;
import tecec.ui.contract.view.IUpdateStudentUI;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

public class UpdateStudentUI extends JDialog implements IUpdateStudentUI {

	private tecec.ui.contract.control.IUpdateStudentController updateStudentController;
	

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtEmail;
	private JButton btnUpdate;

	@Override
	public void setpkStudent(String pkStudent) {
		this.updateStudentController.setPKStudent(pkStudent);
	}
	
	private void updateStudent(){
		RuleViolation violation = this.updateStudentController.getUpdateViolation();
		
		if (violation != null) {
			JOptionPane.showMessageDialog(this, violation.getDescription(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
		else{
			try {
				this.updateStudentController.updateStudent();
				
				this.setVisible(false);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e, "ERRO", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * Create the dialog.
	 */
	public UpdateStudentUI(IUpdateStudentController updateStudentController) {
		this.updateStudentController = updateStudentController;
		
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
					updateStudent();
				}
			});
			contentPanel.add(btnUpdate, "cell 1 5,alignx right");
		}
		initDataBindings();
	}
	protected void initDataBindings() {
		BeanProperty<IUpdateStudentController, String> iUpdateStudentControllerBeanProperty = BeanProperty.create("studentName");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<IUpdateStudentController, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, updateStudentController, iUpdateStudentControllerBeanProperty, txtName, jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<IUpdateStudentController, String> iUpdateStudentControllerBeanProperty_1 = BeanProperty.create("studentEmail");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_1 = BeanProperty.create("text");
		AutoBinding<IUpdateStudentController, String, JTextField, String> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, updateStudentController, iUpdateStudentControllerBeanProperty_1, txtEmail, jTextFieldBeanProperty_1);
		autoBinding_1.bind();
		//
		BeanProperty<IUpdateStudentController, Boolean> iUpdateStudentControllerBeanProperty_2 = BeanProperty.create("canUpdate");
		BeanProperty<JButton, Boolean> jButtonBeanProperty = BeanProperty.create("enabled");
		AutoBinding<IUpdateStudentController, Boolean, JButton, Boolean> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ, updateStudentController, iUpdateStudentControllerBeanProperty_2, btnUpdate, jButtonBeanProperty);
		autoBinding_2.bind();
	}
}
