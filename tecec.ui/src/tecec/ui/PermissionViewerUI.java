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

import tecec.dto.Permission;
import tecec.ui.contract.control.IPermissionViewerController;
import tecec.ui.contract.view.IPermissionViewerUI;
import javax.swing.ListSelectionModel;

public class PermissionViewerUI extends JFrame implements IPermissionViewerUI {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IPermissionViewerController permissionViewerController;
	
	private void showUpdatePermissionUI(){
		this.permissionViewerController.showUpdatePermissionUI();
	}
	
	private void showNewPermissionUI(){
		this.permissionViewerController.showNewPermissionUI();
	}
	
	private void deletePermission(){
		this.permissionViewerController.deletePermission();
	}

	private JPanel contentPane;
	private JTextField txtFilter;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnNewPermission;
	private JButton btnUpdatePermission;
	private JButton btnDeletePermission;

	/**
	 * Create the frame.
	 */
	public PermissionViewerUI(IPermissionViewerController permissionViewerController) {
		this.permissionViewerController = permissionViewerController;	
		
		setDefaultLookAndFeelDecorated(true);
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 726, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow][grow 50][]", "[20px:n][][20px:n][grow][5px:n][20px:n]"));
		
		JLabel lblFilter = new JLabel("Filtro:");
		contentPane.add(lblFilter, "flowx,cell 1 1");
		
		txtFilter = new JTextField();
		contentPane.add(txtFilter, "cell 1 1 2 1,growx");
		txtFilter.setColumns(10);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 1 3 2 1,grow");
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		btnNewPermission = new JButton("Adicionar Nova");
		btnNewPermission.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showNewPermissionUI();
			}
		});
		contentPane.add(btnNewPermission, "flowx,cell 2 5,alignx right,aligny bottom");
		
		btnUpdatePermission = new JButton("Atualizar Selecionada");
		btnUpdatePermission.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showUpdatePermissionUI();				
			}
		});
		contentPane.add(btnUpdatePermission, "flowx,cell 2 5,alignx right,aligny bottom");
		
		btnDeletePermission = new JButton("Excluir Selecionada");
		btnDeletePermission.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deletePermission();
			}
		});
		contentPane.add(btnDeletePermission, "flowx,cell 2 5,alignx right,aligny bottom");
		initDataBindings();
	}
	protected void initDataBindings() {
		BeanProperty<IPermissionViewerController, String> iPermissionViewerControllerBeanProperty = BeanProperty.create("nameFilter");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<IPermissionViewerController, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, permissionViewerController, iPermissionViewerControllerBeanProperty, txtFilter, jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<IPermissionViewerController, List<Permission>> iPermissionViewerControllerBeanProperty_1 = BeanProperty.create("permission");
		JTableBinding<Permission, IPermissionViewerController, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, permissionViewerController, iPermissionViewerControllerBeanProperty_1, table);
		//
		BeanProperty<Permission, String> permissionBeanProperty = BeanProperty.create("description");
		jTableBinding.addColumnBinding(permissionBeanProperty).setColumnName("Descrição");
		//
		jTableBinding.bind();
		//
		BeanProperty<IPermissionViewerController, Permission> iPermissionViewerControllerBeanProperty_2 = BeanProperty.create("selectedPermission");
		BeanProperty<JTable, Permission> jTableBeanProperty = BeanProperty.create("selectedElement");
		AutoBinding<IPermissionViewerController, Permission, JTable, Permission> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, permissionViewerController, iPermissionViewerControllerBeanProperty_2, table, jTableBeanProperty);
		autoBinding_1.bind();
		//
		BeanProperty<IPermissionViewerController, Boolean> iPermissionViewerControllerBeanProperty_3 = BeanProperty.create("canUpdatePermission");
		BeanProperty<JButton, Boolean> jButtonBeanProperty = BeanProperty.create("enabled");
		AutoBinding<IPermissionViewerController, Boolean, JButton, Boolean> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ, permissionViewerController, iPermissionViewerControllerBeanProperty_3, btnUpdatePermission, jButtonBeanProperty);
		autoBinding_2.bind();
		//
		BeanProperty<IPermissionViewerController, Boolean> iPermissionViewerControllerBeanProperty_4 = BeanProperty.create("canDeletePermission");
		AutoBinding<IPermissionViewerController, Boolean, JButton, Boolean> autoBinding_3 = Bindings.createAutoBinding(UpdateStrategy.READ, permissionViewerController, iPermissionViewerControllerBeanProperty_4, btnDeletePermission, jButtonBeanProperty);
		autoBinding_3.bind();
	}
}
