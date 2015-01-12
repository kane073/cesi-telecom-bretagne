package eu.telecom_bretagne.CESI.data.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
	
	
	public Publication findByIdFetch(int id){
		Query query = getEntityManager().createQuery("SELECT publication FROM Publication publication "
				+ "JOIN publication.auteurs auteur " +
				 "WHERE publication.id = :idP");
				 query.setParameter("idP", id);
		return (!query.getResultList().isEmpty())?(Publication)query.getResultList().get(0):null;
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
	
	@SuppressWarnings("unchecked")
	public List<Publication> findByTitre(String titre){
		Query query = getEntityManager().createQuery(ListRequete.findPublicationByTitre+"titreI");
		query.setParameter("titreI", titre);
		return (List<Publication>)query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Publication> findPublicationCriteriaTitre(String titre){
		
		Pattern p = Pattern.compile(" ") ; 
		String [] tableau = p.split(titre) ; 
		StringBuffer requeteBuffer = new StringBuffer("select p from Publication p ");
		boolean premiereClause = true;
		List<String> var = new ArrayList<String>();
		List<String> var1 = new ArrayList<String>();
		int i = 0;
		for (String string : tableau) {
			requeteBuffer.append(premiereClause ? "where " : " or ");
			requeteBuffer.append("UPPER(p.titre) like :titre"+i);
			premiereClause = false;
			var.add("titre"+i);
			var1.add("%"+string.toUpperCase()+"%");
		}
		Query query = getEntityManager().createQuery(requeteBuffer.toString());
		int j=0;
		for (String string : var1) {
			query.setParameter("titre"+j, string);
			j++;
		}
		return (List<Publication>)query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Publication> findPublicationCriteriaNomAuteur(String nom){
		
		Query query = getEntityManager().createQuery("SELECT publication FROM Publication publication "
				+ "INNER JOIN publication.auteurs auteur " +
				 "WHERE UPPER(auteur.nom) like :nomA or UPPER(auteur.prenom) like :prenomA");
				 query.setParameter("nomA", "%"+nom.toUpperCase()+"%");
				 query.setParameter("prenomA", "%"+nom.toUpperCase()+"%");
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
