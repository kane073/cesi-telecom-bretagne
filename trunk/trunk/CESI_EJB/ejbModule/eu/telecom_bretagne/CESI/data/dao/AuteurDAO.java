package eu.telecom_bretagne.CESI.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.CESI.data.model.Auteur;

/**
 * Session Bean implementation class AuteurDAO
 */
@Stateless
@LocalBean
public class AuteurDAO implements DAO<Auteur>{
	
	@PersistenceContext
	EntityManager entityManager;
	
	EntityManager getEntityManager(){
		return entityManager;
	}
	
    /**
     * Default constructor. 
     */
    public AuteurDAO() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Auteur create(Auteur entity) {
		getEntityManager().persist(entity);
		return entity;
	}

	@Override
	public Auteur findById(int id) {
		return getEntityManager().find(Auteur.class, id);
	}

	@Override
	public Auteur update(Auteur entity) {
		getEntityManager().merge(entity);
		return entity;
	}

	@Override
	public void delete(Auteur entity) {
		getEntityManager().detach(entity);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Auteur> findAll() {
		Query query = getEntityManager().createNamedQuery("Auteur.findAll", Auteur.class);
		return (List<Auteur>) query.getResultList();
	}

}
