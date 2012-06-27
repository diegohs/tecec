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
import tecec.ui.contract.control.IUpdateCourseController;
import tecec.ui.contract.view.IUpdateCourseUI;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import javax.swing.border.TitledBorder;

public class UpdateCourseUI extends JDialog implements IUpdateCourseUI {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private IUpdateCourseController updateCourseController;
	private JTextField textField;
	private JComboBox comboTurn;
	private JComboBox comboYear;

	private void updateCourse(){
		RuleViolation violation = this.updateCourseController.getUpdateViolation();

		if (violation != null) {
			JOptionPane.showMessageDialog(this, violation.getDescription(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
		else{
			try {
				this.updateCourseController.updateCourse();
				this.setVisible(false);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e, "ERRO", JOptionPane.ERROR_MESSAGE);
			}
		}
	}


	public UpdateCourseUI(IUpdateCourseController updateCourseController) {
		if (updateCourseController == null)
			throw new IllegalArgumentException ("updateCourseController");

		this.updateCourseController = updateCourseController;
		setDefaultLookAndFeelDecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/tecec/ui/files/icone_tecec.png")));

		setModal(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);

		setBounds(100, 100, 450, 298);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
		}
			{
				JPanel panel = new JPanel();
				panel.setBorder(new TitledBorder(null, "Atualizar Curso:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel.setBounds(10, 11, 414, 239);
				contentPanel.add(panel);
				panel.setLayout(null);
				{
					JLabel lblNome = new JLabel("Nome:");
					lblNome.setBounds(39, 44, 76, 14);
					panel.add(lblNome);
				}
				{
					textField = new JTextField();
					textField.setBounds(84, 41, 320, 20);
					panel.add(textField);
					textField.setColumns(10);
				}
				{
					JLabel lblTurno = new JLabel("Turno:");
					lblTurno.setBounds(39, 93, 50, 14);
					panel.add(lblTurno);
				}
				{
					comboTurn = new JComboBox();
					comboTurn.setBounds(84, 90, 320, 20);
					panel.add(comboTurn);
					comboTurn.setModel(new DefaultComboBoxModel(new String[] {"Noturno", "Vespertino", "Integral", "EAD", "Diurno"}));
				}
				{
					JLabel lblAno = new JLabel("Ano:");
					lblAno.setBounds(48, 142, 79, 14);
					panel.add(lblAno);
				}
				{
					comboYear = new JComboBox();
					comboYear.setBounds(84, 139, 320, 20);
					panel.add(comboYear);
					comboYear.setModel(new DefaultComboBoxModel(new String[] {"2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099"}));
				}
				JButton btnAtualizar = new JButton("Atualizar");
				btnAtualizar.setBounds(271, 205, 133, 23);
				panel.add(btnAtualizar);
				btnAtualizar.addActionListener(new ActionListener () {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						updateCourse();
					}

				});
			}
		initDataBindings();
	}

	@Override
	public void setpKCourse(String pKCourse) {
		this.updateCourseController.setPKCourse(pKCourse);

	}
	protected void initDataBindings() {
		BeanProperty<IUpdateCourseController, String> iUpdateCourseControllerBeanProperty = BeanProperty.create("courseName");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<IUpdateCourseController, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, updateCourseController, iUpdateCourseControllerBeanProperty, textField, jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<IUpdateCourseController, String> iUpdateCourseControllerBeanProperty_1 = BeanProperty.create("courseTurn");
		BeanProperty<JComboBox, Object> jComboBoxBeanProperty = BeanProperty.create("selectedItem");
		AutoBinding<IUpdateCourseController, String, JComboBox, Object> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, updateCourseController, iUpdateCourseControllerBeanProperty_1, comboTurn, jComboBoxBeanProperty);
		autoBinding_1.bind();
		//
		BeanProperty<IUpdateCourseController, String> iUpdateCourseControllerBeanProperty_2 = BeanProperty.create("courseYear");
		AutoBinding<IUpdateCourseController, String, JComboBox, Object> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, updateCourseController, iUpdateCourseControllerBeanProperty_2, comboYear, jComboBoxBeanProperty);
		autoBinding_2.bind();
	}

	@Override
	public void refresh() {
		updateCourseController.refresh();
	}
}
