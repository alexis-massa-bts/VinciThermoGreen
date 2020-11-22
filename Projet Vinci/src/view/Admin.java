package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dataAccess.DataMySQL;
import model.User;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.imageio.metadata.IIOMetadataFormatImpl;
import javax.swing.JButton;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;

/**
 * Classe gérant l'IHM d'administration
 * @author alexis
 *
 */
public class Admin extends JFrame {

	private JPanel contentPane;
	private JTextField tfName;
	private JTextField tfSurname;
	private JTextField tfEmail;
	private ArrayList<User> allUsers = new ArrayList<User>();
	Object[][] dataTable;
	private JTable table;
	private JTextField tfPassword;

	public Admin() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 478);
		setVisible(true);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelCreate = new JPanel();
		panelCreate.setBounds(10, 76, 545, 126);
		contentPane.add(panelCreate);
		panelCreate.setLayout(null);

		JLabel lblCreate = new JLabel("Cr\u00E9er un utilisateur");
		lblCreate.setBounds(212, 5, 121, 19);
		lblCreate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelCreate.add(lblCreate);

		tfName = new JTextField();
		tfName.setBounds(79, 49, 86, 20);
		panelCreate.add(tfName);
		tfName.setColumns(10);

		tfSurname = new JTextField();
		tfSurname.setBounds(79, 94, 86, 20);
		panelCreate.add(tfSurname);
		tfSurname.setColumns(10);

		tfEmail = new JTextField();
		tfEmail.setBounds(249, 94, 143, 20);
		panelCreate.add(tfEmail);
		tfEmail.setColumns(10);

		JCheckBox chckbxRole = new JCheckBox("Administrateur");
		chckbxRole.setBounds(405, 48, 130, 23);
		panelCreate.add(chckbxRole);

		JButton btnCreer = new JButton("Ajouter");
		btnCreer.setBounds(446, 93, 89, 23);
		btnCreer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO : INSERT
				String name = tfName.getText();
				String surname = tfSurname.getText();
				String password = tfPassword.getText();
				String email = tfEmail.getText();
				System.out.println(chckbxRole.isSelected());
				boolean role = chckbxRole.isSelected();
				
				try {
					DataMySQL.insertUser(name, surname, password, email, role);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panelCreate.add(btnCreer);
		
		JLabel lblSurname = new JLabel("Pr\u00E9nom");
		lblSurname.setBounds(23, 52, 46, 14);
		panelCreate.add(lblSurname);
		
		JLabel lblName = new JLabel("Nom");
		lblName.setBounds(23, 97, 46, 14);
		panelCreate.add(lblName);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(205, 97, 46, 14);
		panelCreate.add(lblEmail);
		
		JLabel lblpassword = new JLabel("Mot de passe");
		lblpassword.setBounds(175, 52, 97, 14);
		panelCreate.add(lblpassword);
		
		tfPassword = new JTextField();
		tfPassword.setBounds(249, 49, 86, 20);
		panelCreate.add(tfPassword);
		tfPassword.setColumns(10);

		JLabel lbTitle = new JLabel("Administration");
		lbTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lbTitle.setBounds(179, 11, 269, 54);
		contentPane.add(lbTitle);

		JButton btnTermine = new JButton("Termin\u00E9");
		btnTermine.setBounds(455, 405, 89, 23);
		btnTermine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnTermine);

		JPanel panelUpdate = new JPanel();
		panelUpdate.setBounds(10, 212, 545, 182);
		contentPane.add(panelUpdate);
		panelUpdate.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(544, 0, -542, 182);
		panelUpdate.add(scrollPane);

		ResultSet tmpUsers = DataMySQL.getAllUsers();
		while (tmpUsers.next()) {
			User unUser = new User();
			unUser.setLogin(tmpUsers.getString(1));
			unUser.setName(tmpUsers.getString(3));
			unUser.setSurname(tmpUsers.getString(4));
			unUser.setEmail(tmpUsers.getString(5));
			unUser.setRole(tmpUsers.getBoolean(6));
			allUsers.add(unUser);
		}

		System.out.println(Arrays.deepToString(dataTable));
		table = setTable(dataTable);
		
		//CE TABLEAU N'AFFICHE RIEN, JE NE SAIS PAS POURQUOI
		scrollPane.setViewportView(table);

	}

	/**
	 * Définit le tableau des utilisateurs
	 * @param dataTable
	 * @return
	 */
	private JTable setTable(Object[][] dataTable) {
		dataTable = new Object[allUsers.size()][5];
		for (int i = 0; i < allUsers.size(); i++) {
			User unUser = allUsers.get(i);
			dataTable[i][0] = unUser.getLogin();
			dataTable[i][1] = unUser.getName();
			dataTable[i][2] = unUser.getSurname();
			dataTable[i][3] = unUser.getEmail();
			if (unUser.getRole()) {
				dataTable[i][4] = "Administrateur";
			} else {
				dataTable[i][4] = "Utilisateur";
			}
		}
		String[] titreColonnes = { "Login", "Nom", "Prénom", "Email", "Rôle" };
		JTable uneTable = new JTable(dataTable, titreColonnes);
		return uneTable;
	}
}
