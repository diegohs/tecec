package tecec.ui;

import java.awt.FileDialog;
import java.awt.Frame;

import javax.swing.JInternalFrame;
import net.miginfocom.swing.MigLayout;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

import tecec.ui.contract.control.ICoordinatorPageController;
import tecec.ui.contract.view.ICoordinatorPageUI;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdesktop.beansbinding.BeanProperty;
import java.util.List;
import tecec.ui.contract.record.ActivityRecord;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

import tecec.contract.RuleViolation;
import tecec.dto.Course;
import tecec.dto.Documentation;

import org.jdesktop.beansbinding.ELProperty;
import tecec.dto.Stage;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;

public class CoordinatorPageUI extends JInternalFrame implements ICoordinatorPageUI {
	/**
	 *
	 */
	private static final long serialVersionUID = 6753800487941687072L;
	private JTable table;
	private JTextField txtGrade;

	ICoordinatorPageController controller;
	private JTextArea txtRemark;
	private JButton btnUpdate;
	private JButton btnDownload;
	private JButton btnDelete;

	private void delete(){
		RuleViolation violation = this.controller.getDeletionViolation();

		if (violation != null) {
			JOptionPane.showMessageDialog(this, violation.getDescription(),
					"ERRO", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				this.controller.delete();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e, "ERRO",
						JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	private void update(){
		RuleViolation violation = this.controller.getUpdateViolation();

		if (violation != null) {
			JOptionPane.showMessageDialog(this, violation.getDescription(),
					"ERRO", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				this.controller.update();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e, "ERRO",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void downloadFile(){
		Documentation doc = this.controller.getSelectedHandInFile();

		FileDialog dialog = new FileDialog((Frame)null, "Salvar", FileDialog.SAVE);

		dialog.setFile(doc.getFileName());

		dialog.setVisible(true);

		String fileName = dialog.getDirectory() + dialog.getFile();

		File file = new File(fileName);

		try {
			FileOutputStream stream = new FileOutputStream(file);

			stream.write(doc.getData());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e, "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public CoordinatorPageUI(ICoordinatorPageController controller) {
		this.controller = controller;

		setFrameIcon(new ImageIcon(this.getClass().getResource("/tecec/ui/files/icone_tecec.png")));

		setBounds(100, 100, 642, 461);
		getContentPane().setLayout(new MigLayout("", "[][][88.00px:n][grow][20px:n]", "[20px:n][grow][20px:n][20px:n:30px,grow][20px:n][20px:n][20px:n][20px:n][20px:n]"));

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "cell 1 1 3 1,grow");

		table = new JTable();
		scrollPane.setViewportView(table);

		JLabel lblNota = new JLabel("Nota:");
		getContentPane().add(lblNota, "flowx,cell 1 2 3 1");

		txtGrade = new JTextField();
		getContentPane().add(txtGrade, "cell 1 3 2 1,growx");
		txtGrade.setColumns(10);

		JLabel lblComentrio = new JLabel("Coment\u00E1rio:");
		getContentPane().add(lblComentrio, "cell 3 2");

		txtRemark = new JTextArea();
		getContentPane().add(txtRemark, "cell 3 3 1 3,grow");

		btnUpdate = new JButton("Atualizar Entrega");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		getContentPane().add(btnUpdate, "flowx,cell 3 7,alignx right");

		btnDownload = new JButton("Baixar Arquivo");
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				downloadFile();
			}
		});
		getContentPane().add(btnDownload, "cell 3 7,alignx right");

		btnDelete = new JButton("Excluir Entrega");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		getContentPane().add(btnDelete, "cell 3 7,alignx right");
		initDataBindings();

	}

	@Override
	public void refresh() {
		this.controller.refresh();
	}
	protected void initDataBindings() {
		BeanProperty<ICoordinatorPageController, List<ActivityRecord>> iCoordinatorPageControllerBeanProperty = BeanProperty.create("activities");
		JTableBinding<ActivityRecord, ICoordinatorPageController, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, controller, iCoordinatorPageControllerBeanProperty, table);
		//
		BeanProperty<ActivityRecord, Course> activityRecordBeanProperty = BeanProperty.create("course");
		ELProperty<ActivityRecord, Object> activityRecordEvalutionProperty = ELProperty.create(activityRecordBeanProperty, "${name} ${turn} ${year}");
		jTableBinding.addColumnBinding(activityRecordEvalutionProperty).setColumnName("Curso").setEditable(false);
		//
		BeanProperty<ActivityRecord, String> activityRecordBeanProperty_1 = BeanProperty.create("student.name");
		jTableBinding.addColumnBinding(activityRecordBeanProperty_1).setColumnName("Estudante").setEditable(false);
		//
		BeanProperty<ActivityRecord, String> activityRecordBeanProperty_2 = BeanProperty.create("monograph.title");
		jTableBinding.addColumnBinding(activityRecordBeanProperty_2).setColumnName("Monografia").setEditable(false);
		//
		BeanProperty<ActivityRecord, Stage> activityRecordBeanProperty_3 = BeanProperty.create("stage");
		ELProperty<ActivityRecord, Object> activityRecordEvalutionProperty_1 = ELProperty.create(activityRecordBeanProperty_3, "${name} ${year}");
		jTableBinding.addColumnBinding(activityRecordEvalutionProperty_1).setColumnName("Etapa").setEditable(false);
		//
		BeanProperty<ActivityRecord, String> activityRecordBeanProperty_4 = BeanProperty.create("activity.title");
		jTableBinding.addColumnBinding(activityRecordBeanProperty_4).setColumnName("Atividade").setEditable(false);
		//
		BeanProperty<ActivityRecord, String> activityRecordBeanProperty_5 = BeanProperty.create("activityDueDate");
		jTableBinding.addColumnBinding(activityRecordBeanProperty_5).setColumnName("Data de Entrega").setEditable(false);
		//
		BeanProperty<ActivityRecord, String> activityRecordBeanProperty_6 = BeanProperty.create("handInDate");
		jTableBinding.addColumnBinding(activityRecordBeanProperty_6).setColumnName("Entregue em").setEditable(false);
		//
		BeanProperty<ActivityRecord, String> activityRecordBeanProperty_7 = BeanProperty.create("handInGrade");
		jTableBinding.addColumnBinding(activityRecordBeanProperty_7).setColumnName("Nota").setEditable(false);
		//
		jTableBinding.bind();
		//
		BeanProperty<ICoordinatorPageController, String> iCoordinatorPageControllerBeanProperty_1 = BeanProperty.create("selectedActivity.handIn.grade");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<ICoordinatorPageController, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, controller, iCoordinatorPageControllerBeanProperty_1, txtGrade, jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<ICoordinatorPageController, String> iCoordinatorPageControllerBeanProperty_2 = BeanProperty.create("selectedActivity.handIn.remark");
		BeanProperty<JTextArea, String> jTextAreaBeanProperty = BeanProperty.create("text");
		AutoBinding<ICoordinatorPageController, String, JTextArea, String> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, controller, iCoordinatorPageControllerBeanProperty_2, txtRemark, jTextAreaBeanProperty);
		autoBinding_1.bind();
		//
		BeanProperty<ICoordinatorPageController, Boolean> iCoordinatorPageControllerBeanProperty_3 = BeanProperty.create("canUpdate");
		BeanProperty<JButton, Boolean> jButtonBeanProperty = BeanProperty.create("enabled");
		AutoBinding<ICoordinatorPageController, Boolean, JButton, Boolean> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ, controller, iCoordinatorPageControllerBeanProperty_3, btnUpdate, jButtonBeanProperty);
		autoBinding_2.bind();
		//
		BeanProperty<ICoordinatorPageController, Boolean> iCoordinatorPageControllerBeanProperty_4 = BeanProperty.create("canDownloadFile");
		AutoBinding<ICoordinatorPageController, Boolean, JButton, Boolean> autoBinding_3 = Bindings.createAutoBinding(UpdateStrategy.READ, controller, iCoordinatorPageControllerBeanProperty_4, btnDownload, jButtonBeanProperty);
		autoBinding_3.bind();
		//
		BeanProperty<ICoordinatorPageController, Boolean> iCoordinatorPageControllerBeanProperty_5 = BeanProperty.create("canDelete");
		AutoBinding<ICoordinatorPageController, Boolean, JButton, Boolean> autoBinding_4 = Bindings.createAutoBinding(UpdateStrategy.READ, controller, iCoordinatorPageControllerBeanProperty_5, btnDelete, jButtonBeanProperty);
		autoBinding_4.bind();
		//
		BeanProperty<ICoordinatorPageController, ActivityRecord> iCoordinatorPageControllerBeanProperty_6 = BeanProperty.create("selectedActivity");
		BeanProperty<JTable, ActivityRecord> jTableBeanProperty = BeanProperty.create("selectedElement");
		AutoBinding<ICoordinatorPageController, ActivityRecord, JTable, ActivityRecord> autoBinding_5 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, controller, iCoordinatorPageControllerBeanProperty_6, table, jTableBeanProperty);
		autoBinding_5.bind();
	}
}
