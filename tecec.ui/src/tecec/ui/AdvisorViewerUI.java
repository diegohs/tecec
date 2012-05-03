package tecec.ui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tecec.ui.contract.control.IAdvisorViewerController;
import tecec.ui.contract.view.IAdvisorViewerUI;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
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


public class AdvisorViewerUI extends JDialog implements IAdvisorViewerUI {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IAdvisorViewerController advisorViewerController;
	
	private void showNewAdvisorUI(){
		this.advisorViewerController.showNewAdvisorUI();
	}
	
	private void deleteAdvisor(){
		this.advisorViewerController.deleteAdvisor();
	}

	private final JPanel contentPanel = new JPanel();
	private JTextField txtFilter;
	private JTable tblAdvisor;
	private JButton btnUpdateAdvisor;
	private JButton btnDeleteAdvisor;

	public AdvisorViewerUI(IAdvisorViewerController advisorViewerController) {
		this.advisorViewerController = advisorViewerController;

		setModal(true);
		setBounds(100, 100, 687, 345);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][280.00,grow][]", "[][][][grow][][]"));
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
				tblAdvisor = new JTable();
				tblAdvisor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPane.setViewportView(tblAdvisor);
			}
		}
		{
			JButton btnNewAdvisor = new JButton("Adicionar Novo");
			btnNewAdvisor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showNewAdvisorUI();
				}
			});
			contentPanel.add(btnNewAdvisor, "flowx,cell 1 4,alignx right");
		}
		{
			btnUpdateAdvisor = new JButton("Atualizar Selecionado");
			contentPanel.add(btnUpdateAdvisor, "cell 1 4,alignx right");
		}
		{
			btnDeleteAdvisor = new JButton("Excluir Selecionado");
			btnDeleteAdvisor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteAdvisor();
				}
			});
			contentPanel.add(btnDeleteAdvisor, "cell 1 4,alignx right");
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
}
