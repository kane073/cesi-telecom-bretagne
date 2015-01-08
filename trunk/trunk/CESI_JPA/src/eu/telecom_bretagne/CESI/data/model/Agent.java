package eu.telecom_bretagne.CESI.data.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the agent database table.
 * 
 */
@Entity
@NamedQuery(name="Agent.findAll", query="SELECT a FROM Agent a")
public class Agent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="AGENT_ID_GENERATOR", sequenceName="AGENT_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AGENT_ID_GENERATOR")
	private Integer id;

	private String nom;

	//bi-directional many-to-one association to Departement
	@ManyToOne
	@JoinColumn(name="departement_fk")
	private Departement departement;

	public Agent() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Departement getDepartement() {
		return this.departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

}