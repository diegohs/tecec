package tecec.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import org.jdesktop.beansbinding.BeanProperty;
import tecec.ui.contract.ICourseViewerController;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import java.util.List;
import tecec.dto.Course;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CourseViewerUI extends JFrame {
	
	private ICourseViewerController courseViewerController;
	private NewCourseUI newCourseUI;
	private UpdateCourseUI updateCourseUI;
	
	private void showNewCourseUI()	{
		newCourseUI.setVisible(true);
	}
	
	private void showUpdateCourseUI(){
		Course selectedCourse = this.courseViewerController.getSelectedCourse();
		
		if (selectedCourse == null) {
			JOptionPane.showMessageDialog(this, "Selecione o curso a ser alterado.", "Erro", JOptionPane.ERROR_MESSAGE);
			
			return;
		}
		
		updateCourseUI.showUI(this, selectedCourse.getPKCourse(), selectedCourse.getName());
	}

	private JPanel contentPane;
	private JTextField txtFilter;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnNewCourse;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	/**
	 * Create the frame.
	 */
	public CourseViewerUI(ICourseViewerController courseViewerController, NewCourseUI newCourseUI, UpdateCourseUI updateCourseUI) {
		this.courseViewerController = courseViewerController;
		this.newCourseUI = newCourseUI;
		this.updateCourseUI = updateCourseUI;
		
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
		scrollPane.setViewportView(table);
		
		btnNewCourse = new JButton("Adicionar Novo");
		btnNewCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showNewCourseUI();
			}
		});
		contentPane.add(btnNewCourse, "flowx,cell 2 5,alignx right,aligny bottom");
		
		btnNewButton = new JButton("Atualizar Selecionado");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showUpdateCourseUI();
				
			}
		});
		contentPane.add(btnNewButton, "cell 2 5,alignx right");
		
		btnNewButton_1 = new JButton("Excluir Selecionado");
		contentPane.add(btnNewButton_1, "cell 2 5,alignx right");
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
	}
}
