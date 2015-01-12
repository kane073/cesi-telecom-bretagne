package eu.telecom_bretagne.CESI.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.CESI.data.model.Publication;
import eu.telecom_bretagne.CESI.data.model.Reference;
import eu.telecom_bretagne.CESI.data.util.ListRequete;

/**
 * Session Bean implementation class ReferenceDAO
 */
@Stateless
@LocalBean
public class ReferenceDAO implements DAO<Reference>{
	
	@PersistenceContext
	EntityManager entityManager;
	
	EntityManager getEntityManager(){
		return entityManager;
	}
	
    /**
     * Default constructor. 
     */
    public ReferenceDAO() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Reference create(Reference entity) {
		getEntityManager().persist(entity);
		return entity;
	}

	@Override
	public Reference findById(int id) {
		return getEntityManager().find(Reference.class, id);
	}

	@Override
	public Reference update(Reference entity) {
		getEntityManager().merge(entity);
		return entity;
	}

	@Override
	public void delete(Reference entity) {
		getEntityManager().remove(entity);		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reference> findAll() {
		Query query = getEntityManager().createNamedQuery("Reference.findAll", Reference.class);
		return (List<Reference>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Reference> findByCode(String code) {
		Query query = getEntityManager().createQuery(ListRequete.findReferenceByCode+"codeR");
		query.setParameter("codeR", code);
		return (List<Reference>)query.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<Reference> findByPublication(int identifiant){
		Query query = getEntityManager().createQuery(ListRequete.findReferenceByPublication+"idP");
		query.setParameter("idP", identifiant);
		return (List<Reference>)query.getResultList();
	}
	
	@Override
	public Reference attacherObject(Reference entity, int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
