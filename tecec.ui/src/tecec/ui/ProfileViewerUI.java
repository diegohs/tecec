package tecec.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;


import org.jdesktop.beansbinding.BeanProperty;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import tecec.dto.Profile;
import tecec.ui.contract.control.IProfileViewerController;
import tecec.ui.contract.view.IProfileViewerUI;
import javax.swing.ListSelectionModel;
import java.awt.Font;

public class ProfileViewerUI extends JFrame implements IProfileViewerUI {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IProfileViewerController profileViewerController;
	
	private void showUpdateProfileUI(){
		this.profileViewerController.showUpdateProfileUI();
	}
	
	private void showNewProfileUI(){
		this.profileViewerController.showNewProfileUI();
	}
	
	private void deleteProfile(){
		this.profileViewerController.deleteProfile();
	}

	private JPanel contentPane;
	private JTextField txtFilter;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnNewProfile;
	private JButton btnUpdateProfile;
	private JButton btnDeleteProfile;

	/**
	 * Create the frame.
	 */
	public ProfileViewerUI(IProfileViewerController profileViewerController) {
		this.profileViewerController = profileViewerController;	
		
		setDefaultLookAndFeelDecorated(true);
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 726, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow][grow 50][]", "[20px:n][][][20px:n][grow][5px:n][20px:n]"));
		
		JLabel lblFilter = new JLabel("Filtro:");
		lblFilter.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		contentPane.add(lblFilter, "cell 1 2,alignx center");
		
		txtFilter = new JTextField();
		contentPane.add(txtFilter, "cell 2 2,growx");
		txtFilter.setColumns(10);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 1 4 2 1,grow");
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		btnNewProfile = new JButton("Adicionar Novo");
		btnNewProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showNewProfileUI();
			}
		});
		contentPane.add(btnNewProfile, "flowx,cell 2 6,alignx right,aligny bottom");
		
		btnUpdateProfile = new JButton("Atualizar Selecionado");
		btnUpdateProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showUpdateProfileUI();				
			}
		});
		contentPane.add(btnUpdateProfile, "flowx,cell 2 6,alignx right,aligny bottom");
		
		btnDeleteProfile = new JButton("Excluir Selecionado");
		btnDeleteProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteProfile();
			}
		});
		contentPane.add(btnDeleteProfile, "flowx,cell 2 6,alignx right,aligny bottom");
		initDataBindings();
	}
	protected void initDataBindings() {
		BeanProperty<IProfileViewerController, String> iProfileViewerControllerBeanProperty = BeanProperty.create("nameFilter");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<IProfileViewerController, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, profileViewerController, iProfileViewerControllerBeanProperty, txtFilter, jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<IProfileViewerController, List<Profile>> iProfileViewerControllerBeanProperty_1 = BeanProperty.create("profiles");
		JTableBinding<Profile, IProfileViewerController, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, profileViewerController, iProfileViewerControllerBeanProperty_1, table);
		//
		BeanProperty<Profile, String> profileBeanProperty = BeanProperty.create("name");
		jTableBinding.addColumnBinding(profileBeanProperty).setColumnName("Perfil");
		//
		jTableBinding.bind();
		//
		BeanProperty<IProfileViewerController, Profile> iProfileViewerControllerBeanProperty_2 = BeanProperty.create("selectedProfile");
		BeanProperty<JTable, Profile> jTableBeanProperty = BeanProperty.create("selectedElement");
		AutoBinding<IProfileViewerController, Profile, JTable, Profile> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, profileViewerController, iProfileViewerControllerBeanProperty_2, table, jTableBeanProperty);
		autoBinding_1.bind();
		//
		BeanProperty<IProfileViewerController, Boolean> iProfileViewerControllerBeanProperty_3 = BeanProperty.create("canUpdateProfile");
		BeanProperty<JButton, Boolean> jButtonBeanProperty = BeanProperty.create("enabled");
		AutoBinding<IProfileViewerController, Boolean, JButton, Boolean> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ, profileViewerController, iProfileViewerControllerBeanProperty_3, btnUpdateProfile, jButtonBeanProperty);
		autoBinding_2.bind();
		//
		BeanProperty<IProfileViewerController, Boolean> iProfileViewerControllerBeanProperty_4 = BeanProperty.create("canDeleteProfile");
		AutoBinding<IProfileViewerController, Boolean, JButton, Boolean> autoBinding_3 = Bindings.createAutoBinding(UpdateStrategy.READ, profileViewerController, iProfileViewerControllerBeanProperty_4, btnDeleteProfile, jButtonBeanProperty);
		autoBinding_3.bind();
	}
}
