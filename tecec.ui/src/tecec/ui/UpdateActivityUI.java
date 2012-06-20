package tecec.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;

import tecec.contract.RuleViolation;
import tecec.ui.contract.control.IUpdateActivityController;
import tecec.ui.contract.view.IUpdateActivityUI;
import java.awt.Font;

public class UpdateActivityUI extends JDialog implements IUpdateActivityUI {

	private static final long serialVersionUID = 1L;
	private tecec.ui.contract.control.IUpdateActivityController updateActivityController;

	private void updateActivity() {
		RuleViolation violation = updateActivityController.getUpdateViolation();

		if (violation != null) {
			JOptionPane.showMessageDialog(this, violation.getDescription(),
					"ERRO", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				this.updateActivityController.updateActivity();
				
				setVisible(false);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e, "ERRO",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void setPKActivity(String pKActivity) {
		this.updateActivityController.setPKActivity(pKActivity);
	}

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTitle;
	private JTextField txtDescription;
	private JFormattedTextField txtDueDate;
	private JButton btnUpdateActivity;
	private JLabel lblAtualizarAtividade;

	public UpdateActivityUI(IUpdateActivityController updateActivityController) {
		this.updateActivityController = updateActivityController;
		
		setDefaultLookAndFeelDecorated(true);

		setModal(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][][grow][]", "[grow][][][][][][][][][][grow]"));
		{
			lblAtualizarAtividade = new JLabel("Atualizar Atividade");
			lblAtualizarAtividade.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
			contentPanel.add(lblAtualizarAtividade, "cell 2 1,alignx center");
		}
		{
			JLabel lblNewLabel = new JLabel("T\u00EDtulo:");
			contentPanel.add(lblNewLabel, "flowx,cell 1 3");
		}
		{
			txtTitle = new JTextField();
			contentPanel.add(txtTitle, "cell 2 3,growx");
			txtTitle.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Descri\u00E7\u00E3o:");
			contentPanel.add(lblNewLabel_1, "flowx,cell 2 5");
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Data de Entrega:");
			contentPanel.add(lblNewLabel_2, "flowx,cell 2 7");
		}
		{
			txtDescription = new JTextField();
			contentPanel.add(txtDescription, "cell 2 5,growx");
			txtDescription.setColumns(10);
		}
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"dd-MM-yyyy");

			txtDueDate = new JFormattedTextField(dateFormat);
			txtDueDate.setColumns(10);
			txtDueDate.setFocusLostBehavior(JFormattedTextField.COMMIT);

			try {
				MaskFormatter mask = new MaskFormatter("##/##/####");

				mask.install(txtDueDate);
			} catch (Exception e) {
				throw new RuntimeException(
						"Erro ao construir controle de data do form NewActivityUI");
			}

			contentPanel.add(txtDueDate, "cell 2 7,growx");
		}
		{
			btnUpdateActivity = new JButton("Atualizar");
			btnUpdateActivity.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					updateActivity();
				}
			});
			contentPanel.add(btnUpdateActivity, "cell 2 9,alignx right");
		}
		initDataBindings();
	}

	protected void initDataBindings() {
		BeanProperty<IUpdateActivityController, String> iUpdateActivityControllerBeanProperty = BeanProperty
				.create("activityTitle");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty
				.create("text");
		AutoBinding<IUpdateActivityController, String, JTextField, String> autoBinding = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						updateActivityController,
						iUpdateActivityControllerBeanProperty, txtTitle,
						jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<IUpdateActivityController, String> iUpdateActivityControllerBeanProperty_1 = BeanProperty
				.create("activityDescription");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_1 = BeanProperty
				.create("text");
		AutoBinding<IUpdateActivityController, String, JTextField, String> autoBinding_1 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						updateActivityController,
						iUpdateActivityControllerBeanProperty_1,
						txtDescription, jTextFieldBeanProperty_1);
		autoBinding_1.bind();
		//
		BeanProperty<IUpdateActivityController, String> iUpdateActivityControllerBeanProperty_2 = BeanProperty
				.create("activityDueDate");
		BeanProperty<JFormattedTextField, String> jFormattedTextFieldBeanProperty = BeanProperty
				.create("text");
		AutoBinding<IUpdateActivityController, String, JFormattedTextField, String> autoBinding_2 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						updateActivityController,
						iUpdateActivityControllerBeanProperty_2, txtDueDate,
						jFormattedTextFieldBeanProperty);
		autoBinding_2.bind();
		//
		BeanProperty<IUpdateActivityController, Boolean> iUpdateActivityControllerBeanProperty_3 = BeanProperty
				.create("canUpdate");
		BeanProperty<JButton, Boolean> jButtonBeanProperty = BeanProperty
				.create("enabled");
		AutoBinding<IUpdateActivityController, Boolean, JButton, Boolean> autoBinding_3 = Bindings
				.createAutoBinding(UpdateStrategy.READ,
						updateActivityController,
						iUpdateActivityControllerBeanProperty_3,
						btnUpdateActivity, jButtonBeanProperty);
		autoBinding_3.bind();
	}

	@Override
	public void refresh() {
		updateActivityController.refresh();
	}
}
