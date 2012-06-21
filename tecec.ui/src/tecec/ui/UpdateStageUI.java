package tecec.ui;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tecec.contract.RuleViolation;
import tecec.ui.contract.control.IUpdateStageController;
import tecec.ui.contract.view.IUpdateStageUI;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import javax.swing.border.TitledBorder;

public class UpdateStageUI extends JDialog implements IUpdateStageUI {

	private static final long serialVersionUID = 1L;
	private IUpdateStageController updateStageController;


	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JComboBox comboBox;

	private void updateStage(){
		RuleViolation violation = this.updateStageController.getUpdateViolation();

		if (violation != null) {
			JOptionPane.showMessageDialog(this, violation.getDescription(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
		else{
			try {
				this.updateStageController.updateStage();
				this.setVisible(false);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e, "ERRO", JOptionPane.ERROR_MESSAGE);
			}
		}
	}


	public UpdateStageUI(IUpdateStageController updateStageController) {
		if (updateStageController == null)
			throw new IllegalArgumentException ("updateStageController");

		this.updateStageController = updateStageController;
		setDefaultLookAndFeelDecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/tecec/ui/files/icone_tecec.png")));

		setModal(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);

		setBounds(100, 100, 450, 230);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Atualizar Est\u00E1gio:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 11, 414, 170);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JButton btnNewButton = new JButton("Atualizar");
				btnNewButton.setBounds(329, 136, 75, 23);
				panel.add(btnNewButton);
				{
					JLabel lblNome = new JLabel("Nome:");
					lblNome.setBounds(13, 47, 57, 14);
					panel.add(lblNome);
				}
				{
					textField = new JTextField();
					textField.setBounds(64, 44, 337, 20);
					panel.add(textField);
					textField.setColumns(10);
				}
				{
					JLabel lblAno = new JLabel("Ano:");
					lblAno.setBounds(13, 93, 57, 14);
					panel.add(lblAno);
				}
				{
					comboBox = new JComboBox();
					comboBox.setBounds(64, 90, 337, 20);
					panel.add(comboBox);
					comboBox.setModel(new DefaultComboBoxModel(new String[] {"2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099"}));
				}
				btnNewButton.addActionListener(new ActionListener () {

					@Override
					public void actionPerformed(ActionEvent e) {
						updateStage();

					}

				});
			}
		}
		initDataBindings();
	}

	@Override
	public void setpKStage(String pKStage) {
		this.updateStageController.setPKStage(pKStage);

	}
	protected void initDataBindings() {
		BeanProperty<IUpdateStageController, String> iUpdateStageControllerBeanProperty = BeanProperty.create("stageName");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<IUpdateStageController, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, updateStageController, iUpdateStageControllerBeanProperty, textField, jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<IUpdateStageController, String> iUpdateStageControllerBeanProperty_1 = BeanProperty.create("stageYear");
		BeanProperty<JComboBox, Object> jComboBoxBeanProperty = BeanProperty.create("selectedItem");
		AutoBinding<IUpdateStageController, String, JComboBox, Object> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, updateStageController, iUpdateStageControllerBeanProperty_1, comboBox, jComboBoxBeanProperty);
		autoBinding_1.bind();
	}


	@Override
	public void refresh() {
		updateStageController.refresh();
	}
}
