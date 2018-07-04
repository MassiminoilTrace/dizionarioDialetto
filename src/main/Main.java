package main;

import java.util.Collection;
import java.util.LinkedList;

import vocaboli.NoSuchLanguage;
import vocaboli.Vocabolario;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Collection<String> lingueAccettate = new LinkedList<>();
		lingueAccettate.add("SANREMASCO");
		lingueAccettate.add("ITALIANO");
		Vocabolario gestVoc = new Vocabolario(lingueAccettate);
		gestVoc.aggiungiParola("SANREMAsCO", "BRUGAJA", "ITALIANO", new String[]{"BRICIOLA"});
		gestVoc.aggiungiParola("SANREMASCO", "ABELINATO", "ITALIANO", new String[]{"MINCHIONE"});
		
		System.out.println(gestVoc.ricercaParola("ITALIANO", "SANREMASCO", "BRICIOLA"));//[brugaja}
		System.out.println(gestVoc.cercaQuasi("SANREMASCO", "ABELINARO"));
		System.out.println(gestVoc.cercaQuasi("SANREMASCO", "BLUGAKA"));
		System.out.println(gestVoc.cercaQuasi("italiano", "BRICKOLà"));
		
	}

}
