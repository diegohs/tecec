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

import tecec.ui.contract.control.IMonographViewerController;
import tecec.ui.contract.view.IMonographViewerUI;

import javax.swing.ListSelectionModel;
import tecec.ui.contract.record.MonographRecord;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;


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
	private JTable tblMonograph;
	private JButton btnNewMonograph;
	private JButton btnUpdateMonograph;
	private JButton btnDeleteMonograph;
	private JPanel panelPesquisa;
	private JPanel panelButtons;

	/**
	 * Create the frame.
	 */
	public MonographViewerUI(IMonographViewerController monographViewerController) {
		setMaximumSize(new Dimension(800, 600));
		setMinimumSize(new Dimension(800, 600));
		setPreferredSize(new Dimension(800, 600));
		this.monographViewerController = monographViewerController;

		setDefaultLookAndFeelDecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/tecec/ui/files/icone_tecec.png")));

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 726, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[][][]"));

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

		tblMonograph = new JTable();
		tblMonograph.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tblMonograph);

		panelButtons = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelButtons.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelButtons.setPreferredSize(new Dimension(750, 60));
		panelButtons.setMinimumSize(new Dimension(750, 60));
		panelButtons.setMaximumSize(new Dimension(750, 60));
		panelButtons.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Op��es:", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 1, 10))); // NOI18N
		contentPane.add(panelButtons, "cell 0 2,grow");

		btnNewMonograph = new JButton("Adicionar Novo");
		btnNewMonograph.setPreferredSize(new Dimension(150, 25));
		btnNewMonograph.setMinimumSize(new Dimension(150, 25));
		btnNewMonograph.setMaximumSize(new Dimension(150, 25));
		panelButtons.add(btnNewMonograph);

		btnUpdateMonograph = new JButton("Atualizar Selecionado");
		btnUpdateMonograph.setPreferredSize(new Dimension(200, 25));
		btnUpdateMonograph.setMinimumSize(new Dimension(150, 25));
		btnUpdateMonograph.setMaximumSize(new Dimension(150, 25));
		panelButtons.add(btnUpdateMonograph);

		btnDeleteMonograph = new JButton("Excluir Selecionado");
		btnDeleteMonograph.setPreferredSize(new Dimension(200, 25));
		btnDeleteMonograph.setMinimumSize(new Dimension(150, 25));
		btnDeleteMonograph.setMaximumSize(new Dimension(150, 25));
		panelButtons.add(btnDeleteMonograph);
		btnDeleteMonograph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteMonograph();
			}
		});
		btnUpdateMonograph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showUpdateMonographUI();
			}
		});
		btnNewMonograph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showNewMonographUI();
			}
		});
		initDataBindings();
	}
	protected void initDataBindings() {
		BeanProperty<IMonographViewerController, String> iMonographViewerControllerBeanProperty = BeanProperty.create("nameFilter");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<IMonographViewerController, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, monographViewerController, iMonographViewerControllerBeanProperty, txtFilter, jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<IMonographViewerController, Boolean> iMonographViewerControllerBeanProperty_1 = BeanProperty.create("canUpdateMonograph");
		BeanProperty<JButton, Boolean> jButtonBeanProperty = BeanProperty.create("enabled");
		AutoBinding<IMonographViewerController, Boolean, JButton, Boolean> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ, monographViewerController, iMonographViewerControllerBeanProperty_1, btnUpdateMonograph, jButtonBeanProperty);
		autoBinding_1.bind();
		//
		BeanProperty<IMonographViewerController, Boolean> iMonographViewerControllerBeanProperty_2 = BeanProperty.create("canDeleteMonograph");
		AutoBinding<IMonographViewerController, Boolean, JButton, Boolean> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ, monographViewerController, iMonographViewerControllerBeanProperty_2, btnDeleteMonograph, jButtonBeanProperty);
		autoBinding_2.bind();
		//
		BeanProperty<IMonographViewerController, List<MonographRecord>> iMonographViewerControllerBeanProperty_3 = BeanProperty.create("monographs");
		JTableBinding<MonographRecord, IMonographViewerController, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, monographViewerController, iMonographViewerControllerBeanProperty_3, tblMonograph);
		//
		BeanProperty<MonographRecord, String> monographRecordBeanProperty = BeanProperty.create("monograph.title");
		jTableBinding.addColumnBinding(monographRecordBeanProperty).setColumnName("T\u00EDtulo").setEditable(false);
		//
		BeanProperty<MonographRecord, String> monographRecordBeanProperty_1 = BeanProperty.create("student");
		jTableBinding.addColumnBinding(monographRecordBeanProperty_1).setColumnName("Aluno").setEditable(false);
		//
		BeanProperty<MonographRecord, String> monographRecordBeanProperty_2 = BeanProperty.create("course");
		jTableBinding.addColumnBinding(monographRecordBeanProperty_2).setColumnName("Curso").setEditable(false);
		//
		BeanProperty<MonographRecord, String> monographRecordBeanProperty_3 = BeanProperty.create("area");
		jTableBinding.addColumnBinding(monographRecordBeanProperty_3).setColumnName("\u00C1rea").setEditable(false);
		//
		BeanProperty<MonographRecord, String> monographRecordBeanProperty_4 = BeanProperty.create("advisor");
		jTableBinding.addColumnBinding(monographRecordBeanProperty_4).setColumnName("Orientador").setEditable(false);
		//
		BeanProperty<MonographRecord, String> monographRecordBeanProperty_5 = BeanProperty.create("coadvisor");
		jTableBinding.addColumnBinding(monographRecordBeanProperty_5).setColumnName("Coorientador").setEditable(false);
		//
		BeanProperty<MonographRecord, String> monographRecordBeanProperty_6 = BeanProperty.create("status");
		jTableBinding.addColumnBinding(monographRecordBeanProperty_6).setColumnName("Status").setEditable(false);
		//
		jTableBinding.bind();
		//
		BeanProperty<IMonographViewerController, MonographRecord> iMonographViewerControllerBeanProperty_4 = BeanProperty.create("selectedMonograph");
		BeanProperty<JTable, MonographRecord> jTableBeanProperty = BeanProperty.create("selectedElement");
		AutoBinding<IMonographViewerController, MonographRecord, JTable, MonographRecord> autoBinding_3 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, monographViewerController, iMonographViewerControllerBeanProperty_4, tblMonograph, jTableBeanProperty);
		autoBinding_3.bind();
	}

	@Override
	public void refresh() {
		monographViewerController.refresh();
	}
}
