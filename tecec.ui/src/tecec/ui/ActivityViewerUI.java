package tecec.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.ELProperty;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import tecec.contract.RuleViolation;
import tecec.dto.Activity;
import tecec.ui.contract.control.IActivityViewerController;

public class ActivityViewerUI extends JDialog implements
		tecec.ui.contract.view.IActivityViewerUI {

	private tecec.ui.contract.control.IActivityViewerController activityViewerController;

	private void showNewActivityUI() {
		this.activityViewerController.showNewActivityUI();
	}

	private void showUpdateActivityUI() {
		this.activityViewerController.showUpdateActivityUI();
	}

	private void deleteActivity() {
		RuleViolation violation = this.activityViewerController
				.getDeletionViolation();

		if (violation != null) {
			JOptionPane.showMessageDialog(this, violation.getDescription(),
					"ERRO", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				this.activityViewerController.deleteActivity();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e,
						"ERRO", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private final JPanel contentPanel = new JPanel();
	private JTextField txtFilter;
	private JTable tblActivities;
	private JButton btnNewActivity;
	private JButton btnUpdateActivity;
	private JButton btnDeleteActivity;

	public ActivityViewerUI(IActivityViewerController activityViewerController) {
		this.activityViewerController = activityViewerController;

		setModal(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 520, 335);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow][]", "[20px:n][][20px:n][grow][20px:n][20px:n]"));
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
				tblActivities = new JTable();
				tblActivities.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPane.setViewportView(tblActivities);
			}
		}
		{
			btnNewActivity = new JButton("Cadastrar Atividade");
			btnNewActivity.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showNewActivityUI();
				}
			});
			contentPanel.add(btnNewActivity, "flowx,cell 1 4,alignx right");
		}
		{
			btnUpdateActivity = new JButton("Atualizar Atividade");
			btnUpdateActivity.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showUpdateActivityUI();
				}
			});
			contentPanel.add(btnUpdateActivity, "cell 1 4,alignx right");
		}
		{
			btnDeleteActivity = new JButton("Excluir Atividade");
			btnDeleteActivity.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteActivity();
				}
			});
			contentPanel.add(btnDeleteActivity, "cell 1 4,alignx right");
		}
		initDataBindings();
	}
	protected void initDataBindings() {
		BeanProperty<IActivityViewerController, Boolean> iActivityViewerControllerBeanProperty_1 = BeanProperty.create("canUpdate");
		BeanProperty<JButton, Boolean> jButtonBeanProperty = BeanProperty.create("enabled");
		AutoBinding<IActivityViewerController, Boolean, JButton, Boolean> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ, activityViewerController, iActivityViewerControllerBeanProperty_1, btnUpdateActivity, jButtonBeanProperty);
		autoBinding_1.bind();
		//
		BeanProperty<IActivityViewerController, Boolean> iActivityViewerControllerBeanProperty_2 = BeanProperty.create("canDelete");
		AutoBinding<IActivityViewerController, Boolean, JButton, Boolean> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ, activityViewerController, iActivityViewerControllerBeanProperty_2, btnDeleteActivity, jButtonBeanProperty);
		autoBinding_2.bind();
		//
		BeanProperty<IActivityViewerController, String> iActivityViewerControllerBeanProperty_3 = BeanProperty.create("titleFilter");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<IActivityViewerController, String, JTextField, String> autoBinding_3 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, activityViewerController, iActivityViewerControllerBeanProperty_3, txtFilter, jTextFieldBeanProperty);
		autoBinding_3.bind();
		//
		BeanProperty<IActivityViewerController, List<Activity>> iActivityViewerControllerBeanProperty_4 = BeanProperty.create("activities");
		JTableBinding<Activity, IActivityViewerController, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, activityViewerController, iActivityViewerControllerBeanProperty_4, tblActivities);
		//
		ELProperty<Activity, Object> activityEvalutionProperty = ELProperty.create("${dueDate}");
		jTableBinding.addColumnBinding(activityEvalutionProperty).setColumnName("Data").setEditable(false);
		//
		BeanProperty<Activity, String> activityBeanProperty_1 = BeanProperty.create("title");
		jTableBinding.addColumnBinding(activityBeanProperty_1).setColumnName("T\u00EDtulo").setEditable(false);
		//
		BeanProperty<Activity, String> activityBeanProperty_2 = BeanProperty.create("description");
		jTableBinding.addColumnBinding(activityBeanProperty_2).setColumnName("Descri\u00E7\u00E3o");
		//
		jTableBinding.bind();
		//
		BeanProperty<IActivityViewerController, Activity> iActivityViewerControllerBeanProperty = BeanProperty.create("selectedActivity");
		BeanProperty<JTable, Activity> jTableBeanProperty = BeanProperty.create("selectedElement");
		AutoBinding<IActivityViewerController, Activity, JTable, Activity> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, activityViewerController, iActivityViewerControllerBeanProperty, tblActivities, jTableBeanProperty);
		autoBinding.bind();
	}
}
