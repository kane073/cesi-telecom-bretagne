package eu.telecom_bretagne.CESI.data.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: AuteurInterne
 *
 */
@Entity
@DiscriminatorValue("auteurinterne")
public class Auteurinterne extends Auteur implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Auteurinterne() {
		super();
	}
	
	public Auteurinterne(Auteur auteur) {
		super();
		setNom(auteur.getNom());
		setPrenom(auteur.getPrenom());
		setEmail(auteur.getEmail());
		setSiteperso(auteur.getSiteperso());
		setInstitutionrattchement(auteur.getInstitutionrattchement());
	}
	
	private String idtelecom;
	
	public String getIdtelecom() {
		return this.idtelecom;
	}

	public void setIdtelecom(String idtelecom) {
		this.idtelecom = idtelecom;
	}
	
	
}
