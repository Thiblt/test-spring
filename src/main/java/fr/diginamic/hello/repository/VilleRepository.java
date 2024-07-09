package fr.diginamic.hello.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.diginamic.hello.entity.Departement;
import fr.diginamic.hello.entity.Ville;
@Repository
public interface VilleRepository extends CrudRepository<Ville, Integer> {
	Ville findByNom(String nom);
	
	List<Ville> findBy(); // équivalent de findAll()
	
	List<Ville> findByNomStartingWith(String prefix);
	
	
	List<Ville> findByNbHabitantGreaterThan(int minPopulation);
	
	List<Ville> findByNbHabitantGreaterThanAndNbHabitantLessThan(int minPopulation, int maxPopulation);


    List<Ville> findByDepartementNomAndNbHabitantGreaterThan(Departement departementNom, int minPopulation);

    List<Ville> findByDepartementNomAndNbHabitantGreaterThanAndNbHabitantLessThan(Departement departementNom, int minPopulation, int maxPopulation);

    @Query("SELECT v FROM Ville v WHERE v.departement.nom = :departementNom ORDER BY v.nbHabitant DESC")
    List<Ville> findTopNVillesByDepartement(@Param("departementNom") String departementNom);

	Ville findById(Long id);

	Page<Ville> findAll(Pageable pagination);

}
