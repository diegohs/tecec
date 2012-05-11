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

import tecec.ui.contract.control.IUpdateStatusController;
import tecec.ui.contract.view.IUpdateStatusUI;

public class UpdateStatusUI extends JDialog implements IUpdateStatusUI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private IUpdateStatusController updateStatusController;

	@Override
	public void setpKStatus(String pKStatus) {		
		this.updateStatusController.setPKStatus(pKStatus);
	}
	
	@Override
	public void setVisible(boolean visible){
		super.setVisible(visible);
	}
	
	private void storeStatus() {
		try {
			RuleViolation violation = this.updateStatusController
					.getUpdateViolation();

			if (violation != null) {
				JOptionPane.showMessageDialog(this, violation.getDescription(),
						"Erro", JOptionPane.ERROR_MESSAGE);
			} else {
				this.updateStatusController.updateStatus();
				
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
	private JButton btnUpdateStatus;

	/**
	 * Create the frame.
	 */
	public UpdateStatusUI(IUpdateStatusController updateStatusController) {
		this.updateStatusController = updateStatusController;

		setModal(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 436, 245);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow][grow]",
				"[grow][][56.00][29.00][grow]"));

		JLabel lblNewLabel = new JLabel("Atualizar Status");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblNewLabel, "cell 1 1,alignx center");

		JLabel lblNome = new JLabel("Descrição:");
		contentPane.add(lblNome, "flowx,cell 1 2");

		txtStatusDescription = new JTextField();
		contentPane.add(txtStatusDescription, "cell 1 2,growx");
		txtStatusDescription.setColumns(10);
		
		btnUpdateStatus = new JButton("Atualizar");
		btnUpdateStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				storeStatus();
			}
		});
		contentPane.add(btnUpdateStatus, "cell 1 3,alignx center,growy");
		initDataBindings();
	}
	protected void initDataBindings() {
		BeanProperty<IUpdateStatusController, String> iUpdateStatusControllerBeanProperty = BeanProperty.create("statusDescription");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<IUpdateStatusController, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, updateStatusController, iUpdateStatusControllerBeanProperty, txtStatusDescription, jTextFieldBeanProperty);
		autoBinding.bind();
	}
}
