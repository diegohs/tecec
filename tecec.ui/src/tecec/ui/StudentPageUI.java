package tecec.ui;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JButton;

import tecec.ui.contract.control.IStudentPageController;
import tecec.ui.contract.view.IStudentPageUI;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.jdesktop.beansbinding.BeanProperty;
import java.util.List;
import tecec.ui.contract.record.ActivityRecord;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import tecec.dto.Course;
import org.jdesktop.beansbinding.ELProperty;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;
import javax.swing.JLabel;

public class StudentPageUI extends JInternalFrame implements IStudentPageUI {

	private static final long serialVersionUID = 5492458803232514399L;
	IStudentPageController controller;

	@Override
	public void setPKStudent(String pKStudent) {
		this.controller.setPKStudent(pKStudent);
	}

	private void showNewHandInFileDialog() {
		try {
			FileDialog dialog = new FileDialog((Frame) null);

			dialog.setVisible(true);

			this.controller.handIn(dialog.getDirectory() + dialog.getFile());

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e, "ERRO",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

	private JPanel contentPane;
	private JTable tblActivities;
	private JTextArea txtActivityDescription;
	private JButton btnHandIn;
	private JLabel lblDescription;
	private JLabel lblComentrioDaEntrega;
	private JTextArea txtActivityRemark;

	/**
	 * Create the frame.
	 */
	public StudentPageUI(IStudentPageController controller) {

		setFrameIcon(new ImageIcon(this.getClass().getResource("/tecec/ui/files/icone_tecec.png")));

		this.controller = controller;

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		contentPane
				.setLayout(new MigLayout(
						"",
						"[][grow][]",
						"[20px:n][grow][20px:n][60px:n:60px,grow][][][60px:n:60px,grow][20px:n][30px:n][20px:n]"));

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 1 1,grow");

		tblActivities = new JTable();
		scrollPane.setViewportView(tblActivities);

		lblDescription = new JLabel("Descri\u00E7\u00E3o da Atividade:");
		contentPane.add(lblDescription, "cell 1 2");

		txtActivityDescription = new JTextArea();
		txtActivityDescription.setEditable(false);
		contentPane.add(txtActivityDescription, "cell 1 3,grow");

		lblComentrioDaEntrega = new JLabel("Coment\u00E1rio da Entrega:");
		contentPane.add(lblComentrioDaEntrega, "cell 1 5");

		txtActivityRemark = new JTextArea();
		txtActivityRemark.setEditable(false);
		contentPane.add(txtActivityRemark, "cell 1 6,grow");

		btnHandIn = new JButton("Entregar Atividade");
		btnHandIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showNewHandInFileDialog();
			}
		});
		contentPane.add(btnHandIn, "cell 1 8,alignx right");
		initDataBindings();
	}

	@Override
	public void refresh() {
		controller.refresh();
	}
	protected void initDataBindings() {
		BeanProperty<IStudentPageController, List<ActivityRecord>> iStudentPageControllerBeanProperty = BeanProperty.create("activityRecords");
		JTableBinding<ActivityRecord, IStudentPageController, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, controller, iStudentPageControllerBeanProperty, tblActivities);
		//
		BeanProperty<ActivityRecord, Course> activityRecordBeanProperty = BeanProperty.create("course");
		ELProperty<ActivityRecord, Object> activityRecordEvalutionProperty = ELProperty.create(activityRecordBeanProperty, "${name} ${turn} ${year}");
		jTableBinding.addColumnBinding(activityRecordEvalutionProperty).setColumnName("Curso").setEditable(false);
		//
		BeanProperty<ActivityRecord, String> activityRecordBeanProperty_1 = BeanProperty.create("monograph.title");
		jTableBinding.addColumnBinding(activityRecordBeanProperty_1).setColumnName("Monografia").setEditable(false);
		//
		BeanProperty<ActivityRecord, String> activityRecordBeanProperty_2 = BeanProperty.create("stage.name");
		jTableBinding.addColumnBinding(activityRecordBeanProperty_2).setColumnName("Etapa").setEditable(false);
		//
		BeanProperty<ActivityRecord, String> activityRecordBeanProperty_3 = BeanProperty.create("activity.title");
		jTableBinding.addColumnBinding(activityRecordBeanProperty_3).setColumnName("Atividade").setEditable(false);
		//
		BeanProperty<ActivityRecord, String> activityRecordBeanProperty_4 = BeanProperty.create("activityDueDate");
		jTableBinding.addColumnBinding(activityRecordBeanProperty_4).setColumnName("Data de Entrega").setEditable(false);
		//
		BeanProperty<ActivityRecord, String> activityRecordBeanProperty_5 = BeanProperty.create("handInDate");
		jTableBinding.addColumnBinding(activityRecordBeanProperty_5).setColumnName("Entregue em").setEditable(false);
		//
		BeanProperty<ActivityRecord, String> activityRecordBeanProperty_6 = BeanProperty.create("handInGrade");
		jTableBinding.addColumnBinding(activityRecordBeanProperty_6).setColumnName("Nota").setEditable(false);
		//
		jTableBinding.bind();
		//
		BeanProperty<IStudentPageController, ActivityRecord> iStudentPageControllerBeanProperty_1 = BeanProperty.create("selectedActivityRecord");
		BeanProperty<JTable, ActivityRecord> jTableBeanProperty = BeanProperty.create("selectedElement");
		AutoBinding<IStudentPageController, ActivityRecord, JTable, ActivityRecord> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, controller, iStudentPageControllerBeanProperty_1, tblActivities, jTableBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<IStudentPageController, String> iStudentPageControllerBeanProperty_2 = BeanProperty.create("selectedActivityDescription");
		BeanProperty<JTextArea, String> jTextAreaBeanProperty = BeanProperty.create("text");
		AutoBinding<IStudentPageController, String, JTextArea, String> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ, controller, iStudentPageControllerBeanProperty_2, txtActivityDescription, jTextAreaBeanProperty);
		autoBinding_1.bind();
		//
		BeanProperty<IStudentPageController, Boolean> iStudentPageControllerBeanProperty_3 = BeanProperty.create("canHandIn");
		BeanProperty<JButton, Boolean> jButtonBeanProperty = BeanProperty.create("enabled");
		AutoBinding<IStudentPageController, Boolean, JButton, Boolean> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ, controller, iStudentPageControllerBeanProperty_3, btnHandIn, jButtonBeanProperty);
		autoBinding_2.bind();
		//
		BeanProperty<IStudentPageController, String> iStudentPageControllerBeanProperty_4 = BeanProperty.create("selectedHandInRemark");
		BeanProperty<JTextArea, String> jTextAreaBeanProperty_1 = BeanProperty.create("text");
		AutoBinding<IStudentPageController, String, JTextArea, String> autoBinding_3 = Bindings.createAutoBinding(UpdateStrategy.READ, controller, iStudentPageControllerBeanProperty_4, txtActivityRemark, jTextAreaBeanProperty_1);
		autoBinding_3.bind();
	}
}
