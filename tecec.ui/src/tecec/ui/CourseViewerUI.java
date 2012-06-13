package tecec.ui;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tecec.ui.contract.control.ICourseViewerController;
import tecec.ui.contract.view.ICourseViewerUI;
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
import tecec.dto.Course;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;
import java.awt.Font;

public class CourseViewerUI extends JDialog implements ICourseViewerUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	
	
	private ICourseViewerController courseViewerController;
	private JTextField textField;
	private JTable table;
	private JButton btnAtualizar;
	private JButton btnRemover;
	
	private void showNewCourseUI () {
		courseViewerController.showNewCourseUI();
	}
	
	private void showUpdateCourseUI () {
		courseViewerController.showUpdateCourseUI();
	}
	
	private void deleteCourseUI () {
		this.courseViewerController.deleteCourse();
	}

	public CourseViewerUI(ICourseViewerController courseViewerController) {
		if (courseViewerController == null)
			throw new IllegalArgumentException ("courseViewerController");
		
		this.courseViewerController = courseViewerController;
		
		setDefaultLookAndFeelDecorated(true);
		setModal(true);
		
		setBounds(100, 100, 568, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][][grow]", "[][][grow][]"));
		{
			JLabel lblFiltro = new JLabel("Filtro:");
			lblFiltro.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
			contentPanel.add(lblFiltro, "cell 1 1,alignx trailing");
		}
		{
			textField = new JTextField();
			contentPanel.add(textField, "cell 2 1,growx");
			textField.setColumns(10);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, "cell 1 2 2 1,grow");
			{
				table = new JTable();
				scrollPane.setViewportView(table);
			}
		}
		{
			JButton btnNovo = new JButton("Cadastrar Novo");
			btnNovo.addActionListener(new ActionListener () {

				@Override
				public void actionPerformed(ActionEvent e) {
					showNewCourseUI();
				}
				
			});
			contentPanel.add(btnNovo, "flowx,cell 2 3,alignx left");
		}
		{
			btnAtualizar = new JButton("Atualizar Selecionado");
			btnAtualizar.addActionListener(new ActionListener () {

				@Override
				public void actionPerformed(ActionEvent e) {
					showUpdateCourseUI();
					
				}
				
			});
			
			contentPanel.add(btnAtualizar, "cell 2 3");
		}
		{
			btnRemover = new JButton("Remover Selecionado");
			btnRemover.addActionListener(new ActionListener () {

				@Override
				public void actionPerformed(ActionEvent e) {
					deleteCourseUI();
				}
				
			});
			contentPanel.add(btnRemover, "cell 2 3");
		}
		initDataBindings();
	}
	
	
	
	protected void initDataBindings() {
		BeanProperty<ICourseViewerController, String> iCourseViewerControllerBeanProperty = BeanProperty.create("nameFilter");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<ICourseViewerController, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, courseViewerController, iCourseViewerControllerBeanProperty, textField, jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<ICourseViewerController, List<Course>> iCourseViewerControllerBeanProperty_1 = BeanProperty.create("courses");
		JTableBinding<Course, ICourseViewerController, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, courseViewerController, iCourseViewerControllerBeanProperty_1, table);
		//
		BeanProperty<Course, String> courseBeanProperty = BeanProperty.create("name");
		jTableBinding.addColumnBinding(courseBeanProperty).setColumnName("Nome");
		//
		BeanProperty<Course, String> courseBeanProperty_1 = BeanProperty.create("turn");
		jTableBinding.addColumnBinding(courseBeanProperty_1).setColumnName("Turno");
		//
		BeanProperty<Course, String> courseBeanProperty_2 = BeanProperty.create("year");
		jTableBinding.addColumnBinding(courseBeanProperty_2).setColumnName("Ano");
		//
		jTableBinding.bind();
		//
		BeanProperty<ICourseViewerController, Course> iCourseViewerControllerBeanProperty_2 = BeanProperty.create("selectedCourse");
		BeanProperty<JTable, Course> jTableBeanProperty = BeanProperty.create("selectedElement");
		AutoBinding<ICourseViewerController, Course, JTable, Course> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, courseViewerController, iCourseViewerControllerBeanProperty_2, table, jTableBeanProperty);
		autoBinding_1.bind();
		//
		BeanProperty<ICourseViewerController, Boolean> iCourseViewerControllerBeanProperty_3 = BeanProperty.create("canUpdateCourse");
		BeanProperty<JButton, Boolean> jButtonBeanProperty = BeanProperty.create("enabled");
		AutoBinding<ICourseViewerController, Boolean, JButton, Boolean> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ, courseViewerController, iCourseViewerControllerBeanProperty_3, btnAtualizar, jButtonBeanProperty);
		autoBinding_2.bind();
		//
		BeanProperty<ICourseViewerController, Boolean> iCourseViewerControllerBeanProperty_4 = BeanProperty.create("canDeleteCourse");
		AutoBinding<ICourseViewerController, Boolean, JButton, Boolean> autoBinding_3 = Bindings.createAutoBinding(UpdateStrategy.READ, courseViewerController, iCourseViewerControllerBeanProperty_4, btnRemover, jButtonBeanProperty);
		autoBinding_3.bind();
	}
}
