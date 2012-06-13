package tecec.ui;

import java.awt.BorderLayout;
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
import tecec.ui.contract.control.INewActivityController;
import tecec.ui.contract.view.INewActivityUI;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class NewActivityUI extends JDialog implements INewActivityUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private tecec.ui.contract.control.INewActivityController newActivityController;

	private void insertActivity() {
		RuleViolation violation = this.newActivityController
				.getInsertViolation();

		if (violation != null) {
			JOptionPane.showMessageDialog(this, violation.getDescription(),
					"ERRO", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				this.newActivityController.insertActivity();
				
				this.setVisible(false);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e, "ERRO",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTitle;
	private JTextField txtDescription;
	private JFormattedTextField txtDueDate;
	private JButton btnNewActivity;
	private JLabel lblAdicionarNovaAtividade;

	public NewActivityUI(INewActivityController newActivityController) {
		this.newActivityController = newActivityController;
		
		setDefaultLookAndFeelDecorated(true);

		setModal(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow][]", "[grow][][][][][][][][][grow]"));
		{
			lblAdicionarNovaAtividade = new JLabel("Adicionar nova atividade:");
			lblAdicionarNovaAtividade.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
			contentPanel.add(lblAdicionarNovaAtividade, "cell 1 1,alignx center");
		}
		{
			JLabel lblNewLabel = new JLabel("T\u00EDtulo:");
			contentPanel.add(lblNewLabel, "flowx,cell 1 2");
		}
		{
			txtTitle = new JTextField();
			contentPanel.add(txtTitle, "cell 1 2,growx");
			txtTitle.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Descri\u00E7\u00E3o:");
			contentPanel.add(lblNewLabel_1, "flowx,cell 1 4");
		}
		{
			txtDescription = new JTextField();
			contentPanel.add(txtDescription, "cell 1 4,growx");
			txtDescription.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Data de Entrega:");
			contentPanel.add(lblNewLabel_2, "flowx,cell 1 6");
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
				throw new RuntimeException("Erro ao construir controle de data do form NewActivityUI");
			}
			
			contentPanel.add(txtDueDate, "cell 1 6,growx");
		}
		{
			btnNewActivity = new JButton("Cadastrar");
			btnNewActivity.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					insertActivity();
				}
			});
			contentPanel.add(btnNewActivity, "cell 1 8,alignx right");
		}
		initDataBindings();
	}

	protected void initDataBindings() {
		BeanProperty<INewActivityController, String> iNewActivityControllerBeanProperty = BeanProperty
				.create("activityTitle");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty
				.create("text");
		AutoBinding<INewActivityController, String, JTextField, String> autoBinding = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						newActivityController,
						iNewActivityControllerBeanProperty, txtTitle,
						jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<INewActivityController, String> iNewActivityControllerBeanProperty_1 = BeanProperty
				.create("activityDescription");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_1 = BeanProperty
				.create("text");
		AutoBinding<INewActivityController, String, JTextField, String> autoBinding_1 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						newActivityController,
						iNewActivityControllerBeanProperty_1, txtDescription,
						jTextFieldBeanProperty_1);
		autoBinding_1.bind();
		//
		BeanProperty<INewActivityController, String> iNewActivityControllerBeanProperty_2 = BeanProperty
				.create("activityDueDate");
		BeanProperty<JFormattedTextField, String> jFormattedTextFieldBeanProperty = BeanProperty
				.create("text");
		AutoBinding<INewActivityController, String, JFormattedTextField, String> autoBinding_2 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						newActivityController,
						iNewActivityControllerBeanProperty_2, txtDueDate,
						jFormattedTextFieldBeanProperty);
		autoBinding_2.bind();
		//
		BeanProperty<INewActivityController, Boolean> iNewActivityControllerBeanProperty_3 = BeanProperty
				.create("canInsert");
		BeanProperty<JButton, Boolean> jButtonBeanProperty = BeanProperty
				.create("enabled");
		AutoBinding<INewActivityController, Boolean, JButton, Boolean> autoBinding_3 = Bindings
				.createAutoBinding(UpdateStrategy.READ, newActivityController,
						iNewActivityControllerBeanProperty_3, btnNewActivity,
						jButtonBeanProperty);
		autoBinding_3.bind();
	}
}
