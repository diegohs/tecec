package tecec.ui;

import java.awt.Toolkit;
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

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;

import tecec.ui.contract.control.IUpdateStatusController;
import tecec.ui.contract.view.IUpdateStatusUI;
import javax.swing.border.TitledBorder;

public class UpdateStatusUI extends JDialog implements IUpdateStatusUI {
	private static final long serialVersionUID = 1L;
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
	private JPanel panel;

	/**
	 * Create the frame.
	 */
	public UpdateStatusUI(IUpdateStatusController updateStatusController) {
		this.updateStatusController = updateStatusController;

		setDefaultLookAndFeelDecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/tecec/ui/files/icone_tecec.png")));

		setModal(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 208);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Atualizar Status:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 414, 153);
		contentPane.add(panel);
		panel.setLayout(null);

		btnUpdateStatus = new JButton("Atualizar");
		btnUpdateStatus.setBounds(329, 113, 75, 29);
		panel.add(btnUpdateStatus);

				txtStatusDescription = new JTextField();
				txtStatusDescription.setBounds(74, 42, 330, 20);
				panel.add(txtStatusDescription);
				txtStatusDescription.setColumns(10);

						JLabel lblNome = new JLabel("Descri\u00E7\u00E3o:");
						lblNome.setBounds(10, 45, 81, 14);
						panel.add(lblNome);
		btnUpdateStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				storeStatus();
			}
		});
		initDataBindings();
	}
	protected void initDataBindings() {
		BeanProperty<IUpdateStatusController, String> iUpdateStatusControllerBeanProperty = BeanProperty.create("statusDescription");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<IUpdateStatusController, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, updateStatusController, iUpdateStatusControllerBeanProperty, txtStatusDescription, jTextFieldBeanProperty);
		autoBinding.bind();
	}

	@Override
	public void refresh() {
		updateStatusController.refresh();
	}
}
