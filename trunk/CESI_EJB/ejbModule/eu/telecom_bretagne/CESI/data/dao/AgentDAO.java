package eu.telecom_bretagne.CESI.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.CESI.data.model.Agent;

/**
 * Session Bean implementation class AgentDAO
 */
@Stateless
@LocalBean
public class AgentDAO implements DAO<Agent>{
	
	@PersistenceContext
	EntityManager entityManager;
	
	EntityManager getEntityManager(){
		return entityManager;
	}
    /**
     * Default constructor. 
     */
    public AgentDAO() {
        
    }

	@Override
	public Agent create(Agent entity) {
		getEntityManager().persist(entity);
		return entity;
	}

	@Override
	public Agent findById(int id) {
		return getEntityManager().find(Agent.class, id);
	}

	@Override
	public Agent update(Agent entity) {
		getEntityManager().merge(entity);
		return entity;
	}

	@Override
	public void delete(Agent entity) {
		getEntityManager().remove(entity);		
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Agent> findAll() {
		Query query = getEntityManager().createNamedQuery("Agent.findAll", Agent.class);
		return (List<Agent>) query.getResultList();
	}
	@Override
	public Agent attacherObject(Agent entity, int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
