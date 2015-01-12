package eu.telecom_bretagne.CESI.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class testER {

	public static void main(String[] args) {
		String titre ="La rose médiévale de la cathédrale de Poitiers";
		String expression = " ";
		Pattern p = Pattern.compile(expression) ; 
		String [] tableau = p.split(titre) ;
		StringBuffer requeteBuffer = new StringBuffer("select p from Publication p ");
		boolean premiereClause = true;
		List<String> var = new ArrayList<String>();
		int i = 0;
		for (String string : tableau) {
			//String pattern = String.format("%{0}%", string);
			requeteBuffer.append(premiereClause ? "where " : " or ");
			requeteBuffer.append("p.titre = :titre"+i);
			premiereClause = false;
			var.add("titre"+i);
		}
		for (String string : tableau) {
			
		}
		System.out.println(requeteBuffer.toString());
	}

}
