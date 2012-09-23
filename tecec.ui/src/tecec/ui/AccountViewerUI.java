package tecec.ui;


import javax.swing.JDialog;
import net.miginfocom.swing.MigLayout;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import tecec.contract.RuleViolation;
import tecec.dto.record.AccountRecord;
import tecec.ui.contract.control.IAccountViewerController;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.jdesktop.beansbinding.BeanProperty;
import java.util.List;
import tecec.ui.contract.view.IAccountViewerUI;

import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;
import javax.swing.ListSelectionModel;

public class AccountViewerUI extends JDialog implements IAccountViewerUI {
	/**
	 *
	 */
	private static final long serialVersionUID = 2621342283614897745L;
	private JTable table;
	private JTextField txtFilter;

	IAccountViewerController controller;
	private JButton btnUpdateAccount;
	private JButton btnDeleteAccount;
	private JButton btnExport;
	
	private void export(){
		controller.export();
	}

	private void showNewAccountUI(){
		controller.showNewAccountUI();
	}

	private void showUpdateAccountUI(){
		controller.showUpdateAccountUI();
	}

	private void deleteAccount(){
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

	public AccountViewerUI(IAccountViewerController controller) {
		this.controller = controller;

		setDefaultLookAndFeelDecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/tecec/ui/files/icone_tecec.png")));

		setModal(true);
		setBounds(100, 100, 601, 352);
		getContentPane().setLayout(new MigLayout("", "[20px:n][grow][20px:n]", "[20px:n][20px:n][][20px:n][][20px:n]"));

		JLabel lblNewLabel = new JLabel("Filtro:");
		getContentPane().add(lblNewLabel, "flowx,cell 1 1");

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "cell 1 2,grow");

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);

		JButton btnNewAccount = new JButton("Cadastrar Novo");
		btnNewAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showNewAccountUI();
			}
		});
		
		btnExport = new JButton("Exportar");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				export();
			}
		});
		getContentPane().add(btnExport, "flowx,cell 1 4,alignx right");
		getContentPane().add(btnNewAccount, "cell 1 4,alignx right");

		btnUpdateAccount = new JButton("Atualizar Selecionado");
		btnUpdateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showUpdateAccountUI();
			}
		});
		getContentPane().add(btnUpdateAccount, "cell 1 4,alignx right");

		btnDeleteAccount = new JButton("Excluir Selecionado");
		btnDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteAccount();
			}
		});
		getContentPane().add(btnDeleteAccount, "cell 1 4,alignx right");

		txtFilter = new JTextField();
		getContentPane().add(txtFilter, "cell 1 1,growx");
		txtFilter.setColumns(10);
		initDataBindings();

	}

	@Override
	public void refresh() {
		this.controller.refresh();
	}
	protected void initDataBindings() {
		BeanProperty<IAccountViewerController, List<AccountRecord>> iAccountViewerControllerBeanProperty = BeanProperty.create("accounts");
		JTableBinding<AccountRecord, IAccountViewerController, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, controller, iAccountViewerControllerBeanProperty, table);
		//
		BeanProperty<AccountRecord, String> accountRecordBeanProperty = BeanProperty.create("id");
		jTableBinding.addColumnBinding(accountRecordBeanProperty).setColumnName("ID").setEditable(false);
		//
		BeanProperty<AccountRecord, String> accountRecordBeanProperty_1 = BeanProperty.create("userName");
		jTableBinding.addColumnBinding(accountRecordBeanProperty_1).setColumnName("Usu\u00C3\u00A1rio");
		//
		BeanProperty<AccountRecord, String> accountRecordBeanProperty_2 = BeanProperty.create("profileName");
		jTableBinding.addColumnBinding(accountRecordBeanProperty_2).setColumnName("Perfil").setEditable(false);
		//
		BeanProperty<AccountRecord, String> accountRecordBeanProperty_3 = BeanProperty.create("studentName");
		jTableBinding.addColumnBinding(accountRecordBeanProperty_3).setColumnName("Estudante Associado").setEditable(false);
		//
		jTableBinding.bind();
		//
		BeanProperty<IAccountViewerController, AccountRecord> iAccountViewerControllerBeanProperty_1 = BeanProperty.create("selectedAccount");
		BeanProperty<JTable, AccountRecord> jTableBeanProperty = BeanProperty.create("selectedElement");
		AutoBinding<IAccountViewerController, AccountRecord, JTable, AccountRecord> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, controller, iAccountViewerControllerBeanProperty_1, table, jTableBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<IAccountViewerController, String> iAccountViewerControllerBeanProperty_2 = BeanProperty.create("filter");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<IAccountViewerController, String, JTextField, String> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, controller, iAccountViewerControllerBeanProperty_2, txtFilter, jTextFieldBeanProperty);
		autoBinding_1.bind();
		//
		BeanProperty<IAccountViewerController, Boolean> iAccountViewerControllerBeanProperty_3 = BeanProperty.create("canUpdate");
		BeanProperty<JButton, Boolean> jButtonBeanProperty = BeanProperty.create("enabled");
		AutoBinding<IAccountViewerController, Boolean, JButton, Boolean> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ, controller, iAccountViewerControllerBeanProperty_3, btnUpdateAccount, jButtonBeanProperty);
		autoBinding_2.bind();
		//
		BeanProperty<IAccountViewerController, Boolean> iAccountViewerControllerBeanProperty_4 = BeanProperty.create("canDelete");
		AutoBinding<IAccountViewerController, Boolean, JButton, Boolean> autoBinding_3 = Bindings.createAutoBinding(UpdateStrategy.READ, controller, iAccountViewerControllerBeanProperty_4, btnDeleteAccount, jButtonBeanProperty);
		autoBinding_3.bind();
		//
		BeanProperty<IAccountViewerController, Boolean> iAccountViewerControllerBeanProperty_5 = BeanProperty.create("canExport");
		AutoBinding<IAccountViewerController, Boolean, JButton, Boolean> autoBinding_4 = Bindings.createAutoBinding(UpdateStrategy.READ, controller, iAccountViewerControllerBeanProperty_5, btnExport, jButtonBeanProperty);
		autoBinding_4.bind();
	}
}
