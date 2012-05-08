package tecec.ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import tecec.contract.RuleViolation;
import tecec.ui.contract.control.INewAdvisorController;
import tecec.ui.contract.view.INewAdvisorUI;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewAdvisorUI extends JDialog implements INewAdvisorUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private INewAdvisorController newAdvisorController;

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
	}

	private void createAdvisor() {
		try {
			RuleViolation violation = this.newAdvisorController
					.getCreationViolation();

			if (violation == null) {
				this.newAdvisorController.createAdvisor();

				this.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(this, violation.getDescription(),
						"Erro", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e, "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtEmail;

	/**
	 * Create the dialog.
	 */
	public NewAdvisorUI(INewAdvisorController newAdvisorController) {
		this.newAdvisorController = newAdvisorController;

		setModal(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow][]",
				"[][][][][grow][][]"));
		{
			JLabel lblNewLabel = new JLabel("Nome:");
			contentPanel.add(lblNewLabel, "flowx,cell 1 1");
		}
		{
			JLabel lblNewLabel_1 = new JLabel("E-mail:");
			contentPanel.add(lblNewLabel_1, "flowx,cell 1 3");
		}
		{
			txtName = new JTextField();
			contentPanel.add(txtName, "cell 1 1,growx");
			txtName.setColumns(10);
		}
		{
			txtEmail = new JTextField();
			contentPanel.add(txtEmail, "cell 1 3,growx");
			txtEmail.setColumns(10);
		}
		{
			JButton btnNewCourse = new JButton("Adicionar");
			btnNewCourse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					createAdvisor();
				}
			});
			contentPanel.add(btnNewCourse, "flowx,cell 1 5,alignx right");
		}
		initDataBindings();
	}

	protected void initDataBindings() {
		BeanProperty<INewAdvisorController, String> iNewAdvisorControllerBeanProperty = BeanProperty
				.create("advisorName");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty
				.create("text");
		AutoBinding<INewAdvisorController, String, JTextField, String> autoBinding = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						newAdvisorController,
						iNewAdvisorControllerBeanProperty, txtName,
						jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<INewAdvisorController, String> iNewAdvisorControllerBeanProperty_1 = BeanProperty
				.create("advisorEmail");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_1 = BeanProperty
				.create("text");
		AutoBinding<INewAdvisorController, String, JTextField, String> autoBinding_1 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						newAdvisorController,
						iNewAdvisorControllerBeanProperty_1, txtEmail,
						jTextFieldBeanProperty_1);
		autoBinding_1.bind();
	}
}
