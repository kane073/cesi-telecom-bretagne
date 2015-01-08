package eu.telecom_bretagne.CESI.demo;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import eu.telecom_bretagne.CESI.data.dto.AgentDepartement;
import eu.telecom_bretagne.CESI.data.model.Agent;
import eu.telecom_bretagne.CESI.service.IGestionAgent;
import eu.telecom_bretagne.CESI.service.IGestionAgentDepartement;

public class AppelGestionAgent {

	public static void main(String[] args) {
		// test_listeAgents();
		// test_lireAgents();
		// test_modifierAgent();
		// test_lireAgents();
		test_lire_modifier();
	}

	public static IGestionAgent getGestionAgent() {
		InitialContext ctx = null;
		IGestionAgent gestionAgent = null;
		try {
			ctx = new InitialContext();
			gestionAgent = (IGestionAgent) ctx.lookup(IGestionAgent.JNDI_NAME);
		} catch (NamingException e) {
			// Unable to retrieve the context or the service
			e.printStackTrace();
			System.exit(-1);
		}
		return gestionAgent;
	}

	public static IGestionAgentDepartement getGestionAgentDepartement() {
		InitialContext ctx;
		IGestionAgentDepartement gestionAgentDepartement = null;
		try {
			ctx = new InitialContext();
			gestionAgentDepartement = (IGestionAgentDepartement) ctx
					.lookup(IGestionAgentDepartement.JNDI_NAME);
		} catch (NamingException e) {
			// Unable to retrieve the context or the service
			e.printStackTrace();
			System.exit(-1);
		}
		return gestionAgentDepartement;
	}

	public static void test_listeAgents() {
		for (Agent agent : getGestionAgent().listeAgents()) {
			afficherAgent(agent);
		}
	}

	public static void test_lireAgents() {
		int identifiant = 2;
		Agent agent = getGestionAgent().lireAgent(identifiant);
		afficherAgent(agent);
	}

	public static void test_modifierAgent() {
		getGestionAgent().modifierAgent(2, "nouveau nom agent 2");
	}

	public static void afficherAgent(Agent agent) {
		System.out.println("Agent id:" + agent.getId() + " nom:"
				+ agent.getNom());
	}

	public static void test_lire_modifier() {
		final int id = 2;
		AgentDepartement agtDept = getGestionAgentDepartement().lireAgentDepartement(id);
		printAgentDepartement(agtDept);
		agtDept.setAgent_nom(agtDept.getAgent_nom() + " KANE");
		getGestionAgentDepartement().modifierAgentDepartement(agtDept);
		agtDept = getGestionAgentDepartement().lireAgentDepartement(id);
		printAgentDepartement(agtDept);
	}

	private static void printAgentDepartement(AgentDepartement a) {
		System.out.println("AgentDepartement id: " + a.getAgent_id() + " nom: "
				+ a.getAgent_nom() + " departement:" + a.getDepartement_id()
				+ ":" + a.getDepartement_intitule());
	}
}
