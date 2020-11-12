package control;

import java.sql.ResultSet;
import java.sql.SQLException;

import dataAccess.DataMySQL;

public class ControllerLogin {

	public boolean verifyLogin(String login, String password) throws SQLException {
		boolean correct = false;
		String tmpPassword = "";
		try {
			// TODO login
			tmpPassword = DataMySQL.getPassword(login);		
			
			System.out.println((password instanceof String) + " : " + (tmpPassword instanceof String));
			System.out.println(password + " : " + tmpPassword);
			//TODO j'en suis la
			System.out.println("ALED");
			
			if (tmpPassword == password) {
				correct = true;
			}else {
				correct = false;
			}

		} catch (Exception e) {

		}
		System.out.println(correct);
		return correct;

	}
}