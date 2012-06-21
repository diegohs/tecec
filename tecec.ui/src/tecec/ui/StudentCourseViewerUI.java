package tecec.ui;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tecec.contract.RuleViolation;
import tecec.ui.contract.control.IStudentCourseViewerController;
import tecec.ui.contract.view.IStudentCourseViewerUI;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.jdesktop.beansbinding.BeanProperty;
import java.util.List;
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
import java.awt.Dimension;

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
		getContentPane().setPreferredSize(new Dimension(800, 600));
		setPreferredSize(new Dimension(800, 600));
		getContentPane().setMinimumSize(new Dimension(800, 600));
		setMinimumSize(new Dimension(800, 600));
		getContentPane().setMaximumSize(new Dimension(800, 600));
		setMaximumSize(new Dimension(800, 600));
		this.controller = controller;

		setModal(true);
		setDefaultLookAndFeelDecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/tecec/ui/files/icone_tecec.png")));
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);

		setBounds(100, 100, 531, 368);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setPreferredSize(new Dimension(800, 600));
		contentPanel.setMinimumSize(new Dimension(800, 600));
		contentPanel.setMaximumSize(new Dimension(800, 600));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow][grow][grow]", "[grow][grow][grow]"));
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setPreferredSize(new Dimension(350, 400));
			scrollPane.setMinimumSize(new Dimension(350, 400));
			scrollPane.setMaximumSize(new Dimension(350, 400));
			contentPanel.add(scrollPane, "cell 0 0 1 2,grow");
			{
				tblStages = new JTable();
				tblStages.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPane.setViewportView(tblStages);
			}
		}
		{
			btnInsert = new JButton(">>");
			btnInsert.setMinimumSize(new Dimension(50, 25));
			btnInsert.setMaximumSize(new Dimension(50, 25));
			btnInsert.setPreferredSize(new Dimension(50, 25));
			btnInsert.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					insertCourse();
				}
			});
			contentPanel.add(btnInsert, "flowx,cell 1 0,alignx center,aligny bottom");
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setPreferredSize(new Dimension(350, 400));
			scrollPane.setMinimumSize(new Dimension(350, 400));
			scrollPane.setMaximumSize(new Dimension(350, 400));
			contentPanel.add(scrollPane, "cell 2 0 1 2,grow");
			{
				tblCorrelatedStages = new JTable();
				tblCorrelatedStages.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPane.setViewportView(tblCorrelatedStages);
			}
		}
		{
			btnDelete = new JButton("<<");
			btnDelete.setMinimumSize(new Dimension(50, 25));
			btnDelete.setMaximumSize(new Dimension(50, 25));
			btnDelete.setPreferredSize(new Dimension(50, 25));
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteCourse();
				}
			});
			contentPanel.add(btnDelete, "cell 1 1,alignx center,aligny top");
		}
		{
			{
				JPanel panel = new JPanel();
				panel.setPreferredSize(new Dimension(750, 6));
				panel.setMinimumSize(new Dimension(750, 60));
				panel.setMaximumSize(new Dimension(750, 60));
				panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opção:", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 1, 10))); // NOI18N
				contentPanel.add(panel, "cell 0 2 3 1,grow");
				JButton btnCommit = new JButton("Confirmar");
				btnCommit.setPreferredSize(new Dimension(150, 25));
				btnCommit.setMinimumSize(new Dimension(150, 25));
				btnCommit.setMaximumSize(new Dimension(150, 25));
				panel.add(btnCommit);
				{
					JButton btnCancel = new JButton("Cancelar");
					btnCancel.setPreferredSize(new Dimension(150, 25));
					btnCancel.setMinimumSize(new Dimension(150, 25));
					btnCancel.setMaximumSize(new Dimension(150, 25));
					panel.add(btnCancel);
					btnCancel.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							cancel();
						}
					});
				}
				btnCommit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						commit();
					}
				});
			}
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

	@Override
	public void refresh() {
		controller.refresh();
	}
}
