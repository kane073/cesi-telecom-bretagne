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

	@Temporal(TemporalType.DATE)
	private Date datedebutpublication;

	@Temporal(TemporalType.DATE)
	private Date datefinpublication;

	@Temporal(TemporalType.DATE)
	private Date datepublication;

	private String langue;

	private String resume;

	private String titre;

	private String type;

	//bi-directional many-to-many association to Auteur
	@ManyToMany
	@JoinTable(
		name="pulication_auteur"
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

	

	public String getLangue() {
		return this.langue;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}

	

	public String getResume() {
		return this.resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
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