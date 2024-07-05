package fr.diginamic.hello.entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Departement {
	@NotNull
	@Size(min=2)
	private String nom;
	
	private int codeDepartement ;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy="departement")
	private Set<Ville> villes;
	
	public Departement() {}

	public Departement(@NotNull @Size(min = 2) String nom, Long id, int codeDepartement, Set<Ville> villes) {
		this.nom = nom;
		this.id = id;
		this.villes = villes;
		this.codeDepartement= codeDepartement;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Ville> getVilles() {
		return villes;
	}

	public void setVilles(Set<Ville> villes) {
		this.villes = villes;
	}

	public int getCodeDepartement() {
		return codeDepartement;
	}

	public void setCodeDepartement(int codeDepartement) {
		this.codeDepartement = codeDepartement;
	} 
	
	
	
}
