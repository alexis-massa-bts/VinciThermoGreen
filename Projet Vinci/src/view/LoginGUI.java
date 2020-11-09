package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import control.Controller;

import javax.swing.JButton;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {

	private JTextField tfLogin;
	private JTextField tfPassword;

	private String login;
	private String password;

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
		btnValider.setBounds(345, 249, 89, 23);
		panel.add(btnValider);
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO action bouton valider
				login = tfLogin.getText();
				password = tfPassword.getText();
			}
		});

	}

	public JTextField getTfLogin() {
		return tfLogin;
	}

	public JTextField getTfPassword() {
		return tfPassword;
	}

}
