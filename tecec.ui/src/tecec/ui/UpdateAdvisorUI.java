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

public class UpdateAdvisorUI extends JDialog implements IUpdateAdvisorUI {

	private tecec.ui.contract.control.IUpdateAdvisorController updateAdvisorController;
	

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtEmail;
	private JButton btnUpdate;
	private JPanel panel;

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

	public UpdateAdvisorUI(IUpdateAdvisorController updateAdvisorController) {
		setPreferredSize(new Dimension(450, 230));
		setMinimumSize(new Dimension(450, 230));
		setMaximumSize(new Dimension(450, 230));
		this.updateAdvisorController = updateAdvisorController;
		
		setDefaultLookAndFeelDecorated(true);
		
		setModal(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		setBounds(100, 100, 450, 230);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setPreferredSize(new Dimension(450, 230));
		contentPanel.setMinimumSize(new Dimension(450, 230));
		contentPanel.setMaximumSize(new Dimension(450, 230));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			panel = new JPanel();
			panel.setMinimumSize(new Dimension(440, 220));
			panel.setMaximumSize(new Dimension(440, 220));
			panel.setPreferredSize(new Dimension(440, 220));
			panel.setBorder(new TitledBorder(null, "Atualizar Orientador:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 11, 414, 170);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				txtName = new JTextField();
				txtName.setBounds(64, 44, 337, 20);
				panel.add(txtName);
				txtName.setColumns(60);
			}
			{
				btnUpdate = new JButton("Atualizar");
				btnUpdate.setBounds(329, 136, 75, 23);
				panel.add(btnUpdate);
				{
					JLabel lblNewLabel = new JLabel("Nome:");
					lblNewLabel.setBounds(13, 47, 31, 14);
					panel.add(lblNewLabel);
				}
				{
					txtEmail = new JTextField();
					txtEmail.setBounds(64, 90, 337, 20);
					panel.add(txtEmail);
					txtEmail.setColumns(60);
				}
				{
					JLabel lblNewLabel_1 = new JLabel("E-Mail:");
					lblNewLabel_1.setBounds(13, 93, 32, 14);
					panel.add(lblNewLabel_1);
				}
				btnUpdate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						updateAdvisor();
					}
				});
			}
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
