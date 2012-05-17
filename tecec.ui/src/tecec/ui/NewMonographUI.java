package tecec.ui;

import java.awt.Component;
import java.awt.Font;
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

import net.miginfocom.swing.MigLayout;

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

public class NewMonographUI extends JDialog implements INewMonographUI {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private tecec.ui.contract.control.INewMonographController newMonographController;

	@Override
	public void setVisible(boolean visible){
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
	private JLabel lblTitulo;
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

	

	/**
	 * Create the frame.
	 */
	public NewMonographUI(
			tecec.ui.contract.control.INewMonographController newMonographController) {
		if (newMonographController == null) {
			throw new IllegalArgumentException("newMonographController");
		}

		this.newMonographController = newMonographController;

		setLocationByPlatform(true);
		setModal(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 436, 302);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow][grow]", "[grow][][56.00][][29.00][grow]"));		
		
		lblTitulo = new JLabel("Cadastrar Nova Monografia");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblTitulo, "cell 1 0,alignx center");

		lblTituloMonografia = new JLabel("Título:");
		contentPane.add(lblTituloMonografia, "flowx,cell 0 1,alignx right");

		txtMonographTitle = new JTextField();
		contentPane.add(txtMonographTitle, "cell 1 1,growx");
		txtMonographTitle.setColumns(10);

		lblCurso = new JLabel("Curso:");
		contentPane.add(lblCurso, "cell 0 2,alignx trailing");

		cboCurso = new JComboBox();
		contentPane.add(cboCurso, "cell 1 2,growx");
		
