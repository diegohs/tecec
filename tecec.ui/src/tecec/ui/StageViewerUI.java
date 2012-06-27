package tecec.ui;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import tecec.ui.contract.control.IStageViewerController;
import tecec.ui.contract.view.IStageViewerUI;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import java.util.List;

import tecec.contract.RuleViolation;
import tecec.dto.Stage;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;
import javax.swing.ListSelectionModel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

public class StageViewerUI extends JDialog implements IStageViewerUI {

	private static final long serialVersionUID = 1L;
	private IStageViewerController stageViewerController;

	private void showNewStageUI() {
		stageViewerController.showNewStageUI();
	}

	private void showUpdateStageUI() {
		stageViewerController.newUpdateStageUI();
	}

	private void deleteStage() {
		RuleViolation violation = stageViewerController.getDeletionViolation();

		if (violation != null) {
			JOptionPane.showMessageDialog(this, violation.getDescription(),
					"ERRO", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				stageViewerController.deleteStage();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e, "ERRO",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTable table;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JPanel panelPesquisa;
	private JPanel panelButtons;

	/**
	 * Create the dialog.
	 */
	public StageViewerUI(IStageViewerController stageViewerController) {
		setTitle("Est\u00E1gio");
		getContentPane().setPreferredSize(new Dimension(800, 600));
		setPreferredSize(new Dimension(800, 600));
		getContentPane().setMinimumSize(new Dimension(800, 600));
		setMinimumSize(new Dimension(800, 600));
		getContentPane().setMaximumSize(new Dimension(800, 600));
		setMaximumSize(new Dimension(800, 600));
		if (stageViewerController == null)
			throw new IllegalArgumentException("stageViewerController");
		this.stageViewerController = stageViewerController;

		setDefaultLookAndFeelDecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/tecec/ui/files/icone_tecec.png")));
		setModal(true);

		setBounds(100, 100, 519, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setPreferredSize(new Dimension(800, 600));
		contentPanel.setMinimumSize(new Dimension(800, 600));
		contentPanel.setMaximumSize(new Dimension(800, 600));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel
				.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow]"));
		{
			panelPesquisa = new JPanel();
			panelPesquisa.setPreferredSize(new Dimension(750, 60));
			panelPesquisa.setMinimumSize(new Dimension(750, 60));
			panelPesquisa.setMaximumSize(new Dimension(750, 60));
			panelPesquisa.setBorder(javax.swing.BorderFactory
					.createTitledBorder(null, "Pesquisa:",
							javax.swing.border.TitledBorder.LEFT,
							javax.swing.border.TitledBorder.DEFAULT_POSITION,
							new java.awt.Font("Comic Sans MS", 1, 10))); // NOI18N
			contentPanel.add(panelPesquisa, "cell 0 0,grow");
			{
				textField = new JTextField();
				panelPesquisa.add(textField);
				textField.setColumns(90);
			}
		}
		{
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setPreferredSize(new Dimension(750, 400));
				scrollPane.setMinimumSize(new Dimension(750, 400));
				scrollPane.setMaximumSize(new Dimension(750, 400));
				contentPanel.add(scrollPane, "cell 0 1,grow");
				{
					table = new JTable();
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					scrollPane.setRowHeaderView(table);
				}
			}
			{
				panelButtons = new JPanel();
				FlowLayout flowLayout = (FlowLayout) panelButtons.getLayout();
				flowLayout.setAlignment(FlowLayout.RIGHT);
				panelButtons.setPreferredSize(new Dimension(750, 60));
				panelButtons.setMinimumSize(new Dimension(750, 60));
				panelButtons.setMaximumSize(new Dimension(750, 60));
				panelButtons
						.setBorder(new TitledBorder(null, "Op\u00E7\u00F5es:", TitledBorder.LEADING, TitledBorder.TOP, null, null)); // NOI18N
				contentPanel.add(panelButtons, "cell 0 2,grow");
				btnNewButton = new JButton("Adicionar Novo");
				btnNewButton.setPreferredSize(new Dimension(150, 25));
				btnNewButton.setMinimumSize(new Dimension(150, 25));
				btnNewButton.setMaximumSize(new Dimension(150, 25));
				panelButtons.add(btnNewButton);
				{
					btnNewButton_1 = new JButton("Atualizar Selecionado");
					btnNewButton_1.setPreferredSize(new Dimension(150, 25));
					btnNewButton_1.setMinimumSize(new Dimension(150, 25));
					btnNewButton_1.setMaximumSize(new Dimension(150, 25));
					panelButtons.add(btnNewButton_1);
					{
						btnNewButton_2 = new JButton("Excluir Selecionado");
						btnNewButton_2.setPreferredSize(new Dimension(150, 25));
						btnNewButton_2.setMinimumSize(new Dimension(150, 25));
						btnNewButton_2.setMaximumSize(new Dimension(150, 25));
						panelButtons.add(btnNewButton_2);
						btnNewButton_2.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								deleteStage();
							}

						});
					}
					btnNewButton_1.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							showUpdateStageUI();
						}

					});
				}
				btnNewButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						showNewStageUI();

					}

				});
			}
		}
		initDataBindings();
	}

	protected void initDataBindings() {
		BeanProperty<IStageViewerController, String> iStageViewerControllerBeanProperty = BeanProperty
				.create("nameFilter");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty
				.create("text");
		AutoBinding<IStageViewerController, String, JTextField, String> autoBinding = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						stageViewerController,
						iStageViewerControllerBeanProperty, textField,
						jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<IStageViewerController, List<Stage>> iStageViewerControllerBeanProperty_1 = BeanProperty
				.create("stages");
		JTableBinding<Stage, IStageViewerController, JTable> jTableBinding = SwingBindings
				.createJTableBinding(UpdateStrategy.READ,
						stageViewerController,
						iStageViewerControllerBeanProperty_1, table);
		//
		BeanProperty<Stage, String> stageBeanProperty = BeanProperty
				.create("name");
		jTableBinding.addColumnBinding(stageBeanProperty).setColumnName("Nome");
		//
		BeanProperty<Stage, String> stageBeanProperty_1 = BeanProperty
				.create("year");
		jTableBinding.addColumnBinding(stageBeanProperty_1)
				.setColumnName("Ano");
		//
		jTableBinding.bind();
		//
		BeanProperty<IStageViewerController, Stage> iStageViewerControllerBeanProperty_2 = BeanProperty
				.create("selectedStage");
		BeanProperty<JTable, Stage> jTableBeanProperty = BeanProperty
				.create("selectedElement");
		AutoBinding<IStageViewerController, Stage, JTable, Stage> autoBinding_1 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						stageViewerController,
						iStageViewerControllerBeanProperty_2, table,
						jTableBeanProperty);
		autoBinding_1.bind();
		//
		BeanProperty<IStageViewerController, Boolean> iStageViewerControllerBeanProperty_3 = BeanProperty
				.create("canUpdateStage");
		BeanProperty<JButton, Boolean> jButtonBeanProperty = BeanProperty
				.create("enabled");
		AutoBinding<IStageViewerController, Boolean, JButton, Boolean> autoBinding_2 = Bindings
				.createAutoBinding(UpdateStrategy.READ, stageViewerController,
						iStageViewerControllerBeanProperty_3, btnNewButton_1,
						jButtonBeanProperty);
		autoBinding_2.bind();
		//
		BeanProperty<IStageViewerController, Boolean> iStageViewerControllerBeanProperty_4 = BeanProperty
				.create("canDeleteStage");
		AutoBinding<IStageViewerController, Boolean, JButton, Boolean> autoBinding_3 = Bindings
				.createAutoBinding(UpdateStrategy.READ, stageViewerController,
						iStageViewerControllerBeanProperty_4, btnNewButton_2,
						jButtonBeanProperty);
		autoBinding_3.bind();
	}

	@Override
	public void refresh() {
		stageViewerController.refresh();
	}
}
