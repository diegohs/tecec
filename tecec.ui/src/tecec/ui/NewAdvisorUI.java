package tecec.ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import tecec.contract.RuleViolation;
import tecec.ui.contract.control.INewAdvisorController;
import tecec.ui.contract.view.INewAdvisorUI;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import java.awt.Dimension;

public class NewAdvisorUI extends JDialog implements INewAdvisorUI {


	private static final long serialVersionUID = 1L;
	private INewAdvisorController newAdvisorController;

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
	}

	private void createAdvisor() {
		try {
			RuleViolation violation = this.newAdvisorController
					.getCreationViolation();

			if (violation == null) {
				this.newAdvisorController.createAdvisor();

				this.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(this, violation.getDescription(),
						"Erro", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e, "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtEmail;

	public NewAdvisorUI(INewAdvisorController newAdvisorController) {
		this.newAdvisorController = newAdvisorController;
		
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
			JPanel panel = new JPanel();
			panel.setPreferredSize(new Dimension(440, 220));
			panel.setMinimumSize(new Dimension(440, 220));
			panel.setMaximumSize(new Dimension(440, 220));
			panel.setBorder(new TitledBorder(null, "Cadastrar Novo Orientador:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 11, 414, 170);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Nome:");
				lblNewLabel.setBounds(13, 47, 55, 14);
				panel.add(lblNewLabel);
			}
			{
				txtName = new JTextField();
				txtName.setBounds(64, 44, 337, 20);
				panel.add(txtName);
				txtName.setColumns(60);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("E-mail:");
				lblNewLabel_1.setBounds(13, 93, 55, 17);
				panel.add(lblNewLabel_1);
			}
			{
				txtEmail = new JTextField();
				txtEmail.setBounds(64, 90, 337, 20);
				panel.add(txtEmail);
				txtEmail.setColumns(60);
			}
			{
				JButton btnNewCourse = new JButton("Cadastrar");
				btnNewCourse.setBounds(274, 135, 127, 23);
				panel.add(btnNewCourse);
				btnNewCourse.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						createAdvisor();
					}
				});
			}
		}
		initDataBindings();
	}

	protected void initDataBindings() {
		BeanProperty<INewAdvisorController, String> iNewAdvisorControllerBeanProperty = BeanProperty
				.create("advisorName");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty
				.create("text");
		AutoBinding<INewAdvisorController, String, JTextField, String> autoBinding = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						newAdvisorController,
						iNewAdvisorControllerBeanProperty, txtName,
						jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<INewAdvisorController, String> iNewAdvisorControllerBeanProperty_1 = BeanProperty
				.create("advisorEmail");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_1 = BeanProperty
				.create("text");
		AutoBinding<INewAdvisorController, String, JTextField, String> autoBinding_1 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						newAdvisorController,
						iNewAdvisorControllerBeanProperty_1, txtEmail,
						jTextFieldBeanProperty_1);
		autoBinding_1.bind();
	}

	@Override
	public void refresh() {
		newAdvisorController.refresh();
	}
}
