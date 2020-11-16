package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import dataAccess.DataMySQL;

import javax.swing.JButton;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.awt.event.MouseAdapter;

public class LoginGUI extends JFrame {

	private JTextField tfLogin;
	private JTextField tfPassword;

	private static String login;
	private static String password;

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
			public void mouseClicked(MouseEvent e) {
				// Action bouton valider
				login = tfLogin.getText();
				password = tfPassword.getText();
			}
		});
		btnValider.setBounds(345, 249, 89, 23);
		panel.add(btnValider);

	}

	public JTextField getTfLogin() {
		return tfLogin;
	}

	public JTextField getTfPassword() {
		return tfPassword;
	}

	public boolean verifyLogin(String password) throws SQLException {
		boolean correct = false;
		String tmpPassword = "";
		try {
			// TODO login
			tmpPassword = DataMySQL.getPassword(login);

			System.out.println((password instanceof String) + " : " + (tmpPassword instanceof String));
			System.out.println(password + " : " + tmpPassword);
			// TODO j'en suis la
			System.out.println("ALED");

			if (tmpPassword == password) {
				correct = true;
			} else {
				correct = false;
			}

		} catch (Exception e) {

		}
		System.out.println(correct);
		return correct;

	}

}
