package tecec.ui;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


import tecec.ui.contract.control.IStageViewerController;
import tecec.ui.contract.view.IStageViewerUI;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import java.util.List;
import tecec.dto.Stage;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

public class StageViewerUI extends JDialog implements IStageViewerUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IStageViewerController stageViewerController;
	
	private void showNewStageUI () {
		stageViewerController.showNewStageUI();
	}
	
	private void showUpdateStageUI () {
		stageViewerController.newUpdateStageUI();
	}
	
	private void deleteStage () {
		stageViewerController.deleteStage();
	}	
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTable table;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;

	/**
	 * Create the dialog.
	 */
	public StageViewerUI(IStageViewerController stageViewerController) {
		if (stageViewerController == null)
			throw new IllegalArgumentException ("stageViewerController");
		this.stageViewerController = stageViewerController;
		
		setDefaultLookAndFeelDecorated(true);
		setModal(true);
		
		
		setBounds(100, 100, 519, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][][grow][]"));
		{
			JLabel lblFiltro = new JLabel("Filtro:");
			contentPanel.add(lblFiltro, "cell 0 1,alignx trailing");
		}
		{
			textField = new JTextField();
			contentPanel.add(textField, "cell 1 1,growx");
			textField.setColumns(10);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, "cell 1 2,grow");
			{
				table = new JTable();
				scrollPane.setRowHeaderView(table);
			}
		}
		{
			btnNewButton = new JButton("Adicionar Novo");
			btnNewButton.addActionListener(new ActionListener () {

				@Override
				public void actionPerformed(ActionEvent e) {
					showNewStageUI();
					
				}
				
			});
			contentPanel.add(btnNewButton, "flowx,cell 1 3");
		}
		{
			btnNewButton_1 = new JButton("Atualizar Selecionado");
			btnNewButton_1.addActionListener(new ActionListener () {

				@Override
				public void actionPerformed(ActionEvent e) {
					showUpdateStageUI();
				}
				
			});
			contentPanel.add(btnNewButton_1, "cell 1 3");
		}
		{
			btnNewButton_2 = new JButton("Excluir Selecionado");
			btnNewButton_2.addActionListener(new ActionListener () {

				@Override
				public void actionPerformed(ActionEvent e) {
					deleteStage();
				}
				
			});
			contentPanel.add(btnNewButton_2, "cell 1 3");
		}
		initDataBindings();
	}
	protected void initDataBindings() {
		BeanProperty<IStageViewerController, String> iStageViewerControllerBeanProperty = BeanProperty.create("nameFilter");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<IStageViewerController, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, stageViewerController, iStageViewerControllerBeanProperty, textField, jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<IStageViewerController, List<Stage>> iStageViewerControllerBeanProperty_1 = BeanProperty.create("stages");
		JTableBinding<Stage, IStageViewerController, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, stageViewerController, iStageViewerControllerBeanProperty_1, table);
		//
		BeanProperty<Stage, String> stageBeanProperty = BeanProperty.create("name");
		jTableBinding.addColumnBinding(stageBeanProperty).setColumnName("Nome").setEditable(false);
		//
		BeanProperty<Stage, String> stageBeanProperty_1 = BeanProperty.create("year");
		jTableBinding.addColumnBinding(stageBeanProperty_1).setColumnName("Ano").setEditable(false);
		//
		jTableBinding.bind();
		//
		BeanProperty<IStageViewerController, Stage> iStageViewerControllerBeanProperty_2 = BeanProperty.create("selectedStage");
		BeanProperty<JTable, Stage> jTableBeanProperty = BeanProperty.create("selectedElement");
		AutoBinding<IStageViewerController, Stage, JTable, Stage> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, stageViewerController, iStageViewerControllerBeanProperty_2, table, jTableBeanProperty);
		autoBinding_1.bind();
		//
		BeanProperty<IStageViewerController, Boolean> iStageViewerControllerBeanProperty_3 = BeanProperty.create("canUpdateStage");
		BeanProperty<JButton, Boolean> jButtonBeanProperty = BeanProperty.create("enabled");
		AutoBinding<IStageViewerController, Boolean, JButton, Boolean> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ, stageViewerController, iStageViewerControllerBeanProperty_3, btnNewButton_1, jButtonBeanProperty);
		autoBinding_2.bind();
		//
		BeanProperty<IStageViewerController, Boolean> iStageViewerControllerBeanProperty_4 = BeanProperty.create("canDeleteStage");
		AutoBinding<IStageViewerController, Boolean, JButton, Boolean> autoBinding_3 = Bindings.createAutoBinding(UpdateStrategy.READ, stageViewerController, iStageViewerControllerBeanProperty_4, btnNewButton_2, jButtonBeanProperty);
		autoBinding_3.bind();
	}
}
