package tecec.ui;

import java.awt.Component;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;

import tecec.contract.RuleViolation;
import tecec.dto.Advisor;
import tecec.dto.Course;
import tecec.dto.Status;
import tecec.dto.Student;
import tecec.ui.contract.control.INewMonographController;
import tecec.ui.contract.view.INewMonographUI;
import javax.swing.JComboBox;
import java.util.List;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.SwingBindings;
import tecec.dto.Area;
import javax.swing.border.TitledBorder;
import java.awt.Dimension;

public class NewMonographUI extends JDialog implements INewMonographUI {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private tecec.ui.contract.control.INewMonographController newMonographController;

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
	}

	private void storeMonograph() {
		try {
			RuleViolation violation = this.newMonographController
					.getCreationViolation();
			if (violation != null) {
				JOptionPane.showMessageDialog(this, violation.getDescription(),
						"Erro", JOptionPane.ERROR_MESSAGE);
			} else {
				this.newMonographController.createMonograph();

				this.setVisible(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e, "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private JPanel contentPane;
	private JButton btnCreateMonograph;

	private JLabel lblTituloMonografia;
	private JTextField txtMonographTitle;
	private JLabel lblArea;
	private JComboBox cboArea;
	private JLabel lblAluno;
	private JComboBox cboAluno;
	private JLabel lblOrientador;
	private JComboBox cboOrientador;
	private JLabel lblCoorientador;
	private JComboBox cboCoorientador;
	private JLabel lblStatus;
	private JComboBox cboStatus;
	private JLabel lblCurso;
	private JComboBox cboCurso;
	private JPanel panel;

	/**
	 * Create the frame.
	 */
	public NewMonographUI(
			tecec.ui.contract.control.INewMonographController newMonographController) {
		setPreferredSize(new Dimension(450, 370));
		setMinimumSize(new Dimension(450, 370));
		setMaximumSize(new Dimension(450, 370));
		if (newMonographController == null) {
			throw new IllegalArgumentException("newMonographController");
		}

		this.newMonographController = newMonographController;

		setDefaultLookAndFeelDecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/tecec/ui/files/icone_tecec.png")));

		setLocationByPlatform(true);
		setModal(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setPreferredSize(new Dimension(440, 360));
		panel.setMinimumSize(new Dimension(440, 360));
		panel.setMaximumSize(new Dimension(440, 360));
		panel.setBorder(new TitledBorder(null, "Cadastrar nova Monografia:",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 414, 315);
		contentPane.add(panel);
		panel.setLayout(null);
		lblTituloMonografia = new JLabel("TÃ­tulo:");
		contentPane.add(lblTituloMonografia, "flowx,cell 0 1,alignx right");

		btnCreateMonograph = new JButton("Cadastrar");
		btnCreateMonograph.setBounds(299, 281, 105, 23);
		panel.add(btnCreateMonograph);

		lblTituloMonografia = new JLabel("T\u00EDtulo:");
		lblTituloMonografia.setBounds(10, 36, 66, 14);
		panel.add(lblTituloMonografia);

		cboCurso = new JComboBox();
		cboCurso.setBounds(67, 64, 337, 20);
		panel.add(cboCurso);

		cboCurso.setRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index,
						isSelected, cellHasFocus);

				if (value instanceof Course) {
					Course course = (Course) value;
					setText(course.getName());
				}

				return this;
			}
		});

		txtMonographTitle = new JTextField();
		txtMonographTitle.setBounds(67, 33, 337, 20);
		panel.add(txtMonographTitle);
		txtMonographTitle.setColumns(10);

		lblCurso = new JLabel("Curso:");
		lblCurso.setBounds(10, 67, 94, 14);
		panel.add(lblCurso);
		lblArea = new JLabel("Ã�rea:");
		contentPane.add(lblArea, "cell 0 3,alignx trailing");

		cboArea = new JComboBox();
		cboArea.setBounds(67, 95, 337, 20);
		panel.add(cboArea);

		cboArea.setRenderer(new DefaultListCellRenderer() {
			/**
																													 *
																													 */
			private static final long serialVersionUID = 1L;

			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index,
						isSelected, cellHasFocus);

				if (value instanceof Area) {
					Area area = (Area) value;
					setText(area.getName());
				}

				return this;
			}
		});

		lblArea = new JLabel("\u00C1rea:");
		lblArea.setBounds(10, 98, 38, 14);
		panel.add(lblArea);

		cboAluno = new JComboBox();
		cboAluno.setBounds(67, 126, 337, 20);
		panel.add(cboAluno);

		cboAluno.setRenderer(new DefaultListCellRenderer() {
			/**
																																	 *
																																	 */
			private static final long serialVersionUID = 1L;

			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index,
						isSelected, cellHasFocus);

				if (value instanceof Student) {
					Student student = (Student) value;
					setText(student.getName());
				}

				return this;
			}
		});

		lblAluno = new JLabel("Aluno:");
		lblAluno.setBounds(10, 129, 66, 14);
		panel.add(lblAluno);

		cboOrientador = new JComboBox();
		cboOrientador.setBounds(129, 157, 275, 20);
		panel.add(cboOrientador);

		cboOrientador.setRenderer(new DefaultListCellRenderer() {
			/**
																																					 *
																																					 */
			private static final long serialVersionUID = 1L;

			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index,
						isSelected, cellHasFocus);

				if (value instanceof Advisor) {
					Advisor advisor = (Advisor) value;
					setText(advisor.getName());
				}

				return this;
			}
		});

		lblOrientador = new JLabel("Orientador:");
		lblOrientador.setBounds(20, 160, 84, 14);
		panel.add(lblOrientador);

		cboCoorientador = new JComboBox();
		cboCoorientador.setBounds(129, 188, 275, 20);
		panel.add(cboCoorientador);

		cboCoorientador.setRenderer(new DefaultListCellRenderer() {
			/**
																																									 *
																																									 */
			private static final long serialVersionUID = 1L;

			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index,
						isSelected, cellHasFocus);

				if (value instanceof Advisor) {
					Advisor advisor = (Advisor) value;
					setText(advisor.getName());
				}

				return this;
			}
		});

		lblCoorientador = new JLabel("Coorientador:");
		lblCoorientador.setBounds(10, 191, 121, 14);
		panel.add(lblCoorientador);

		cboStatus = new JComboBox();
		cboStatus.setBounds(129, 219, 275, 20);
		panel.add(cboStatus);

		cboStatus.setRenderer(new DefaultListCellRenderer() {
			/**
																																													 *
																																													 */
			private static final long serialVersionUID = 1L;

			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index,
						isSelected, cellHasFocus);

				if (value instanceof Status) {
					Status status = (Status) value;
					setText(status.getDescription());
				}

				return this;
			}
		});

		lblStatus = new JLabel("Status:");
		lblStatus.setBounds(45, 222, 66, 14);
		panel.add(lblStatus);
		btnCreateMonograph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				storeMonograph();
			}
		});
		initDataBindings();
	}

	protected void initDataBindings() {
		BeanProperty<INewMonographController, String> iNewMonographControllerBeanProperty = BeanProperty
				.create("monographTitle");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty
				.create("text");
		AutoBinding<INewMonographController, String, JTextField, String> autoBinding = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						newMonographController,
						iNewMonographControllerBeanProperty, txtMonographTitle,
						jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<INewMonographController, List<Course>> iNewMonographControllerBeanProperty_3 = BeanProperty
				.create("courses");
		JComboBoxBinding<Course, INewMonographController, JComboBox> jComboBinding = SwingBindings
				.createJComboBoxBinding(UpdateStrategy.READ_WRITE,
						newMonographController,
						iNewMonographControllerBeanProperty_3, cboCurso);
		jComboBinding.bind();
		//
		BeanProperty<INewMonographController, Integer> iNewMonographControllerBeanProperty_1 = BeanProperty
				.create("selecteCourseIndex");
		BeanProperty<JComboBox, Integer> jComboBoxBeanProperty = BeanProperty
				.create("selectedIndex");
		AutoBinding<INewMonographController, Integer, JComboBox, Integer> autoBinding_1 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						newMonographController,
						iNewMonographControllerBeanProperty_1, cboCurso,
						jComboBoxBeanProperty);
		autoBinding_1.bind();
		//
		BeanProperty<INewMonographController, Course> iNewMonographControllerBeanProperty_2 = BeanProperty
				.create("selectedCourse");
		BeanProperty<JComboBox, Object> jComboBoxBeanProperty_1 = BeanProperty
				.create("selectedItem");
		AutoBinding<INewMonographController, Course, JComboBox, Object> autoBinding_2 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						newMonographController,
						iNewMonographControllerBeanProperty_2, cboCurso,
						jComboBoxBeanProperty_1);
		autoBinding_2.bind();
		//
		BeanProperty<INewMonographController, List<Area>> iNewMonographControllerBeanProperty_4 = BeanProperty
				.create("areas");
		JComboBoxBinding<Area, INewMonographController, JComboBox> jComboBinding_1 = SwingBindings
				.createJComboBoxBinding(UpdateStrategy.READ_WRITE,
						newMonographController,
						iNewMonographControllerBeanProperty_4, cboArea);
		jComboBinding_1.bind();
		//
		BeanProperty<INewMonographController, Integer> iNewMonographControllerBeanProperty_5 = BeanProperty
				.create("selectecAreaIndex");
		AutoBinding<INewMonographController, Integer, JComboBox, Integer> autoBinding_3 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						newMonographController,
						iNewMonographControllerBeanProperty_5, cboArea,
						jComboBoxBeanProperty);
		autoBinding_3.bind();
		//
		BeanProperty<INewMonographController, Area> iNewMonographControllerBeanProperty_6 = BeanProperty
				.create("selectedArea");
		AutoBinding<INewMonographController, Area, JComboBox, Object> autoBinding_4 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						newMonographController,
						iNewMonographControllerBeanProperty_6, cboArea,
						jComboBoxBeanProperty_1);
		autoBinding_4.bind();
		//
		BeanProperty<INewMonographController, List<Student>> iNewMonographControllerBeanProperty_7 = BeanProperty
				.create("students");
		JComboBoxBinding<Student, INewMonographController, JComboBox> jComboBinding_2 = SwingBindings
				.createJComboBoxBinding(UpdateStrategy.READ_WRITE,
						newMonographController,
						iNewMonographControllerBeanProperty_7, cboAluno);
		jComboBinding_2.bind();
		//
		BeanProperty<INewMonographController, Integer> iNewMonographControllerBeanProperty_8 = BeanProperty
				.create("selectedStudentIndex");
		AutoBinding<INewMonographController, Integer, JComboBox, Integer> autoBinding_5 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						newMonographController,
						iNewMonographControllerBeanProperty_8, cboAluno,
						jComboBoxBeanProperty);
		autoBinding_5.bind();
		//
		BeanProperty<INewMonographController, Student> iNewMonographControllerBeanProperty_9 = BeanProperty
				.create("selectedStudent");
		AutoBinding<INewMonographController, Student, JComboBox, Object> autoBinding_6 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						newMonographController,
						iNewMonographControllerBeanProperty_9, cboAluno,
						jComboBoxBeanProperty_1);
		autoBinding_6.bind();
		//
		BeanProperty<INewMonographController, List<Advisor>> iNewMonographControllerBeanProperty_10 = BeanProperty
				.create("advisors");
		JComboBoxBinding<Advisor, INewMonographController, JComboBox> jComboBinding_3 = SwingBindings
				.createJComboBoxBinding(UpdateStrategy.READ_WRITE,
						newMonographController,
						iNewMonographControllerBeanProperty_10, cboOrientador);
		jComboBinding_3.bind();
		//
		BeanProperty<INewMonographController, Integer> iNewMonographControllerBeanProperty_11 = BeanProperty
				.create("selectedAdvisorIndex");
		AutoBinding<INewMonographController, Integer, JComboBox, Integer> autoBinding_7 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						newMonographController,
						iNewMonographControllerBeanProperty_11, cboOrientador,
						jComboBoxBeanProperty);
		autoBinding_7.bind();
		//
		BeanProperty<INewMonographController, Advisor> iNewMonographControllerBeanProperty_12 = BeanProperty
				.create("selectedAdvisor");
		AutoBinding<INewMonographController, Advisor, JComboBox, Object> autoBinding_8 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						newMonographController,
						iNewMonographControllerBeanProperty_12, cboOrientador,
						jComboBoxBeanProperty_1);
		autoBinding_8.bind();
		//
		BeanProperty<INewMonographController, List<Status>> iNewMonographControllerBeanProperty_13 = BeanProperty
				.create("status");
		JComboBoxBinding<Status, INewMonographController, JComboBox> jComboBinding_5 = SwingBindings
				.createJComboBoxBinding(UpdateStrategy.READ_WRITE,
						newMonographController,
						iNewMonographControllerBeanProperty_13, cboStatus);
		jComboBinding_5.bind();
		//
		BeanProperty<INewMonographController, Integer> iNewMonographControllerBeanProperty_14 = BeanProperty
				.create("selectedStatusIndex");
		AutoBinding<INewMonographController, Integer, JComboBox, Integer> autoBinding_11 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						newMonographController,
						iNewMonographControllerBeanProperty_14, cboStatus,
						jComboBoxBeanProperty);
		autoBinding_11.bind();
		//
		BeanProperty<INewMonographController, Status> iNewMonographControllerBeanProperty_15 = BeanProperty
				.create("selectedStatus");
		AutoBinding<INewMonographController, Status, JComboBox, Object> autoBinding_12 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						newMonographController,
						iNewMonographControllerBeanProperty_15, cboStatus,
						jComboBoxBeanProperty_1);
		autoBinding_12.bind();
		//
		BeanProperty<INewMonographController, List<Advisor>> iNewMonographControllerBeanProperty_16 = BeanProperty
				.create("coadvisors");
		JComboBoxBinding<Advisor, INewMonographController, JComboBox> jComboBinding_4 = SwingBindings
				.createJComboBoxBinding(UpdateStrategy.READ_WRITE,
						newMonographController,
						iNewMonographControllerBeanProperty_16, cboCoorientador);
		jComboBinding_4.bind();
		//
		BeanProperty<INewMonographController, Integer> iNewMonographControllerBeanProperty_17 = BeanProperty
				.create("selectedCoadvisorIndex");
		AutoBinding<INewMonographController, Integer, JComboBox, Integer> autoBinding_9 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						newMonographController,
						iNewMonographControllerBeanProperty_17,
						cboCoorientador, jComboBoxBeanProperty);
		autoBinding_9.bind();
		//
		BeanProperty<INewMonographController, Advisor> iNewMonographControllerBeanProperty_18 = BeanProperty
				.create("selectedCoadvisor");
		AutoBinding<INewMonographController, Advisor, JComboBox, Object> autoBinding_10 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						newMonographController,
						iNewMonographControllerBeanProperty_18,
						cboCoorientador, jComboBoxBeanProperty_1);
		autoBinding_10.bind();
		//
		BeanProperty<INewMonographController, Boolean> iNewMonographControllerBeanProperty_19 = BeanProperty
				.create("canSelectStudent");
		BeanProperty<JComboBox, Boolean> jComboBoxBeanProperty_2 = BeanProperty
				.create("enabled");
		AutoBinding<INewMonographController, Boolean, JComboBox, Boolean> autoBinding_13 = Bindings
				.createAutoBinding(UpdateStrategy.READ, newMonographController,
						iNewMonographControllerBeanProperty_19, cboAluno,
						jComboBoxBeanProperty_2);
		autoBinding_13.bind();
	}

	@Override
	public void refresh() {
		newMonographController.refresh();
	}
}
