package model;

public class Alerte {
	private int num_stade;
	private int nom_zone;
	private float farenheit;
	private String destinataire;
	
	public int getNum_stade() {
		return num_stade;
	}
	public void setNum_stade(int num_stade) {
		this.num_stade = num_stade;
	}
	public int getNom_zone() {
		return nom_zone;
	}
	public void setNom_zone(int nom_zone) {
		this.nom_zone = nom_zone;
	}
	public float getFarenheit() {
		return farenheit;
	}
	public void setFarenheit(float farenheit) {
		this.farenheit = farenheit;
	}
	public String getDestinataire() {
		return destinataire;
	}
	public void setDestinataire(String destinataire) {
		this.destinataire = destinataire;
	}
	
}
