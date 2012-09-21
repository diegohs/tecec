package tecec.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.border.TitledBorder;

public class ProfileViewerUI extends JFrame implements IProfileViewerUI {

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
	private JPanel panelPesquisa;
	private JPanel panelButtons;

	/**
	 * Create the frame.
	 */
	public ProfileViewerUI(IProfileViewerController profileViewerController) {
		setPreferredSize(new Dimension(800, 600));
		setMinimumSize(new Dimension(800, 600));
		setMaximumSize(new Dimension(800, 600));
		this.profileViewerController = profileViewerController;

		setDefaultLookAndFeelDecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/tecec/ui/files/icone_tecec.png")));

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 726, 441);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(800, 600));
		contentPane.setMinimumSize(new Dimension(800, 600));
		contentPane.setMaximumSize(new Dimension(800, 600));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[800px]", "[60px][400px][60px]"));

		panelPesquisa = new JPanel();
		panelPesquisa.setPreferredSize(new Dimension(750, 60));
		panelPesquisa.setMinimumSize(new Dimension(750, 60));
		panelPesquisa.setMaximumSize(new Dimension(750, 60));
		panelPesquisa.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisa:", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 1, 10))); // NOI18N
		contentPane.add(panelPesquisa, "cell 0 0,grow");

		txtFilter = new JTextField();
		panelPesquisa.add(txtFilter);
		txtFilter.setColumns(90);

		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(750, 400));
		scrollPane.setMinimumSize(new Dimension(750, 400));
		scrollPane.setMaximumSize(new Dimension(750, 400));
		contentPane.add(scrollPane, "cell 0 1,grow");

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);

		panelButtons = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelButtons.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelButtons.setPreferredSize(new Dimension(750, 60));
		panelButtons.setMinimumSize(new Dimension(750, 60));
		panelButtons.setMaximumSize(new Dimension(750, 60));
		panelButtons.setBorder(new TitledBorder(null, "Op\u00E7\u00F5es:", TitledBorder.LEADING, TitledBorder.TOP, null, null)); // NOI18N
		contentPane.add(panelButtons, "cell 0 2,grow");

		btnNewProfile = new JButton("Adicionar Novo");
		btnNewProfile.setPreferredSize(new Dimension(150, 25));
		btnNewProfile.setMinimumSize(new Dimension(150, 25));
		btnNewProfile.setMaximumSize(new Dimension(150, 25));
		panelButtons.add(btnNewProfile);

		btnUpdateProfile = new JButton("Atualizar Selecionado");
		btnUpdateProfile.setPreferredSize(new Dimension(200, 25));
		btnUpdateProfile.setMinimumSize(new Dimension(150, 25));
		btnUpdateProfile.setMaximumSize(new Dimension(150, 25));
		panelButtons.add(btnUpdateProfile);

		btnDeleteProfile = new JButton("Excluir Selecionado");
		btnDeleteProfile.setPreferredSize(new Dimension(200, 25));
		btnDeleteProfile.setMinimumSize(new Dimension(150, 25));
		btnDeleteProfile.setMaximumSize(new Dimension(150, 25));
		panelButtons.add(btnDeleteProfile);
		btnDeleteProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteProfile();
			}
		});
		btnUpdateProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showUpdateProfileUI();
			}
		});
		btnNewProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showNewProfileUI();
			}
		});
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

	@Override
	public void refresh() {
		profileViewerController.refresh();
	}
}
