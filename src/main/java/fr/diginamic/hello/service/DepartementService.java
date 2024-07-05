package fr.diginamic.hello.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.hello.entity.Departement;
import fr.diginamic.hello.repository.DepartementRepository;

@Service
public class DepartementService {
	@Autowired
	private DepartementRepository departementRepo;
	
	public List<Departement> extractDepartements(){
		
		return departementRepo.findAll();
	}
	
	public Departement extractDepartement(Long id) {
		return departementRepo.findById(id);
	}
	
	public Departement extractDepartement(String nom) {
		return departementRepo.findByNom(nom);
	}
	
	public Departement insertDepartement(Departement departement){
		Departement departementBDD =departementRepo.findById(departement.getId());
		if (departementBDD==null) {
			departementRepo.save(departement);
			return departement;
		}
		else {
			return null;
		}
		
	}
	
	public Departement modifierDepartement(Long id, Departement departementModifiee){
		Departement d =departementRepo.findById(id);
		if (d==null) {
			return null;
		}
		d.setNom(departementModifiee.getNom());
		return d;
		
		
	}
	

	public Departement supprimerDepartement(Long id){
		Departement d =departementRepo.findById(id);
		if (d==null) {
			return null;
		}
		departementRepo.delete(d);
		return d;
	}
	
}



