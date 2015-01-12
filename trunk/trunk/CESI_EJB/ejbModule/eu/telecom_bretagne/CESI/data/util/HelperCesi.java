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
	
	/**
	 * Generateur de chaine
	 * @param length
	 * @return
	 */
	public static String generate(int length)
	{
		    String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; 
		    String pass = "";
		    for(int x=0;x<length;x++)
		    {
		       int i = (int)Math.floor(Math.random() * 62); 
		       pass += chars.charAt(i);
		    }
		    System.out.println(pass);
		    return pass;
	}
}
