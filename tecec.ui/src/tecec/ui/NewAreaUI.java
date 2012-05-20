package tecec.ui;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;

import tecec.contract.RuleViolation;
import tecec.ui.contract.control.INewAreaController;
import tecec.ui.contract.view.INewAreaUI;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.util.List;
import tecec.dto.Area;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.SwingBindings;

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
		
		setDefaultLookAndFeelDecorated(true);
		

		setModal(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 493, 277);
		getContentPane().setLayout(
				new MigLayout("", "[][grow][]", "[grow][][][][][][][][grow]"));
		{
			JLabel lblNewLabel = new JLabel("Super \u00C1rea:");
			getContentPane().add(lblNewLabel, "flowx,cell 1 1");
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Nome:");
			getContentPane().add(lblNewLabel_1, "flowx,cell 1 3");
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Descri\u00E7\u00E3o:");
			getContentPane().add(lblNewLabel_2, "flowx,cell 1 5");
		}
		{
			txtAreaName = new JTextField();
			getContentPane().add(txtAreaName, "cell 1 3,growx");
			txtAreaName.setColumns(10);
		}
		{
			txtDescription = new JTextField();
			getContentPane().add(txtDescription, "cell 1 5,growx");
			txtDescription.setColumns(10);
		}
		{
			btnNewArea = new JButton("Cadastrar");
			btnNewArea.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					createArea();
				}
			});
			getContentPane().add(btnNewArea, "cell 1 7,alignx right");
		}
		{
			cboAreas = new JComboBox();
			
			cboAreas.setRenderer(new DefaultListCellRenderer(){
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
			
			getContentPane().add(cboAreas, "cell 1 1,growx");
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
}
