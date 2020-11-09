/**
 * @author J�r�me Valenti 
 */
package control;

import model.Mesure;
import model.Stade;
import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import dataAccess.DataMySQL;
import model.Mesure;
import view.ConsoleGUI;

/**
 * <p>
 * Le cont&ocirc;lleur :
 * </p>
 * <ol>
 * <li>lit les mesures de temp�rature dans la base de donn�es</li>
 * <li>retourne la collection des mesures<br />
 * </li>
 * </ol>
 * 
 * @author J�r�me Valenti
 * @version 2.0.0
 *
 */
public class Controller {

	private ArrayList<Mesure> allData = new ArrayList<Mesure>();
	private ArrayList<String> allStadiums = new ArrayList<String>();

	public Controller() throws ParseException, SQLException {
		DataMySQL.openConnection();
		updateData("Stade de Paris");
	}

	/**
	 * Mise � jour des donn�es affich�es
	 * @param selectedStadium
	 * @throws ParseException
	 * @throws SQLException
	 */
	public void updateData(String selectedStadium) throws ParseException, SQLException {

		// Chaque ligne est un enregistrement de donn�es
		// Chaque enregistrement contient des champs
		String[] fields = null;
		String numZone = null;
		Date horoDate = null;
		float fahrenheit;
		//Clear all datas
		allData.clear();
		// Traitement les champs de la requ�te
		ResultSet myRs = DataMySQL.getAllData(selectedStadium);
		
		while (myRs.next()) {
			Mesure laMesure = new Mesure();
			laMesure.setNumZone(myRs.getString(1));
			laMesure.setHoroDate(myRs.getDate(2));
			laMesure.setFahrenheit(myRs.getFloat(3));
			
			allData.add(laMesure);
		}
		
	}

	/**
	 * Filtrage des donn�es en fonction de la zone selectionn�e
	 * @param laZone
	 * @return ArrayList<Mesure> laSelection
	 */
	public ArrayList<Mesure> filtrerLesMesure(String laZone) {
		// Parcours de la collection
		// Ajout � laSelection des objets qui correspondent aux param�tres
		// Envoi de la collection
		ArrayList<Mesure> laSelection = new ArrayList<Mesure>();
		for (Mesure mesure : allData) {
			if (laZone.compareTo("*") == 0) {
				laSelection.add(mesure);
			} else {
				if (laZone.compareTo(mesure.getNumZone()) == 0) {
					laSelection.add(mesure);
				}
			}
		}
		return laSelection;
	}

	/**
	 * <p>
	 * Retourne la collection des mesures
	 * </p>
	 * 
	 * @return ArrayList<Mesure>
	 */
	public ArrayList<Mesure> getLesMesures() {
		return allData;
	}

	/**
	 * <p>
	 * Convertion d'une String en Date
	 * </p>
	 * 
	 * @param strDate
	 * @return Date
	 * @throws ParseException
	 */
	private Date strToDate(String strDate) throws ParseException {

		SimpleDateFormat leFormat = null;
		Date laDate = new Date();
		leFormat = new SimpleDateFormat("yy-MM-dd hh:mm");

		laDate = leFormat.parse(strDate);
		return laDate;
	}

	/**
	 * Renvoie la liste des stades
	 * @return ArrayList of stadiums
	 * @throws SQLException
	 */
	public ArrayList<String> getAllStadiums() throws SQLException {
		allStadiums = DataMySQL.getAllStadiums();
		return allStadiums;
	}
}
