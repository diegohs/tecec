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
		setBounds(100, 100, 430, 169);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[][56.00][29.00]"));

		JLabel lblNewLabel = new JLabel("Cadastrar Novo Status");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblNewLabel, "cell 0 0,alignx center");

		JLabel lblNome = new JLabel("Descrição:");
		contentPane.add(lblNome, "flowx,cell 0 1");

		txtStatusDescription = new JTextField();
		contentPane.add(txtStatusDescription, "cell 0 1,growx");
		txtStatusDescription.setColumns(10);

		btnCreateStatus = new JButton("Cadastrar");
		btnCreateStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				storeStatus();
			}
		});
		contentPane.add(btnCreateStatus, "cell 0 2,alignx right,growy");
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
