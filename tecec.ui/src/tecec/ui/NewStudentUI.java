package tecec.ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tecec.ui.contract.NewCourseController;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewStudentUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private tecec.ui.contract.NewCourseController newStudentController;

	private void storeCourse() {
		String errorMessage = this.newStudentController
				.getInvalidFieldsMessage();

		if (errorMessage != null) {
			JOptionPane.showMessageDialog(this, errorMessage, "Erro",
					JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				this.newStudentController.storeCourse();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Erro",
						JOptionPane.ERROR_MESSAGE);
			}

			JOptionPane
					.showMessageDialog(this, "Aluno cadastrado com sucesso.");
		}
	}

	private JPanel contentPane;
	private JTextField txtStudentName;
	private JButton btnCreateStudent;
	private JLabel lblEmail;
	private JTextField txtStudentEmail;

	/**
	 * Create the frame.
	 */
	public NewStudentUI(
			tecec.ui.contract.NewCourseController newCourseController) {
		if (newCourseController == null) {
			throw new IllegalArgumentException("newCourseController");
		}

		this.newStudentController = newCourseController;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 436, 245);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow][grow]", "[grow][][][][][][24.00][29.00][grow]"));
		
				JLabel lblNewLabel = new JLabel("Cadastrar Novo Aluno");
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
				contentPane.add(lblNewLabel, "cell 1 0,alignx center");

		btnCreateStudent = new JButton("Cadastrar");
		btnCreateStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				storeCourse();
			}
		});
				
						JLabel lblNome = new JLabel("Nome:");
						contentPane.add(lblNome, "flowx,cell 1 2");
		
				lblEmail = new JLabel("E-mail:");
				contentPane.add(lblEmail, "flowx,cell 1 4");
		contentPane.add(btnCreateStudent, "cell 1 7,alignx center,growy");
						
								txtStudentName = new JTextField();
								contentPane.add(txtStudentName, "cell 1 2,growx");
								txtStudentName.setColumns(10);
								
										txtStudentEmail = new JTextField();
										txtStudentEmail.setColumns(10);
										contentPane.add(txtStudentEmail, "cell 1 4,growx");
		initDataBindings();
	}

	protected void initDataBindings() {
		BeanProperty<NewCourseController, String> newCourseControllerBeanProperty = BeanProperty
				.create("course.name");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty
				.create("text");
		AutoBinding<NewCourseController, String, JTextField, String> autoBinding = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						newStudentController, newCourseControllerBeanProperty,
						txtStudentName, jTextFieldBeanProperty);
		autoBinding.bind();
	}
}
