package fr.diginamic.hello.entity;

public class Ville {
	private String nom;
	private int nbHabitant;
	private int id;
	
	
	public Ville(String nom, int nbHabitant, int id) {
		this.nom = nom;
		this.nbHabitant = nbHabitant;
		this.id= id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getNbHabitant() {
		return nbHabitant;
	}
	public void setNbHabitant(int nbHabitant) {
		this.nbHabitant = nbHabitant;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

}
