package eu.telecom_bretagne.CESI.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.CESI.data.model.Departement;

/**
 * Session Bean implementation class DepartementDAO
 */
@Stateless
@LocalBean
public class DepartementDAO implements DAO<Departement>{
	
	@PersistenceContext
	EntityManager entityManager;
	
	EntityManager getEntityManager(){
		return entityManager;
	}
    /**
     * Default constructor. 
     */
    public DepartementDAO() {
        
    }

	@Override
	public Departement create(Departement entity) {
		getEntityManager().persist(entity);
		return entity;
	}

	@Override
	public Departement findById(int id) {
		return getEntityManager().find(Departement.class, id);
		
	}

	@Override
	public Departement update(Departement entity) {
		getEntityManager().merge(entity);
		return entity;
	}

	@Override
	public void delete(Departement entity) {
		getEntityManager().remove(entity);		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Departement> findAll() {
		Query query = getEntityManager().createNamedQuery("Departement.findAll", Departement.class);
		return (List<Departement>) query.getResultList();
	}
	@Override
	public Departement attacherObject(Departement entity, int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
