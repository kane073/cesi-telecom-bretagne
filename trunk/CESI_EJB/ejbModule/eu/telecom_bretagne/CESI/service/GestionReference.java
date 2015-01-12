package eu.telecom_bretagne.CESI.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.CESI.data.dao.PublicationDAO;
import eu.telecom_bretagne.CESI.data.dao.ReferenceDAO;
import eu.telecom_bretagne.CESI.data.model.Auteur;
import eu.telecom_bretagne.CESI.data.model.Publication;
import eu.telecom_bretagne.CESI.data.model.Reference;
import eu.telecom_bretagne.CESI.data.util.HelperCesi;
import eu.telecom_bretagne.CESI.exception.MessageExceptionCesi;

/**
 * Session Bean implementation class GestionReference
 */
@Stateless
@LocalBean
public class GestionReference implements IGestionReference {
	
	@EJB
	ReferenceDAO referenceDAO;
	
	@EJB
	PublicationDAO publicationDAO;
	
    /**
     * Default constructor. 
     */
    public GestionReference() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<Reference> listReference() {
		return referenceDAO.findAll();
	}

	@Override
	public Reference lireReference(int identifiant) {
		return referenceDAO.findById(identifiant);
	}

	@Override
	public Reference creerReference(String code, int identifiant) throws Exception {
		
		if(!HelperCesi.listIsEmpty(referenceDAO.findByCode(code))){
			throw new Exception(MessageExceptionCesi.ExceptionReferenceCodeExiste.getMessage());
		}
		Publication publication = publicationDAO.findByIdFetch(identifiant);
		if(publication==null){
			throw new Exception(MessageExceptionCesi.ExceptionReferencePublicationNotExiste.getMessage());
		}
		
		if(publication.getReferences()!=null && !publication.getReferences().isEmpty()){
			for(Reference r:publication.getReferences()){
				if(r.getDatefinreference()!=null){
					throw new Exception(MessageExceptionCesi.ExceptionReferencePublicationExiste.getMessage());
				}
			}
		}
		Reference reference = new Reference();
		reference.setCodereference(code);
		reference.setDatedebutreference(new Date());
		reference.setPublication(publication);
		StringBuilder stringInstitution = new StringBuilder();
		for(Auteur auteur : publication.getAuteurs()){
			stringInstitution.append(auteur.getInstitutionrattchement().getNominstitution());
			stringInstitution.append(" ;");
		}
		stringInstitution.delete(stringInstitution.length()-2, stringInstitution.length());
		
		reference.setInstitutionrattachement(stringInstitution.toString());
		
		Set<Reference> rs = new HashSet<Reference>();
		rs.add(reference);
		publication.setReferences(rs);
		
		referenceDAO.create(reference);
		publicationDAO.update(publication);
		
		return reference;
	}

	@Override
	public void modifierReference(int identifiant, String code)  throws Exception {
		Reference reference = lireReference(identifiant);
		if(reference.getCodereference().equals(code)&& referenceDAO.findByCode(code)!=null){
			throw new Exception(MessageExceptionCesi.ExceptionReferenceCodeExiste.getMessage());
		}
		reference.setCodereference(code);
		referenceDAO.update(reference);
	}
	
	@Override
	public void supprimerReference(int identifiant)  throws Exception {
		Reference reference = lireReference(identifiant);
		reference.setDatefinreference(new Date());
	}

	@Override
	public Reference consulterReference(String code) throws Exception {
		List<Reference> references = referenceDAO.findByCode(code);
		if(HelperCesi.listIsEmpty(references)){
			throw new Exception(MessageExceptionCesi.ExceptionReferenceCodeNotExiste.getMessage());
		}
		if(references.get(0).getDatefinreference()!=null){
			throw new Exception(MessageExceptionCesi.ExceptionReferenceSupprimer.getMessage());
		}
		return references.get(0);
	}

}
