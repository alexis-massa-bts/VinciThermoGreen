package control;

import java.sql.SQLException;
import java.text.ParseException;

import dataAccess.DataMySQL;
import view.ConsoleGUI;
import view.LoginGUI;

public class Controller {

	private static ConsoleGUI console;
	private static LoginGUI login;

	public static void main(String[] args) throws ParseException, SQLException {
		DataMySQL.openConnection();

		login = new LoginGUI();
		login.setUp(login);

		console = new ConsoleGUI();
		console.setUp(console);
		
		// show loginGUI
		login.setVisible(true);
		System.out.println("Affichage de monLogin");

		// Tentative connexion
		boolean authorized = true;
		while (authorized) {
			if (login.verifyLogin()) {
				// Connexion réussie
				// hide loginGUI
				login.setVisible(false);
				// show consoleGUI
				login.setVisible(true);
				authorized = false;
			}
		}


	}

}
