package eu.telecom_bretagne.CESI.exception;

public enum MessageExceptionCesi {

	ExceptionAuteur("Un auteur avec cet email existe d�j� dans le syst�me!"), 
	ExceptionInstitution("Un institution du m�me non exise d�j� dans le syst�me!"),
	ExceptionPublicationExiste("Une publicationn avec le titre existe d�j� dans le syst�me"),
	ExceptionPublicationAuteur("Renseigner au moins un auteur qui existe dans le syst�me"),
	ExceptionRechercheAuteur("Aucune publication avec ce auteur n'existe dans le syst�me"),
	ExceptionRechercheTitre ("Aucune publication avec ce titre n'existe dans le syst�me");
	
	
	private String message = "";

	MessageExceptionCesi(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
