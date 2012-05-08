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
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import tecec.contract.RuleViolation;
import tecec.ui.contract.control.IAreaViewerController;
import tecec.ui.contract.record.AreaRecord;
import tecec.ui.contract.view.IAreaViewerUI;

public class AreaViewerUI extends JDialog implements IAreaViewerUI {

	private tecec.ui.contract.control.IAreaViewerController areaViewerController;

	private void createArea() {
		this.areaViewerController.showNewAreaUI();
	}

	private void updateArea() {
		this.areaViewerController.showUpdateAreaUI();
	}

	private void deleteArea() {
		RuleViolation violation = this.areaViewerController
				.getDeletionViolation();

		if (violation != null) {
			JOptionPane.showMessageDialog(this, violation.getDescription(),
					"ERRO", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				this.areaViewerController.deleteArea();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e, "ERRO",
						JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNameFilter;
	private JTable tblArea;
	private JButton btnUpdateArea;
	private JButton btnDeleteArea;

	/**
	 * Create the dialog.
	 */
	public AreaViewerUI(IAreaViewerController areViewerController) {
		this.areaViewerController = areViewerController;

		setModal(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 579, 339);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow][]",
				"[][][][grow][][]"));
		{
			JLabel lblNewLabel = new JLabel("Filtro");
			contentPanel.add(lblNewLabel, "flowx,cell 1 1");
		}
		{
			txtNameFilter = new JTextField();
			contentPanel.add(txtNameFilter, "cell 1 1,growx");
			txtNameFilter.setColumns(10);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, "cell 1 3,grow");
			{
				tblArea = new JTable();
				tblArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPane.setViewportView(tblArea);
			}
		}
		{
			JButton btnNewArea = new JButton("Adicionar \u00C1rea");
			btnNewArea.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					createArea();
				}
			});
			contentPanel.add(btnNewArea, "flowx,cell 1 4,alignx right");
		}
		{
			btnUpdateArea = new JButton("Atualizar \u00C1rea");
			btnUpdateArea.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					updateArea();
				}
			});
			contentPanel.add(btnUpdateArea, "cell 1 4,alignx right");
		}
		{
			btnDeleteArea = new JButton("Excluir \u00C1rea");
			btnDeleteArea.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteArea();
				}
			});
			contentPanel.add(btnDeleteArea, "cell 1 4,alignx right");
		}
		initDataBindings();
	}
	protected void initDataBindings() {
		BeanProperty<IAreaViewerController, String> iAreaViewerControllerBeanProperty = BeanProperty.create("nameFilter");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<IAreaViewerController, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, areaViewerController, iAreaViewerControllerBeanProperty, txtNameFilter, jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<IAreaViewerController, List<AreaRecord>> iAreaViewerControllerBeanProperty_1 = BeanProperty.create("areas");
		JTableBinding<AreaRecord, IAreaViewerController, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, areaViewerController, iAreaViewerControllerBeanProperty_1, tblArea);
		//
		BeanProperty<AreaRecord, String> areaRecordBeanProperty = BeanProperty.create("mainAreaName");
		jTableBinding.addColumnBinding(areaRecordBeanProperty).setColumnName("Super \u00C1rea").setEditable(false);
		//
		BeanProperty<AreaRecord, String> areaRecordBeanProperty_1 = BeanProperty.create("area.name");
		jTableBinding.addColumnBinding(areaRecordBeanProperty_1).setColumnName("Nome").setEditable(false);
		//
		BeanProperty<AreaRecord, String> areaRecordBeanProperty_2 = BeanProperty.create("area.description");
		jTableBinding.addColumnBinding(areaRecordBeanProperty_2).setColumnName("Descri\u00E7\u00E3o").setEditable(false);
		//
		jTableBinding.bind();
		//
		BeanProperty<IAreaViewerController, Boolean> iAreaViewerControllerBeanProperty_3 = BeanProperty.create("canUpdateArea");
		BeanProperty<JButton, Boolean> jButtonBeanProperty = BeanProperty.create("enabled");
		AutoBinding<IAreaViewerController, Boolean, JButton, Boolean> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ, areaViewerController, iAreaViewerControllerBeanProperty_3, btnUpdateArea, jButtonBeanProperty);
		autoBinding_2.bind();
		//
		BeanProperty<IAreaViewerController, Boolean> iAreaViewerControllerBeanProperty_4 = BeanProperty.create("canDeleteArea");
		AutoBinding<IAreaViewerController, Boolean, JButton, Boolean> autoBinding_3 = Bindings.createAutoBinding(UpdateStrategy.READ, areaViewerController, iAreaViewerControllerBeanProperty_4, btnDeleteArea, jButtonBeanProperty);
		autoBinding_3.bind();
		//
		BeanProperty<IAreaViewerController, AreaRecord> iAreaViewerControllerBeanProperty_5 = BeanProperty.create("selectedArea");
		BeanProperty<JTable, AreaRecord> jTableBeanProperty = BeanProperty.create("selectedElement");
		AutoBinding<IAreaViewerController, AreaRecord, JTable, AreaRecord> autoBinding_4 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, areaViewerController, iAreaViewerControllerBeanProperty_5, tblArea, jTableBeanProperty);
		autoBinding_4.bind();
	}
}
