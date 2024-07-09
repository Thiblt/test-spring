package fr.diginamic.hello.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import fr.diginamic.hello.dto.VilleDto;
import fr.diginamic.hello.entity.Departement;
import fr.diginamic.hello.entity.Ville;
import fr.diginamic.hello.exception.RestException;
import fr.diginamic.hello.mapper.VilleMapper;
import fr.diginamic.hello.repository.DepartementRepository;
import fr.diginamic.hello.repository.VilleRepository;

@Service
public class VilleService {
	

	
	@Autowired
	private DepartementRepository departementRepo;
	
	@Autowired
    private VilleRepository villeRepository;
	
	@Autowired
	private VilleMapper villeMapper;


    /** Methode pour obtenir la liste des villes avec une pagination
     * @param page, le numéro de la page
     * @param size, le nombre d'élément par page
     * @return Une page d'objet VilleDto
     */
    public Page<VilleDto> displayVille(int page, int size) {
    	Pageable pagination = PageRequest.of(page, size);
    	Page<Ville>villes=villeRepository.findAll(pagination);
    	Page<VilleDto> villeDtoPage = villes.map(villeMapper::BeanToDto);
    	return villeDtoPage;
    }
    
    /** Methode pour obtenir une ville avec un identifiant
     * @param id, l'identifiant de la ville
     * @return Un objet VilleDto
     */
    public VilleDto displayVilleById( Long id) {
        Ville ville =villeRepository.findById(id);
        VilleDto villeDto=villeMapper.BeanToDto(ville);
        return villeDto;
    }

    /** Méthode pour obtenir une ville avec son nom
     * @param nom, le nom de la ville recherché
     * @return Un objet VilleDto
  
     */
    public VilleDto displayVilleByNom( String nom) {
    	 Ville ville =villeRepository.findByNom(nom);
    	 VilleDto villeDto=villeMapper.BeanToDto(ville);
         return villeDto;
    }
    
    /** Méthode pour obtenir une liste de villes commençant par certaines lettres
     * @param prefix, le debut du nom des villes recherchées
     * @return Une liste d'objet VilleDto
     */
    public List<VilleDto> displayVilleByPrefix(String prefix) {
    	List<Ville>villes=villeRepository.findByNomStartingWith(prefix);
        List<VilleDto> villesDto = villes.stream()
                .map(villeMapper::BeanToDto)
                .collect(Collectors.toList());
    	return villesDto;
    }
    
    /** Methode pour obtenir une liste de villes avec au moins N habitant
     * @param minPopulation, nombre minimum d'habitant
     * @return Une liste d'objet VilleDto
     */
    public List<VilleDto> displayVilleByPopulationMin(int minPopulation) {
        List<Ville>villes=villeRepository.findByNbHabitantGreaterThan(minPopulation);
        List<VilleDto> villesDto = villes.stream()
                .map(villeMapper::BeanToDto)
                .collect(Collectors.toList());
    	return villesDto;
    }
    
    /** Méthode pour obtenir une liste de villes avec au moins N habitant et moins de N2 habitant
     * @param minPopulation, nombre minimum d'habitant
     * @param maxPopulation, nombre maximum d'habitant
     * @return Une liste d'objet VilleDto
     */
    public List<VilleDto> displayVilleByNbHabitantGreaterAndLessThan(int minPopulation, int maxPopulation) {
    	List<Ville>villes= villeRepository.findByNbHabitantGreaterThanAndNbHabitantLessThan(minPopulation,maxPopulation);
        List<VilleDto> villesDto = villes.stream()
                .map(villeMapper::BeanToDto)
                .collect(Collectors.toList());
    	return villesDto;
    }
    
    /** Méthode pour obtenir une liste de villes d'un departement avec au moins N habitant
     * @param minPopulation, nombre minimum d'habitant
     * @param departement, le département concerné
     * @return Une liste d'objet VilleDto
     */
    public List<VilleDto> displayVilleByDepartementNomAndNbHabitantGreaterThan( int minPopulation, Departement departement) {
    	List<Ville>villes=villeRepository.findByDepartementNomAndNbHabitantGreaterThan(departement, minPopulation);
    	List<VilleDto> villesDto = villes.stream()
                .map(villeMapper::BeanToDto)
                .collect(Collectors.toList());
    	return villesDto;
    }
    
