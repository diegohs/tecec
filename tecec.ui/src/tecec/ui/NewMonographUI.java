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
import tecec.dto.Course;
import tecec.ui.contract.control.INewMonographController;
import tecec.ui.contract.view.INewMonographUI;
import javax.swing.JComboBox;

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

		lblAluno = new JLabel("Aluno:");
		contentPane.add(lblAluno, "cell 0 4,alignx trailing");

		cboAluno = new JComboBox();
		contentPane.add(cboAluno, "cell 1 4,growx");

		lblOrientador = new JLabel("Orientador:");
		contentPane.add(lblOrientador, "cell 0 5,alignx trailing");

		cboOrientador = new JComboBox();
		contentPane.add(cboOrientador, "cell 1 5,growx");

		lblCoorientador = new JLabel("Coorientador");
		contentPane.add(lblCoorientador, "cell 0 6,alignx trailing");

		cboCoorientador = new JComboBox();
		contentPane.add(cboCoorientador, "cell 1 6,growx");

		lblStatus = new JLabel("Status:");
		contentPane.add(lblStatus, "cell 0 7,alignx trailing");

		cboStatus = new JComboBox();
		contentPane.add(cboStatus, "cell 1 7,growx");

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
		BeanProperty<INewMonographController, String> iNewMonographControllerBeanProperty = BeanProperty
				.create("monographTitle");
		
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty
				.create("text");
		
		AutoBinding<INewMonographController, String, JTextField, String> autoBinding = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						newMonographController, iNewMonographControllerBeanProperty,
						txtMonographTitle, jTextFieldBeanProperty);
		
		autoBinding.bind();
	}
}
