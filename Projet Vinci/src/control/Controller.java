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
		
		
		
		console = new ConsoleGUI();
		console.updateData("Stade de Paris");
	}

}
