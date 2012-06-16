package tecec.ui;

import javax.swing.JDialog;import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tecec.ui.contract.control.INewStatusController;
import tecec.ui.contract.view.INewStatusUI;
import tecec.contract.RuleViolation;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import org.jdesktop.beansbinding.*;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

public class NewStatusUI extends JDialog implements INewStatusUI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private tecec.ui.contract.control.INewStatusController newStatusController;
		
	@Override
	public void setVisible(boolean visible){
		super.setVisible(visible);
	}
	
	private void storeStatus() {
		try {
			RuleViolation violation = this.newStatusController
					.getCreationViolation();
			if (violation != null) {
				JOptionPane.showMessageDialog(this, violation.getDescription(),
						"Erro", JOptionPane.ERROR_MESSAGE);
			} else {
				this.newStatusController.createStatus();
				
				this.setVisible(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e, "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private JPanel contentPane;
	private JTextField txtStatusDescription;
	private JButton btnCreateStatus;
	private JPanel panel;

	/**
	 * Create the frame.
	 */
	public NewStatusUI(
			tecec.ui.contract.control.INewStatusController newStatusController) {
		if (newStatusController == null) {
			throw new IllegalArgumentException("newStatusController");
		}

		this.newStatusController = newStatusController;
		
		setDefaultLookAndFeelDecorated(true);

		setLocationByPlatform(true);
		setModal(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 208);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Cadastrar Novo Status:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 414, 153);
		contentPane.add(panel);
		panel.setLayout(null);
		
				txtStatusDescription = new JTextField();
				txtStatusDescription.setBounds(74, 42, 330, 20);
				panel.add(txtStatusDescription);
				txtStatusDescription.setColumns(10);
				
						JLabel lblNome = new JLabel("Descri\u00E7\u00E3o:");
						lblNome.setBounds(10, 45, 81, 14);
						panel.add(lblNome);
						
								btnCreateStatus = new JButton("Cadastrar");
								btnCreateStatus.setBounds(323, 114, 81, 28);
								panel.add(btnCreateStatus);
								btnCreateStatus.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {
										storeStatus();
									}
								});
		initDataBindings();
	}

	protected void initDataBindings() {
		BeanProperty<INewStatusController, String> iNewStatusControllerBeanProperty = BeanProperty
				.create("statusDescription");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty
				.create("text");
		AutoBinding<INewStatusController, String, JTextField, String> autoBinding = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						newStatusController, iNewStatusControllerBeanProperty,
						txtStatusDescription, jTextFieldBeanProperty);
		autoBinding.bind();
	}
}
