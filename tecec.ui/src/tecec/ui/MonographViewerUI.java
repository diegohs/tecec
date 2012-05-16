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

import tecec.dto.Monograph;
import tecec.ui.contract.control.IMonographViewerController;
import tecec.ui.contract.view.IMonographViewerUI;

import javax.swing.ListSelectionModel;

public class MonographViewerUI extends JFrame implements IMonographViewerUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IMonographViewerController monographViewerController;
	
	private void showUpdateMonographUI(){
		this.monographViewerController.showUpdateMonographUI();
	}
	
	private void showNewMonographUI(){
		this.monographViewerController.showNewMonographUI();
	}
	
	private void deleteMonograph(){
		this.monographViewerController.deleteMonograph();
	}
	
	private JPanel contentPane;
	private JTextField txtFilter;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnNewMonograph;
	private JButton btnUpdateMonograph;
	private JButton btnDeleteMonograph;
	
	/**
	 * Create the frame.
	 */
	public MonographViewerUI(IMonographViewerController monographViewerController) {
		this.monographViewerController = monographViewerController;	
		
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
		
		btnNewMonograph = new JButton("Adicionar Novo");
		btnNewMonograph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showNewMonographUI();
			}
		});
		contentPane.add(btnNewMonograph, "flowx,cell 2 5,alignx right,aligny bottom");
		
		btnUpdateMonograph = new JButton("Atualizar Selecionado");
		btnUpdateMonograph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showUpdateMonographUI();				
			}
		});
		contentPane.add(btnUpdateMonograph, "flowx,cell 2 5,alignx right,aligny bottom");
		
		btnDeleteMonograph = new JButton("Excluir Selecionado");
		btnDeleteMonograph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteMonograph();
			}
		});
		contentPane.add(btnDeleteMonograph, "flowx,cell 2 5,alignx right,aligny bottom");
		initDataBindings();
	}
	
	protected void initDataBindings() {
		BeanProperty<IMonographViewerController, String> iMonographViewerControllerBeanProperty = BeanProperty.create("nameFilter");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<IMonographViewerController, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, monographViewerController, iMonographViewerControllerBeanProperty, txtFilter, jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<IMonographViewerController, List<Monograph>> iMonographViewerControllerBeanProperty_1 = BeanProperty.create("monograph");
		JTableBinding<Monograph, IMonographViewerController, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, monographViewerController, iMonographViewerControllerBeanProperty_1, table);
		//
		BeanProperty<Monograph, String> monographBeanProperty = BeanProperty.create("description");
		jTableBinding.addColumnBinding(monographBeanProperty).setColumnName("Monograph");
		//
		jTableBinding.bind();
		//
		BeanProperty<IMonographViewerController, Monograph> iMonographViewerControllerBeanProperty_2 = BeanProperty.create("selectedMonograph");
		BeanProperty<JTable, Monograph> jTableBeanProperty = BeanProperty.create("selectedElement");
		AutoBinding<IMonographViewerController, Monograph, JTable, Monograph> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, monographViewerController, iMonographViewerControllerBeanProperty_2, table, jTableBeanProperty);
		autoBinding_1.bind();
		//
		BeanProperty<IMonographViewerController, Boolean> iMonographViewerControllerBeanProperty_3 = BeanProperty.create("canUpdateMonograph");
		BeanProperty<JButton, Boolean> jButtonBeanProperty = BeanProperty.create("enabled");
		AutoBinding<IMonographViewerController, Boolean, JButton, Boolean> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ, monographViewerController, iMonographViewerControllerBeanProperty_3, btnUpdateMonograph, jButtonBeanProperty);
		autoBinding_2.bind();
		//
		BeanProperty<IMonographViewerController, Boolean> iMonographViewerControllerBeanProperty_4 = BeanProperty.create("canDeleteMonograph");
		AutoBinding<IMonographViewerController, Boolean, JButton, Boolean> autoBinding_3 = Bindings.createAutoBinding(UpdateStrategy.READ, monographViewerController, iMonographViewerControllerBeanProperty_4, btnDeleteMonograph, jButtonBeanProperty);
		autoBinding_3.bind();
	}
}
