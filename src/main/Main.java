package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

import vocaboli.NoSuchLanguage;
import vocaboli.Vocabolario;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Collection<String> lingueAccettate = new LinkedList<>();
		lingueAccettate.add("SANREMASCO");
		lingueAccettate.add("ITALIANO");
		Vocabolario gestVoc = new Vocabolario(lingueAccettate);
		gestVoc.caricaDati(new FileReader("./parole.csv"));
//		gestVoc.aggiungiParole("SANREMAsCO", "BRUGAJA", "ITALIANO", new String[]{"BRICIOLA", "pezzetto"});
//		gestVoc.aggiungiParola("SANREMASCO", "ABELINATO", "ITALIANO", "MINCHIONE");
//		
		System.out.println(gestVoc.listaParole.size());
		
		System.out.println(gestVoc.ricercaParola("SANREMASCO","ITALIANO", gestVoc.cercaQuasi("SANREMASCO", "acata")));//[brugaja}
		System.out.println(gestVoc.cercaQuasi("SANREMASCO", "acata"));
		System.out.println(gestVoc.cercaQuasi("SANREMASCO", "AIpEi"));
		System.out.println("Ricerca di stralci"+ gestVoc.cercaPezzoParola("ci", "Sanremasco"));
		System.out.println(gestVoc.ricercaParola("SANREMASCO","ITALIANO", "D'ACIATUN"));
//		System.out.println(gestVoc.cercaQuasi("italiano", "BRICKOLà"));
//		System.out.println(gestVoc.cercaQuasi("italiano", "mnchine"));
//		System.out.println(gestVoc.ricercaParola("sanremasco", "italiano", "brugaja"));
		
	}

}
