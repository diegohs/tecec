package tecec.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
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
import tecec.dto.record.AreaRecord;
import tecec.ui.contract.control.IAreaViewerController;
import tecec.ui.contract.view.IAreaViewerUI;
import java.awt.Dimension;
import java.awt.Toolkit;

import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;

public class AreaViewerUI extends JDialog implements IAreaViewerUI {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private tecec.ui.contract.control.IAreaViewerController areaViewerController;

	private void export() {
		this.areaViewerController.export();
	}

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
	private JPanel panelPesquisa;
	private JPanel panelButtons;
	private JButton btnExport;

	/**
	 * Create the dialog.
	 */
	public AreaViewerUI(IAreaViewerController areViewerController) {
		setTitle("\u00C1reas");
		setPreferredSize(new Dimension(800, 600));
		setMinimumSize(new Dimension(800, 600));
		setMaximumSize(new Dimension(800, 600));
		this.areaViewerController = areViewerController;

		setDefaultLookAndFeelDecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				MainUI.class.getResource("/tecec/ui/files/icone_tecec.png")));

		setModal(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 579, 339);
		getContentPane().setLayout(new MigLayout("", "[800px]", "[600px]"));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, "cell 0 0,grow");
		contentPanel.setLayout(new MigLayout("", "[800px]",
				"[50px][400px][50px]"));
		{
			panelPesquisa = new JPanel();
			panelPesquisa.setPreferredSize(new Dimension(750, 60));
			panelPesquisa.setMinimumSize(new Dimension(750, 60));
			panelPesquisa.setMaximumSize(new Dimension(750, 60));
			panelPesquisa.setBorder(javax.swing.BorderFactory
					.createTitledBorder(null, "Pesquisa:",
							javax.swing.border.TitledBorder.LEFT,
							javax.swing.border.TitledBorder.DEFAULT_POSITION,
							new java.awt.Font("Comic Sans MS", 1, 10))); // NOI18N
			contentPanel.add(panelPesquisa,
					"cell 0 0,alignx center,aligny center");
			{
				txtNameFilter = new JTextField();
				panelPesquisa.add(txtNameFilter);
				txtNameFilter.setColumns(90);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setPreferredSize(new Dimension(750, 400));
			scrollPane.setMinimumSize(new Dimension(750, 400));
			scrollPane.setMaximumSize(new Dimension(750, 400));
			contentPanel.add(scrollPane, "cell 0 1,grow");
			{
				tblArea = new JTable();
				tblArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPane.setViewportView(tblArea);
			}
		}
		{
			{
				panelButtons = new JPanel();
				FlowLayout flowLayout = (FlowLayout) panelButtons.getLayout();
				flowLayout.setAlignment(FlowLayout.RIGHT);
				panelButtons.setPreferredSize(new Dimension(750, 60));
				panelButtons.setMinimumSize(new Dimension(750, 60));
				panelButtons.setMaximumSize(new Dimension(750, 60));
				panelButtons.setBorder(new TitledBorder(null,
						"Op\u00E7\u00F5es: ", TitledBorder.LEADING,
						TitledBorder.TOP, null, null)); // NOI18N
				contentPanel.add(panelButtons, "flowx,cell 0 2");
				{
					btnExport = new JButton("Exportar");
					btnExport.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							export();
						}
					});
					panelButtons.add(btnExport);
				}
				JButton btnNewArea = new JButton("Adicionar \u00C1rea");
				btnNewArea.setPreferredSize(new Dimension(150, 25));
				panelButtons.add(btnNewArea);
				{
					btnUpdateArea = new JButton("Atualizar \u00C1rea");
					btnUpdateArea.setPreferredSize(new Dimension(150, 25));
					panelButtons.add(btnUpdateArea);
					{
						btnDeleteArea = new JButton("Excluir \u00C1rea");
						btnDeleteArea.setPreferredSize(new Dimension(150, 25));
						panelButtons.add(btnDeleteArea);
						btnDeleteArea.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								deleteArea();
							}
						});
					}
					btnUpdateArea.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							updateArea();
						}
					});
				}
				btnNewArea.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						createArea();
					}
				});
			}
		}
		initDataBindings();
	}

	protected void initDataBindings() {
		BeanProperty<IAreaViewerController, String> iAreaViewerControllerBeanProperty = BeanProperty
				.create("nameFilter");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty
				.create("text");
		AutoBinding<IAreaViewerController, String, JTextField, String> autoBinding = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						areaViewerController,
						iAreaViewerControllerBeanProperty, txtNameFilter,
						jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<IAreaViewerController, List<AreaRecord>> iAreaViewerControllerBeanProperty_1 = BeanProperty
				.create("areas");
		JTableBinding<AreaRecord, IAreaViewerController, JTable> jTableBinding = SwingBindings
				.createJTableBinding(UpdateStrategy.READ, areaViewerController,
						iAreaViewerControllerBeanProperty_1, tblArea);
		//
		BeanProperty<AreaRecord, String> areaRecordBeanProperty = BeanProperty
				.create("mainAreaName");
		jTableBinding.addColumnBinding(areaRecordBeanProperty)
				.setColumnName("Super \u00C1rea").setEditable(false);
		//
		BeanProperty<AreaRecord, String> areaRecordBeanProperty_1 = BeanProperty
				.create("area.name");
		jTableBinding.addColumnBinding(areaRecordBeanProperty_1)
				.setColumnName("Nome").setEditable(false);
		//
		BeanProperty<AreaRecord, String> areaRecordBeanProperty_2 = BeanProperty
				.create("area.description");
		jTableBinding.addColumnBinding(areaRecordBeanProperty_2)
				.setColumnName("Descri\u00E7\u00E3o").setEditable(false);
		//
		jTableBinding.bind();
		//
		BeanProperty<IAreaViewerController, Boolean> iAreaViewerControllerBeanProperty_3 = BeanProperty
				.create("canUpdateArea");
		BeanProperty<JButton, Boolean> jButtonBeanProperty = BeanProperty
				.create("enabled");
		AutoBinding<IAreaViewerController, Boolean, JButton, Boolean> autoBinding_2 = Bindings
				.createAutoBinding(UpdateStrategy.READ, areaViewerController,
						iAreaViewerControllerBeanProperty_3, btnUpdateArea,
						jButtonBeanProperty);
		autoBinding_2.bind();
		//
		BeanProperty<IAreaViewerController, Boolean> iAreaViewerControllerBeanProperty_4 = BeanProperty
				.create("canDeleteArea");
		AutoBinding<IAreaViewerController, Boolean, JButton, Boolean> autoBinding_3 = Bindings
				.createAutoBinding(UpdateStrategy.READ, areaViewerController,
						iAreaViewerControllerBeanProperty_4, btnDeleteArea,
						jButtonBeanProperty);
		autoBinding_3.bind();
		//
		BeanProperty<IAreaViewerController, AreaRecord> iAreaViewerControllerBeanProperty_5 = BeanProperty
				.create("selectedArea");
		BeanProperty<JTable, AreaRecord> jTableBeanProperty = BeanProperty
				.create("selectedElement");
		AutoBinding<IAreaViewerController, AreaRecord, JTable, AreaRecord> autoBinding_4 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						areaViewerController,
						iAreaViewerControllerBeanProperty_5, tblArea,
						jTableBeanProperty);
		autoBinding_4.bind();
	}

	@Override
	public void refresh() {
		areaViewerController.refresh();
	}
}
