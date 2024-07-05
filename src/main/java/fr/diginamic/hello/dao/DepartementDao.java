package fr.diginamic.hello.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.diginamic.hello.entity.Departement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Service
public class DepartementDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Departement>extractDepartements() {
		TypedQuery<Departement> query = em.createQuery("SELECT d FROM Departement d", Departement.class);
		return query.getResultList();
	}
	
	public Departement extractDepartement(Long id) {
		TypedQuery<Departement> query = em.createQuery("SELECT d FROM Departement d where d.id= :idRequest", Departement.class);
		query.setParameter("idRequest", id);
		List<Departement> departements= query.getResultList();
		if(departements.size()>0) {
			return departements.get(0);
		}
		return null;
	}
	public Departement extractDepartement(String nom) {
		TypedQuery<Departement> query = em.createQuery("SELECT d FROM Departement d where d.nom= :nom", Departement.class);
		query.setParameter("nom", nom);
		List<Departement> departements= query.getResultList();
		if(departements.size()>0) {
			return departements.get(0);
		}
		return null;
	}
	@Transactional
	public Departement deleteDepartement(Departement departement) {
		em.remove(departement);
		return departement;
	}
	
	@Transactional
	public Departement insertDepartement(Departement departement) {
		em.persist(departement);
		return departement;
	}
	@Transactional
	public Departement modifyDepartement(Long id, Departement departement) {
		Departement departementAncien=extractDepartement(id);
		departementAncien.setNom(departement.getNom());
		return departementAncien;
	}
	
}
