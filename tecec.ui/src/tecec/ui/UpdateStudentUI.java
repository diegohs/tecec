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

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

import javax.swing.border.TitledBorder;
import java.awt.Dimension;

public class UpdateStudentUI extends JDialog implements IUpdateStudentUI {

	private tecec.ui.contract.control.IUpdateStudentController updateStudentController;
	

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtEmail;
	private JButton btnUpdate;
	private JButton btnCourses;
	private JPanel panel;

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
	
	private void showStudentCourseViewerUI(){
		this.updateStudentController.showStudentCourseUI();
	}
	
	/**
	 * Create the dialog.
	 */
	public UpdateStudentUI(IUpdateStudentController updateStudentController) {
		this.updateStudentController = updateStudentController;
		
		setDefaultLookAndFeelDecorated(true);
		
		setModal(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 230);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setSize(new Dimension(45, 230));
		contentPanel.setMinimumSize(new Dimension(450, 230));
		contentPanel.setMaximumSize(new Dimension(450, 230));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
		}
		{
			panel = new JPanel();
			panel.setPreferredSize(new Dimension(440, 220));
			panel.setMinimumSize(new Dimension(440, 220));
			panel.setMaximumSize(new Dimension(440, 220));
			panel.setBorder(new TitledBorder(null, "Atualizar Estudante:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 11, 414, 170);
			contentPanel.add(panel);
			panel.setLayout(null);
			btnUpdate = new JButton("Atualizar");
			btnUpdate.setBounds(268, 135, 133, 23);
			panel.add(btnUpdate);
			{
				btnCourses = new JButton("Associar Cursos");
				btnCourses.setBounds(109, 135, 147, 23);
				panel.add(btnCourses);
				{
					txtEmail = new JTextField();
					txtEmail.setBounds(64, 90, 337, 20);
					panel.add(txtEmail);
					txtEmail.setColumns(60);
				}
				{
					txtName = new JTextField();
					txtName.setBounds(64, 44, 337, 20);
					panel.add(txtName);
					txtName.setColumns(60);
				}
				{
					JLabel lblNewLabel_1 = new JLabel("E-Mail:");
					lblNewLabel_1.setBounds(13, 93, 58, 14);
					panel.add(lblNewLabel_1);
				}
				{
					JLabel lblNewLabel = new JLabel("Nome:");
					lblNewLabel.setBounds(13, 47, 58, 14);
					panel.add(lblNewLabel);
				}
				btnCourses.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						showStudentCourseViewerUI();
					}
				});
			}
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					updateStudent();
				}
			});
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
