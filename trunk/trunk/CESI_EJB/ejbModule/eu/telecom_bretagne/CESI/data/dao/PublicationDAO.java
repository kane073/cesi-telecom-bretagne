package eu.telecom_bretagne.CESI.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.CESI.data.model.Institutionrattchement;
import eu.telecom_bretagne.CESI.data.model.Publication;
import eu.telecom_bretagne.CESI.data.util.ListRequete;

/**
 * Session Bean implementation class Publication
 */
@Stateless
@LocalBean
public class PublicationDAO implements DAO<Publication> {
	
	@PersistenceContext
	EntityManager entityManager;
	
	EntityManager getEntityManager(){
		return entityManager;
	}
	
    /**
     * Default constructor. 
     */
    public PublicationDAO() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Publication create(Publication entity) {
		getEntityManager().persist(entity);
		return entity;
	}

	@Override
	public Publication findById(int id) {
		return getEntityManager().find(Publication.class, id);
	}

	@Override
	public Publication update(Publication entity) {
		getEntityManager().merge(entity);
		return entity;
	}

	@Override
	public void delete(Publication entity) {
		getEntityManager().remove(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Publication> findAll() {
		Query query = getEntityManager().createNamedQuery("Publication.findAll", Publication.class);
		return (List<Publication>) query.getResultList();
	}
	
	public List<Publication> findByTitre(String titre){
		Query query = getEntityManager().createQuery(ListRequete.findPublicationByTitre+"titreI");
		query.setParameter("titreI", titre);
		return (List<Publication>)query.getResultList();
	}
	
	public List<Publication> findByAuteur(){
		
		return null;
	}
	
	@Override
	public Publication attacherObject(Publication entity, int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
