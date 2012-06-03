package tecec.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tecec.contract.RuleViolation;

import tecec.ui.contract.control.INewStageController;
import tecec.ui.contract.view.INewStageUI;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

public class NewStageUI extends JDialog implements INewStageUI {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private INewStageController newStageController;

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
	}

	private void createStage() {
		try {
			RuleViolation violation = this.newStageController
					.getCreationViolation();

			if (violation == null) {
				this.newStageController.createStage();

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
	private JTextField textField;
	private JLabel lblAno;
	private JComboBox comboBox;
	private JButton btnCadastrar;

	
	/**
	 * Create the dialog.
	 */
	public NewStageUI(INewStageController newStageController) {
		
		if (newStageController == null)
			throw new IllegalArgumentException ("newStageController");
		
		this.newStageController = newStageController;
		
		setDefaultLookAndFeelDecorated(true);

		setModal(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][][180.00,grow]", "[][][][][][][][][]"));
		{
			JLabel lblNome = new JLabel("Nome:");
			contentPanel.add(lblNome, "cell 1 1,alignx trailing");
		}
		{
			textField = new JTextField();
			contentPanel.add(textField, "cell 2 1,growx");
			textField.setColumns(10);
		}
		{
			lblAno = new JLabel("Ano:");
			contentPanel.add(lblAno, "cell 1 3,alignx trailing");
		}
		{
			comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099"}));
			contentPanel.add(comboBox, "cell 2 3,growx");
		}
		{
			btnCadastrar = new JButton("Cadastrar");
			btnCadastrar.addActionListener(new ActionListener () {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					createStage();
				}
				
			});
			contentPanel.add(btnCadastrar, "cell 2 8,alignx right");
		}
		initDataBindings();
	}
	protected void initDataBindings() {
		BeanProperty<INewStageController, String> iNewStageControllerBeanProperty = BeanProperty.create("stageName");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<INewStageController, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, newStageController, iNewStageControllerBeanProperty, textField, jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<INewStageController, String> iNewStageControllerBeanProperty_1 = BeanProperty.create("stageYear");
		BeanProperty<JComboBox, Object> jComboBoxBeanProperty = BeanProperty.create("selectedItem");
		AutoBinding<INewStageController, String, JComboBox, Object> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, newStageController, iNewStageControllerBeanProperty_1, comboBox, jComboBoxBeanProperty);
		autoBinding_1.bind();
	}
}
