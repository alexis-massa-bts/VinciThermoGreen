/**
 * @author Jérôme Valenti
 */
package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.PasswordAuthentication;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.ListIterator;
import java.util.Map;

import javax.mail.MessagingException;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.twilio.Twilio;

import control.*;
import dataAccess.*;
import model.*;
import java.sql.*;

/**
 * <p>
 * ConsoleGUI : IHM de l'application de consultation des températures
 * </p>
 * <p>
 * Projet Vinci Thermo Green
 * </p>
 * 
 * @author Jérôme Valenti
 * @version 2.0.0
 * @see control.ControllerConsole
 * @see model.Mesure
 */
public class ConsoleGUI extends JFrame {

	private static ArrayList<Mesure> allData = new ArrayList<Mesure>();
	private static ArrayList<String> allStadiums = new ArrayList<String>();
	private Controller control = new Controller();
	private Admin admin;
	private int minSlider;
	private int maxSlider;

	/**
	 * <p>
	 * Container intermédiaire JPanel
	 * </p>
	 * <p>
	 * Contient les critères de filtrage des données de la table
	 * </p>
	 * 
	 * @see JPanel
	 */
	JPanel pnlCriteria = new JPanel();

	/**
	 * <p>
	 * Bouton radio pour le choix de conversion
	 * </p>
	 */
	private static JRadioButton rdbtnCelsius = new JRadioButton("Celsius");
	JRadioButton rdbtnFahrenheit = new JRadioButton("Fahrenheit");

	/**
	 * <p>
	 * Liste de choix d'une zone</>
	 * 
	 * @see JComboBox
	 */
	JComboBox<String> choixZone = new JComboBox<String>();

	/**
	 * <p>
	 * Saisie de la date de début de période
	 * </p>
	 * 
	 * @see JTextField
	 */
	private JTextField dateDebut;

	/**
	 * <p>
	 * Saisie de la date de fin de période
	 * </p>
	 * 
	 * @see JTextField
	 */
	private JTextField dateFin;

	private JButton btnFiltrer = new JButton("Filtrer");

	/**
	 * <p>
	 * Container intermédiaire JPanel
	 * </p>
	 * <p>
	 * Contient l'affichage graphique des données de la Table
	 * </p>
	 * 
	 * @see JPanel
	 */
	JPanel pnlParam = new JPanel();
	JPanel pnlGraph = new JPanel();

	/**
	 * <p>
	 * Affiche la température minimale sur la période
	 * </p>
	 * 
	 * @see JTextField
	 */
	private static JTextField tempMin;

	/**
	 * <p>
	 * Affiche la température moyenne sur la période
	 * </p>
	 * 
	 * @see JTextField
	 */
	private static JTextField tempMoy;

	/**
	 * <p>
	 * Affiche la température maximale sur la période
	 * </p>
	 * 
	 * @see JTextField
	 */
	private static JTextField tempMax;

	/**
	 * <p>
	 * Pour recevoir les données collectées
	 * </p>
	 * 
	 * @see JTable
	 */
	private static JTable laTable;

	/**
	 * <p>
	 * Un objet de la classe Mesure
	 * </p>
	 * 
	 * @see model.Mesure
	 */
	private static Mesure uneMesure;

	/**
	 * <p>
	 * Pour recevoir les données collectées
	 * </p>
	 * 
	 * @see ArrayList
	 * @see model.Mesure
	 */
	private static ArrayList<Mesure> lesMesures = new ArrayList<Mesure>();

	/**
	 * <p>
	 * Pour recevoir le JTable qui contient les mesures selectionnées
	 * </p>
	 */
	private static JScrollPane scrollPane = new JScrollPane();

	/**
	 * <p>
	 * Tableau d'objet pour alimenter la JTable
	 * </p>
	 */
	private static Object[][] data;

	/**
	 * <p>
	 * Container intermédiaire JPanel
	 * </p>
	 * <p>
	 * Contient les bornes des valeurs nominales
	 * </p>
	 * 
	 * @see JPanel
	 */
	JPanel pnlBounds = new JPanel();

