package eu.telecom_bretagne.CESI.exception;

public enum MessageExceptionCesi {

	ExceptionAuteur("Un auteur avec cet email existe d�j� dans le syst�me!"), 
	ExceptionInstitution("Une institution du m�me nom existe d�j� dans le syst�me!"),
	ExceptionPublicationExiste("Une publication avec le titre existe d�j� dans le syst�me"),
	ExceptionPublicationNotExiste("Cette publication n'existe pas dans le syst�me"),
	ExceptionPublicationNotModifier("Cette publication ne peut �tre modifi�e car elle a d�j� une r�f�rence!"),
	ExceptionPublicationAuteur("Renseigner au moins un auteur qui existe dans le syst�me"),
	ExceptionRechercheAuteur("Aucune publication avec cet auteur n'existe dans le syst�me"),
	ExceptionRechercheTitre ("Aucune publication avec ce titre n'existe dans le syst�me"),
	ExceptionReferenceCodeExiste("Une autre r�f�rence existe dans le syst�me avec ce code"),
	ExceptionReferenceCodeNotExiste("Aucune r�f�rence avec ce code n'existe dans le syst�me"),
	ExceptionReferenceSupprimer("Cette r�f�rence a �t� supprim�e!"),
	ExceptionReferencePublicationNotExiste("La publication que vous voulez r�f�rencer n'existe pas dans le syst�me"),
	ExceptionReferencePublicationExiste("La publication cette publication � d�j� une r�f�rence");
	
	
	private String message = "";

	MessageExceptionCesi(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
