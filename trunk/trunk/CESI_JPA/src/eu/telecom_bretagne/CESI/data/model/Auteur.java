package eu.telecom_bretagne.CESI.data.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the auteur database table.
 * 
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",discriminatorType=DiscriminatorType.STRING)  
@DiscriminatorValue(value="auteur")
@NamedQuery(name="Auteur.findAll", query="SELECT a FROM Auteur a")
public class Auteur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="AUTEUR_ID_GENERATOR", sequenceName="AUTEUR_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AUTEUR_ID_GENERATOR")
	private Integer id;

	private String email;

	private String nom;

	private String prenom;

	private String siteperso;

	private String type;

	//bi-directional many-to-one association to Institutionrattchement
	@ManyToOne
	@JoinColumn(name="id_institution")
	private Institutionrattchement institutionrattchement;

	//bi-directional many-to-many association to Publication
	@ManyToMany(mappedBy="auteurs")
	private Set<Publication> publications;

	public Auteur() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getSiteperso() {
		return this.siteperso;
	}

	public void setSiteperso(String siteperso) {
		this.siteperso = siteperso;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Institutionrattchement getInstitutionrattchement() {
		return this.institutionrattchement;
	}

	public void setInstitutionrattchement(Institutionrattchement institutionrattchement) {
		this.institutionrattchement = institutionrattchement;
	}

	public Set<Publication> getPublications() {
		return this.publications;
	}

	public void setPublications(Set<Publication> publications) {
		this.publications = publications;
	}

}