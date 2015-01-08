package eu.telecom_bretagne.CESI.data.util;

import java.util.List;

public class HelperCesi {
	
	
	/**
	 * Vérifie qu'une liste n'est pas null et vide
	 * @param T
	 * @return
	 */
	public static boolean listIsEmpty(List T){
		if(null != T && !T.isEmpty()){
			return false;
		}
		return true;
	}
}
