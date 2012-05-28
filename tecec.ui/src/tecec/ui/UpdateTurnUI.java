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

import tecec.ui.contract.control.IUpdateTurnController;
import tecec.ui.contract.view.IUpdateTurnUI;

public class UpdateTurnUI extends JDialog implements IUpdateTurnUI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IUpdateTurnController updateTurnController;

	@Override
	public void setpKTurn(String pKTurn) {
		this.updateTurnController.setPKTurn(pKTurn);
	}
	
	@Override
	public void setVisible(boolean visible){
		super.setVisible(visible);
	}
	
	private void storeTurn() {
		try {
			RuleViolation violation = this.updateTurnController
					.getUpdateViolation();

			if (violation != null) {
				JOptionPane.showMessageDialog(this, violation.getDescription(),
						"Erro", JOptionPane.ERROR_MESSAGE);
			} else {
				this.updateTurnController.updateTurn();
				
				this.setVisible(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e, "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private JPanel contentPane;
	private JTextField txtTurnName;
	private JButton btnUpdateTurn;

	/**
	 * Create the frame.
	 */
	public UpdateTurnUI(IUpdateTurnController updateTurnController) {
		this.updateTurnController = updateTurnController;
		
		setDefaultLookAndFeelDecorated(true);

		setModal(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 436, 245);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow][grow]",
				"[grow][][56.00][29.00][grow]"));

		JLabel lblNewLabel = new JLabel("Atualizar Curso");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblNewLabel, "cell 1 1,alignx center");

		JLabel lblNome = new JLabel("Nome:");
		contentPane.add(lblNome, "flowx,cell 1 2");

		txtTurnName = new JTextField();
		contentPane.add(txtTurnName, "cell 1 2,growx");
		txtTurnName.setColumns(10);

		btnUpdateTurn = new JButton("Atualizar");
		btnUpdateTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				storeTurn();
			}
		});
		contentPane.add(btnUpdateTurn, "cell 1 3,alignx center,growy");
		initDataBindings();
	}
	protected void initDataBindings() {
		BeanProperty<IUpdateTurnController, String> iUpdateTurnControllerBeanProperty = BeanProperty.create("turnName");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<IUpdateTurnController, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, updateTurnController, iUpdateTurnControllerBeanProperty, txtTurnName, jTextFieldBeanProperty);
		autoBinding.bind();
	}
}
