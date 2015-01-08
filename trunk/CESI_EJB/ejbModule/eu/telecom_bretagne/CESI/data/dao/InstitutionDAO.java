package eu.telecom_bretagne.CESI.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.CESI.data.model.Institutionrattchement;
import eu.telecom_bretagne.CESI.data.util.ListRequete;

/**
 * Session Bean implementation class InstitutionDAO
 */
@Stateless
@LocalBean
public class InstitutionDAO implements DAO<Institutionrattchement>{
	
	@PersistenceContext
	EntityManager entityManager;
	
	EntityManager getEntityManager(){
		return entityManager;
	}
	
    /**
     * Default constructor. 
     */
    public InstitutionDAO() {
    }

	@Override
	public Institutionrattchement create(Institutionrattchement entity) {
		getEntityManager().persist(entity);
		return entity;
	}

	@Override
	public Institutionrattchement findById(int id) {
		return getEntityManager().find(Institutionrattchement.class, id);
	}

	@Override
	public Institutionrattchement update(Institutionrattchement entity) {
		getEntityManager().merge(entity);
		return entity;
	}

	@Override
	public void delete(Institutionrattchement entity) {
		getEntityManager().detach(entity);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Institutionrattchement> findAll() {
		Query query = getEntityManager().createNamedQuery("Institutionrattchement.findAll", Institutionrattchement.class);
		return (List<Institutionrattchement>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Institutionrattchement> findByName(String nom){
		Query query = getEntityManager().createQuery(ListRequete.findInstitutionByNom+"nomI");
		query.setParameter("nomI", nom);
		return (List<Institutionrattchement>) query.getResultList();
	}
}
