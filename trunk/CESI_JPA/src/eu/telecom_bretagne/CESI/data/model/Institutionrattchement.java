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
	@SequenceGenerator(name="INSTITUTIONRATTCHEMENT_IDINSTITUTION_GENERATOR", sequenceName="INSTITUTIONRATTCHEMENT_IDINSTITUTION_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INSTITUTIONRATTCHEMENT_IDINSTITUTION_GENERATOR")
	private Integer idinstitution;

	private String adresse;

	private String nominstitution;

	//bi-directional many-to-one association to Auteur
	@OneToMany(mappedBy="institutionrattchement")
	private Set<Auteur> auteurs;

	public Institutionrattchement() {
	}

	public Integer getIdinstitution() {
		return this.idinstitution;
	}

	public void setIdinstitution(Integer idinstitution) {
		this.idinstitution = idinstitution;
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