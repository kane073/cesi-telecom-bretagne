package eu.telecom_bretagne.CESI.exception;

public enum MessageExceptionCesi {

	ExceptionAuteur("Un auteur avec cet email existe déjà dans le système!"), 
	ExceptionInstitution("Une institution du même nom existe déjà dans le système!"),
	ExceptionPublicationExiste("Une publication avec le titre existe déjà dans le système"),
	ExceptionPublicationNotExiste("Cette publication n'existe pas dans le système"),
	ExceptionPublicationNotModifier("Cette publication ne peut être modifiée car elle a déjà une référence!"),
	ExceptionPublicationAuteur("Renseigner au moins un auteur qui existe dans le système"),
	ExceptionRechercheAuteur("Aucune publication avec cet auteur n'existe dans le système"),
	ExceptionRechercheTitre ("Aucune publication avec ce titre n'existe dans le système"),
	ExceptionReferenceCodeExiste("Une autre référence existe dans le système avec ce code"),
	ExceptionReferenceCodeNotExiste("Aucune référence avec ce code n'existe dans le système"),
	ExceptionReferenceSupprimer("Cette référence a été supprimée!"),
	ExceptionReferencePublicationNotExiste("La publication que vous voulez référencer n'existe pas dans le système"),
	ExceptionReferencePublicationExiste("La publication cette publication à déjà une référence");
	
	
	private String message = "";

	MessageExceptionCesi(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
