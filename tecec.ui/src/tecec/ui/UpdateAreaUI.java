package tecec.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.SwingBindings;

import tecec.contract.RuleViolation;
import tecec.dto.Area;
import tecec.ui.contract.control.IUpdateAreaController;
import tecec.ui.contract.view.IUpdateAreaUI;
import java.awt.Font;
import javax.swing.border.TitledBorder;

public class UpdateAreaUI extends JDialog implements IUpdateAreaUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();

	private IUpdateAreaController updateAreaController;
	private JTextField txtName;
	private JTextField txtDescription;
	private JComboBox cboMainArea;

	@Override
	public void setPKArea(String pKArea) {
		this.updateAreaController.setPKArea(pKArea);
	}

	private void updateArea() {
		RuleViolation violation = this.updateAreaController
				.getUpdateViolation();

		if (violation != null) {
			JOptionPane.showMessageDialog(this, violation.getDescription(),
					"ERRO", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				this.updateAreaController.updateArea();

				this.setVisible(false);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e, "ERRO", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public UpdateAreaUI(IUpdateAreaController updateAreaController) {
		this.updateAreaController = updateAreaController;
		
		setDefaultLookAndFeelDecorated(true);

		setModal(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 298);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Atualizar \u00C1rea:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 11, 414, 239);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Super \u00C1rea:");
				lblNewLabel.setBounds(13, 44, 76, 14);
				panel.add(lblNewLabel);
			}
			{
				cboMainArea = new JComboBox();
				cboMainArea.setBounds(84, 41, 320, 20);
				panel.add(cboMainArea);
				
				cboMainArea.setRenderer(new DefaultListCellRenderer(){
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
				txtName = new JTextField();
				txtName.setBounds(84, 90, 320, 20);
				panel.add(txtName);
				txtName.setColumns(10);
			}
			{
				txtDescription = new JTextField();
				txtDescription.setBounds(84, 140, 320, 20);
				panel.add(txtDescription);
				txtDescription.setColumns(10);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Descri\u00E7\u00E3o:");
				lblNewLabel_2.setBounds(13, 143, 79, 14);
				panel.add(lblNewLabel_2);
			}
			{
				JButton btnUpdate = new JButton("Atualizar");
				btnUpdate.setBounds(329, 205, 75, 23);
				panel.add(btnUpdate);
				btnUpdate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						updateArea();
					}
				});
			}
		}
		initDataBindings();
	}
	protected void initDataBindings() {
		BeanProperty<IUpdateAreaController, String> iUpdateAreaControllerBeanProperty = BeanProperty.create("areaName");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<IUpdateAreaController, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, updateAreaController, iUpdateAreaControllerBeanProperty, txtName, jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<IUpdateAreaController, String> iUpdateAreaControllerBeanProperty_1 = BeanProperty.create("areaDescription");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_1 = BeanProperty.create("text");
		AutoBinding<IUpdateAreaController, String, JTextField, String> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, updateAreaController, iUpdateAreaControllerBeanProperty_1, txtDescription, jTextFieldBeanProperty_1);
		autoBinding_1.bind();
		//
		BeanProperty<IUpdateAreaController, List<Area>> iUpdateAreaControllerBeanProperty_2 = BeanProperty.create("areas");
		JComboBoxBinding<Area, IUpdateAreaController, JComboBox> jComboBinding = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ, updateAreaController, iUpdateAreaControllerBeanProperty_2, cboMainArea);
		jComboBinding.bind();
		//
		BeanProperty<IUpdateAreaController, Area> iUpdateAreaControllerBeanProperty_3 = BeanProperty.create("selectedArea");
		BeanProperty<JComboBox, Object> jComboBoxBeanProperty = BeanProperty.create("selectedItem");
		AutoBinding<IUpdateAreaController, Area, JComboBox, Object> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, updateAreaController, iUpdateAreaControllerBeanProperty_3, cboMainArea, jComboBoxBeanProperty);
		autoBinding_2.bind();
		//
		BeanProperty<IUpdateAreaController, Integer> iUpdateAreaControllerBeanProperty_4 = BeanProperty.create("selectedAreaIndex");
		BeanProperty<JComboBox, Integer> jComboBoxBeanProperty_1 = BeanProperty.create("selectedIndex");
		AutoBinding<IUpdateAreaController, Integer, JComboBox, Integer> autoBinding_3 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, updateAreaController, iUpdateAreaControllerBeanProperty_4, cboMainArea, jComboBoxBeanProperty_1);
		autoBinding_3.bind();
	}
}
