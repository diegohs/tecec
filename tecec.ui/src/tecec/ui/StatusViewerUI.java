package tecec.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;


import org.jdesktop.beansbinding.BeanProperty;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import tecec.dto.Status;
import tecec.ui.contract.control.IStatusViewerController;
import tecec.ui.contract.view.IStatusViewerUI;
import javax.swing.ListSelectionModel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

public class StatusViewerUI extends JFrame implements IStatusViewerUI {

	private static final long serialVersionUID = 1L;
	private IStatusViewerController statusViewerController;

	private void showUpdateStatusUI(){
		this.statusViewerController.showUpdateStatusUI();
	}

	private void showNewStatusUI(){
		this.statusViewerController.showNewStatusUI();
	}

	private void deleteStatus(){
		this.statusViewerController.deleteStatus();
	}

	private JPanel contentPane;
	private JTextField txtFilter;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnNewStatus;
	private JButton btnUpdateStatus;
	private JButton btnDeleteStatus;
	private JPanel panelPesquisa;
	private JPanel panelButtons;

	/**
	 * Create the frame.
	 */
	public StatusViewerUI(IStatusViewerController statusViewerController) {
		setTitle("Status");
		setPreferredSize(new Dimension(800, 600));
		setMinimumSize(new Dimension(800, 600));
		setMaximumSize(new Dimension(800, 600));
		this.statusViewerController = statusViewerController;

		setDefaultLookAndFeelDecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/tecec/ui/files/icone_tecec.png")));

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 726, 441);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(800, 600));
		contentPane.setMinimumSize(new Dimension(800, 600));
		contentPane.setMaximumSize(new Dimension(800, 600));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow]"));

		panelPesquisa = new JPanel();
		panelPesquisa.setPreferredSize(new Dimension(750, 60));
		panelPesquisa.setMinimumSize(new Dimension(750, 60));
		panelPesquisa.setMaximumSize(new Dimension(750, 60));
		panelPesquisa.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisa:", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 1, 10))); // NOI18N
		contentPane.add(panelPesquisa, "cell 0 0,grow");

		txtFilter = new JTextField();
		panelPesquisa.add(txtFilter);
		txtFilter.setColumns(90);

		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(750, 400));
		scrollPane.setMinimumSize(new Dimension(750, 400));
		scrollPane.setMaximumSize(new Dimension(750, 400));
		contentPane.add(scrollPane, "cell 0 1,grow");

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);

		panelButtons = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelButtons.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelButtons.setPreferredSize(new Dimension(750, 60));
		panelButtons.setMinimumSize(new Dimension(750, 60));
		panelButtons.setMaximumSize(new Dimension(750, 60));
		panelButtons.setBorder(new TitledBorder(null, "Op\u00E7\u00F5es:", TitledBorder.LEADING, TitledBorder.TOP, null, null)); // NOI18N
		contentPane.add(panelButtons, "cell 0 2,grow");

		btnNewStatus = new JButton("Adicionar Novo");
		btnNewStatus.setPreferredSize(new Dimension(150, 25));
		btnNewStatus.setMinimumSize(new Dimension(150, 25));
		btnNewStatus.setMaximumSize(new Dimension(150, 25));
		panelButtons.add(btnNewStatus);

		btnUpdateStatus = new JButton("Atualizar Selecionado");
		btnUpdateStatus.setPreferredSize(new Dimension(150, 25));
		btnUpdateStatus.setMinimumSize(new Dimension(150, 25));
		btnUpdateStatus.setMaximumSize(new Dimension(150, 25));
		panelButtons.add(btnUpdateStatus);

		btnDeleteStatus = new JButton("Excluir Selecionado");
		btnDeleteStatus.setPreferredSize(new Dimension(150, 25));
		btnDeleteStatus.setMinimumSize(new Dimension(150, 25));
		btnDeleteStatus.setMaximumSize(new Dimension(150, 25));
		panelButtons.add(btnDeleteStatus);
		btnDeleteStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteStatus();
			}
		});
		btnUpdateStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showUpdateStatusUI();
			}
		});
		btnNewStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showNewStatusUI();
			}
		});
		initDataBindings();
	}
	protected void initDataBindings() {
		BeanProperty<IStatusViewerController, String> iStatusViewerControllerBeanProperty = BeanProperty.create("nameFilter");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<IStatusViewerController, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, statusViewerController, iStatusViewerControllerBeanProperty, txtFilter, jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<IStatusViewerController, List<Status>> iStatusViewerControllerBeanProperty_1 = BeanProperty.create("status");
		JTableBinding<Status, IStatusViewerController, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, statusViewerController, iStatusViewerControllerBeanProperty_1, table);
		//
		BeanProperty<Status, String> statusBeanProperty = BeanProperty.create("description");
		jTableBinding.addColumnBinding(statusBeanProperty).setColumnName("Status");
		//
		jTableBinding.bind();
		//
		BeanProperty<IStatusViewerController, Status> iStatusViewerControllerBeanProperty_2 = BeanProperty.create("selectedStatus");
		BeanProperty<JTable, Status> jTableBeanProperty = BeanProperty.create("selectedElement");
		AutoBinding<IStatusViewerController, Status, JTable, Status> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, statusViewerController, iStatusViewerControllerBeanProperty_2, table, jTableBeanProperty);
		autoBinding_1.bind();
		//
		BeanProperty<IStatusViewerController, Boolean> iStatusViewerControllerBeanProperty_3 = BeanProperty.create("canUpdateStatus");
		BeanProperty<JButton, Boolean> jButtonBeanProperty = BeanProperty.create("enabled");
		AutoBinding<IStatusViewerController, Boolean, JButton, Boolean> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ, statusViewerController, iStatusViewerControllerBeanProperty_3, btnUpdateStatus, jButtonBeanProperty);
		autoBinding_2.bind();
		//
		BeanProperty<IStatusViewerController, Boolean> iStatusViewerControllerBeanProperty_4 = BeanProperty.create("canDeleteStatus");
		AutoBinding<IStatusViewerController, Boolean, JButton, Boolean> autoBinding_3 = Bindings.createAutoBinding(UpdateStrategy.READ, statusViewerController, iStatusViewerControllerBeanProperty_4, btnDeleteStatus, jButtonBeanProperty);
		autoBinding_3.bind();
	}

	@Override
	public void refresh() {
		statusViewerController.refresh();
	}
}
