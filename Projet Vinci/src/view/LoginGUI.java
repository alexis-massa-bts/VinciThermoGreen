package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import javax.swing.JButton;
import java.awt.Label;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.event.MouseAdapter;

import control.Controller;

public class LoginGUI extends JFrame {

	private JTextField tfLogin;
	private JTextField tfPassword;

	private static String login;
	private static String password;

	private Controller control = new Controller();

	public LoginGUI() {

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		tfLogin = new JTextField();
		tfLogin.setBounds(284, 172, 150, 20);
		panel.add(tfLogin);
		tfLogin.setColumns(10);

		Label labelLogin = new Label("Login");
		labelLogin.setBounds(216, 172, 62, 22);
		panel.add(labelLogin);

		tfPassword = new JTextField();
		tfPassword.setColumns(10);
		tfPassword.setBounds(284, 203, 150, 20);
		panel.add(tfPassword);

		Label labelPassword = new Label("Password");
		labelPassword.setBounds(216, 203, 62, 22);
		panel.add(labelPassword);

		JButton btnValider = new JButton("Valider");
		btnValider.addMouseListener(new MouseAdapter() {
			@Override
			/**
			 * Appui sur le bouton "Valider" du LoginGUI
			 */
			public void mouseClicked(MouseEvent e) {
				// Action bouton valider
				login = tfLogin.getText();
				password = tfPassword.getText();
				try {
					
					if (control.verifyLogin(login, password)) {
						// Connexion réussie
						// hide loginGUI
						setVisible(false);

						// Instance console
						ConsoleGUI console = new ConsoleGUI();
						console.setUp(console);
					} else {

					}
					;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		
		btnValider.setBounds(345, 249, 89, 23);
		panel.add(btnValider);

	}

	/**
	 Préparation de LoginGUI
	 * @param login
	 */
	public void setUp(LoginGUI login) {
		// Gestion de la connexion
		// Construit l'IHM de connexion
		login.setLocation(100, 100);
		login.setSize(715, 660);
		setVisible(true);
	}

	public JTextField getTfLogin() {
		return tfLogin;
	}

	public JTextField getTfPassword() {
		return tfPassword;
	}
}
