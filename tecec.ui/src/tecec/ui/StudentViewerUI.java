package tecec.ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tecec.ui.contract.control.IStudentViewerController;
import tecec.ui.contract.view.IStudentViewerUI;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import java.util.List;
import tecec.dto.Student;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class StudentViewerUI extends JDialog implements IStudentViewerUI {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IStudentViewerController studentViewerController;
	
	private void showNewStudentUI(){
		this.studentViewerController.showNewStudentUI();
	}
	
	private void showUpdateStudentUI(){
		this.studentViewerController.showUpdateStudentUI();
	}
	
	private void deleteStudent(){
		this.studentViewerController.deleteStudent();
	}

	private final JPanel contentPanel = new JPanel();
	private JTextField txtFilter;
	private JTable tblStudent;
	private JButton btnUpdateStudent;
	private JButton btnDeleteStudent;

	public StudentViewerUI(IStudentViewerController studentViewerController) {
		this.studentViewerController = studentViewerController;
		
		setDefaultLookAndFeelDecorated(true);

		setModal(true);
		setBounds(100, 100, 687, 345);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][280.00,grow][]", "[][][][grow][][]"));
		{
			JLabel lblNewLabel = new JLabel("Filtro:");
			contentPanel.add(lblNewLabel, "flowx,cell 1 1");
		}
		{
			txtFilter = new JTextField();
			contentPanel.add(txtFilter, "cell 1 1,growx");
			txtFilter.setColumns(10);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, "cell 1 3,grow");
			{
				tblStudent = new JTable();
				tblStudent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPane.setViewportView(tblStudent);
			}
		}
		{
			JButton btnNewStudent = new JButton("Adicionar Novo");
			btnNewStudent.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showNewStudentUI();
				}
			});
			contentPanel.add(btnNewStudent, "flowx,cell 1 4,alignx right");
		}
		{
			btnUpdateStudent = new JButton("Atualizar Selecionado");
			btnUpdateStudent.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showUpdateStudentUI();
				}
			});
			contentPanel.add(btnUpdateStudent, "cell 1 4,alignx right");
		}
		{
			btnDeleteStudent = new JButton("Excluir Selecionado");
			btnDeleteStudent.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteStudent();
				}
			});
			contentPanel.add(btnDeleteStudent, "cell 1 4,alignx right");
		}
		initDataBindings();
	}
	protected void initDataBindings() {
		BeanProperty<IStudentViewerController, String> iStudentViewerControllerBeanProperty = BeanProperty.create("nameFilter");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<IStudentViewerController, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, studentViewerController, iStudentViewerControllerBeanProperty, txtFilter, jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<IStudentViewerController, List<Student>> iStudentViewerControllerBeanProperty_1 = BeanProperty.create("students");
		JTableBinding<Student, IStudentViewerController, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, studentViewerController, iStudentViewerControllerBeanProperty_1, tblStudent);
		//
		BeanProperty<Student, String> studentBeanProperty = BeanProperty.create("name");
		jTableBinding.addColumnBinding(studentBeanProperty).setColumnName("Nome").setEditable(false);
		//
		BeanProperty<Student, String> studentBeanProperty_1 = BeanProperty.create("email");
		jTableBinding.addColumnBinding(studentBeanProperty_1).setColumnName("E-mail").setEditable(false);
		//
		jTableBinding.bind();
		//
		BeanProperty<IStudentViewerController, Student> iStudentViewerControllerBeanProperty_2 = BeanProperty.create("selectedStudent");
		BeanProperty<JTable, Student> jTableBeanProperty = BeanProperty.create("selectedElement");
		AutoBinding<IStudentViewerController, Student, JTable, Student> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, studentViewerController, iStudentViewerControllerBeanProperty_2, tblStudent, jTableBeanProperty);
		autoBinding_1.bind();
		//
		BeanProperty<IStudentViewerController, Boolean> iStudentViewerControllerBeanProperty_3 = BeanProperty.create("canUpdateStudent");
		BeanProperty<JButton, Boolean> jButtonBeanProperty = BeanProperty.create("enabled");
		AutoBinding<IStudentViewerController, Boolean, JButton, Boolean> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ, studentViewerController, iStudentViewerControllerBeanProperty_3, btnUpdateStudent, jButtonBeanProperty);
		autoBinding_2.bind();
		//
		BeanProperty<IStudentViewerController, Boolean> iStudentViewerControllerBeanProperty_4 = BeanProperty.create("canDeleteStudent");
		AutoBinding<IStudentViewerController, Boolean, JButton, Boolean> autoBinding_3 = Bindings.createAutoBinding(UpdateStrategy.READ, studentViewerController, iStudentViewerControllerBeanProperty_4, btnDeleteStudent, jButtonBeanProperty);
		autoBinding_3.bind();
	}
}
