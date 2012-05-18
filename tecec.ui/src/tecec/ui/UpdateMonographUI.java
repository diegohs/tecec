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

import tecec.contract.RuleViolation;
import net.miginfocom.swing.MigLayout;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;

import tecec.ui.contract.control.IUpdateMonographController;
import tecec.ui.contract.view.IUpdateMonographUI;
import javax.swing.JComboBox;
import java.util.List;
import tecec.dto.Course;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.SwingBindings;
import tecec.dto.Area;
import tecec.dto.Student;
import tecec.dto.Advisor;
import tecec.dto.Status;

public class UpdateMonographUI extends JDialog implements IUpdateMonographUI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private IUpdateMonographController updateMonographController;

	@Override
	public void setVisible(boolean visible){
		super.setVisible(visible);
	}
	
	private void storeMonograph() {
		try {
			RuleViolation violation = this.updateMonographController
					.getUpdateViolation();

			if (violation != null) {
				JOptionPane.showMessageDialog(this, violation.getDescription(),
						"Erro", JOptionPane.ERROR_MESSAGE);
			} else {
				this.updateMonographController.updateMonograph();
				
				this.setVisible(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e, "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private JPanel contentPane;
	private JTextField txtMonographTitle;
	private JButton btnUpdateMonograph;
	private JLabel lblCourse;
	private JLabel lblArea;
	private JLabel lblStudent;
	private JLabel lblAdvisor;
	private JLabel lblCoadvisor;
	private JLabel lblStatus;
	private JComboBox cboCourse;
	private JComboBox cboArea;
	private JComboBox cboStudent;
	private JComboBox cboAdvisor;
	private JComboBox cboCoadvisor;
	private JComboBox cboStatus;

	/**
	 * Create the frame.
	 */
	public UpdateMonographUI(IUpdateMonographController updateMonographController) {
		this.updateMonographController = updateMonographController;

		setModal(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 400, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		//contentPane.setLayout(new MigLayout("", "[grow][grow][grow]","[grow][][56.00][29.00][grow]"));
		contentPane.setLayout(new MigLayout("", "[][grow][]", "[][][][][][][][][][][]"));
				
						JLabel lblTitle = new JLabel("Atualizar Monografia");
						lblTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
						contentPane.add(lblTitle, "cell 1 0,grow");
		
				JLabel lblMonographTitle = new JLabel("Título:");
				contentPane.add(lblMonographTitle, "cell 0 2,alignx right");
		
				txtMonographTitle = new JTextField();
				contentPane.add(txtMonographTitle, "cell 1 2,growx");
				txtMonographTitle.setColumns(10);
		
		lblCourse = new JLabel("Curso:");
		contentPane.add(lblCourse, "cell 0 3,alignx trailing");
		
		cboCourse = new JComboBox();
		contentPane.add(cboCourse, "cell 1 3,growx");
		
		cboCourse.setRenderer(new DefaultListCellRenderer(){
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
		contentPane.add(lblArea, "cell 0 4,alignx trailing");
		
		cboArea = new JComboBox();
		contentPane.add(cboArea, "cell 1 4,growx");
		
		cboArea.setRenderer(new DefaultListCellRenderer(){
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
		
		lblStudent = new JLabel("Aluno:");
		contentPane.add(lblStudent, "cell 0 5,alignx trailing");
		
		cboStudent = new JComboBox();
		contentPane.add(cboStudent, "cell 1 5,growx");
		
		cboStudent.setRenderer(new DefaultListCellRenderer(){
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
		
		lblAdvisor = new JLabel("Orientador:");
		contentPane.add(lblAdvisor, "cell 0 6,alignx trailing");
		
		cboAdvisor = new JComboBox();
		contentPane.add(cboAdvisor, "cell 1 6,growx");
		
		cboAdvisor.setRenderer(new DefaultListCellRenderer(){
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
		
		lblCoadvisor = new JLabel("Coorientador:");
		contentPane.add(lblCoadvisor, "cell 0 7,alignx trailing");
		
		cboCoadvisor = new JComboBox();
		contentPane.add(cboCoadvisor, "cell 1 7,growx");
		
		cboCoadvisor.setRenderer(new DefaultListCellRenderer(){
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
		contentPane.add(lblStatus, "cell 0 8,alignx trailing");
		
		cboStatus = new JComboBox();
		contentPane.add(cboStatus, "cell 1 8,growx");
		
		cboStatus.setRenderer(new DefaultListCellRenderer(){
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
		
		btnUpdateMonograph = new JButton("Atualizar");
		btnUpdateMonograph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				storeMonograph();
			}
		});
		contentPane.add(btnUpdateMonograph, "cell 1 9,alignx right,growy");
		initDataBindings();
	}

	@Override
	public void setPKMonograph(String pKMonograph) {
		this.updateMonographController.setPKMonograph(pKMonograph);
	}
	protected void initDataBindings() {
		BeanProperty<IUpdateMonographController, String> iUpdateMonographControllerBeanProperty = BeanProperty.create("monographTitle");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<IUpdateMonographController, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, updateMonographController, iUpdateMonographControllerBeanProperty, txtMonographTitle, jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<IUpdateMonographController, List<Course>> iUpdateMonographControllerBeanProperty_1 = BeanProperty.create("courses");
		JComboBoxBinding<Course, IUpdateMonographController, JComboBox> jComboBinding = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ, updateMonographController, iUpdateMonographControllerBeanProperty_1, cboCourse);
		jComboBinding.bind();
		//
		BeanProperty<IUpdateMonographController, Course> iUpdateMonographControllerBeanProperty_2 = BeanProperty.create("selectedCourse");
		BeanProperty<JComboBox, Object> jComboBoxBeanProperty = BeanProperty.create("selectedItem");
		AutoBinding<IUpdateMonographController, Course, JComboBox, Object> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, updateMonographController, iUpdateMonographControllerBeanProperty_2, cboCourse, jComboBoxBeanProperty);
		autoBinding_1.bind();
		//
		BeanProperty<IUpdateMonographController, Integer> iUpdateMonographControllerBeanProperty_3 = BeanProperty.create("selectedCourseIndex");
		BeanProperty<JComboBox, Integer> jComboBoxBeanProperty_1 = BeanProperty.create("selectedIndex");
		AutoBinding<IUpdateMonographController, Integer, JComboBox, Integer> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, updateMonographController, iUpdateMonographControllerBeanProperty_3, cboCourse, jComboBoxBeanProperty_1);
		autoBinding_2.bind();
		//
		BeanProperty<IUpdateMonographController, List<Area>> iUpdateMonographControllerBeanProperty_4 = BeanProperty.create("areas");
		JComboBoxBinding<Area, IUpdateMonographController, JComboBox> jComboBinding_1 = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ, updateMonographController, iUpdateMonographControllerBeanProperty_4, cboArea);
		jComboBinding_1.bind();
		//
		BeanProperty<IUpdateMonographController, Area> iUpdateMonographControllerBeanProperty_5 = BeanProperty.create("selectedArea");
		AutoBinding<IUpdateMonographController, Area, JComboBox, Object> autoBinding_3 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, updateMonographController, iUpdateMonographControllerBeanProperty_5, cboArea, jComboBoxBeanProperty);
		autoBinding_3.bind();
		//
		BeanProperty<IUpdateMonographController, Integer> iUpdateMonographControllerBeanProperty_6 = BeanProperty.create("selectedAreaIndex");
		AutoBinding<IUpdateMonographController, Integer, JComboBox, Integer> autoBinding_4 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, updateMonographController, iUpdateMonographControllerBeanProperty_6, cboArea, jComboBoxBeanProperty_1);
		autoBinding_4.bind();
		//
		BeanProperty<IUpdateMonographController, List<Student>> iUpdateMonographControllerBeanProperty_7 = BeanProperty.create("students");
		JComboBoxBinding<Student, IUpdateMonographController, JComboBox> jComboBinding_2 = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ, updateMonographController, iUpdateMonographControllerBeanProperty_7, cboStudent);
		jComboBinding_2.bind();
		//
		BeanProperty<IUpdateMonographController, Student> iUpdateMonographControllerBeanProperty_8 = BeanProperty.create("selectedStudent");
		AutoBinding<IUpdateMonographController, Student, JComboBox, Object> autoBinding_5 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, updateMonographController, iUpdateMonographControllerBeanProperty_8, cboStudent, jComboBoxBeanProperty);
		autoBinding_5.bind();
		//
		BeanProperty<IUpdateMonographController, Integer> iUpdateMonographControllerBeanProperty_9 = BeanProperty.create("selectedStudentIndex");
		AutoBinding<IUpdateMonographController, Integer, JComboBox, Integer> autoBinding_6 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, updateMonographController, iUpdateMonographControllerBeanProperty_9, cboStudent, jComboBoxBeanProperty_1);
		autoBinding_6.bind();
		//
		BeanProperty<IUpdateMonographController, List<Advisor>> iUpdateMonographControllerBeanProperty_10 = BeanProperty.create("advisors");
		JComboBoxBinding<Advisor, IUpdateMonographController, JComboBox> jComboBinding_3 = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ, updateMonographController, iUpdateMonographControllerBeanProperty_10, cboAdvisor);
		jComboBinding_3.bind();
		//
		BeanProperty<IUpdateMonographController, Advisor> iUpdateMonographControllerBeanProperty_11 = BeanProperty.create("selectedAdvisor");
		AutoBinding<IUpdateMonographController, Advisor, JComboBox, Object> autoBinding_7 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, updateMonographController, iUpdateMonographControllerBeanProperty_11, cboAdvisor, jComboBoxBeanProperty);
		autoBinding_7.bind();
		//
		BeanProperty<IUpdateMonographController, Integer> iUpdateMonographControllerBeanProperty_12 = BeanProperty.create("selectedAdvisorIndex");
		AutoBinding<IUpdateMonographController, Integer, JComboBox, Integer> autoBinding_8 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, updateMonographController, iUpdateMonographControllerBeanProperty_12, cboAdvisor, jComboBoxBeanProperty_1);
		autoBinding_8.bind();
		//
		BeanProperty<IUpdateMonographController, List<Advisor>> iUpdateMonographControllerBeanProperty_13 = BeanProperty.create("coadvisors");
		JComboBoxBinding<Advisor, IUpdateMonographController, JComboBox> jComboBinding_4 = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ, updateMonographController, iUpdateMonographControllerBeanProperty_13, cboCoadvisor);
		jComboBinding_4.bind();
		//
		BeanProperty<IUpdateMonographController, Advisor> iUpdateMonographControllerBeanProperty_14 = BeanProperty.create("selectedCoadvisor");
		AutoBinding<IUpdateMonographController, Advisor, JComboBox, Object> autoBinding_9 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, updateMonographController, iUpdateMonographControllerBeanProperty_14, cboCoadvisor, jComboBoxBeanProperty);
		autoBinding_9.bind();
		//
		BeanProperty<IUpdateMonographController, Integer> iUpdateMonographControllerBeanProperty_15 = BeanProperty.create("selectedCoadvisorIndex");
		AutoBinding<IUpdateMonographController, Integer, JComboBox, Integer> autoBinding_10 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, updateMonographController, iUpdateMonographControllerBeanProperty_15, cboCoadvisor, jComboBoxBeanProperty_1);
		autoBinding_10.bind();
		//
		BeanProperty<IUpdateMonographController, List<Status>> iUpdateMonographControllerBeanProperty_16 = BeanProperty.create("status");
		JComboBoxBinding<Status, IUpdateMonographController, JComboBox> jComboBinding_5 = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ, updateMonographController, iUpdateMonographControllerBeanProperty_16, cboStatus);
		jComboBinding_5.bind();
		//
		BeanProperty<IUpdateMonographController, Status> iUpdateMonographControllerBeanProperty_17 = BeanProperty.create("selectedStatus");
		AutoBinding<IUpdateMonographController, Status, JComboBox, Object> autoBinding_11 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, updateMonographController, iUpdateMonographControllerBeanProperty_17, cboStatus, jComboBoxBeanProperty);
		autoBinding_11.bind();
		//
		BeanProperty<IUpdateMonographController, Integer> iUpdateMonographControllerBeanProperty_18 = BeanProperty.create("selectedStatusIndex");
		AutoBinding<IUpdateMonographController, Integer, JComboBox, Integer> autoBinding_12 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, updateMonographController, iUpdateMonographControllerBeanProperty_18, cboStatus, jComboBoxBeanProperty_1);
		autoBinding_12.bind();
	}
}
