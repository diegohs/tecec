package tecec.ui;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tecec.ui.contract.control.ICourseViewerController;
import tecec.ui.contract.view.ICourseViewerUI;
import net.miginfocom.swing.MigLayout;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import java.util.List;

import tecec.contract.RuleViolation;
import tecec.dto.Course;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.border.TitledBorder;

public class CourseViewerUI extends JDialog implements ICourseViewerUI {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();

	private ICourseViewerController courseViewerController;
	private JTextField textField;
	private JTable table;
	private JButton btnAtualizar;
	private JButton btnRemover;
	private JPanel panelPesquisa;
	private JPanel panelButtons;
	private JButton btnNewCourse;

	private void showNewCourseUI() {
		courseViewerController.showNewCourseUI();
	}

	private void showUpdateCourseUI() {
		courseViewerController.showUpdateCourseUI();
	}

	private void deleteCourseUI() {
		RuleViolation violation = this.courseViewerController
				.getDeletionViolation();

		if (violation != null) {
			JOptionPane.showMessageDialog(this, violation.getDescription(),
					"ERRO", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				this.courseViewerController.deleteCourse();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e, "ERRO",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public CourseViewerUI(ICourseViewerController courseViewerController) {
		setTitle("Cursos");
		setPreferredSize(new Dimension(800, 600));
		setMinimumSize(new Dimension(800, 600));
		setMaximumSize(new Dimension(800, 600));
		getContentPane().setPreferredSize(new Dimension(800, 600));
		getContentPane().setMinimumSize(new Dimension(800, 600));
		getContentPane().setMaximumSize(new Dimension(800, 600));

		if (courseViewerController == null)
			throw new IllegalArgumentException("courseViewerController");

		this.courseViewerController = courseViewerController;

		setDefaultLookAndFeelDecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/tecec/ui/files/icone_tecec.png")));
		setModal(true);

		setBounds(100, 100, 568, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setPreferredSize(new Dimension(800, 600));
		contentPanel.setMinimumSize(new Dimension(800, 600));
		contentPanel.setMaximumSize(new Dimension(800, 600));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[800px]",
				"[50px][400px][50px]"));
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
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setPreferredSize(new Dimension(750, 400));
			scrollPane.setMinimumSize(new Dimension(750, 400));
			scrollPane.setMaximumSize(new Dimension(750, 400));
			contentPanel.add(scrollPane, "cell 0 1,grow");
			{
				table = new JTable();
				scrollPane.setViewportView(table);
			}
		}
		{
			{
				{
					panelButtons = new JPanel();
					FlowLayout flowLayout = (FlowLayout) panelButtons
							.getLayout();
					flowLayout.setAlignment(FlowLayout.RIGHT);
					panelButtons.setPreferredSize(new Dimension(750, 60));
					panelButtons.setMinimumSize(new Dimension(750, 60));
					panelButtons.setMaximumSize(new Dimension(750, 60));
					panelButtons
							.setBorder(new TitledBorder(null, "Op\u00E7\u00F5es:", TitledBorder.LEADING, TitledBorder.TOP, null, null)); // NOI18N
					contentPanel.add(panelButtons, "cell 0 2,grow");
					{
						btnNewCourse = new JButton("Cadastrar Novo");
						btnNewCourse.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								showNewCourseUI();
							}
						});
						panelButtons.add(btnNewCourse);
					}
					{
						btnAtualizar = new JButton("Atualizar Selecionado");
						btnAtualizar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								showUpdateCourseUI();
							}
						});
						btnAtualizar.setPreferredSize(new Dimension(200, 25));
						btnAtualizar.setMinimumSize(new Dimension(150, 25));
						btnAtualizar.setMaximumSize(new Dimension(150, 25));
						panelButtons.add(btnAtualizar);
						{
							btnRemover = new JButton("Remover Selecionado");
							btnRemover.setPreferredSize(new Dimension(200, 25));
							btnRemover.setMinimumSize(new Dimension(150, 25));
							btnRemover.setMaximumSize(new Dimension(150, 25));
							panelButtons.add(btnRemover);
							btnRemover.addActionListener(new ActionListener() {

								public void actionPerformed(ActionEvent e) {
									deleteCourseUI();
								}
							});

						}
					}
					btnAtualizar = new JButton("Atualizar Selecionado");
					btnAtualizar.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							showUpdateCourseUI();
						}
					});

					contentPanel.add(btnAtualizar, "cell 2 3,alignx right");
				}
				{
					btnRemover = new JButton("Remover Selecionado");
					btnRemover.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							showUpdateCourseUI();

						}

					});
				}
			}
			contentPanel.add(btnRemover, "cell 2 3,alignx right");
		}
		initDataBindings();
	}

	protected void initDataBindings() {
		BeanProperty<ICourseViewerController, String> iCourseViewerControllerBeanProperty = BeanProperty
				.create("nameFilter");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty
				.create("text");
		AutoBinding<ICourseViewerController, String, JTextField, String> autoBinding = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						courseViewerController,
						iCourseViewerControllerBeanProperty, textField,
						jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<ICourseViewerController, List<Course>> iCourseViewerControllerBeanProperty_1 = BeanProperty
				.create("courses");
		JTableBinding<Course, ICourseViewerController, JTable> jTableBinding = SwingBindings
				.createJTableBinding(UpdateStrategy.READ,
						courseViewerController,
						iCourseViewerControllerBeanProperty_1, table);
		//
		BeanProperty<Course, String> courseBeanProperty = BeanProperty
				.create("name");
		jTableBinding.addColumnBinding(courseBeanProperty)
				.setColumnName("Nome");
		//
		BeanProperty<Course, String> courseBeanProperty_1 = BeanProperty
				.create("turn");
		jTableBinding.addColumnBinding(courseBeanProperty_1).setColumnName(
				"Turno");
		//
		BeanProperty<Course, String> courseBeanProperty_2 = BeanProperty
				.create("year");
		jTableBinding.addColumnBinding(courseBeanProperty_2).setColumnName(
				"Ano");
		//
		jTableBinding.bind();
		//
		BeanProperty<ICourseViewerController, Course> iCourseViewerControllerBeanProperty_2 = BeanProperty
				.create("selectedCourse");
		BeanProperty<JTable, Course> jTableBeanProperty = BeanProperty
				.create("selectedElement");
		AutoBinding<ICourseViewerController, Course, JTable, Course> autoBinding_1 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						courseViewerController,
						iCourseViewerControllerBeanProperty_2, table,
						jTableBeanProperty);
		autoBinding_1.bind();
		//
		BeanProperty<ICourseViewerController, Boolean> iCourseViewerControllerBeanProperty_3 = BeanProperty
				.create("canUpdateCourse");
		BeanProperty<JButton, Boolean> jButtonBeanProperty = BeanProperty
				.create("enabled");
		AutoBinding<ICourseViewerController, Boolean, JButton, Boolean> autoBinding_2 = Bindings
				.createAutoBinding(UpdateStrategy.READ, courseViewerController,
						iCourseViewerControllerBeanProperty_3, btnAtualizar,
						jButtonBeanProperty);
		autoBinding_2.bind();
		//
		BeanProperty<ICourseViewerController, Boolean> iCourseViewerControllerBeanProperty_4 = BeanProperty
				.create("canDeleteCourse");
		AutoBinding<ICourseViewerController, Boolean, JButton, Boolean> autoBinding_3 = Bindings
				.createAutoBinding(UpdateStrategy.READ, courseViewerController,
						iCourseViewerControllerBeanProperty_4, btnRemover,
						jButtonBeanProperty);
		autoBinding_3.bind();
	}

	@Override
	public void refresh() {
		courseViewerController.refresh();
	}
}