		cboCurso.setRenderer(new DefaultListCellRenderer(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected,
						cellHasFocus);
				
				if (value instanceof Course) {
					Course course = (Course)value;
					setText(course.getName());
				}
				
				return this;
			}
		});

		lblArea = new JLabel("Área:");
		contentPane.add(lblArea, "cell 0 3,alignx trailing");

		cboArea = new JComboBox();
		contentPane.add(cboArea, "cell 1 3,growx");
		
		cboArea.setRenderer(new DefaultListCellRenderer(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected,
						cellHasFocus);
				
				if (value instanceof Area) {
					Area area = (Area)value;
					setText(area.getName());
				}
				
				return this;
			}
		});

		lblAluno = new JLabel("Aluno:");
		contentPane.add(lblAluno, "cell 0 4,alignx trailing");

		cboAluno = new JComboBox();
		contentPane.add(cboAluno, "cell 1 4,growx");
		
		cboAluno.setRenderer(new DefaultListCellRenderer(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected,
						cellHasFocus);
				
				if (value instanceof Student) {
					Student student = (Student)value;
					setText(student.getName());
				}
				
				return this;
			}
		});


		lblOrientador = new JLabel("Orientador:");
		contentPane.add(lblOrientador, "cell 0 5,alignx trailing");

		cboOrientador = new JComboBox();
		contentPane.add(cboOrientador, "cell 1 5,growx");
		
		cboOrientador.setRenderer(new DefaultListCellRenderer(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected,
						cellHasFocus);
				
				if (value instanceof Advisor) {
					Advisor advisor = (Advisor)value;
					setText(advisor.getName());
				}
				
				return this;
			}
		});

		lblCoorientador = new JLabel("Coorientador");
		contentPane.add(lblCoorientador, "cell 0 6,alignx trailing");

		cboCoorientador = new JComboBox();
		contentPane.add(cboCoorientador, "cell 1 6,growx");
		
		cboCoorientador.setRenderer(new DefaultListCellRenderer(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected,
						cellHasFocus);
				
				if (value instanceof Advisor) {
					Advisor advisor = (Advisor)value;
					setText(advisor.getName());
				}
				
				return this;
			}
		});

		lblStatus = new JLabel("Status:");
		contentPane.add(lblStatus, "cell 0 7,alignx trailing");

		cboStatus = new JComboBox();
		contentPane.add(cboStatus, "cell 1 7,growx");
		
		cboStatus.setRenderer(new DefaultListCellRenderer(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected,
						cellHasFocus);
				
				if (value instanceof Status) {
					Status status = (Status)value;
					setText(status.getDescription());
				}
				
				return this;
			}
		});

		btnCreateMonograph = new JButton("Cadastrar");
		btnCreateMonograph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				storeMonograph();
			}
		});
		contentPane.add(btnCreateMonograph, "cell 1 8,alignx center,growy");
		initDataBindings();
	}
	protected void initDataBindings() {
		BeanProperty<INewMonographController, String> iNewMonographControllerBeanProperty = BeanProperty.create("monographTitle");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<INewMonographController, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, newMonographController, iNewMonographControllerBeanProperty, txtMonographTitle, jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<INewMonographController, List<Course>> iNewMonographControllerBeanProperty_3 = BeanProperty.create("courses");
		JComboBoxBinding<Course, INewMonographController, JComboBox> jComboBinding = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, newMonographController, iNewMonographControllerBeanProperty_3, cboCurso);
		jComboBinding.bind();
		//
		BeanProperty<INewMonographController, Integer> iNewMonographControllerBeanProperty_1 = BeanProperty.create("selecteCourseIndex");
		BeanProperty<JComboBox, Integer> jComboBoxBeanProperty = BeanProperty.create("selectedIndex");
		AutoBinding<INewMonographController, Integer, JComboBox, Integer> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, newMonographController, iNewMonographControllerBeanProperty_1, cboCurso, jComboBoxBeanProperty);
		autoBinding_1.bind();
		//
		BeanProperty<INewMonographController, Course> iNewMonographControllerBeanProperty_2 = BeanProperty.create("selectedCourse");
		BeanProperty<JComboBox, Object> jComboBoxBeanProperty_1 = BeanProperty.create("selectedItem");
		AutoBinding<INewMonographController, Course, JComboBox, Object> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, newMonographController, iNewMonographControllerBeanProperty_2, cboCurso, jComboBoxBeanProperty_1);
		autoBinding_2.bind();
		//
		BeanProperty<INewMonographController, List<Area>> iNewMonographControllerBeanProperty_4 = BeanProperty.create("areas");
		JComboBoxBinding<Area, INewMonographController, JComboBox> jComboBinding_1 = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, newMonographController, iNewMonographControllerBeanProperty_4, cboArea);
		jComboBinding_1.bind();
		//
		BeanProperty<INewMonographController, Integer> iNewMonographControllerBeanProperty_5 = BeanProperty.create("selectecAreaIndex");
		AutoBinding<INewMonographController, Integer, JComboBox, Integer> autoBinding_3 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, newMonographController, iNewMonographControllerBeanProperty_5, cboArea, jComboBoxBeanProperty);
		autoBinding_3.bind();
		//
		BeanProperty<INewMonographController, Area> iNewMonographControllerBeanProperty_6 = BeanProperty.create("selectedArea");
		AutoBinding<INewMonographController, Area, JComboBox, Object> autoBinding_4 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, newMonographController, iNewMonographControllerBeanProperty_6, cboArea, jComboBoxBeanProperty_1);
		autoBinding_4.bind();
		//
		BeanProperty<INewMonographController, List<Student>> iNewMonographControllerBeanProperty_7 = BeanProperty.create("students");
		JComboBoxBinding<Student, INewMonographController, JComboBox> jComboBinding_2 = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, newMonographController, iNewMonographControllerBeanProperty_7, cboAluno);
		jComboBinding_2.bind();
		//
		BeanProperty<INewMonographController, Integer> iNewMonographControllerBeanProperty_8 = BeanProperty.create("selectedStudentIndex");
		AutoBinding<INewMonographController, Integer, JComboBox, Integer> autoBinding_5 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, newMonographController, iNewMonographControllerBeanProperty_8, cboAluno, jComboBoxBeanProperty);
		autoBinding_5.bind();
		//
		BeanProperty<INewMonographController, Student> iNewMonographControllerBeanProperty_9 = BeanProperty.create("selectedStudent");
		AutoBinding<INewMonographController, Student, JComboBox, Object> autoBinding_6 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, newMonographController, iNewMonographControllerBeanProperty_9, cboAluno, jComboBoxBeanProperty_1);
		autoBinding_6.bind();
		//
		BeanProperty<INewMonographController, List<Advisor>> iNewMonographControllerBeanProperty_10 = BeanProperty.create("advisors");
		JComboBoxBinding<Advisor, INewMonographController, JComboBox> jComboBinding_3 = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, newMonographController, iNewMonographControllerBeanProperty_10, cboOrientador);
		jComboBinding_3.bind();
		//
		BeanProperty<INewMonographController, Integer> iNewMonographControllerBeanProperty_11 = BeanProperty.create("selectedAdvisorIndex");
		AutoBinding<INewMonographController, Integer, JComboBox, Integer> autoBinding_7 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, newMonographController, iNewMonographControllerBeanProperty_11, cboOrientador, jComboBoxBeanProperty);
		autoBinding_7.bind();
		//
		BeanProperty<INewMonographController, Advisor> iNewMonographControllerBeanProperty_12 = BeanProperty.create("selectedAdvisor");
		AutoBinding<INewMonographController, Advisor, JComboBox, Object> autoBinding_8 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, newMonographController, iNewMonographControllerBeanProperty_12, cboOrientador, jComboBoxBeanProperty_1);
		autoBinding_8.bind();
		//
		BeanProperty<INewMonographController, List<Status>> iNewMonographControllerBeanProperty_13 = BeanProperty.create("status");
		JComboBoxBinding<Status, INewMonographController, JComboBox> jComboBinding_5 = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, newMonographController, iNewMonographControllerBeanProperty_13, cboStatus);
		jComboBinding_5.bind();
		//
		BeanProperty<INewMonographController, Integer> iNewMonographControllerBeanProperty_14 = BeanProperty.create("selectedStatusIndex");
		AutoBinding<INewMonographController, Integer, JComboBox, Integer> autoBinding_11 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, newMonographController, iNewMonographControllerBeanProperty_14, cboStatus, jComboBoxBeanProperty);
		autoBinding_11.bind();
		//
		BeanProperty<INewMonographController, Status> iNewMonographControllerBeanProperty_15 = BeanProperty.create("selectedStatus");
		AutoBinding<INewMonographController, Status, JComboBox, Object> autoBinding_12 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, newMonographController, iNewMonographControllerBeanProperty_15, cboStatus, jComboBoxBeanProperty_1);
		autoBinding_12.bind();
		//
		BeanProperty<INewMonographController, List<Advisor>> iNewMonographControllerBeanProperty_16 = BeanProperty.create("coadvisors");
		JComboBoxBinding<Advisor, INewMonographController, JComboBox> jComboBinding_4 = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, newMonographController, iNewMonographControllerBeanProperty_16, cboCoorientador);
		jComboBinding_4.bind();
		//
		BeanProperty<INewMonographController, Integer> iNewMonographControllerBeanProperty_17 = BeanProperty.create("selectedCoadvisorIndex");
		AutoBinding<INewMonographController, Integer, JComboBox, Integer> autoBinding_9 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, newMonographController, iNewMonographControllerBeanProperty_17, cboCoorientador, jComboBoxBeanProperty);
		autoBinding_9.bind();
		//
		BeanProperty<INewMonographController, Advisor> iNewMonographControllerBeanProperty_18 = BeanProperty.create("selectedCoadvisor");
		AutoBinding<INewMonographController, Advisor, JComboBox, Object> autoBinding_10 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, newMonographController, iNewMonographControllerBeanProperty_18, cboCoorientador, jComboBoxBeanProperty_1);
		autoBinding_10.bind();
	}
}
