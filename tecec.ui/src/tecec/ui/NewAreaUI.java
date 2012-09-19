package tecec.ui;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;

import tecec.contract.RuleViolation;
import tecec.ui.contract.control.INewAreaController;
import tecec.ui.contract.view.INewAreaUI;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.util.List;
import tecec.dto.Area;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.SwingBindings;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

public class NewAreaUI extends JDialog implements INewAreaUI {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private void createArea() {
		RuleViolation violation = this.newAreaController.getCreationViolation();

		if (violation != null) {
			JOptionPane.showMessageDialog(this, violation.getDescription(),
					"ERRO", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				this.newAreaController.createArea();

				this.setVisible(false);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e, "ERRO",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private INewAreaController newAreaController;
	private JTextField txtAreaName;
	private JTextField txtDescription;
	private JButton btnNewArea;
	private JComboBox cboAreas;

	/**
	 * Create the dialog.
	 */
	public NewAreaUI(INewAreaController newAreaController) {
		this.newAreaController = newAreaController;

		JDialog.setDefaultLookAndFeelDecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/tecec/ui/files/icone_tecec.png")));

		setModal(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 298);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cadastrar Nova \u00C1rea:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 414, 239);
		getContentPane().add(panel);
		panel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Super \u00C1rea:");
			lblNewLabel.setBounds(13, 44, 91, 14);
			panel.add(lblNewLabel);
		}
		{
			cboAreas = new JComboBox();
			cboAreas.setBounds(105, 41, 299, 20);
			panel.add(cboAreas);

			cboAreas.setRenderer(new DefaultListCellRenderer(){
				/**
				 *
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public Component getListCellRendererComponent(JList list,
						Object value, int index, boolean isSelected,
						boolean cellHasFocus) {
					super.getListCellRendererComponent(list, value, index, isSelected,
							cellHasFocus);

					if (value instanceof Area) {
						Area area = (Area)value;
						setText(area.getName());
					}

					return this;
				}
			});
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Nome:");
			lblNewLabel_1.setBounds(39, 93, 50, 14);
			panel.add(lblNewLabel_1);
		}
		{
			txtAreaName = new JTextField();
			txtAreaName.setBounds(94, 90, 310, 20);
			panel.add(txtAreaName);
			txtAreaName.setColumns(10);
		}
		{
			txtDescription = new JTextField();
			txtDescription.setBounds(94, 140, 310, 20);
			panel.add(txtDescription);
			txtDescription.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Descri\u00E7\u00E3o:");
			lblNewLabel_2.setBounds(13, 143, 79, 14);
			panel.add(lblNewLabel_2);
		}
		{
			btnNewArea = new JButton("Cadastrar");
			btnNewArea.setBounds(287, 200, 117, 23);
			panel.add(btnNewArea);
			btnNewArea.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					createArea();
				}
			});
		}
		initDataBindings();
	}
	protected void initDataBindings() {
		BeanProperty<INewAreaController, String> iNewAreaControllerBeanProperty = BeanProperty.create("areaName");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<INewAreaController, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, newAreaController, iNewAreaControllerBeanProperty, txtAreaName, jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<INewAreaController, String> iNewAreaControllerBeanProperty_1 = BeanProperty.create("description");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_1 = BeanProperty.create("text");
		AutoBinding<INewAreaController, String, JTextField, String> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, newAreaController, iNewAreaControllerBeanProperty_1, txtDescription, jTextFieldBeanProperty_1);
		autoBinding_1.bind();
		//
		BeanProperty<INewAreaController, Boolean> iNewAreaControllerBeanProperty_3 = BeanProperty.create("canCreateArea");
		BeanProperty<JButton, Boolean> jButtonBeanProperty = BeanProperty.create("enabled");
		AutoBinding<INewAreaController, Boolean, JButton, Boolean> autoBinding_3 = Bindings.createAutoBinding(UpdateStrategy.READ, newAreaController, iNewAreaControllerBeanProperty_3, btnNewArea, jButtonBeanProperty);
		autoBinding_3.bind();
		//
		BeanProperty<INewAreaController, List<Area>> iNewAreaControllerBeanProperty_2 = BeanProperty.create("areas");
		JComboBoxBinding<Area, INewAreaController, JComboBox> jComboBinding = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, newAreaController, iNewAreaControllerBeanProperty_2, cboAreas);
		jComboBinding.bind();
		//
		BeanProperty<INewAreaController, Integer> iNewAreaControllerBeanProperty_4 = BeanProperty.create("selectedAreaIndex");
		BeanProperty<JComboBox, Integer> jComboBoxBeanProperty = BeanProperty.create("selectedIndex");
		AutoBinding<INewAreaController, Integer, JComboBox, Integer> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, newAreaController, iNewAreaControllerBeanProperty_4, cboAreas, jComboBoxBeanProperty);
		autoBinding_2.bind();
		//
		BeanProperty<INewAreaController, Area> iNewAreaControllerBeanProperty_5 = BeanProperty.create("selectedArea");
		BeanProperty<JComboBox, Object> jComboBoxBeanProperty_1 = BeanProperty.create("selectedItem");
		AutoBinding<INewAreaController, Area, JComboBox, Object> autoBinding_4 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, newAreaController, iNewAreaControllerBeanProperty_5, cboAreas, jComboBoxBeanProperty_1);
		autoBinding_4.bind();
	}
	@Override
	public void refresh() {
		newAreaController.refresh();
	}
}
