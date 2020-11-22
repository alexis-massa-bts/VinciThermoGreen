package control;

import java.sql.SQLException;
import java.text.ParseException;

import org.mindrot.jbcrypt.BCrypt;

import dataAccess.DataMySQL;
import view.ConsoleGUI;
import view.LoginGUI;

public class Controller {

	private static LoginGUI login;
	/**
	 * MAIN de l'application
	 * @param args
	 * @throws ParseException
	 * @throws SQLException
	 */
	public static void main(String[] args) throws ParseException, SQLException {
		DataMySQL.openConnection();

		login = new LoginGUI();
		login.setUp(login);
	}
	
	/**
	 * Vérifier la connection
	 * @param login
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public boolean verifyLogin(String login, String password) throws SQLException {
		boolean correct = false;
		String tmpPassword = "";
		try {
			tmpPassword = DataMySQL.getPassword(login);
			if (BCrypt.checkpw(password, tmpPassword)) {
				correct = true;
			} else {
				correct = false;
			}

		} catch (Exception e) {

		}
		return correct;

	}
}
