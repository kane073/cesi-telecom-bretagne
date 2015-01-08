package eu.telecom_bretagne.CESI.data.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the publication database table.
 * 
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",discriminatorType=DiscriminatorType.STRING)  
@DiscriminatorValue(value="publication")
@NamedQuery(name="Publication.findAll", query="SELECT p FROM Publication p")
public class Publication implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PUBLICATION_ID_GENERATOR", sequenceName="PUBLICATION_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PUBLICATION_ID_GENERATOR")
	private Integer id;

	private String collectivite;

	@Temporal(TemporalType.DATE)
	private Date dateapparition;

	@Temporal(TemporalType.DATE)
	private Date dateconference;

	@Temporal(TemporalType.DATE)
	private Date datedebutpublication;

	@Temporal(TemporalType.DATE)
	private Date datefinpublication;

	@Temporal(TemporalType.DATE)
	private Date datepublication;

	private String description;

	private String editeur;

	private String isbn;

	private String langue;

	private String lieu;

	private String nomjournal;

	private String resume;

	private String sujet;

	private String theme;

	private String titre;

	private String type;

	private String volume;

	//bi-directional many-to-many association to Auteur
	@ManyToMany
	@JoinTable(
		name="pulicationauteur"
		, joinColumns={
			@JoinColumn(name="pub_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id")
			}
		)
	private Set<Auteur> auteurs;

	//bi-directional many-to-one association to Reference
	@OneToMany(mappedBy="publication")
	private Set<Reference> references;

	public Publication() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCollectivite() {
		return this.collectivite;
	}

	public void setCollectivite(String collectivite) {
		this.collectivite = collectivite;
	}

	public Date getDateapparition() {
		return this.dateapparition;
	}

	public void setDateapparition(Date dateapparition) {
		this.dateapparition = dateapparition;
	}

	public Date getDateconference() {
		return this.dateconference;
	}

	public void setDateconference(Date dateconference) {
		this.dateconference = dateconference;
	}

	public Date getDatedebutpublication() {
		return this.datedebutpublication;
	}

	public void setDatedebutpublication(Date datedebutpublication) {
		this.datedebutpublication = datedebutpublication;
	}

	public Date getDatefinpublication() {
		return this.datefinpublication;
	}

	public void setDatefinpublication(Date datefinpublication) {
		this.datefinpublication = datefinpublication;
	}

	public Date getDatepublication() {
		return this.datepublication;
	}

	public void setDatepublication(Date datepublication) {
		this.datepublication = datepublication;
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

	public String getLangue() {
		return this.langue;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}

	public String getLieu() {
		return this.lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public String getNomjournal() {
		return this.nomjournal;
	}

	public void setNomjournal(String nomjournal) {
		this.nomjournal = nomjournal;
	}

	public String getResume() {
		return this.resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getSujet() {
		return this.sujet;
	}

	public void setSujet(String sujet) {
		this.sujet = sujet;
	}

	public String getTheme() {
		return this.theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVolume() {
		return this.volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public Set<Auteur> getAuteurs() {
		return this.auteurs;
	}

	public void setAuteurs(Set<Auteur> auteurs) {
		this.auteurs = auteurs;
	}

	public Set<Reference> getReferences() {
		return this.references;
	}

	public void setReferences(Set<Reference> references) {
		this.references = references;
	}

	public Reference addReference(Reference reference) {
		getReferences().add(reference);
		reference.setPublication(this);

		return reference;
	}

	public Reference removeReference(Reference reference) {
		getReferences().remove(reference);
		reference.setPublication(null);

		return reference;
	}

}