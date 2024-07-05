package fr.diginamic.hello.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.hello.dao.VilleDao;
import fr.diginamic.hello.entity.Departement;
import fr.diginamic.hello.entity.Ville;
import fr.diginamic.hello.repository.DepartementRepository;
import fr.diginamic.hello.repository.VilleRepository;

@Service
public class VilleService {
	@Autowired
	private VilleDao villeDao;
	
	@Autowired
	private VilleRepository villeRepo;
	
	@Autowired
	private DepartementRepository departementRepo;
	
	public List<Ville> extractVilles(){
		
		return villeDao.extractVilles();
	}
	
	public Ville extractVille(Long idVille) {
		return villeDao.extractVille(idVille);
	}
	
	public Ville extractVille(String nom) {
		return villeDao.extractVille(nom);
	}
	
	public Ville insertVille(Ville ville){
		Ville villeBDD =villeDao.extractVille(ville.getId());
		if (villeBDD==null) {
			villeDao.insertVille(ville);
			return ville;
		}
		else {
			return null;
		}
		
	}
	
	public Ville modifierVille(Long idVille, Ville villeModifiee){
		return villeDao.modifyVille(idVille, villeModifiee);
		
		
	}
	

	public Ville supprimerVille(Long idVille){
		return villeDao.deleteVille(idVille);
	}
	
	public List<Ville> extractPlusGrandeVilleDepartement(String departement, int nombre) {
		return villeDao.extractPlusGrandeVilleDepartement(departement, nombre);
	}
	public List<Ville> villesByPopulationRangeAndDepartement(String departement, int min, int max) {
		return villeDao.findVillesByPopulationRangeAndDepartement(departement, min, max);
	}
	
	public void saveVillefromCsv(List<String> lignes) {for (int i = 1; i < lignes.size(); i++) {
        String[] elements = lignes.get(i).split(";", -1);
       
            Ville ville = new Ville();
           
            ville.setNom(elements[6]);
            ville.setNbHabitant(Integer.parseInt(elements[9]));
            Departement departement= departementRepo.findByCodeDepartement(Integer.parseInt(elements[2]));
            if(departement ==null) {
            	departement=new Departement();
            	departement.setCodeDepartement(Integer.parseInt(elements[2]));
            	departementRepo.save(departement);
            }
            ville.setDepartement(departement);
            
           villeRepo.save(ville);
        }
    }
	
}
