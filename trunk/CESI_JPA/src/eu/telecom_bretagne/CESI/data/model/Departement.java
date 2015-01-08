package eu.telecom_bretagne.CESI.data.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the departement database table.
 * 
 */
@Entity
@NamedQuery(name="Departement.findAll", query="SELECT d FROM Departement d")
public class Departement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DEPARTEMENT_ID_GENERATOR", sequenceName="DEPARTEMENT_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DEPARTEMENT_ID_GENERATOR")
	private Integer id;

	private String intitule;

	//bi-directional many-to-one association to Agent
	@OneToMany(mappedBy="departement")
	private Set<Agent> agents;

	public Departement() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIntitule() {
		return this.intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public Set<Agent> getAgents() {
		return this.agents;
	}

	public void setAgents(Set<Agent> agents) {
		this.agents = agents;
	}

	public Agent addAgent(Agent agent) {
		getAgents().add(agent);
		agent.setDepartement(this);

		return agent;
	}

	public Agent removeAgent(Agent agent) {
		getAgents().remove(agent);
		agent.setDepartement(null);

		return agent;
	}

}