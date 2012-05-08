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

public class UpdateAreaUI extends JDialog implements IUpdateAreaUI {

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

		setModal(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow][]",
				"[][][][][][][][][]"));
		{
			JLabel lblNewLabel = new JLabel("Super \u00C1rea:");
			contentPanel.add(lblNewLabel, "flowx,cell 1 1");
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Nome:");
			contentPanel.add(lblNewLabel_1, "flowx,cell 1 3");
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Descri\u00E7\u00E3o:");
			contentPanel.add(lblNewLabel_2, "flowx,cell 1 5");
		}
		{
			txtName = new JTextField();
			contentPanel.add(txtName, "cell 1 3,growx");
			txtName.setColumns(10);
		}
		{
			txtDescription = new JTextField();
			contentPanel.add(txtDescription, "cell 1 5,growx");
			txtDescription.setColumns(10);
		}
		{
			cboMainArea = new JComboBox();
			
			cboMainArea.setRenderer(new DefaultListCellRenderer(){
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
			
			contentPanel.add(cboMainArea, "cell 1 1,growx");
		}
		{
			JButton btnUpdate = new JButton("Atualizar");
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					updateArea();
				}
			});
			contentPanel.add(btnUpdate, "cell 1 7,alignx right");
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
