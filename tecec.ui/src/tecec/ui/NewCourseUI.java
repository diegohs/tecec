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


public class NewCourseUI extends JFrame {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private tecec.ui.contract.NewCourseController newCourseController;
	
	private void storeCourse(){
		String errorMessage = this.newCourseController.getInvalidFieldsMessage();
		
		if (errorMessage != null) {
			JOptionPane.showMessageDialog(this, errorMessage, "Erro", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			try {
				this.newCourseController.storeCourse();	
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}			
			
			JOptionPane.showMessageDialog(this, "Curso cadastrado com sucesso.");
		}		
	}
	
	private JPanel contentPane;
	private JTextField txtCourseName;
	private JButton btnCreateCourse;

	/**
	 * Create the frame.
	 */
	public NewCourseUI(tecec.ui.contract.NewCourseController newCourseController) {
		if (newCourseController == null) {
			throw new IllegalArgumentException("newCourseController");
		}
		
		this.newCourseController = newCourseController;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 436, 245);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow][grow]", "[grow][][56.00][29.00][grow]"));
		
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
		BeanProperty<NewCourseController, String> newCourseControllerBeanProperty = BeanProperty.create("course.name");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<NewCourseController, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, newCourseController, newCourseControllerBeanProperty, txtCourseName, jTextFieldBeanProperty);
		autoBinding.bind();
	}
}
