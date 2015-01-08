package eu.telecom_bretagne.CESI.data.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: AuteurInterne
 *
 */
@Entity
@DiscriminatorValue("auteurexterne")
public class Auteurexterne extends Auteur implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Auteurexterne() {
		super();
	}
	
	public Auteurexterne(Auteur auteur) {
		super();
		setNom(auteur.getNom());
		setPrenom(auteur.getPrenom());
		setEmail(auteur.getEmail());
		setSiteperso(auteur.getSiteperso());
		setInstitutionrattchement(auteur.getInstitutionrattchement());
	}

	private String idexterne;


	public String getIdexterne() {
		return this.idexterne;
	}

	public void setIdexterne(String idexterne) {
		this.idexterne = idexterne;
	}

}
