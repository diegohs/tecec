package tecec.ui;


import javax.swing.JDialog;import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tecec.ui.contract.control.INewTurnController;
import tecec.ui.contract.view.INewTurnUI;
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

public class NewTurnUI extends JDialog implements INewTurnUI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private tecec.ui.contract.control.INewTurnController newTurnController;
		
	@Override
	public void setVisible(boolean visible){
		super.setVisible(visible);
	}
	
	private void storeTurn() {
		try {
			RuleViolation violation = this.newTurnController
					.getCreationViolation();
			if (violation != null) {
				JOptionPane.showMessageDialog(this, violation.getDescription(),
						"Erro", JOptionPane.ERROR_MESSAGE);
			} else {
				this.newTurnController.createTurn();
				
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
	private JButton btnCreateTurn;

	/**
	 * Create the frame.
	 */
	public NewTurnUI(
			tecec.ui.contract.control.INewTurnController newTurnController) {
		if (newTurnController == null) {
			throw new IllegalArgumentException("newTurnController");
		}

		this.newTurnController = newTurnController;
		
		setDefaultLookAndFeelDecorated(true);

		setLocationByPlatform(true);
		setModal(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 436, 245);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow][grow]",
				"[grow][][56.00][29.00][grow]"));

		JLabel lblNewLabel = new JLabel("Cadastrar Novo Curso");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblNewLabel, "cell 1 1,alignx center");

		JLabel lblNome = new JLabel("Nome:");
		contentPane.add(lblNome, "flowx,cell 1 2");

		txtTurnName = new JTextField();
		contentPane.add(txtTurnName, "cell 1 2,growx");
		txtTurnName.setColumns(10);

		btnCreateTurn = new JButton("Cadastrar");
		btnCreateTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				storeTurn();
			}
		});
		contentPane.add(btnCreateTurn, "cell 1 3,alignx center,growy");
		initDataBindings();
	}

	protected void initDataBindings() {
		BeanProperty<INewTurnController, String> iNewTurnControllerBeanProperty = BeanProperty
				.create("turnName");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty
				.create("text");
		AutoBinding<INewTurnController, String, JTextField, String> autoBinding = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE,
						newTurnController, iNewTurnControllerBeanProperty,
						txtTurnName, jTextFieldBeanProperty);
		autoBinding.bind();
	}
}
