package fr.diginamic.hello.controleurs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.hello.entity.Departement;
import fr.diginamic.hello.entity.Ville;
import fr.diginamic.hello.service.DepartementService;

@RestController
@RequestMapping("/departements")
public class DepartementControlleur {
	 @Autowired
	    private DepartementService departementService;

	    @GetMapping
	    public List<Departement> displayVille() {
	        return departementService.extractDepartements();
	    }

	    @GetMapping("/{id}")
	    public Departement displayVilleById(@PathVariable Long id) {
	        return departementService.extractDepartement(id);
	    }

	    @GetMapping("/nom/{nom}")
	    public Departement displayVilleByNom(@PathVariable String nom) {
	        return departementService.extractDepartement(nom);
	    }

	    @PostMapping
	    public ResponseEntity<String> saveDepartement(@RequestBody Departement departement) {
	    	Departement departementAjouter = departementService.insertDepartement(departement);
	        if (departementAjouter == null) {
	            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
	        }
	        return new ResponseEntity<>("Success", HttpStatus.CREATED);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<String> modifyDepartement(@RequestBody Departement departement, @PathVariable Long id) {
	    	Departement departementModifier = departementService.modifierDepartement(id, departement);
	        if (departementModifier == null) {
	            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
	        }
	        return new ResponseEntity<>("Success", HttpStatus.OK);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<String> deleteVille(@PathVariable Long id) {
	    	Departement departementSupprimer = departementService.supprimerDepartement(id);
	        if (departementSupprimer == null) {
	            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
	        }
	        return new ResponseEntity<>("Success", HttpStatus.OK);
	    }

}
