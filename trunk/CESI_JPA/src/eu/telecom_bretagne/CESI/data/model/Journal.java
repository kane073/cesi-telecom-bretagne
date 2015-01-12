package eu.telecom_bretagne.CESI.data.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the publication database table.
 * 
 */
@Entity
@DiscriminatorValue("journal")
public class Journal extends Publication implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Journal() {
		super();
	}
	
	public Journal(Publication publication){
		super();
		setTitre(publication.getTitre());
		setResume(publication.getResume());
		setLangue(publication.getLangue());
		setDatepublication(publication.getDatepublication());
		setDatedebutpublication(publication.getDatedebutpublication());
		setDatefinpublication(publication.getDatefinpublication());
		setAuteurs(publication.getAuteurs());
	}
	
	@Temporal(TemporalType.DATE)
	private Date dateapparition;

	private String editeur;

	private String nomjournal;

	private String sujet;

	private String volume;

	public Date getDateapparition() {
		return this.dateapparition;
	}

	public void setDateapparition(Date dateapparition) {
		this.dateapparition = dateapparition;
	}


	public String getEditeur() {
		return this.editeur;
	}

	public void setEditeur(String editeur) {
		this.editeur = editeur;
	}


	public String getNomjournal() {
		return this.nomjournal;
	}

	public void setNomjournal(String nomjournal) {
		this.nomjournal = nomjournal;
	}	

	public String getSujet() {
		return this.sujet;
	}

	public void setSujet(String sujet) {
		this.sujet = sujet;
	}

	public String getVolume() {
		return this.volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

}