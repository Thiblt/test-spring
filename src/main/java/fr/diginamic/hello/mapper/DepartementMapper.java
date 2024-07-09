package fr.diginamic.hello.mapper;

import org.springframework.stereotype.Service;

import fr.diginamic.hello.dto.DepartementDto;
import fr.diginamic.hello.entity.Departement;

@Service
public class DepartementMapper {

	
	public Departement dtoToBean(DepartementDto dep){
		Departement newDep= new Departement();
		newDep.setNom(dep.getNom());
		newDep.setCodeDepartement(dep.getCodeDepartement());
		return newDep;
		
	}
	
	public DepartementDto BeanToDto(Departement dep){
		DepartementDto newDep= new DepartementDto();
		newDep.setCodeDepartement(dep.getCodeDepartement());
		newDep.setNom(dep.getNom());
		return newDep;
		
	}

}
