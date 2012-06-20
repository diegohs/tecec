package tecec.ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tecec.contract.RuleViolation;
import tecec.ui.contract.control.IMonographStageViewerController;
import tecec.ui.contract.view.IMonographStageViewerUI;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.jdesktop.beansbinding.BeanProperty;
import java.util.List;
import tecec.dto.Stage;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.ELProperty;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import java.awt.Dimension;

public class MonographStageViewerUI extends JDialog implements IMonographStageViewerUI {

	private static final long serialVersionUID = 4782681832315871391L;

	IMonographStageViewerController controller;

	@Override
	public void setPKMonograph(String pKMonograph) {
		this.controller.setMonograph(pKMonograph);
	}

	private void insertStage(){
		RuleViolation violation = this.controller.getInsertViolation();

		if (violation != null) {
			JOptionPane.showMessageDialog(this, violation.getDescription(),
					"ERRO", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				this.controller.insertMonographStage();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e, "ERRO",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void deleteStage(){
		RuleViolation violation = this.controller.getDeleteViolation();

		if (violation != null) {
			JOptionPane.showMessageDialog(this, violation.getDescription(),
					"ERRO", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				this.controller.deleteMonographStage();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e, "ERRO",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void commit(){
		try {
			this.controller.commit();

			this.setVisible(false);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e, "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void cancel(){
		this.setVisible(false);
	}

	private final JPanel contentPanel = new JPanel();
	private JTable tblStages;
	private JTable tblCorrelatedStages;
	private JButton btnInsert;
	private JButton btnDelete;

	/**
	 * Create the dialog.
	 */
	public MonographStageViewerUI(IMonographStageViewerController controller) {
		getContentPane().setPreferredSize(new Dimension(800, 600));
		setPreferredSize(new Dimension(800, 600));
		getContentPane().setMinimumSize(new Dimension(800, 600));
		setMinimumSize(new Dimension(800, 600));
		getContentPane().setMaximumSize(new Dimension(800, 600));
		setMaximumSize(new Dimension(800, 600));
		this.controller = controller;

		setModal(true);
		setDefaultLookAndFeelDecorated(true);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);

		setBounds(100, 100, 531, 368);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setPreferredSize(new Dimension(800, 600));
		contentPanel.setMinimumSize(new Dimension(800, 600));
		contentPanel.setMaximumSize(new Dimension(800, 600));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow][grow][grow]", "[grow][grow][grow]"));
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setPreferredSize(new Dimension(350, 400));
			scrollPane.setMinimumSize(new Dimension(350, 400));
			scrollPane.setMaximumSize(new Dimension(350, 400));
			contentPanel.add(scrollPane, "cell 0 0 1 2,grow");
			{
				tblStages = new JTable();
				tblStages.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPane.setViewportView(tblStages);
			}
		}
		{
			btnInsert = new JButton(">>");
			btnInsert.setPreferredSize(new Dimension(50, 25));
			btnInsert.setMinimumSize(new Dimension(50, 25));
			btnInsert.setMaximumSize(new Dimension(50, 25));
			btnInsert.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					insertStage();
				}
			});
			contentPanel.add(btnInsert, "flowx,cell 1 0,alignx center,aligny bottom");
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setPreferredSize(new Dimension(350, 400));
			scrollPane.setMinimumSize(new Dimension(350, 400));
			scrollPane.setMaximumSize(new Dimension(350, 400));
			contentPanel.add(scrollPane, "cell 2 0 1 2,grow");
			{
				tblCorrelatedStages = new JTable();
				tblCorrelatedStages.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPane.setViewportView(tblCorrelatedStages);
			}
		}
		{
			btnDelete = new JButton("<<");
			btnDelete.setPreferredSize(new Dimension(50, 25));
			btnDelete.setMinimumSize(new Dimension(50, 25));
			btnDelete.setMaximumSize(new Dimension(50, 25));
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteStage();
				}
			});
			contentPanel.add(btnDelete, "cell 1 1,alignx center,aligny top");
		}
		{
			{
				JPanel panel = new JPanel();
				panel.setPreferredSize(new Dimension(750, 60));
				panel.setMinimumSize(new Dimension(750, 60));
				panel.setMaximumSize(new Dimension(750, 60));
				panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opção:", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 1, 10))); // NOI18N
				contentPanel.add(panel, "cell 0 2 3 1,grow");
				JButton btnCommit = new JButton("Confirmar");
				btnCommit.setPreferredSize(new Dimension(150, 25));
				btnCommit.setMinimumSize(new Dimension(150, 25));
				btnCommit.setMaximumSize(new Dimension(150, 25));
				panel.add(btnCommit);
				{
					JButton btnCancel = new JButton("Cancelar");
					btnCancel.setPreferredSize(new Dimension(150, 25));
					btnCancel.setMinimumSize(new Dimension(150, 25));
					btnCancel.setMaximumSize(new Dimension(150, 25));
					panel.add(btnCancel);
					btnCancel.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							cancel();
						}
					});
				}
				btnCommit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						commit();
					}
				});
			}
		}
		initDataBindings();
	}
	protected void initDataBindings() {
		BeanProperty<IMonographStageViewerController, List<Stage>> iMonographStageViewerControllerBeanProperty = BeanProperty.create("stages");
		JTableBinding<Stage, IMonographStageViewerController, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, controller, iMonographStageViewerControllerBeanProperty, tblStages);
		//
		ELProperty<Stage, Object> stageEvalutionProperty = ELProperty.create("${year} ${name}");
		jTableBinding.addColumnBinding(stageEvalutionProperty).setColumnName("N\u00E3o-Correlacionadas").setEditable(false);
		//
		jTableBinding.bind();
		//
		BeanProperty<IMonographStageViewerController, List<Stage>> iMonographStageViewerControllerBeanProperty_1 = BeanProperty.create("correlatedStages");
		JTableBinding<Stage, IMonographStageViewerController, JTable> jTableBinding_1 = SwingBindings.createJTableBinding(UpdateStrategy.READ, controller, iMonographStageViewerControllerBeanProperty_1, tblCorrelatedStages);
		//
		ELProperty<Stage, Object> stageEvalutionProperty_1 = ELProperty.create("${year} ${name}");
		jTableBinding_1.addColumnBinding(stageEvalutionProperty_1).setColumnName("Correlacionadas").setEditable(false);
		//
		jTableBinding_1.bind();
		//
		BeanProperty<IMonographStageViewerController, Boolean> iMonographStageViewerControllerBeanProperty_2 = BeanProperty.create("canInsert");
		BeanProperty<JButton, Boolean> jButtonBeanProperty = BeanProperty.create("enabled");
		AutoBinding<IMonographStageViewerController, Boolean, JButton, Boolean> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ, controller, iMonographStageViewerControllerBeanProperty_2, btnInsert, jButtonBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<IMonographStageViewerController, Boolean> iMonographStageViewerControllerBeanProperty_3 = BeanProperty.create("canDelete");
		AutoBinding<IMonographStageViewerController, Boolean, JButton, Boolean> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ, controller, iMonographStageViewerControllerBeanProperty_3, btnDelete, jButtonBeanProperty);
		autoBinding_1.bind();
		//
		BeanProperty<IMonographStageViewerController, Stage> iMonographStageViewerControllerBeanProperty_4 = BeanProperty.create("selectedStage");
		BeanProperty<JTable, Stage> jTableBeanProperty = BeanProperty.create("selectedElement");
		AutoBinding<IMonographStageViewerController, Stage, JTable, Stage> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, controller, iMonographStageViewerControllerBeanProperty_4, tblStages, jTableBeanProperty);
		autoBinding_2.bind();
		//
		BeanProperty<IMonographStageViewerController, Stage> iMonographStageViewerControllerBeanProperty_5 = BeanProperty.create("selectedCorrelatedStage");
		BeanProperty<JTable, Stage> jTableBeanProperty_1 = BeanProperty.create("selectedElement");
		AutoBinding<IMonographStageViewerController, Stage, JTable, Stage> autoBinding_3 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, controller, iMonographStageViewerControllerBeanProperty_5, tblCorrelatedStages, jTableBeanProperty_1);
		autoBinding_3.bind();
	}

	@Override
	public void refresh() {
		controller.refresh();
	}
}
