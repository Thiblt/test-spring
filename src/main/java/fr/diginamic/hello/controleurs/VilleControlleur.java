package fr.diginamic.hello.controleurs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import fr.diginamic.hello.entity.Ville;
import fr.diginamic.hello.repository.VilleRepository;

@RestController
@RequestMapping("/villes")
public class VilleControlleur {

    @Autowired
    private VilleRepository villeRepository;

    @GetMapping("/pagination")
    public Page<Ville> displayVille(@RequestParam int page, @RequestParam int size) {
    	Pageable pagination = PageRequest.of(page, size);
    	return villeRepository.findAll(pagination);
    }

    @GetMapping("/{id}")
    public Ville displayVilleById(@PathVariable Long id) {
        return villeRepository.findById(id);
    }

    @GetMapping("/nom/{nom}")
    public Ville displayVilleByNom(@PathVariable String nom) {
        return villeRepository.findByNom(nom);
    }
    
    @GetMapping("/prefix/{prefix}")
    public List<Ville> displayVilleByPrefix(@PathVariable String prefix) {
        return villeRepository.findByNomStartingWith(prefix);
    }
    @GetMapping("/nbHabitantMin/{minPopulation}")
    public List<Ville> displayVilleByPopulationMin(@PathVariable int minPopulation) {
        return villeRepository.findByNbHabitantGreaterThan(minPopulation);
    }
    @GetMapping("/nbHabitant/{minPopulation}/{maxPopulation}")
    public List<Ville> displayVilleByNom(@PathVariable int minPopulation, @PathVariable int maxPopulation) {
        return villeRepository.findByNbHabitantGreaterThanAndNbHabitantLessThan(minPopulation,maxPopulation);
    }
    @GetMapping("/departement/{departement}/nbHabitantMin/{minPopulation}")
    public List<Ville> displayVilleByNom(@PathVariable int minPopulation, @PathVariable String departement) {
        return villeRepository.findByDepartementNomAndNbHabitantGreaterThan(departement, minPopulation);
    }
    @GetMapping("/departement/{departement}/nbHabitantMin/{minPopulation}/{maxPopulation}")
    public List<Ville> displayVilleByNom(@PathVariable int minPopulation, @PathVariable int maxPopulation, @PathVariable String departement) {
        return villeRepository.findByDepartementNomAndNbHabitantGreaterThanAndNbHabitantLessThan(departement,minPopulation,maxPopulation);
    }
    @GetMapping("/departement/{departement}/top/{nombre}")
    public List<Ville> displayVilleByNom(@PathVariable String departement,  @PathVariable int nombre) {
        return villeRepository.findTopNVillesByDepartement(departement, nombre);
    }


    @PostMapping
    public ResponseEntity<String> saveVille(@RequestBody Ville ville) {
        Ville villeAjouter = villeRepository.save(ville);
        if (villeAjouter == null) {
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modifyVille(@RequestBody Ville ville, @PathVariable Long id) {
        Ville villeModifier = villeRepository.findById(id);
        if (villeModifier == null) {
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
        villeModifier.setNbHabitant(ville.getNbHabitant());
        villeModifier.setNom(ville.getNom());
        villeModifier.setDepartement(ville.getDepartement());
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVille(@PathVariable Long id) {
        Ville villeSupprimer = villeRepository.findById(id);
        if (villeSupprimer == null) {
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
        villeRepository.delete(villeSupprimer);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}