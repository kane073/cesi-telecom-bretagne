package eu.telecom_bretagne.CESI.demo;

import java.text.SimpleDateFormat;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import eu.telecom_bretagne.CESI.data.model.Reference;
import eu.telecom_bretagne.CESI.service.IGestionReference;

public class AppelGestionReference {

	public static void main(String[] args) {
		//test_listeReference();
		//test_creerReference();
		//test_consulterPublication();
		//test_modiferRefence();
		//test_supprimerReference();
		
	}
	
	public static IGestionReference getGestionReference() {
		InitialContext ctx = null;
		IGestionReference gestionReference = null;
		try {
			ctx = new InitialContext();
			gestionReference = (IGestionReference) ctx
					.lookup(IGestionReference.JNDI_NAME);
		} catch (NamingException e) {
			// Unable to retrieve the context or the service
			e.printStackTrace();
			System.exit(-1);
		}
		return gestionReference;
	}
	
	public static void afficher_reference(Reference reference){
		System.out.println("Code referene :"+reference.getCodereference()+"\n"+
							"Institution :"+ reference.getInstitutionrattachement()+"\n"
							+ "Titre publication : "+reference.getPublication().getTitre());
		
		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yy");
		if(reference.getDatedebutreference()!=null){
			System.out.println("Date debut référencement: "+ formater.format(reference.getDatedebutreference()));
		}
		if(reference.getDatefinreference()!=null){
			System.out.println("Date fin référencement: "+ formater.format(reference.getDatefinreference()));
		}
	}
	
	public static void test_listeReference(){
		for(Reference reference:getGestionReference().listReference()){
			afficher_reference(reference);
		}
	}
	
	public static void test_creerReference(){
		try {
			Reference reference = getGestionReference().creerReference("CC55", 7);
			afficher_reference(reference);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void test_consulterPublication(){
		try {
			Reference reference = getGestionReference().consulterReference("CC55");
			afficher_reference(reference);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
	
	}
	
	public static void test_modiferRefence(){
		try {
			getGestionReference().modifierReference(2, "CC56");
			afficher_reference(getGestionReference().lireReference(2));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void test_supprimerReference(){
		try {
			getGestionReference().supprimerReference(3);
			afficher_reference(getGestionReference().lireReference(3));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
