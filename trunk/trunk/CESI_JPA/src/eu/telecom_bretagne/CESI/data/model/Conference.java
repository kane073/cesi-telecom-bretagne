package eu.telecom_bretagne.CESI.data.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the publication database table.
 * 
 */
@Entity
@DiscriminatorValue("conference")
public class Conference extends Publication implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@Temporal(TemporalType.DATE)
	private Date dateconference;

	private String description;

	private String editeur;

	private String isbn;
	
	private String lieu;
	
	private String theme;

	public Conference() {
		super();
	} 
	
	public Conference(Publication publication){
		super();
		setTitre(publication.getTitre());
		setResume(publication.getResume());
		setLangue(publication.getLangue());
		setDatepublication(publication.getDatepublication());
		setDatedebutpublication(publication.getDatedebutpublication());
		setDatefinpublication(publication.getDatefinpublication());
		setAuteurs(publication.getAuteurs());
	}

	public Date getDateconference() {
		return this.dateconference;
	}

	public void setDateconference(Date dateconference) {
		this.dateconference = dateconference;
	}


	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEditeur() {
		return this.editeur;
	}

	public void setEditeur(String editeur) {
		this.editeur = editeur;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getLieu() {
		return this.lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}


	public String getTheme() {
		return this.theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

}