	public ConsoleGUI(String username, boolean role) throws ParseException, SQLException {
		// Appelle le constructeur de la classe mère
		super();
		setLocation(-357, -248);
		setIconImage(Toolkit.getDefaultToolkit().getImage("img\\vinci_ico.jpg"));
		setTitle("Vinci Thermo Green");
		setSize(781, 811);
		setResizable(false);
		setFont(new Font("Consolas", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Pane pointe sur le container racine
		Container pane = getContentPane();
		// Fixe le Layout de la racine à Absolute
		getContentPane().setLayout(null);

		// Définit le JPanel des critères
		pnlCriteria.setBounds(10, 267, 325, 145);
		pnlCriteria.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Filtrage",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.GRAY));
		pnlCriteria.setBackground(UIManager.getColor("Label.background"));
		pnlCriteria.setLayout(null);
		pane.add(pnlCriteria);

		// Ajoute deux boutons radio au JPanel pnlCriteria
		rdbtnCelsius.setFont(new Font("Consolas", Font.PLAIN, 12));
		rdbtnCelsius.setBounds(15, 20, 100, 23);
		pnlCriteria.add(rdbtnCelsius);

		// Sélectionne la convertion celsius par défaut
		rdbtnCelsius.setSelected(true);

		rdbtnFahrenheit.setFont(new Font("Consolas", Font.PLAIN, 12));
		rdbtnFahrenheit.setBounds(115, 20, 100, 23);
		pnlCriteria.add(rdbtnFahrenheit);

		// Groupe les boutons radio.
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnCelsius);
		group.add(rdbtnFahrenheit);

		choixZone.setBounds(115, 50, 100, 20);
		pnlCriteria.add(choixZone);

		// un bouchon "Quick & Dirty" pour peupler la liste déroulante
		// TODO peupler la liste avec un équivalent à SELECT DISTINCT
		// TODO implémenter la classe métier Zone pour peupler une JComboBox<Zone>
		choixZone.addItem("*");
		choixZone.addItem("1");
		choixZone.addItem("2");
		choixZone.addItem("3");
		choixZone.addItem("4");

		JLabel lblZone = new JLabel("Zone");
		lblZone.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblZone.setBounds(15, 54, 99, 14);
		pnlCriteria.add(lblZone);

		JLabel lblDebut = new JLabel("D\u00E9but");
		lblDebut.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblDebut.setBounds(15, 83, 46, 14);
		pnlCriteria.add(lblDebut);

		dateDebut = new JTextField();
		dateDebut.setBounds(115, 79, 100, 20);
		pnlCriteria.add(dateDebut);
		dateDebut.setColumns(10);

		JLabel lblFin = new JLabel("Fin");
		lblFin.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblFin.setBounds(15, 114, 46, 14);
		pnlCriteria.add(lblFin);

		dateFin = new JTextField();
		dateFin.setColumns(10);
		dateFin.setBounds(115, 110, 100, 20);
		pnlCriteria.add(dateFin);

		btnFiltrer.setBounds(225, 109, 89, 23);
		pnlCriteria.add(btnFiltrer);
		btnFiltrer.addActionListener(new filtrerData());

		JLabel lblLogoVinci = new JLabel();
		lblLogoVinci.setIcon(new ImageIcon("img\\s_vinci.png"));
		lblLogoVinci.setBounds(221, 11, 95, 35);
		pnlCriteria.add(lblLogoVinci);

		// Définit le JScrollPane qui reçoit la JTable
		scrollPane.setBounds(10, 423, 325, 348);
		pane.add(scrollPane);

		// Définit le JPanel des paramètres du graphique
		pnlParam.setBounds(410, 267, 355, 309);
		pnlParam.setBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Graphique des temp\u00E9ratures",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(128, 128, 128)));
		pnlParam.setBackground(UIManager.getColor("Label.background"));
		pnlParam.setLayout(null);
		pane.add(pnlParam);

		JCheckBox chckbxDistinctZone = new JCheckBox("Distinguer les zones");
		chckbxDistinctZone.setFont(new Font("Consolas", Font.PLAIN, 12));
		chckbxDistinctZone.setBounds(15, 20, 165, 23);
		pnlParam.add(chckbxDistinctZone);

		JLabel lblTypeDeGraphique = new JLabel("Type de graphique");
		lblTypeDeGraphique.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblTypeDeGraphique.setBounds(15, 50, 120, 14);
		pnlParam.add(lblTypeDeGraphique);

		JComboBox choixGraphique = new JComboBox();
		choixGraphique.setBounds(152, 47, 190, 20);
		pnlParam.add(choixGraphique);

		JButton btnActualiser = new JButton("Actualiser");
		btnActualiser.setBounds(222, 19, 120, 23);
		pnlParam.add(btnActualiser);

		JLabel lblMin = new JLabel("Min");
		lblMin.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblMin.setBounds(15, 278, 30, 14);
		pnlParam.add(lblMin);

		tempMin = new JTextField();
		tempMin.setEditable(false);
		tempMin.setBounds(55, 274, 50, 20);
		pnlParam.add(tempMin);
		tempMin.setColumns(10);

		JLabel lblMoy = new JLabel("Moy");
		lblMoy.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblMoy.setBounds(137, 276, 30, 14);
		pnlParam.add(lblMoy);

		tempMoy = new JTextField();
		tempMoy.setEditable(false);
		tempMoy.setColumns(10);
		tempMoy.setBounds(177, 272, 50, 20);
		pnlParam.add(tempMoy);

		JLabel lblMax = new JLabel("Max");
		lblMax.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblMax.setBounds(252, 276, 30, 14);
		pnlParam.add(lblMax);

		tempMax = new JTextField();
		tempMax.setEditable(false);
		tempMax.setColumns(10);
		tempMax.setBounds(292, 272, 50, 20);
		pnlParam.add(tempMax);

		// Définit le JPanel qui recoit le graphique
		pnlGraph.setBorder(new TitledBorder(null, "Graphique", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlGraph.setBackground(UIManager.getColor("Label.background"));
		pnlGraph.setBounds(15, 75, 330, 186);

		// pose le pnlGraph dans le pnlParam
		pnlParam.add(pnlGraph);
		pnlGraph.setLayout(null);

		/*
		 * Get list of stadiums from controller
		 */
		allStadiums = getAllStadiums();

		JComboBox<String> stadeChoix = new JComboBox();
		stadeChoix.setBounds(234, 140, 228, 61);
		getContentPane().add(stadeChoix);

		for (int i = 0; i < allStadiums.size(); i++) {
			stadeChoix.addItem(allStadiums.get(i));
		}
		// Change data when stadeChoix updated
		stadeChoix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateData((String) stadeChoix.getSelectedItem());
					// displayLesMesures(lesMesures);
					laTable = setTable(lesMesures);
					scrollPane.setViewportView(laTable);
					minSlider = DataMySQL.getMin((String) stadeChoix.getSelectedItem());
					maxSlider = DataMySQL.getMax((String) stadeChoix.getSelectedItem());
					verifDebord(minSlider, maxSlider, (String) stadeChoix.getSelectedItem());
				} catch (ParseException | SQLException | MessagingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		// Définit le JPanel des bornes nominales
		pnlBounds.setBounds(410, 587, 355, 184);
		pnlBounds.setBorder(new TitledBorder(null, "D\u00E9bord des valeurs nominales", TitledBorder.LEADING,
				TitledBorder.TOP, null, Color.GRAY));
		pnlBounds.setBackground(UIManager.getColor("Label.background"));
		pnlBounds.setLayout(null);
		pane.add(pnlBounds);

		Hashtable<Integer, JLabel> labels = new Hashtable<Integer, JLabel>();
		labels.put(1, new JLabel("51"));
		labels.put(2, new JLabel("52"));
		labels.put(3, new JLabel("53"));
		labels.put(4, new JLabel("54"));

		JLabel lblDebordMin = new JLabel("Minimum (\u00B0F)");
		lblDebordMin.setBounds(17, 30, 87, 14);
		pnlBounds.add(lblDebordMin);

		JLabel lblDebordMaximum = new JLabel("Maximum (\u00B0F)");
		lblDebordMaximum.setBounds(17, 109, 79, 14);
		pnlBounds.add(lblDebordMaximum);

		minSlider = DataMySQL.getMin((String) stadeChoix.getSelectedItem());
		maxSlider = DataMySQL.getMax((String) stadeChoix.getSelectedItem());

		JSlider sliderMin = new JSlider();
		sliderMin.setMajorTickSpacing(1);
		sliderMin.setSnapToTicks(true);
		sliderMin.setPaintLabels(true);
		sliderMin.setPaintTicks(true);
		sliderMin.setValue(minSlider);
		sliderMin.setMaximum(55);
		sliderMin.setMinimum(50);
		sliderMin.setLabelTable(labels);
		sliderMin.setBounds(17, 55, 240, 43);
		pnlBounds.add(sliderMin);

		JSlider sliderMax = new JSlider();
		sliderMax.setMajorTickSpacing(1);
		sliderMax.setSnapToTicks(true);
		sliderMax.setPaintLabels(true);
		sliderMax.setPaintTicks(true);
		sliderMax.setValue(maxSlider);
		sliderMax.setMaximum(55);
		sliderMax.setMinimum(50);
		sliderMin.setLabelTable(labels);
		sliderMax.setBounds(17, 130, 240, 43);
		pnlBounds.add(sliderMax);

		JButton btnDebord = new JButton("D\u00E9bord");
		btnDebord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int min = sliderMin.getValue();
				int max = sliderMax.getValue();
				if (min < max) {
					try {
						DataMySQL.setDebord(min, max, (String) stadeChoix.getSelectedItem());
						JOptionPane.showMessageDialog(pnlBounds, "Débord mis à jour !");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(pnlBounds, "Min >= Max !");
				}
			}
		});
		btnDebord.setBounds(262, 26, 79, 23);
		pnlBounds.add(btnDebord);

		JButton btnSlider = new JButton("Actualiser");
		btnSlider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sliderMin.setValue(minSlider);
				sliderMax.setValue(maxSlider);
			}
		});
		btnSlider.setBounds(163, 26, 89, 23);
		pnlBounds.add(btnSlider);

		JLabel lbAlerte = new JLabel();
		lbAlerte.setIcon(new ImageIcon("img\\s_green_button.png"));
		lbAlerte.setBounds(266, 55, 75, 75);
		pnlBounds.add(lbAlerte);

		JLabel lblNewLabel = new JLabel("Logo");
		lblNewLabel.setIcon(new ImageIcon(
				"C:\\Users\\uti311\\Documents\\LocalRepository\\VinciThermoGreen\\img\\Logo_Vinci_Concessions.gif"));
		lblNewLabel.setBounds(10, 25, 250, 90);
		getContentPane().add(lblNewLabel);

		JLabel lbConnecte = new JLabel("Connect\u00E9 en tant que : ");
		lbConnecte.setBounds(489, 25, 159, 14);
		getContentPane().add(lbConnecte);

		JLabel lbUsername = new JLabel(username);
		lbUsername.setBounds(658, 25, 86, 14);
		getContentPane().add(lbUsername);

		JLabel lbRole = new JLabel();
		lbRole.setBounds(658, 50, 86, 14);
		if (role) {
			lbRole.setText("Administrateur");
		} else {
			lbRole.setText("Utilisateur");
		}
		getContentPane().add(lbRole);

		JButton btnAdministrer = new JButton("Administrer");
		btnAdministrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					admin = new Admin();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAdministrer.setBounds(512, 50, 107, 23);
		getContentPane().add(btnAdministrer);

		JButton btnDeconnexion = new JButton("Quitter");
		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnDeconnexion.setBounds(647, 75, 118, 23);
		getContentPane().add(btnDeconnexion);
	}

	protected void verifDebord(int minSlider, int maxSlider, String stade) throws SQLException, MessagingException {
		for (Mesure mesure : lesMesures) {
			if(mesure.getFahrenheit()<(float) minSlider || mesure.getFahrenheit()>(float) maxSlider) {
				//Mail du destinataire à récupérer dans la base de donnée. Ici, je mets mon mail pour m'envoyer le mail à moi-même.
				MailUtil.sendMail("alexis2massa@gmail.com", stade);
				//SMS du destinataire à récupérer dans la base de donnée. Ici, je mets mon numéro pour m'envoyer le SMS à moi-même.
				SmsUtil.sendSMS("+3368813760" ,stade);
			}
		}
	}

	/**
	 * Préparation de ConsoleGUI
	 * 
	 * @param console
	 * @throws ParseException
	 * @throws SQLException
	 */
	public void setUp(ConsoleGUI console) throws ParseException, SQLException {
		console.updateData("Stade de Paris");
		// Gestion des mesures

		// Demande l'acquisition des data
		lesMesures = getLesMesures();

		// Construit le tableau d'objet
		// laTable = setTable(lesMesures);

		// Definit le JScrollPane qui va recevoir la JTable
		scrollPane.setViewportView(laTable);
		// affiche le graphique
		console.setChart();

		console.setLocation(100, 100);
		System.out.println("Création IHM");
		setVisible(true);

	}

	/**
	 * <p>
	 * Transfert les données de la collection vers un tableau d'objets
	 * </p>
	 * <p>
	 * La température est en degré Fahrenheit
	 * </p>
	 * 
	 * @param ArrayList<Mesure>
	 * @return Object[][]
	 */
	private static JTable setTable(ArrayList<Mesure> mesures) {

		float min = 0;
		float max = 0;
		float moy = 0;
		DecimalFormat round = new DecimalFormat("0.##");
		Object[][] dataTable = new Object[mesures.size()][3];

		if (rdbtnCelsius.isSelected()) {

			// System.out.println("Celsius : " + rdbtnCelsius.isSelected() + " | " +
			// mesures.size());

			// Initialisation de min et max
			min = mesures.get(0).getCelsius();
			max = mesures.get(0).getCelsius();

			for (int i = 0; i < mesures.size(); i++) {

				uneMesure = lesMesures.get(i);
				dataTable[i][0] = uneMesure.getNumZone();
				dataTable[i][1] = uneMesure.getHoroDate();
				dataTable[i][2] = round.format(uneMesure.getCelsius());

				// Min, max et moy
				moy = moy + uneMesure.getCelsius();

				if (uneMesure.getCelsius() < min) {
					min = uneMesure.getCelsius();
				}
				if (uneMesure.getCelsius() > max) {
					max = uneMesure.getCelsius();
				}
			}
		} else {

			// System.out.println("Celsius : " + rdbtnCelsius.isSelected() + " | " +
			// mesures.size());

			// Initialisation de min et max
			min = mesures.get(0).getFahrenheit();
			max = mesures.get(0).getFahrenheit();

			for (int i = 0; i < mesures.size(); i++) {
				uneMesure = lesMesures.get(i);
				dataTable[i][0] = uneMesure.getNumZone();
				dataTable[i][1] = uneMesure.getHoroDate();
				dataTable[i][2] = round.format(uneMesure.getFahrenheit());

				// Min, max et moy
				moy = moy + uneMesure.getFahrenheit();

				if (uneMesure.getFahrenheit() < min) {
					min = uneMesure.getFahrenheit();
				}
				if (uneMesure.getCelsius() > max) {
					max = uneMesure.getFahrenheit();
				}
			}
		}

		String[] titreColonnes = { "Zone", "Date-heure", "T°" };
		JTable uneTable = new JTable(dataTable, titreColonnes);
		// Les données de la JTable ne sont pas modifiables
		uneTable.setEnabled(false);

		// Arroundi et affecte les zones texte min, max et moy
		tempMin.setText(round.format(min));
		tempMax.setText(round.format(max));
		moy = moy / mesures.size();
		tempMoy.setText(round.format(moy));

		return uneTable;
	}

	// TODO factoriser le code avec setTable
	// TODO gérer le choix du graphique
	/**
	 * <p>
	 * Impl&eacute;mente la biblioth&egrave;que JFreeChart :
	 * </p>
	 * <ol>
	 * <li>d&eacute;finit le type de container de donn&eacute;es -&gt;
	 * DefaultCategoryDataset</li>
	 * <li>alimente le container des donn&eacute;es</li>
	 * <li>Fabrique un graphique lin&eacute;aire -&gt;
	 * ChartFactory.createLineChart</li>
	 * <li>Englobe le graphique dans un panel sp&eacute;cifique -&gt; new
	 * ChartPanel(chart)</li>
	 * <li>Englobe ce panel dans un JPanel de l'IHM -&gt;
	 * pnlGraph.add(chartPanel)<br />
	 * </li>
	 * </ol>
	 * 
	 * @author Jérôme Valenti
	 * @see JFreeChart
	 */
	public void setChart() {

		int i1 = 0, i2 = 0, i3 = 0, i4 = 0;
		DefaultCategoryDataset dataChart = new DefaultCategoryDataset();

		// Set data ((Number)temp,zone,dateHeure)
		for (int i = 0; i < lesMesures.size(); i++) {

			uneMesure = lesMesures.get(i);

			switch (uneMesure.getNumZone()) {
			case "1":
				dataChart.addValue((Number) uneMesure.getCelsius(), uneMesure.getNumZone(), i1);
				i1++;
				break;
			case "2":
				dataChart.addValue((Number) uneMesure.getCelsius(), uneMesure.getNumZone(), i2);
				i2++;
				break;
			case "3":
				dataChart.addValue((Number) uneMesure.getCelsius(), uneMesure.getNumZone(), i3);
				i3++;
				break;
			case "4":
				dataChart.addValue((Number) uneMesure.getCelsius(), uneMesure.getNumZone(), i4);
				i4++;
				break;
			default:
				break;
			}
		}

		// un bouchon pour tester
		// Set data ((Number)temp,zone,dateHeure)
//        dataChart.addValue((Number)1.0, "01", 1);
//        dataChart.addValue((Number)5.0, "02", 1);
//        dataChart.addValue((Number)4.0, "01", 2);
//        dataChart.addValue((Number)7.0, "02", 2);
//        dataChart.addValue((Number)3.0, "01", 3);
//        dataChart.addValue((Number)6.0, "02", 3);
//        dataChart.addValue((Number)5.0, "01", 4);
//        dataChart.addValue((Number)8.0, "02", 4);
//        dataChart.addValue((Number)5.0, "01", 5);
//        dataChart.addValue((Number)4.0, "02", 5);
//        dataChart.addValue((Number)7.0, "01", 6);
//        dataChart.addValue((Number)4.0, "02", 6);
//        dataChart.addValue((Number)7.0, "01", 7);
//        dataChart.addValue((Number)2.0, "02", 7);
//        dataChart.addValue((Number)8.0, "01", 8);
//        dataChart.addValue((Number)1.0, "02", 8);
//		System.out.println(dataChart.getRowCount() + " lignes " + dataChart.getColumnCount() + " colonnes");

		JFreeChart chart = ChartFactory.createLineChart(null, // chart title
				"Heure", // domain axis label
				"Températures", // range axis label
				dataChart, // data
				PlotOrientation.VERTICAL, // orientation
				true, // include legend
				true, // tooltips
				false // urls
		);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBounds(5, 20, 320, 190);
		chartPanel.setVisible(true);
		pnlGraph.add(chartPanel);
		// System.out.println("chartPanel added to pnlGraph");
	}

	/**
	 * <p>
	 * Classe interne qui gère le clique sur le bouton filtrer
	 * 
	 * @author Jérôme Valenti
	 */
	class filtrerData implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			lesMesures = filtrerLesMesure(choixZone.getSelectedItem().toString());
