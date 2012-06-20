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
import tecec.ui.contract.control.INewStudentController;
import tecec.ui.contract.view.INewStudentUI;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

public class NewStudentUI extends JDialog implements INewStudentUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private INewStudentController newStudentController;

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
	}

	private void createStudent() {
		try {
			RuleViolation violation = this.newStudentController
					.getCreationViolation();

			if (violation == null) {
				this.newStudentController.createStudent();

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

	/**
	 * Create the dialog.
	 */
	public NewStudentUI(INewStudentController newStudentController) {
		this.newStudentController = newStudentController;
		
		setDefaultLookAndFeelDecorated(true);

		setModal(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 230);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setPreferredSize(new Dimension(450, 230));
		contentPanel.setLocation(new Point(450, 230));
		contentPanel.setMaximumSize(new Dimension(45, 230));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cadastrar Novo Aluno:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
				JLabel lblNewLabel = new JLabel("Nome:");
				lblNewLabel.setBounds(13, 47, 57, 14);
				panel.add(lblNewLabel);
			}
			{
				txtEmail = new JTextField();
				txtEmail.setBounds(64, 90, 337, 20);
				panel.add(txtEmail);
				txtEmail.setColumns(60);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("E-mail:");
				lblNewLabel_1.setBounds(13, 93, 57, 14);
				panel.add(lblNewLabel_1);
			}
			{
				JButton btnNewCourse = new JButton("Cadastrar");
				btnNewCourse.setBounds(274, 135, 127, 23);
				panel.add(btnNewCourse);
				btnNewCourse.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						createStudent();
					}
				});
			}
		}
		initDataBindings();
	}

	protected void initDataBindings() {
		BeanProperty<INewStudentController, String> iNewStudentControllerBeanProperty = BeanProperty
				.create("studentName");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty
				.create("text");
		AutoBinding<INewStudentController, String, JTextField, String> autoBinding = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						newStudentController,
						iNewStudentControllerBeanProperty, txtName,
						jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<INewStudentController, String> iNewStudentControllerBeanProperty_1 = BeanProperty
				.create("studentEmail");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_1 = BeanProperty
				.create("text");
		AutoBinding<INewStudentController, String, JTextField, String> autoBinding_1 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						newStudentController,
						iNewStudentControllerBeanProperty_1, txtEmail,
						jTextFieldBeanProperty_1);
		autoBinding_1.bind();
	}

	@Override
	public void refresh() {
		newStudentController.refresh();
	}
}
