package tecec.ui;

import java.awt.BorderLayout;
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
import org.jdesktop.beansbinding.ELProperty;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import tecec.contract.RuleViolation;
import tecec.dto.Activity;
import tecec.ui.contract.control.IActivityViewerController;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

public class ActivityViewerUI extends JDialog implements
		tecec.ui.contract.view.IActivityViewerUI {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private tecec.ui.contract.control.IActivityViewerController activityViewerController;
	
	private void export(){
		this.activityViewerController.export();
	}

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
	private JPanel panelPesquisa;
	private JPanel panelButtons;
	private JButton btnExport;

	public ActivityViewerUI(IActivityViewerController activityViewerController) {
		setTitle("Atividades");
		getContentPane().setPreferredSize(new Dimension(800, 600));
		getContentPane().setMinimumSize(new Dimension(800, 600));
		getContentPane().setMaximumSize(new Dimension(800, 600));
		setPreferredSize(new Dimension(800, 600));
		setMinimumSize(new Dimension(800, 600));
		setMaximumSize(new Dimension(800, 600));
		this.activityViewerController = activityViewerController;

		setDefaultLookAndFeelDecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/tecec/ui/files/icone_tecec.png")));

		setModal(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 600, 335);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setPreferredSize(new Dimension(800, 600));
		contentPanel.setMinimumSize(new Dimension(800, 600));
		contentPanel.setMaximumSize(new Dimension(800, 600));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow]"));
		{
			panelPesquisa = new JPanel();
			panelPesquisa.setPreferredSize(new Dimension(750, 60));
			panelPesquisa.setMinimumSize(new Dimension(750, 60));
			panelPesquisa.setMaximumSize(new Dimension(750, 60));
			panelPesquisa.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisa:", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 1, 10))); // NOI18N
			contentPanel.add(panelPesquisa, "cell 0 0,grow");
			{
				txtFilter = new JTextField();
				panelPesquisa.add(txtFilter);
				txtFilter.setColumns(90);
			}
		}
		{
			{
				JScrollPane scrollPane = new JScrollPane();
				contentPanel.add(scrollPane, "cell 0 1,grow");
				{
					tblActivities = new JTable();
					tblActivities.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					scrollPane.setViewportView(tblActivities);
				}
			}
			{
				panelButtons = new JPanel();
				FlowLayout flowLayout = (FlowLayout) panelButtons.getLayout();
				flowLayout.setAlignment(FlowLayout.RIGHT);
				panelButtons.setPreferredSize(new Dimension(750, 60));
				panelButtons.setMinimumSize(new Dimension(750, 60));
				panelButtons.setMaximumSize(new Dimension(750, 60));
				panelButtons.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Op\u00E7\u00F5es:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				contentPanel.add(panelButtons, "cell 0 2,grow");
				{
					btnExport = new JButton("Export");
					btnExport.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							export();
						}
					});
					panelButtons.add(btnExport);
				}
				btnNewActivity = new JButton("Cadastrar Atividade");
				btnNewActivity.setPreferredSize(new Dimension(150, 25));
				btnNewActivity.setMinimumSize(new Dimension(150, 25));
				btnNewActivity.setMaximumSize(new Dimension(150, 25));
				panelButtons.add(btnNewActivity);
				{
					btnUpdateActivity = new JButton("Atualizar Atividade");
					btnUpdateActivity.setPreferredSize(new Dimension(150, 25));
					btnUpdateActivity.setMinimumSize(new Dimension(150, 25));
					btnUpdateActivity.setMaximumSize(new Dimension(150, 25));
					panelButtons.add(btnUpdateActivity);
					{
						btnDeleteActivity = new JButton("Excluir Atividade");
						btnDeleteActivity.setPreferredSize(new Dimension(150, 25));
						btnDeleteActivity.setMinimumSize(new Dimension(150, 25));
						btnDeleteActivity.setMaximumSize(new Dimension(150, 25));
						panelButtons.add(btnDeleteActivity);
						btnDeleteActivity.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								deleteActivity();
							}
						});
					}
					btnUpdateActivity.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							showUpdateActivityUI();
						}
					});
				}
				btnNewActivity.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						showNewActivityUI();
					}
				});
			}
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
		jTableBinding.addColumnBinding(activityBeanProperty_1).setColumnName("Título").setEditable(false);
		//
		BeanProperty<Activity, String> activityBeanProperty_2 = BeanProperty.create("description");
		jTableBinding.addColumnBinding(activityBeanProperty_2).setColumnName("Descrição");
		//
		jTableBinding.bind();
		//
		BeanProperty<IActivityViewerController, Activity> iActivityViewerControllerBeanProperty = BeanProperty.create("selectedActivity");
		BeanProperty<JTable, Activity> jTableBeanProperty = BeanProperty.create("selectedElement");
		AutoBinding<IActivityViewerController, Activity, JTable, Activity> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, activityViewerController, iActivityViewerControllerBeanProperty, tblActivities, jTableBeanProperty);
		autoBinding.bind();
	}

	@Override
	public void refresh() {
		this.activityViewerController.refresh();
	}
}
