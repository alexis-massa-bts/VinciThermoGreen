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

import javax.swing.JOptionPane;

import org.mindrot.jbcrypt.BCrypt;

/**
 * <p>
 * La classe de lien avec la base de données:
 * </p>
 * 
 * @author Alexis Massa
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

	public static String getPassword() {
		return password;
	}

	/**
	 * Returns the login of the entered user
	 * 
	 * @param login
	 * @return password
	 * @throws SQLException
	 */
	public static String getPassword(String login) throws SQLException {
		myStmt = myConn.createStatement();
		myRs = myStmt.executeQuery("select password from user where login = '" + login + "';");
		myRs.next();
		String password = myRs.getString(1);
		return password;
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
	 * Récupère les mesures correspondant au stade sélectionné dans la base de
	 * données
	 * 
	 * @return ResultSet of data
	 * @throws SQLException
	 */
	public static ResultSet getAllData(String selectedStadium) throws SQLException {
		myStmt = myConn.createStatement();
		myRs = myStmt.executeQuery("select num_zone, horodate, fahrenheit from mesure"
				+ " inner join stade on mesure.id_stade = stade.id_stade" + " where nom_stade = '" + selectedStadium
				+ "'");

		return myRs;
	}

	/**
	 * Liste de tous les utilisateurs
	 * 
	 * @return myRs : ResultSet des utilisateurs
	 * @throws SQLException
	 */
	public static ResultSet getAllUsers() throws SQLException {
		myStmt = myConn.createStatement();
		myRs = myStmt.executeQuery("select * from user");
		return myRs;
	}

	/**
	 * Hashe un mot de passe
	 * 
	 * @param password_plaintext
	 * @return hashed_password : Le mot de passe hashé
	 */
	public static String hashPassword(String password_plaintext) {
		String salt = BCrypt.gensalt();
		String hashed_password = BCrypt.hashpw(password_plaintext, salt);
		return (hashed_password);
	}

	public static boolean getRole(String login) throws SQLException {
		myStmt = myConn.createStatement();
		myRs = myStmt.executeQuery("select role from user where login = '" + login + "'");
		myRs.next();
		return myRs.getBoolean("role");
	}

	public static String getUsername(String login) throws SQLException {
		String fullName = "";
		myStmt = myConn.createStatement();
		myRs = myStmt.executeQuery("select name, surname from user where login = '" + login + "'");
		myRs.next();
		fullName = myRs.getString("name") + " " + myRs.getString("surname");
		return fullName;
	}

	public static void insertUser(String name, String surname, String password, String email, boolean role)
			throws SQLException {
		String login = name.charAt(0) + surname;

		myStmt = myConn.createStatement();
		myRs = myStmt.executeQuery("select * from user where name =  '" + login + "'");
		if (myRs.next()) {
			JOptionPane.showMessageDialog(null, "Un utilisateur portant ce nom existe déjà !");
		} else {
			myStmt.executeUpdate("insert ignore into user values('" + login + "','" + hashPassword(password) + "','"
					+ name + "','" + surname + "','" + email + "'," + role + ")");
			JOptionPane.showMessageDialog(null, "L'utilisateur a été ajouté ! Son login est : " + login);
		}
	}

	public static int getMin(String selectedStadium) throws SQLException {
		myStmt = myConn.createStatement();
		myRs = myStmt.executeQuery("select temp_min from stade where nom_stade = '" + selectedStadium + "'");
		myRs.next();
		return myRs.getInt("temp_min");
	}

	public static int getMax(String selectedStadium) throws SQLException {
		myStmt = myConn.createStatement();
		myRs = myStmt.executeQuery("select temp_max from stade where nom_stade = '" + selectedStadium + "'");
		myRs.next();
		return myRs.getInt("temp_max");
	}

	public static void setDebord(int min, int max, String selectedStadium) throws SQLException {
		myStmt = myConn.createStatement();
		myStmt.executeUpdate("update stade set temp_max =" + max + ", temp_min =" + min + " where nom_stade = '"
				+ selectedStadium + "'");

	}

	public static String getMail(String login) throws SQLException {
		myStmt = myConn.createStatement();
		myRs = myStmt.executeQuery("select email from user where login = amassa");
		myRs.next();
		return myRs.getString("email");
	}

}