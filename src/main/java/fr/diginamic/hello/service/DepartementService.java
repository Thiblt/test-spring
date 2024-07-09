package fr.diginamic.hello.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.hello.dto.DepartementDto;
import fr.diginamic.hello.entity.Departement;
import fr.diginamic.hello.mapper.DepartementMapper;
import fr.diginamic.hello.repository.DepartementRepository;

@Service
public class DepartementService {
	@Autowired
	private DepartementRepository departementRepo;
	@Autowired
	private DepartementMapper departementMapper;
	
	public List<DepartementDto> extractDepartements(){
		
		List<Departement>deps= departementRepo.findAll();
		List<DepartementDto>depsDto =new ArrayList();
		for(Departement dep: deps) {
			DepartementDto depDto =departementMapper.BeanToDto(dep);
			depsDto.add(depDto);
		}
		return depsDto;
	}
	
	public DepartementDto extractDepartement(Long id) {
		return departementMapper.BeanToDto(departementRepo.findById(id));
	}
	
	public DepartementDto extractDepartement(String nom) {
		return departementMapper.BeanToDto(departementRepo.findByNom(nom));
	}
	
	public Departement insertDepartement(DepartementDto departementDto){
		Departement departement=departementMapper.dtoToBean(departementDto);
		Departement departementBDD =departementRepo.findById(departement.getId());
		if (departementBDD==null) {
			departementRepo.save(departement);
			return departement;
		}
		else {
			return null;
		}
		
	}
	
	public Departement modifierDepartement(Long id, DepartementDto departementModifiee){
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



