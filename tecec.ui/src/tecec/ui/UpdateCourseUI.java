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

import tecec.ui.contract.control.IUpdateCourseController;
import tecec.ui.contract.view.IUpdateCourseUI;

public class UpdateCourseUI extends JDialog implements IUpdateCourseUI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IUpdateCourseController updateCourseController;

	@Override
	public void setpKCourse(String pKCourse) {
		this.updateCourseController.setPKCourse(pKCourse);
	}
	
	@Override
	public void setVisible(boolean visible){
		super.setVisible(visible);
	}
	
	private void storeCourse() {
		try {
			RuleViolation violation = this.updateCourseController
					.getUpdateViolation();

			if (violation != null) {
				JOptionPane.showMessageDialog(this, violation.getDescription(),
						"Erro", JOptionPane.ERROR_MESSAGE);
			} else {
				this.updateCourseController.updateCourse();
				
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
	private JButton btnUpdateCourse;

	/**
	 * Create the frame.
	 */
	public UpdateCourseUI(IUpdateCourseController updateCourseController) {
		this.updateCourseController = updateCourseController;

		setModal(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 436, 245);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow][grow]",
				"[grow][][56.00][29.00][grow]"));

		JLabel lblNewLabel = new JLabel("Atualizar Curso");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblNewLabel, "cell 1 1,alignx center");

		JLabel lblNome = new JLabel("Nome:");
		contentPane.add(lblNome, "flowx,cell 1 2");

		txtCourseName = new JTextField();
		contentPane.add(txtCourseName, "cell 1 2,growx");
		txtCourseName.setColumns(10);

		btnUpdateCourse = new JButton("Atualizar");
		btnUpdateCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				storeCourse();
			}
		});
		contentPane.add(btnUpdateCourse, "cell 1 3,alignx center,growy");
		initDataBindings();
	}
	protected void initDataBindings() {
		BeanProperty<IUpdateCourseController, String> iUpdateCourseControllerBeanProperty = BeanProperty.create("courseName");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<IUpdateCourseController, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, updateCourseController, iUpdateCourseControllerBeanProperty, txtCourseName, jTextFieldBeanProperty);
		autoBinding.bind();
	}
}
