package eu.telecom_bretagne.CESI.data.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the institutionrattchement database table.
 * 
 */
@Entity
@NamedQuery(name="Institutionrattchement.findAll", query="SELECT i FROM Institutionrattchement i")
public class Institutionrattchement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="INSTITUTIONRATTCHEMENT_IDINSTITUTION_GENERATOR", sequenceName="INSTITUTIONRATTCHEMENT_ID_INSTITUTION_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INSTITUTIONRATTCHEMENT_IDINSTITUTION_GENERATOR")
	@Column(name="id_institution")
	private Integer idInstitution;

	private String adresse;

	private String nominstitution;

	//bi-directional many-to-one association to Auteur
	@OneToMany(mappedBy="institutionrattchement")
	private Set<Auteur> auteurs;

	public Institutionrattchement() {
	}

	public Integer getIdInstitution() {
		return this.idInstitution;
	}

	public void setIdInstitution(Integer idInstitution) {
		this.idInstitution = idInstitution;
	}

	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getNominstitution() {
		return this.nominstitution;
	}

	public void setNominstitution(String nominstitution) {
		this.nominstitution = nominstitution;
	}

	public Set<Auteur> getAuteurs() {
		return this.auteurs;
	}

	public void setAuteurs(Set<Auteur> auteurs) {
		this.auteurs = auteurs;
	}

	public Auteur addAuteur(Auteur auteur) {
		getAuteurs().add(auteur);
		auteur.setInstitutionrattchement(this);

		return auteur;
	}

	public Auteur removeAuteur(Auteur auteur) {
		getAuteurs().remove(auteur);
		auteur.setInstitutionrattchement(null);

		return auteur;
	}

}