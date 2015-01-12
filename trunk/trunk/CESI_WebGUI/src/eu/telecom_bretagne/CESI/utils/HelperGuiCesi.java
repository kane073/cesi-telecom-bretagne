package eu.telecom_bretagne.CESI.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class HelperGuiCesi {
	
	public static String afficheHeureFormat(String format, Date date){
		SimpleDateFormat formater = new SimpleDateFormat(format);
		if(date==null)
			return "";
		return formater.format(date);
	}
	
	public static boolean valideChamp(String champ, int taille){
		if(champ != null && champ.length()!=taille){
			return true;
		}
		return false;
	}
	
	public static boolean isValidEmailAddress(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
		}
}
