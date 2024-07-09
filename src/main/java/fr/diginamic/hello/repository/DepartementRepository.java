package fr.diginamic.hello.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.hello.entity.Departement;


@Repository
public interface DepartementRepository extends CrudRepository<Departement, Integer> {
	Departement findByNom(String nom);
	
	List<Departement> findAll();
	Departement findById(Long id);
	Departement findByCodeDepartement(String codeDepartement);
}