//			System.out.println(
//					"Filtrer Celsius : " + rdbtnCelsius.isSelected() + " Fahrenheit : " + rdbtnFahrenheit.isSelected()
//							+ " choix : " + choixZone.getSelectedItem() + " début : " + dateDebut.getText());
			// displayLesMesures(lesMesures);

			// Construit le tableau d'objet
			laTable = setTable(lesMesures);

			// Definit le JScrollPane qui va recevoir la JTable
			scrollPane.setViewportView(laTable);

			// System.out.println("Before setChart in filtrerData()");
			// affiche le graphique
			setChart();
			// System.out.println("After setChart in filtrerData()");
		}
	}

	/**
	 * Méthode de vérification des données les affichant dans la console
	 * 
	 * @param uneCollection
	 */
	private void displayLesMesures(ArrayList<Mesure> uneCollection) {

		for (int i = 0; i < uneCollection.size(); i++) {
			System.out.println(i + " " + uneCollection.get(i).getNumZone() + " | " + uneCollection.get(i).getHoroDate()
					+ " | " + uneCollection.get(i).getCelsius());
		}
	}

	public void updateData(String selectedStadium) throws ParseException, SQLException {

		// Chaque ligne est un enregistrement de données
		// Chaque enregistrement contient des champs
		String[] fields = null;
		String numZone = null;
		Date horoDate = null;
		float fahrenheit;
		// Clear all datas
		allData.clear();
		// Traitement les champs de la requête
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
	 * Filtrage des données en fonction de la zone selectionnée
	 * 
	 * @param laZone
	 * @return ArrayList<Mesure> laSelection
	 */
	public ArrayList<Mesure> filtrerLesMesure(String laZone) {
		// Parcours de la collection
		// Ajout à laSelection des objets qui correspondent aux paramètres
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
	public static ArrayList<Mesure> getLesMesures() {
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
	 * 
	 * @return ArrayList of stadiums
	 * @throws SQLException
	 */
	public ArrayList<String> getAllStadiums() throws SQLException {
		allStadiums = DataMySQL.getAllStadiums();
		return allStadiums;
	}
}