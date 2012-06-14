package tecec.ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tecec.contract.RuleViolation;
import tecec.ui.contract.control.IMonographStageViewerController;
import tecec.ui.contract.control.IStudentCourseViewerController;
import tecec.ui.contract.view.IMonographStageViewerUI;
import tecec.ui.contract.view.IStudentCourseViewerUI;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.jdesktop.beansbinding.BeanProperty;
import java.util.List;
import tecec.dto.Stage;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.ELProperty;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import tecec.dto.Course;

public class StudentCourseViewerUI extends JDialog implements IStudentCourseViewerUI {
	
	private static final long serialVersionUID = 4782681832315871391L;
	
	IStudentCourseViewerController controller;

	@Override
	public void setPKStudent(String pKStudent) {
		this.controller.setPKStudent(pKStudent);
	}
	
	private void insertCourse(){
		RuleViolation violation = this.controller.getInsertViolation();

		if (violation != null) {
			JOptionPane.showMessageDialog(this, violation.getDescription(),
					"ERRO", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				this.controller.insertCourse();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e, "ERRO",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void deleteCourse(){
		RuleViolation violation = this.controller.getDeletionViolation();

		if (violation != null) {
			JOptionPane.showMessageDialog(this, violation.getDescription(),
					"ERRO", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				this.controller.deleteCourse();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e, "ERRO",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void commit(){
		try {
			this.controller.commit();
			
			this.setVisible(false);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e, "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void cancel(){
		this.setVisible(false);
	}

	private final JPanel contentPanel = new JPanel();
	private JTable tblStages;
	private JTable tblCorrelatedStages;
	private JButton btnInsert;
	private JButton btnDelete;

	/**
	 * Create the dialog.
	 */
	public StudentCourseViewerUI(IStudentCourseViewerController controller) {
		this.controller = controller;

		setModal(true);
		setDefaultLookAndFeelDecorated(true);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		setBounds(100, 100, 531, 368);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow][120px:n][120px:n][120px:n][grow]", "[][50px:n,grow][50px:n,grow][][30px:n][]"));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, "cell 1 1 1 2,grow");
			{
				tblStages = new JTable();
				tblStages.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPane.setViewportView(tblStages);
			}
		}
		{
			btnInsert = new JButton(">>");
			btnInsert.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					insertCourse();
				}
			});
			contentPanel.add(btnInsert, "flowx,cell 2 1,alignx center,aligny bottom");
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, "cell 3 1 1 2,grow");
			{
				tblCorrelatedStages = new JTable();
				tblCorrelatedStages.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPane.setViewportView(tblCorrelatedStages);
			}
		}
		{
			btnDelete = new JButton("<<");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteCourse();
				}
			});
			contentPanel.add(btnDelete, "cell 2 2,alignx center,aligny top");
		}
		{
			JButton btnCommit = new JButton("Confirmar");
			btnCommit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					commit();
				}
			});
			contentPanel.add(btnCommit, "flowx,cell 2 4,growx");
		}
		{
			JButton btnCancel = new JButton("Cancelar");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cancel();
				}
			});
			contentPanel.add(btnCancel, "cell 3 4,growx");
		}
		initDataBindings();
	}
	protected void initDataBindings() {
		BeanProperty<IStudentCourseViewerController, List<Course>> iStudentCourseViewerControllerBeanProperty = BeanProperty.create("courses");
		JTableBinding<Course, IStudentCourseViewerController, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, controller, iStudentCourseViewerControllerBeanProperty, tblStages);
		//
		ELProperty<Course, Object> courseEvalutionProperty = ELProperty.create("${year} ${turn} ${name}");
		jTableBinding.addColumnBinding(courseEvalutionProperty).setColumnName("N\u00E3o-Correlacionados").setEditable(false);
		//
		jTableBinding.bind();
		//
		BeanProperty<IStudentCourseViewerController, Course> iStudentCourseViewerControllerBeanProperty_1 = BeanProperty.create("selectedCourse");
		BeanProperty<JTable, Course> jTableBeanProperty = BeanProperty.create("selectedElement");
		AutoBinding<IStudentCourseViewerController, Course, JTable, Course> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, controller, iStudentCourseViewerControllerBeanProperty_1, tblStages, jTableBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<IStudentCourseViewerController, List<Course>> iStudentCourseViewerControllerBeanProperty_2 = BeanProperty.create("correlatedCourses");
		JTableBinding<Course, IStudentCourseViewerController, JTable> jTableBinding_1 = SwingBindings.createJTableBinding(UpdateStrategy.READ, controller, iStudentCourseViewerControllerBeanProperty_2, tblCorrelatedStages);
		//
		ELProperty<Course, Object> courseEvalutionProperty_1 = ELProperty.create("${year} ${turn} ${name}");
		jTableBinding_1.addColumnBinding(courseEvalutionProperty_1).setColumnName("Correlacionados").setEditable(false);
		//
		jTableBinding_1.bind();
		//
		BeanProperty<IStudentCourseViewerController, Course> iStudentCourseViewerControllerBeanProperty_3 = BeanProperty.create("selectedCorrelatedCourse");
		BeanProperty<JTable, Course> jTableBeanProperty_1 = BeanProperty.create("selectedElement");
		AutoBinding<IStudentCourseViewerController, Course, JTable, Course> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, controller, iStudentCourseViewerControllerBeanProperty_3, tblCorrelatedStages, jTableBeanProperty_1);
		autoBinding_1.bind();
		//
		BeanProperty<IStudentCourseViewerController, Boolean> iStudentCourseViewerControllerBeanProperty_4 = BeanProperty.create("canDelete");
		BeanProperty<JButton, Boolean> jButtonBeanProperty = BeanProperty.create("enabled");
		AutoBinding<IStudentCourseViewerController, Boolean, JButton, Boolean> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ, controller, iStudentCourseViewerControllerBeanProperty_4, btnDelete, jButtonBeanProperty);
		autoBinding_2.bind();
		//
		BeanProperty<IStudentCourseViewerController, Boolean> iStudentCourseViewerControllerBeanProperty_5 = BeanProperty.create("canInsert");
		AutoBinding<IStudentCourseViewerController, Boolean, JButton, Boolean> autoBinding_3 = Bindings.createAutoBinding(UpdateStrategy.READ, controller, iStudentCourseViewerControllerBeanProperty_5, btnInsert, jButtonBeanProperty);
		autoBinding_3.bind();
	}
}
