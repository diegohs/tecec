package tecec.ui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tecec.ui.contract.control.IAdvisorViewerController;
import tecec.ui.contract.view.IAdvisorViewerUI;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import java.util.List;
import tecec.dto.Advisor;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.border.TitledBorder;


public class AdvisorViewerUI extends JDialog implements IAdvisorViewerUI {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private IAdvisorViewerController advisorViewerController;

	private void showNewAdvisorUI(){
		this.advisorViewerController.showNewAdvisorUI();
	}

	private void showUpdateAdvisorUI(){
		this.advisorViewerController.showUpdateAdvisorUI();
	}

	private void deleteAdvisor(){
		this.advisorViewerController.deleteAdvisor();
	}

	private final JPanel contentPanel = new JPanel();
	private JTextField txtFilter;
	private JTable tblAdvisor;
	private JButton btnUpdateAdvisor;
	private JButton btnDeleteAdvisor;
	private JPanel panelPesquisa;
	private JPanel panelButtons;

	public AdvisorViewerUI(IAdvisorViewerController advisorViewerController) {
		setTitle("Orientadores");
		setPreferredSize(new Dimension(800, 600));
		setMinimumSize(new Dimension(800, 600));
		setMaximumSize(new Dimension(800, 600));
		this.advisorViewerController = advisorViewerController;

		setDefaultLookAndFeelDecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/tecec/ui/files/icone_tecec.png")));

		setModal(true);
		setBounds(100, 100, 605, 520);
		getContentPane().setLayout(new MigLayout("", "[800px]", "[grow]"));
		contentPanel.setMaximumSize(new Dimension(800, 600));
		contentPanel.setMinimumSize(new Dimension(800, 600));
		contentPanel.setPreferredSize(new Dimension(800, 600));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, "cell 0 0,grow");
		contentPanel.setLayout(new MigLayout("", "[800px]", "[50px][400px][50px]"));
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
				scrollPane.setPreferredSize(new Dimension(750, 400));
				scrollPane.setMinimumSize(new Dimension(750, 400));
				scrollPane.setMaximumSize(new Dimension(750, 400));
				contentPanel.add(scrollPane, "cell 0 1,grow");
				{
					tblAdvisor = new JTable();
					tblAdvisor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					scrollPane.setViewportView(tblAdvisor);
				}
			}
		}
		{

			panelButtons = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelButtons.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panelButtons.setPreferredSize(new Dimension(750, 60));
			panelButtons.setMinimumSize(new Dimension(750, 60));
			panelButtons.setMaximumSize(new Dimension(750, 60));
			panelButtons.setBorder(new TitledBorder(null, "Op\u00E7\u00F5es:", TitledBorder.LEADING, TitledBorder.TOP, null, null)); // NOI18N
			contentPanel.add(panelButtons, "cell 0 2,grow");
			JButton btnNewAdvisor = new JButton("Adicionar Novo");
			btnNewAdvisor.setPreferredSize(new Dimension(150, 25));
			btnNewAdvisor.setMinimumSize(new Dimension(150, 25));
			btnNewAdvisor.setMaximumSize(new Dimension(150, 25));
			panelButtons.add(btnNewAdvisor);
			{
				btnUpdateAdvisor = new JButton("Atualizar Selecionado");
				btnUpdateAdvisor.setPreferredSize(new Dimension(200, 25));
				btnUpdateAdvisor.setMinimumSize(new Dimension(150, 25));
				btnUpdateAdvisor.setMaximumSize(new Dimension(150, 25));
				panelButtons.add(btnUpdateAdvisor);
				{
					btnDeleteAdvisor = new JButton("Excluir Selecionado");
					btnDeleteAdvisor.setPreferredSize(new Dimension(200, 25));
					btnDeleteAdvisor.setMinimumSize(new Dimension(150, 25));
					btnDeleteAdvisor.setMaximumSize(new Dimension(150, 25));
					panelButtons.add(btnDeleteAdvisor);
					btnDeleteAdvisor.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							deleteAdvisor();
						}
					});
				}
				btnUpdateAdvisor.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						showUpdateAdvisorUI();
					}
				});
			}
			btnNewAdvisor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showNewAdvisorUI();
				}
			});
		}
		initDataBindings();
	}
	protected void initDataBindings() {
		BeanProperty<IAdvisorViewerController, String> iAdvisorViewerControllerBeanProperty = BeanProperty.create("nameFilter");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<IAdvisorViewerController, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, advisorViewerController, iAdvisorViewerControllerBeanProperty, txtFilter, jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<IAdvisorViewerController, List<Advisor>> iAdvisorViewerControllerBeanProperty_1 = BeanProperty.create("advisors");
		JTableBinding<Advisor, IAdvisorViewerController, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, advisorViewerController, iAdvisorViewerControllerBeanProperty_1, tblAdvisor);
		//
		BeanProperty<Advisor, String> advisorBeanProperty = BeanProperty.create("name");
		jTableBinding.addColumnBinding(advisorBeanProperty).setColumnName("Nome").setEditable(false);
		//
		BeanProperty<Advisor, String> advisorBeanProperty_1 = BeanProperty.create("email");
		jTableBinding.addColumnBinding(advisorBeanProperty_1).setColumnName("E-mail").setEditable(false);
		//
		jTableBinding.bind();
		//
		BeanProperty<IAdvisorViewerController, Advisor> iAdvisorViewerControllerBeanProperty_2 = BeanProperty.create("selectedAdvisor");
		BeanProperty<JTable, Advisor> jTableBeanProperty = BeanProperty.create("selectedElement");
		AutoBinding<IAdvisorViewerController, Advisor, JTable, Advisor> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, advisorViewerController, iAdvisorViewerControllerBeanProperty_2, tblAdvisor, jTableBeanProperty);
		autoBinding_1.bind();
		//
		BeanProperty<IAdvisorViewerController, Boolean> iAdvisorViewerControllerBeanProperty_3 = BeanProperty.create("canUpdateAdvisor");
		BeanProperty<JButton, Boolean> jButtonBeanProperty = BeanProperty.create("enabled");
		AutoBinding<IAdvisorViewerController, Boolean, JButton, Boolean> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ, advisorViewerController, iAdvisorViewerControllerBeanProperty_3, btnUpdateAdvisor, jButtonBeanProperty);
		autoBinding_2.bind();
		//
		BeanProperty<IAdvisorViewerController, Boolean> iAdvisorViewerControllerBeanProperty_4 = BeanProperty.create("canDeleteAdvisor");
		AutoBinding<IAdvisorViewerController, Boolean, JButton, Boolean> autoBinding_3 = Bindings.createAutoBinding(UpdateStrategy.READ, advisorViewerController, iAdvisorViewerControllerBeanProperty_4, btnDeleteAdvisor, jButtonBeanProperty);
		autoBinding_3.bind();
	}

	@Override
	public void refresh() {
		advisorViewerController.refresh();
	}
}
