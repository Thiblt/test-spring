package fr.diginamic.hello.dto;

public class VilleDto {
	private int nbHabitant;
	private int codeVille;
	private String nom;
	private String codeDepartement;
	
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getNbHabitant() {
		return nbHabitant;
	}
	public void setNbHabitant(int nbHabtiant) {
		this.nbHabitant = nbHabtiant;
	}
	public int getCodeVille() {
		return codeVille;
	}
	public void setCodeVille(int codeVille) {
		this.codeVille = codeVille;
	}
	public String getCodeDepartement() {
		return codeDepartement;
	}
	public void setCodeDepartement(String codeDepartement) {
		this.codeDepartement = codeDepartement;
	}
	public VilleDto() {}
	
	public VilleDto(int nbHabitant, int codeVille, String codeDepartement) {
		this.nbHabitant = nbHabitant;
		this.codeVille = codeVille;
		this.codeDepartement = codeDepartement;
	}
	
	

}
