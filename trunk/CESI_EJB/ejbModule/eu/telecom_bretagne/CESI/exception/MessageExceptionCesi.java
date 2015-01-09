package eu.telecom_bretagne.CESI.exception;

public enum MessageExceptionCesi {

	ExceptionAuteur("Un auteur avec cet email existe déjà dans le système!"), 
	ExceptionInstitution("Un institution du même non exise déjà dans le système!"),
	ExceptionPublicationExiste("Une publicationn avec le titre existe déjà dans le système"),
	ExceptionPublicationAuteur("Renseigner au moins un auteur qui existe dans le système"),
	ExceptionRechercheAuteur("Aucune publication avec ce auteur n'existe dans le système"),
	ExceptionRechercheTitre ("Aucune publication avec ce titre n'existe dans le système");
	
	
	private String message = "";

	MessageExceptionCesi(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
