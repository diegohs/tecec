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
	public void setPKMonograph(String pKMonograph) {		
		this.updateMonographController.setPKMonograph(pKMonograph);
	}
	
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

	/**
	 * Create the frame.
	 */
	public UpdateMonographUI(IUpdateMonographController updateMonographController) {
		this.updateMonographController = updateMonographController;

		setModal(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 436, 245);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow][grow]",
				"[grow][][56.00][29.00][grow]"));

		JLabel lblNewLabel = new JLabel("Atualizar Monograph");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblNewLabel, "cell 1 1,alignx center");

		JLabel lblNome = new JLabel("Título:");
		contentPane.add(lblNome, "flowx,cell 1 2");

		txtMonographTitle = new JTextField();
		contentPane.add(txtMonographTitle, "cell 1 2,growx");
		txtMonographTitle.setColumns(10);
		
		btnUpdateMonograph = new JButton("Atualizar");
		btnUpdateMonograph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				storeMonograph();
			}
		});
		contentPane.add(btnUpdateMonograph, "cell 1 3,alignx center,growy");
		initDataBindings();
	}
	protected void initDataBindings() {
		BeanProperty<IUpdateMonographController, String> iUpdateMonographControllerBeanProperty = BeanProperty.create("monographTitle");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<IUpdateMonographController, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, updateMonographController, iUpdateMonographControllerBeanProperty, txtMonographTitle, jTextFieldBeanProperty);
		autoBinding.bind();
	}
}
