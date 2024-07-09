package fr.diginamic.hello.controleurs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.hello.dto.VilleDto;
import fr.diginamic.hello.entity.Departement;
import fr.diginamic.hello.entity.Ville;
import fr.diginamic.hello.exception.RestException;
import fr.diginamic.hello.service.VilleService;

@RestController
@RequestMapping("/villes")
public class VilleControlleur {

    @Autowired
    private VilleService villeService;

    /** Route pour obtenir la liste des villes avec une pagination
     * @param page, le numéro de la page
     * @param size, le nombre d'élément par page
     * @return Une page d'objet VilleDto
     * @throws RestException si le résultat est null
     */
    @GetMapping("/pagination")
    public Page<VilleDto> displayVille(@RequestParam int page, @RequestParam int size) throws RestException {
    	Page<VilleDto> villeDtoPage=villeService.displayVille(page, size);
    	if(villeDtoPage.isEmpty()) {
    		throw  new RestException("Aucune ville trouvée");
    	}
    	return villeDtoPage;
    }

    /** Route pour obtenir une ville avec un identifiant
     * @param id, l'identifiant de la ville
     * @return Un objet VilleDto
     * @throws RestException si le résultat est null
     */
    @GetMapping("/{id}")
    public VilleDto displayVilleById(@PathVariable Long id) throws RestException {
    	VilleDto villeDto=villeService.displayVilleById(id);
    	if(villeDto == null) {
    		throw  new RestException("Aucune ville trouvée");
    	}
    	return villeDto;
    }
    
    /** Route pour obtenir une ville avec son nom
     * @param nom, le nom de la ville recherché
     * @return Un objet VilleDto
     * @throws RestException si le résultat est null
     */
    @GetMapping("/nom/{nom}")
    public VilleDto displayVilleByNom(@PathVariable String nom) throws RestException {
    	VilleDto villeDto=villeService.displayVilleByNom(nom);
    	if(villeDto == null) {
    		throw  new RestException("Aucune ville trouvée");
    	}
    	return villeDto;
    }
    /** Route pour obtenir une liste de villes commençant par certaines lettres
     * @param prefix, le debut du nom des villes recherchées
     * @return Une liste d'objet VilleDto
     * @throws RestException si le résultat est null
     */
    @GetMapping("/prefix/{prefix}")
    public List<VilleDto> displayVilleByPrefix(@PathVariable String prefix) throws RestException {
    	List<VilleDto> villesDto= villeService.displayVilleByPrefix(prefix);
    	if(villesDto.isEmpty()) {
    		throw  new RestException("Aucune ville trouvée");
    	}
    	return villesDto;
    }
    
    /** Route pour obtenir une liste de villes avec au moins N habitant
     * @param minPopulation, nombre minimum d'habitant
     * @return Une liste d'objet VilleDto
     * @throws RestException si le résultat est null
     */
    @GetMapping("/nbHabitantMin/{minPopulation}")
    public List<VilleDto> displayVilleByPopulationMin(@PathVariable int minPopulation) throws RestException {
    	List<VilleDto> villesDto= villeService.displayVilleByPopulationMin(minPopulation);
    	if(villesDto.isEmpty()) {
    		throw  new RestException("Aucune ville trouvée");
    	}
    	return villesDto;
    }
    
    /** Route pour obtenir une liste de villes avec au moins N habitant et moins de N2 habitant
     * @param minPopulation, nombre minimum d'habitant
     * @param maxPopulation, nombre maximum d'habitant
     * @return Une liste d'objet VilleDto
     * @throws RestException si le résultat est null
     */
    @GetMapping("/nbHabitant/{minPopulation}/{maxPopulation}")
    public List<VilleDto> displayVilleByNbHabitantGreaterAndLessThan(@PathVariable int minPopulation, @PathVariable int maxPopulation) throws RestException {
    	List<VilleDto> villesDto= villeService.displayVilleByNbHabitantGreaterAndLessThan(minPopulation, maxPopulation);
    	if(villesDto.isEmpty()) {
    		throw  new RestException("Aucune ville trouvée");
    	}
    	return villesDto;
    }
    /** Route pour obtenir une liste de villes d'un departement avec au moins N habitant
     * @param minPopulation, nombre minimum d'habitant
     * @param departement, le département concerné
     * @return Une liste d'objet VilleDto
     * @throws RestException si le résultat est null
     */
    @GetMapping("/departement/nbHabitantMin/{minPopulation}")
    public List<VilleDto> displayVilleByDepartementNomAndNbHabitantGreaterThan(@PathVariable int minPopulation, @RequestParam Departement departement) throws RestException {
    	List<VilleDto> villesDto= villeService.displayVilleByDepartementNomAndNbHabitantGreaterThan(minPopulation, departement);
    	if(villesDto.isEmpty()) {
    		throw  new RestException("Aucune ville trouvée");
    	}
    	return villesDto;
    }
    
    /** Route pour obtenir une liste de villes d'un departement avec au moins N habitant moins de N2 habitant
     * @param minPopulation, nombre minimum d'habitant
     * @param maxPopulation, nombre maximum d'habitant
     * @param departement, le département concerné
     * @return Une liste d'objet VilleDto
     * @throws RestException si le résultat est null
     */
    @GetMapping("/departement/nbHabitantMin/{minPopulation}/{maxPopulation}")
    public List<VilleDto> displayVilleByDepartementAndNbHabitantGreaterAndLessThan(@PathVariable int minPopulation, @PathVariable int maxPopulation, @RequestParam Departement departement) throws RestException {
    	List<VilleDto> villesDto= villeService.displayVilleByDepartementAndNbHabitantGreaterAndLessThan(minPopulation, maxPopulation,departement);
    	if(villesDto.isEmpty()) {
    		throw  new RestException("Aucune ville trouvée");
    	}
    	return villesDto;
    }
    /** Route pour obtenir une liste des plus grande villes d'un departement 
     * @param nombre, nombre de résultat attendu
     * @param departement, le département concerné
     * @return Une liste d'objet VilleDto
     * @throws RestException si le résultat est null
     */
    @GetMapping("/departement/top/{nombre}")
    public List<VilleDto> displayTopNVillesByDepartement(@RequestParam Departement departement,  @PathVariable int nombre) throws RestException {
    	List<VilleDto> villesDto= villeService.displayTopNVillesByDepartement(departement,nombre);
    	if(villesDto.isEmpty()) {
    		throw  new RestException("Aucune ville trouvée");
    	}
    	return villesDto;
    }

    /** Route pour sauvegarder une ville 
     * @param ville, l'objet a sauvegarder
     * @return ResponseEntity, un message pour le front
     */
    @PostMapping
    public ResponseEntity<String> saveVille(@RequestBody VilleDto ville) throws RestException {
        Ville villeAjouter = villeService.save(ville);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
    /** Route pour modifier une ville 
     * @param id, l'id de la ville a modifié
     * @param ville, les modifications apportées
     * @return ResponseEntity, un message pour le front 
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> modifyVille(@RequestBody VilleDto ville, @PathVariable Long id) throws RestException{
        Ville villeModifier = villeService.modify(ville,id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
    
    /** Route pour supprimer une ville 
     * @param id, l'id de la ville a supprimer
     * @return ResponseEntity, un message pour le front 
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVille(@PathVariable Long id) throws RestException {
       villeService.delete(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}