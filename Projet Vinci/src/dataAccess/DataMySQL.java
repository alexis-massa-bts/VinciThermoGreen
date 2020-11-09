/**
 * @author Alexis Massa
 */

package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Mesure;
import view.ConsoleGUI;

/**
 * <p>
 * La classe de lien avec la base de données:
 * </p>
 * 
 * @author Alexis MAssa
 * @version 3.0.0
 *
 */

public class DataMySQL {

	private static Connection myConn = null;
	private static Statement myStmt = null;
	private static ResultSet myRs = null;

	private static String url = "";
	private static String user = "";
	private static String password = "";
	private static ArrayList<String> allStadiums = new ArrayList<String>();
	private static ArrayList<String> allData = new ArrayList<String>();

//Getters and Setters
	public static Connection getMyConn() {
		return myConn;
	}

	public static Statement getMyStmt() {
		return myStmt;
	}

	public static void setMyStmt(Statement myStmt) {
		DataMySQL.myStmt = myStmt;
	}

	public static ResultSet getMyRs() {
		return myRs;
	}

	public static void setMyRs(ResultSet myRs) {
		DataMySQL.myRs = myRs;
	}

	public static void setMyConn(Connection myConn) {
		DataMySQL.myConn = myConn;
	}

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		DataMySQL.url = url;
	}

	public static String getUser() {
		return user;
	}

	public static void setUser(String user) {
		DataMySQL.user = user;
	}

	public static ResultSet getPassword(String login) throws SQLException {
		myStmt = myConn.createStatement();
		myRs = myStmt.executeQuery("select login, password from user");
		return myRs;
	}

	public static void setPassword(String password) {
		DataMySQL.password = password;
	}

	/*
	 * Ouvre une connexion à la BDD
	 */
	public static void openConnection() {
		url = "jdbc:mysql://localhost:3306/vincithermogreen";
		user = "amassa";
		password = "amassa";
		// Get a connection to the database
		try {
			myConn = DriverManager.getConnection(url, user, password);
			System.out.println("Connexion réussie !");
			setMyConn(myConn);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connexion échouée !");
		}
	}

	public static void closeConnection() throws SQLException {
		myConn.close();
	}

	/**
	 * Récupère la liste des stades dans la base de données
	 * 
	 * @return ArrayList of stadium
	 * @throws SQLException
	 */
	public static ArrayList<String> getAllStadiums() throws SQLException {
		myStmt = myConn.createStatement();
		myRs = myStmt.executeQuery("select nom_stade from stade");
		while (myRs.next()) {
			allStadiums.add(myRs.getString(1));
		}
		return allStadiums;
	}

	/**
	 * Récupère les mesures correspondant au stade sélectionné dans la base de données
	 * 
	 * @return ResultSet of data
	 * @throws SQLException 
	 */
	public static ResultSet getAllData(String selectedStadium) throws SQLException {
		myStmt = myConn.createStatement();
		myRs = myStmt.executeQuery("select num_zone, horodate, fahrenheit from mesure"
				+ " inner join stade on mesure.id_stade = stade.id_stade" + " where nom_stade = '"
				+ selectedStadium + "'");
		
		return myRs;
	}
	
	public static ResultSet getAllUsers() throws SQLException {
		myStmt = myConn.createStatement();
		myRs = myStmt.executeQuery("select login, password from user");
		return myRs;
	}


}