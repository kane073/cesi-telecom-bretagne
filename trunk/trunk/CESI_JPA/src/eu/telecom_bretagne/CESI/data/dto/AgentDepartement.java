package eu.telecom_bretagne.CESI.data.dto;

import java.io.Serializable;

public class AgentDepartement implements Serializable{
	private static final long serialVersionUID = 1294119239428024077L;
	private Integer agent_id;
	private String agent_nom;
	private Integer departement_id;
	private String departement_intitule;

	public AgentDepartement(Integer agent_id, String agent_nom,
			Integer departement_id, String departement_intitule) {
		this.agent_id = agent_id;
		this.agent_nom = agent_nom;
		this.departement_id = departement_id;
		this.departement_intitule = departement_intitule;
	}

	public String getAgent_nom() {
		return agent_nom;
	}

	public void setAgent_nom(String agent_nom) {
		this.agent_nom = agent_nom;
	}

	public String getDepartement_intitule() {
		return departement_intitule;
	}

	public Integer getAgent_id() {
		return agent_id;
	}

	public Integer getDepartement_id() {
		return departement_id;
	}
}