    /** Route pour obtenir une liste de villes d'un departement avec au moins N habitant moins de N2 habitant
     * @param minPopulation, nombre minimum d'habitant
     * @param maxPopulation, nombre maximum d'habitant
     * @param departement, le département concerné
     * @return Une liste d'objet VilleDto
     */
    public List<VilleDto> displayVilleByDepartementAndNbHabitantGreaterAndLessThan(int minPopulation,int maxPopulation, Departement departement) {
    	List<Ville>villes= villeRepository.findByDepartementNomAndNbHabitantGreaterThanAndNbHabitantLessThan(departement,minPopulation,maxPopulation);
    	List<VilleDto> villesDto = villes.stream()
                .map(villeMapper::BeanToDto)
                .collect(Collectors.toList());
    	return villesDto;
    }
   
    /** Route pour obtenir une liste des plus grande villes d'un departement 
     * @param nombre, nombre de résultat attendu
     * @param departement, le département concerné
     * @return Une liste d'objet VilleDto
     */
    public List<VilleDto> displayTopNVillesByDepartement(Departement departement, int nombre) {
    	List<Ville>villes=villeRepository.findTopNVillesByDepartement(departement.getNom());
    	List<VilleDto> villesDto = villes.stream()
                .map(villeMapper::BeanToDto)
                .limit(nombre)
                .collect(Collectors.toList());
    	return villesDto;
    }
    
    /** Methode pour sauvegarder une ville 
     * @param ville, l'objet a sauvegarder
     * @return ResponseEntity, un message pour le front 
     * @throws RestException si le résultat est null
     */
    public Ville save(VilleDto villeDto) throws RestException {
    	Ville ville= villeMapper.dtoToBean(villeDto);
    	Ville villeAjouter=villeRepository.save(ville);
    	if (villeAjouter == null) {
        	throw  new RestException("La sauvegarde n'a pas pu etre faite");
        }
    	return villeAjouter;
    }
    
    /** Méthode pour modifier une ville 
     * @param id, l'id de la ville a modifié
     * @param ville, les modifications apportées
     * @return ResponseEntity, un message pour le front 
     * @throws RestException si le résultat est null
     */
    public Ville modify(VilleDto villeDto, Long id) throws RestException {
    	Ville ville= villeRepository.findById(id);
    	if (ville==null){
    		throw  new RestException("La ville a modifier n'existe pas");
    	}
    	ville.setNom(villeDto.getNom());
    	ville.setNbHabitant(villeDto.getNbHabitant());
    	Departement d= departementRepo.findByCodeDepartement(villeDto.getCodeDepartement());
    	if (d!= null) {
    		ville.setDepartement(d);
    	}
    	return ville;
    }
    
    /** Méthode pour supprimer une ville 
     * @param id, l'id de la ville a supprimer
     * @return ResponseEntity, un message pour le front 
     * @throws RestException si le résultat est null
     */
    public void delete(Long id) throws RestException {
    	Ville ville= villeRepository.findById(id);
    	if (ville==null){
    		throw  new RestException("La ville a supprimer n'existe pas");
    	}

    	villeRepository.delete(ville);
    }
	
    /** Méthode pour enregister en base les lignes de texte d'un fichier CSV 
     * et les transformer en objet Ville et Departement 
     * @param lignes, les lignes du fichier CSV
     */
	public void saveVillefromCsv(List<String> lignes) {for (int i = 1; i < lignes.size(); i++) {
        String[] elements = lignes.get(i).split(";", -1);
       
            Ville ville = new Ville();
           
            ville.setNom(elements[6]);
            ville.setNbHabitant(Integer.parseInt(elements[9].replace(" ", "")));
            Departement departement= departementRepo.findByCodeDepartement(elements[2].trim());
            if(departement ==null) {
            	departement=new Departement();
            	departement.setCodeDepartement(elements[2].trim());
            	departementRepo.save(departement);
            }
            ville.setDepartement(departement);
            
           villeRepository.save(ville);
        }
	
}
}
