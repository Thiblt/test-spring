package fr.diginamic.hello.controleurs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.hello.entity.Ville;

@RestController 
@RequestMapping("/villes") 
public class VilleControlleur {
	private List<Ville> villes = new ArrayList<>(Arrays.asList(
	        new Ville("Nice", 343000, 1),
	        new Ville("Carcassonne", 47800, 2),
	        new Ville("Narbonne", 53000, 3),
	        new Ville("Lyon", 484000, 4),
	        new Ville("Foix", 9700, 5),
	        new Ville("Pau", 72200, 6),
	        new Ville("Marseille", 850700,7),
	        new Ville("Tarbes", 40600, 8)
	    ));
	
	@GetMapping public List<Ville> diplayVille(){ 
		
		return villes;
		} 
	
   @GetMapping(path= "/{id}")
   public Ville diplayVilleById(@PathVariable Long id){
	   for(Ville villeData: villes) {
			if(id== villeData.getId()){
				 return villeData;
			}
		}
		return null;
		
		} 
	
	@PostMapping
	public ResponseEntity<String> saveVille(@RequestBody Ville ville){
		System.out.println("coucou");
		boolean idExistant =false;
	for(Ville villeData: villes) {
		if(ville.getNom().equals(villeData.getNom())){
			 return ResponseEntity.ok("Ville existe déjà");
		}
		if(ville.getId()== villeData.getId())
		{idExistant=true;}
	}
	if(!idExistant) {
		villes.add(ville);
		return ResponseEntity.ok("Succes!");
		
	}
	
	return ResponseEntity.ok("Id déja existant");
	}
	
	@PutMapping(path= "/{id}")
	public ResponseEntity<String> modifyVille(@RequestBody Ville ville, @PathVariable Long id){
	for(Ville villeData: villes) {
		if(id== villeData.getId())
		{villeData.setNom(ville.getNom());
		villeData.setId(ville.getId());
		villeData.setNbHabitant(ville.getNbHabitant());
		return ResponseEntity.ok("Succes!");}
	}
	
	return ResponseEntity.ok("Id non trouvé");
	}
	
	@DeleteMapping(path= "/{id}")
	public ResponseEntity<String> deleteVille(@PathVariable Long id){
	for(Ville villeData: villes) {
		if(id== villeData.getId()) {
			villes.remove(villeData);
			
		
		return ResponseEntity.ok("Succes!");
		}
	}
	
	return ResponseEntity.ok("Id non trouvé");
	}
}