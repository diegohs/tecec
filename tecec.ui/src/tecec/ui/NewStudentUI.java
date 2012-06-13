package tecec.ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
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
import java.awt.Font;

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
		setBounds(100, 100, 431, 228);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow][]", "[][][][][][][]"));
		{
			JLabel lblCadastrarNovoEstudante = new JLabel("Cadastrar novo Estudante");
			lblCadastrarNovoEstudante.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
			contentPanel.add(lblCadastrarNovoEstudante, "cell 1 1,alignx center");
		}
		{
			JLabel lblNewLabel = new JLabel("Nome:");
			contentPanel.add(lblNewLabel, "flowx,cell 1 2");
		}
		{
			JLabel lblNewLabel_1 = new JLabel("E-mail:");
			contentPanel.add(lblNewLabel_1, "flowx,cell 1 4");
		}
		{
			txtName = new JTextField();
			contentPanel.add(txtName, "cell 1 2,growx");
			txtName.setColumns(10);
		}
		{
			txtEmail = new JTextField();
			contentPanel.add(txtEmail, "cell 1 4,growx");
			txtEmail.setColumns(10);
		}
		{
			JButton btnNewCourse = new JButton("Cadastrar");
			btnNewCourse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					createStudent();
				}
			});
			contentPanel.add(btnNewCourse, "flowx,cell 1 5,alignx right");
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
}
