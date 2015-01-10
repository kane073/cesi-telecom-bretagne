package eu.telecom_bretagne.CESI.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.CESI.data.model.Auteur;
import eu.telecom_bretagne.CESI.data.model.Auteurexterne;
import eu.telecom_bretagne.CESI.data.model.Auteurinterne;
import eu.telecom_bretagne.CESI.data.util.ListRequete;

/**
 * Session Bean implementation class AuteurDAO
 */
@Stateless
@LocalBean
public class AuteurDAO implements DAO<Auteur>{
	
	@PersistenceContext
	EntityManager entityManager;
	
	public EntityManager getEntityManager(){
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
	
	public Auteurexterne findByIdAuteurExterne(int id){
		return getEntityManager().find(Auteurexterne.class, id);
	}
	
	public Auteurinterne findByIdAuteurInterne(int id){
		return getEntityManager().find(Auteurinterne.class, id);
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
	
	@SuppressWarnings("unchecked")
	public List<Auteur> findByEmail(String email){
		Query query = getEntityManager().createQuery(ListRequete.findAuteurByEmail+"emailA");
		query.setParameter("emailA", email);
		return (List<Auteur>) query.getResultList();
	}
	
	public void enregistrerLaSession(){
		getEntityManager().flush();
	}
	public void detacheEntity (Auteur auteur){
		getEntityManager().detach(auteur);
	}
	@Override
	public Auteur attacherObject(Auteur entity, int id) {
		
		return null;
	}
}
