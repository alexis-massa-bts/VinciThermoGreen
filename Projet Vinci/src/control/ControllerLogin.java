package control;

import java.sql.ResultSet;
import java.sql.SQLException;

import dataAccess.DataMySQL;

public class ControllerLogin {

	public static boolean verifyLogin(String login, String password) throws SQLException {
		boolean found = false;
		String tmpUsername = "";
		String tmpPassword = "";
		ResultSet allUsers = DataMySQL.getAllUsers();
		try {
			//TODO login
			System.out.println(login + " " +  password);
		} catch (Exception e) {

		}
		return found;

	}
}