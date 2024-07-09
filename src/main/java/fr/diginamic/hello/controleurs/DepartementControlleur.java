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

import fr.diginamic.hello.dto.DepartementDto;
import fr.diginamic.hello.entity.Departement;
import fr.diginamic.hello.exception.RestException;
import fr.diginamic.hello.service.DepartementService;

@RestController
@RequestMapping("/departements")
public class DepartementControlleur {
	 @Autowired
	    private DepartementService departementService;

	    @GetMapping
	    public List<DepartementDto> displayVille() throws RestException {
	        List<DepartementDto> departements= departementService.extractDepartements();
	        if(departements.isEmpty()) {
	    		throw  new RestException("Aucun departement trouvée");
	    	}
	    	return departements;
	    }

	    @GetMapping("/{id}")
	    public DepartementDto displayVilleById(@PathVariable Long id) throws RestException {
	        DepartementDto departement= departementService.extractDepartement(id);
	        if(departement==null) {
	    		throw new RestException("Aucun departement trouvée");
	    	}
	    	return departement;
	    }

	    @GetMapping("/nom/{nom}")
	    public DepartementDto displayVilleByNom(@PathVariable String nom) throws RestException {
	    	DepartementDto departement=departementService.extractDepartement(nom);
	        if(departement==null) {
	    		throw new RestException("Aucun departement trouvée");
	    	}
	    	return departement;
	    }

	    @PostMapping
	    public ResponseEntity<String> saveDepartement(@RequestBody DepartementDto departement) throws RestException {
	    	Departement departementAjouter = departementService.insertDepartement(departement);
	        if (departementAjouter == null) {
	        	throw new RestException("le département n'a pas pu être ajouter");
	        }
	        return new ResponseEntity<>("Success", HttpStatus.CREATED);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<String> modifyDepartement(@RequestBody DepartementDto departement, @PathVariable Long id) throws RestException {
	    	Departement departementModifier = departementService.modifierDepartement(id, departement);
	        if (departementModifier == null) {
	        	throw new RestException("le département n'a pas pu être modifié");
	        }
	        return new ResponseEntity<>("Success", HttpStatus.OK);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<String> deleteVille(@PathVariable Long id) throws RestException {
	    	Departement departementSupprimer = departementService.supprimerDepartement(id);
	        if (departementSupprimer == null) {
	        	throw new RestException("le département n'a pas pu être supprimé");
	        }
	        return new ResponseEntity<>("Success", HttpStatus.OK);
	    }

}
