package tecec.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.JOptionPane;

import org.jdesktop.beansbinding.BeanProperty;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import tecec.dto.Course;
import tecec.ui.contract.control.ICourseViewerController;
import tecec.ui.contract.view.ICouseViewerUI;
import javax.swing.ListSelectionModel;

public class CourseViewerUI extends JFrame implements ICouseViewerUI {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ICourseViewerController courseViewerController;
	
	private void showUpdateCourseUI(){
		this.courseViewerController.showUpdateCourseUI();
	}
	
	private void showNewCourseUI(){
		this.courseViewerController.showNewCourseUI();
	}
	
	private void deleteCourse(){
		this.courseViewerController.deleteCourse();
	}

	private JPanel contentPane;
	private JTextField txtFilter;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnNewCourse;
	private JButton btnUpdateCourse;
	private JButton btnDeleteCourse;

	/**
	 * Create the frame.
	 */
	public CourseViewerUI(ICourseViewerController courseViewerController) {
		this.courseViewerController = courseViewerController;	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 726, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow][grow 50][]", "[20px:n][][20px:n][grow][5px:n][20px:n]"));
		
		JLabel lblFilter = new JLabel("Filtro:");
		contentPane.add(lblFilter, "flowx,cell 1 1");
		
		txtFilter = new JTextField();
		contentPane.add(txtFilter, "cell 1 1 2 1,growx");
		txtFilter.setColumns(10);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 1 3 2 1,grow");
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		btnNewCourse = new JButton("Adicionar Novo");
		btnNewCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showNewCourseUI();
			}
		});
		contentPane.add(btnNewCourse, "flowx,cell 2 5,alignx right,aligny bottom");
		
		btnUpdateCourse = new JButton("Atualizar Selecionado");
		btnUpdateCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showUpdateCourseUI();				
			}
		});
		contentPane.add(btnUpdateCourse, "flowx,cell 2 5,alignx right,aligny bottom");
		
		btnDeleteCourse = new JButton("Excluir Selecionado");
		btnDeleteCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteCourse();
			}
		});
		contentPane.add(btnDeleteCourse, "flowx,cell 2 5,alignx right,aligny bottom");
		initDataBindings();
	}
	protected void initDataBindings() {
		BeanProperty<ICourseViewerController, String> iCourseViewerControllerBeanProperty = BeanProperty.create("nameFilter");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<ICourseViewerController, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, courseViewerController, iCourseViewerControllerBeanProperty, txtFilter, jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<ICourseViewerController, List<Course>> iCourseViewerControllerBeanProperty_1 = BeanProperty.create("courses");
		JTableBinding<Course, ICourseViewerController, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, courseViewerController, iCourseViewerControllerBeanProperty_1, table);
		//
		BeanProperty<Course, String> courseBeanProperty = BeanProperty.create("name");
		jTableBinding.addColumnBinding(courseBeanProperty).setColumnName("Curso");
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
		AutoBinding<ICourseViewerController, Boolean, JButton, Boolean> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ, courseViewerController, iCourseViewerControllerBeanProperty_3, btnUpdateCourse, jButtonBeanProperty);
		autoBinding_2.bind();
		//
		BeanProperty<ICourseViewerController, Boolean> iCourseViewerControllerBeanProperty_4 = BeanProperty.create("canDeleteCourse");
		AutoBinding<ICourseViewerController, Boolean, JButton, Boolean> autoBinding_3 = Bindings.createAutoBinding(UpdateStrategy.READ, courseViewerController, iCourseViewerControllerBeanProperty_4, btnDeleteCourse, jButtonBeanProperty);
		autoBinding_3.bind();
	}
}
