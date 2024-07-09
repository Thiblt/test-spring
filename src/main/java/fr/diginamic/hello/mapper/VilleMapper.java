package fr.diginamic.hello.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.hello.dto.VilleDto;
import fr.diginamic.hello.entity.Departement;
import fr.diginamic.hello.entity.Ville;
import fr.diginamic.hello.repository.DepartementRepository;

@Service
public class VilleMapper {
	@Autowired
	private DepartementRepository departementRepo;
	
	public Ville dtoToBean(VilleDto ville){
		Ville newVille= new Ville();
		Departement d=departementRepo.findByCodeDepartement(ville.getCodeDepartement());
		newVille.setDepartement(d);
		newVille.setNom(ville.getNom());
		newVille.setNbHabitant(ville.getNbHabitant());
		return newVille;
		
	}
	
	public VilleDto BeanToDto(Ville ville){
		VilleDto newVille= new VilleDto();
		newVille.setCodeDepartement(ville.getDepartement().getCodeDepartement());
		newVille.setNom(ville.getNom());
		newVille.setNbHabitant(ville.getNbHabitant());
		return newVille;
		
	}

}
