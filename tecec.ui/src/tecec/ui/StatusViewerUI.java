package tecec.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import java.awt.Font;

public class StatusViewerUI extends JFrame implements IStatusViewerUI {
	
	/**
	 * 
	 */
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

	/**
	 * Create the frame.
	 */
	public StatusViewerUI(IStatusViewerController statusViewerController) {
		this.statusViewerController = statusViewerController;	
		
		setDefaultLookAndFeelDecorated(true);
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 726, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[53.00,grow][][][][grow 50][]", "[20px:n][][][][][][fill][20px:n][205.00,grow][5px:n][20px:n]"));
		
		JLabel lblFilter = new JLabel("Filtro:");
		lblFilter.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		contentPane.add(lblFilter, "flowx,cell 0 4 1 2,alignx center");
		
		btnNewStatus = new JButton("Adicionar Novo");
		btnNewStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showNewStatusUI();
			}
		});
		
		txtFilter = new JTextField();
		contentPane.add(txtFilter, "cell 1 5 4 1,grow");
		txtFilter.setColumns(10);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 6 5 3,grow");
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		contentPane.add(btnNewStatus, "flowx,cell 4 10,alignx right,aligny bottom");
		
		btnUpdateStatus = new JButton("Atualizar Selecionado");
		btnUpdateStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showUpdateStatusUI();				
			}
		});
		contentPane.add(btnUpdateStatus, "flowx,cell 4 10,alignx right,aligny bottom");
		
		btnDeleteStatus = new JButton("Excluir Selecionado");
		btnDeleteStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteStatus();
			}
		});
		contentPane.add(btnDeleteStatus, "flowx,cell 4 10,alignx right,aligny bottom");
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
}
