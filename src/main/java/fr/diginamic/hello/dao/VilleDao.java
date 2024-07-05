package fr.diginamic.hello.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.diginamic.hello.entity.Departement;
import fr.diginamic.hello.entity.Ville;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Service
public class VilleDao {
	@PersistenceContext
	private EntityManager em;
	
	public List<Ville>extractVilles() {
		TypedQuery<Ville> query = em.createQuery("SELECT v FROM Ville v", Ville.class);
		return query.getResultList();
	}
	
	public Ville extractVille(Long id) {
		TypedQuery<Ville> query = em.createQuery("SELECT v FROM Ville v where v.id= :idRequest", Ville.class);
		query.setParameter("idRequest", id);
		List<Ville> villes= query.getResultList();
		if(villes.size()>0) {
			return villes.get(0);
		}
		return null;
	}
	public Ville extractVille(String nom) {
		TypedQuery<Ville> query = em.createQuery("SELECT v FROM Ville v where v.nom= :nom", Ville.class);
		query.setParameter("nom", nom);
		List<Ville> villes= query.getResultList();
		if(villes.size()>0) {
			return villes.get(0);
		}
		return null;
	}
	@Transactional
	public Ville deleteVille(Long id) {
		Ville ville=extractVille(id);
		if(ville==null) {
			return null;
		}
		em.remove(ville);
		return ville;
	}
	
	@Transactional
	public Ville insertVille(Ville ville) {
		em.persist(ville);
		return ville;
	}
	@Transactional
	public Ville modifyVille(Long id, Ville ville) {
		Ville villeAncienne=extractVille(id);
		if(villeAncienne==null) {
			return null;
		}
		villeAncienne.setNom(ville.getNom());
		villeAncienne.setNbHabitant(ville.getNbHabitant());
		return villeAncienne;
	}
	public List<Ville> extractPlusGrandeVilleDepartement(String departement, int nombre) {
		TypedQuery<Ville> query = em.createQuery("SELECT v FROM ville v JOIN v.departement d where d.nom= :departement ORDER BY v.nbHabitant DESC", Ville.class);
		query.setParameter("departement", departement);
		 query.setMaxResults(nombre);
		return query.getResultList();
		
	}
	public List<Ville> findVillesByPopulationRangeAndDepartement(String departement, int min, int max) {
	    TypedQuery<Ville> query = em.createQuery("SELECT v FROM Ville v WHERE v.nbHabitant BETWEEN :min AND :max AND v.departement.id = :departementId", Ville.class);
	    query.setParameter("min", min);
	    query.setParameter("max", max);
	    query.setParameter("departementId", departement);
	    return query.getResultList();
	}
}
