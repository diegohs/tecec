package tecec.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
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
		setBounds(100, 100, 347, 297);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		//contentPane.setLayout(new MigLayout("", "[grow][grow][grow]","[grow][][56.00][29.00][grow]"));
		contentPane.setLayout(new MigLayout("", "[][grow][]", "[][][][][][][][][][][]"));
				
						JLabel lblTitle = new JLabel("Atualizar Monografia");
						lblTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
						contentPane.add(lblTitle, "cell 1 0,alignx center");
		
				JLabel lblMonographTitle = new JLabel("Título:");
				contentPane.add(lblMonographTitle, "cell 0 2,alignx right");
		
				txtMonographTitle = new JTextField();
				contentPane.add(txtMonographTitle, "cell 1 2,growx");
				txtMonographTitle.setColumns(10);
		
		lblCourse = new JLabel("Curso:");
		contentPane.add(lblCourse, "cell 0 3,alignx trailing");
		
		cboCourse = new JComboBox();
		contentPane.add(cboCourse, "cell 1 3,growx");
		
		lblArea = new JLabel("Área:");
		contentPane.add(lblArea, "cell 0 4,alignx trailing");
		
		cboArea = new JComboBox();
		contentPane.add(cboArea, "cell 1 4,growx");
		
		lblStudent = new JLabel("Aluno:");
		contentPane.add(lblStudent, "cell 0 5,alignx trailing");
		
		cboStudent = new JComboBox();
		contentPane.add(cboStudent, "cell 1 5,growx");
		
		lblAdvisor = new JLabel("Orientador:");
		contentPane.add(lblAdvisor, "cell 0 6,alignx trailing");
		
		cboAdvisor = new JComboBox();
		contentPane.add(cboAdvisor, "cell 1 6,growx");
		
		lblCoadvisor = new JLabel("Coorientador:");
		contentPane.add(lblCoadvisor, "cell 0 7,alignx trailing");
		
		cboCoadvisor = new JComboBox();
		contentPane.add(cboCoadvisor, "cell 1 7,growx");
		
		lblStatus = new JLabel("Status:");
		contentPane.add(lblStatus, "cell 0 8,alignx trailing");
		
		cboStatus = new JComboBox();
		contentPane.add(cboStatus, "cell 1 8,growx");
		
		btnUpdateMonograph = new JButton("Atualizar");
		btnUpdateMonograph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				storeMonograph();
			}
		});
		contentPane.add(btnUpdateMonograph, "cell 1 9,alignx right,growy");
		initDataBindings();
	}
	protected void initDataBindings() {
		
	}

	@Override
	public void setPKMonograph(String pKMonograph) {
		// TODO Auto-generated method stub
		
	}
}
