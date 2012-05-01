package tecec.ui;

import javax.swing.JDialog;import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

import tecec.ui.contract.INewCourseController;
import tecec.contract.RuleViolation;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

import java.awt.Container;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import org.jdesktop.beansbinding.*;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewCourseUI extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private tecec.ui.contract.INewCourseController newCourseController;
	
	public void showUI(Container parent){		
		this.setModal(true);
		this.setLocationRelativeTo(parent);
		
		this.setVisible(true);
	}
	
	private void storeCourse() {
		try {
			RuleViolation violation = this.newCourseController
					.getCreationViolation();
			if (violation != null) {
				JOptionPane.showMessageDialog(this, violation.getDescription(),
						"Erro", JOptionPane.ERROR_MESSAGE);
			} else {
				this.newCourseController.createCourse();

				JOptionPane.showMessageDialog(this,
						"Curso cadastrado com sucesso.");
				
				this.setVisible(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e, "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private JPanel contentPane;
	private JTextField txtCourseName;
	private JButton btnCreateCourse;

	/**
	 * Create the frame.
	 */
	public NewCourseUI(
			tecec.ui.contract.INewCourseController newCourseController) {
		if (newCourseController == null) {
			throw new IllegalArgumentException("newCourseController");
		}

		this.newCourseController = newCourseController;

		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 436, 245);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow][grow]",
				"[grow][][56.00][29.00][grow]"));

		JLabel lblNewLabel = new JLabel("Cadastrar Novo Curso");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblNewLabel, "cell 1 1,alignx center");

		JLabel lblNome = new JLabel("Nome:");
		contentPane.add(lblNome, "flowx,cell 1 2");

		txtCourseName = new JTextField();
		contentPane.add(txtCourseName, "cell 1 2,growx");
		txtCourseName.setColumns(10);

		btnCreateCourse = new JButton("Cadastrar");
		btnCreateCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				storeCourse();
			}
		});
		contentPane.add(btnCreateCourse, "cell 1 3,alignx center,growy");
		initDataBindings();
	}

	protected void initDataBindings() {
		BeanProperty<INewCourseController, String> iNewCourseControllerBeanProperty = BeanProperty
				.create("courseName");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty
				.create("text");
		AutoBinding<INewCourseController, String, JTextField, String> autoBinding = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						newCourseController, iNewCourseControllerBeanProperty,
						txtCourseName, jTextFieldBeanProperty);
		autoBinding.bind();
	}
}
