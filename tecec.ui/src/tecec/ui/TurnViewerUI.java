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

import tecec.dto.Turn;
import tecec.ui.contract.control.ITurnViewerController;
import tecec.ui.contract.view.ITurnViewerUI;
import javax.swing.ListSelectionModel;

public class TurnViewerUI extends JFrame implements ITurnViewerUI {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ITurnViewerController turnViewerController;
	
	private void showUpdateTurnUI(){
		this.turnViewerController.showUpdateTurnUI();
	}
	
	private void showNewTurnUI(){
		this.turnViewerController.showNewTurnUI();
	}
	
	private void deleteTurn(){
		this.turnViewerController.deleteTurn();
	}

	private JPanel contentPane;
	private JTextField txtFilter;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnNewTurn;
	private JButton btnUpdateTurn;
	private JButton btnDeleteTurn;

	/**
	 * Create the frame.
	 */
	public TurnViewerUI(ITurnViewerController turnViewerController) {
		this.turnViewerController = turnViewerController;	
		
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
		
		btnNewTurn = new JButton("Adicionar Novo");
		btnNewTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showNewTurnUI();
			}
		});
		contentPane.add(btnNewTurn, "flowx,cell 2 5,alignx right,aligny bottom");
		
		btnUpdateTurn = new JButton("Atualizar Selecionado");
		btnUpdateTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showUpdateTurnUI();				
			}
		});
		contentPane.add(btnUpdateTurn, "flowx,cell 2 5,alignx right,aligny bottom");
		
		btnDeleteTurn = new JButton("Excluir Selecionado");
		btnDeleteTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteTurn();
			}
		});
		contentPane.add(btnDeleteTurn, "flowx,cell 2 5,alignx right,aligny bottom");
		initDataBindings();
	}
	protected void initDataBindings() {
		BeanProperty<ITurnViewerController, String> iTurnViewerControllerBeanProperty = BeanProperty.create("nameFilter");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<ITurnViewerController, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, turnViewerController, iTurnViewerControllerBeanProperty, txtFilter, jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<ITurnViewerController, List<Turn>> iTurnViewerControllerBeanProperty_1 = BeanProperty.create("turns");
		JTableBinding<Turn, ITurnViewerController, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, turnViewerController, iTurnViewerControllerBeanProperty_1, table);
		//
		BeanProperty<Turn, String> turnBeanProperty = BeanProperty.create("name");
		jTableBinding.addColumnBinding(turnBeanProperty).setColumnName("Curso");
		//
		jTableBinding.bind();
		//
		BeanProperty<ITurnViewerController, Turn> iTurnViewerControllerBeanProperty_2 = BeanProperty.create("selectedTurn");
		BeanProperty<JTable, Turn> jTableBeanProperty = BeanProperty.create("selectedElement");
		AutoBinding<ITurnViewerController, Turn, JTable, Turn> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, turnViewerController, iTurnViewerControllerBeanProperty_2, table, jTableBeanProperty);
		autoBinding_1.bind();
		//
		BeanProperty<ITurnViewerController, Boolean> iTurnViewerControllerBeanProperty_3 = BeanProperty.create("canUpdateTurn");
		BeanProperty<JButton, Boolean> jButtonBeanProperty = BeanProperty.create("enabled");
		AutoBinding<ITurnViewerController, Boolean, JButton, Boolean> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ, turnViewerController, iTurnViewerControllerBeanProperty_3, btnUpdateTurn, jButtonBeanProperty);
		autoBinding_2.bind();
		//
		BeanProperty<ITurnViewerController, Boolean> iTurnViewerControllerBeanProperty_4 = BeanProperty.create("canDeleteTurn");
		AutoBinding<ITurnViewerController, Boolean, JButton, Boolean> autoBinding_3 = Bindings.createAutoBinding(UpdateStrategy.READ, turnViewerController, iTurnViewerControllerBeanProperty_4, btnDeleteTurn, jButtonBeanProperty);
		autoBinding_3.bind();
	}
}
