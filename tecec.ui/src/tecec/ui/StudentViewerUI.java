package tecec.ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tecec.ui.contract.control.IStudentViewerController;
import tecec.ui.contract.view.IStudentViewerUI;
import net.miginfocom.swing.MigLayout;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import java.util.List;

import tecec.contract.RuleViolation;
import tecec.dto.Student;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;


public class StudentViewerUI extends JDialog implements IStudentViewerUI {

	private static final long serialVersionUID = 1L;
	private IStudentViewerController studentViewerController;

	private void showNewStudentUI(){
		this.studentViewerController.showNewStudentUI();
	}

	private void showUpdateStudentUI(){
		this.studentViewerController.showUpdateStudentUI();
	}

	private void deleteStudent(){
		RuleViolation violation = this.studentViewerController.getDeletionViolation();

		if (violation != null) {
			JOptionPane.showMessageDialog(this, violation.getDescription(),
					"ERRO", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				this.studentViewerController.deleteStudent();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e, "ERRO",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private final JPanel contentPanel = new JPanel();
	private JTextField txtFilter;
	private JTable tblStudent;
	private JButton btnUpdateStudent;
	private JButton btnDeleteStudent;
	private JPanel panelPesquisa;
	private JPanel panelButtons;

	public StudentViewerUI(IStudentViewerController studentViewerController) {
		setTitle("Alunos");
		getContentPane().setPreferredSize(new Dimension(800, 600));
		setPreferredSize(new Dimension(800, 600));
		getContentPane().setMinimumSize(new Dimension(800, 600));
		setMinimumSize(new Dimension(800, 600));
		getContentPane().setMaximumSize(new Dimension(800, 600));
		setMaximumSize(new Dimension(800, 600));
		this.studentViewerController = studentViewerController;

		setDefaultLookAndFeelDecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/tecec/ui/files/icone_tecec.png")));

		setModal(true);
		setBounds(100, 100, 687, 345);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setPreferredSize(new Dimension(800, 600));
		contentPanel.setMinimumSize(new Dimension(800, 600));
		contentPanel.setMaximumSize(new Dimension(800, 600));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow]"));
		{
			panelPesquisa = new JPanel();
			panelPesquisa.setPreferredSize(new Dimension(750, 60));
			panelPesquisa.setMinimumSize(new Dimension(750, 60));
			panelPesquisa.setMaximumSize(new Dimension(750, 60));
			panelPesquisa.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisa:", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 1, 10))); // NOI18N
			contentPanel.add(panelPesquisa, "cell 0 0,grow");
			{
				txtFilter = new JTextField();
				panelPesquisa.add(txtFilter);
				txtFilter.setColumns(90);
			}
		}
		{
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setPreferredSize(new Dimension(750, 400));
				scrollPane.setMinimumSize(new Dimension(750, 400));
				scrollPane.setMaximumSize(new Dimension(750, 400));
				contentPanel.add(scrollPane, "cell 0 1,grow");
				{
					tblStudent = new JTable();
					tblStudent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					scrollPane.setViewportView(tblStudent);
				}
			}
			{
				panelButtons = new JPanel();
				FlowLayout flowLayout = (FlowLayout) panelButtons.getLayout();
				flowLayout.setAlignment(FlowLayout.RIGHT);
				panelButtons.setPreferredSize(new Dimension(750, 60));
				panelButtons.setMinimumSize(new Dimension(750, 60));
				panelButtons.setMaximumSize(new Dimension(750, 60));
				panelButtons.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opção:", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 1, 10))); // NOI18N
				contentPanel.add(panelButtons, "cell 0 2,grow");
				JButton btnNewStudent = new JButton("Adicionar Novo");
				btnNewStudent.setPreferredSize(new Dimension(150, 25));
				btnNewStudent.setMinimumSize(new Dimension(150, 25));
				btnNewStudent.setMaximumSize(new Dimension(150, 25));
				panelButtons.add(btnNewStudent);
				{
					btnUpdateStudent = new JButton("Atualizar Selecionado");
					btnUpdateStudent.setPreferredSize(new Dimension(150, 25));
					btnUpdateStudent.setMinimumSize(new Dimension(150, 25));
					btnUpdateStudent.setMaximumSize(new Dimension(150, 25));
					panelButtons.add(btnUpdateStudent);
					{
						btnDeleteStudent = new JButton("Excluir Selecionado");
						btnDeleteStudent.setPreferredSize(new Dimension(150, 25));
						btnDeleteStudent.setMinimumSize(new Dimension(150, 25));
						btnDeleteStudent.setMaximumSize(new Dimension(150, 25));
						panelButtons.add(btnDeleteStudent);
						btnDeleteStudent.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								deleteStudent();
							}
						});
					}
					btnUpdateStudent.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							showUpdateStudentUI();
						}
					});
				}
				btnNewStudent.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						showNewStudentUI();
					}
				});
			}
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

	@Override
	public void refresh() {
		studentViewerController.refresh();
	}
}
