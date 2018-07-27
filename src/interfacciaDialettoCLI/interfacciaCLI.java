package interfacciaDialettoCLI;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Collectors;

import vocaboli.Vocabolario;

public class interfacciaCLI {

	public static void main(String[] args) {
		System.out.println("Dizionario interattivo, versione da terminale.\nCreato e ideato da Massimo Gismondi - giugno - luglio 2018\n\n");
		
		System.out.print("Caricamento parole dizionario... ");
		String percorso;
		if (args.length==1)
		{
			percorso=args[0];
		}
		else
		{
			percorso = "./parole.csv";
		}
		Vocabolario vc = null;
		try {
			//System.out.println("Creazione ogg vocabolario");
			vc = new Vocabolario(new FileReader(percorso));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("Errore, file "+percorso + "non trovato");
			e.printStackTrace();
			//System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("IOException avvenuta");
			e.printStackTrace();
			//System.exit(0);
		}
		
		System.out.println("[OK] caricate "+vc.listaParole.size()+" parole");
		
//		System.out.println("\n\nDizionario avviato correttamente:");
//		System.out.println("Scegli l'operazione da eseguire:\n\t1 - ricerca da "+vc.getListaLingue().get(0)+" a "+vc.getListaLingue().get(1));
//		System.out.println("\t2 - ricerca da "+vc.getListaLingue().get(1)+" a"+vc.getListaLingue().get(0));
//		System.out.println("\t9 - esci");
		System.out.println("\nDizionario avviato correttamente:");
		
		int scelta=0;
		Scanner sc = new Scanner(System.in);
		do
		{
			System.out.println("\n\nScegli l'operazione da eseguire:\n\t1 - ricerca da "+vc.getListaLingue().get(0)+" a "+vc.getListaLingue().get(1));
			System.out.println("\t2 - ricerca da "+vc.getListaLingue().get(1)+" a "+vc.getListaLingue().get(0));
			System.out.println("\t3 - parola in "+vc.getListaLingue().get(0)+" che inizia con...");
			System.out.println("\t4 - parola in "+vc.getListaLingue().get(1)+" che inizia con...");
			System.out.println("\t9 - esci");
			scelta = 0;
			while (scelta == 0)
			{
				
				try
				{
					System.out.print(">>> ");
					scelta = sc.nextInt();
				}
				catch(InputMismatchException e)
				{
					System.out.println("Inserisci un numero");
					scelta = 0;
					sc.nextLine();
				}
			}
			if (scelta==1 || scelta==2)
			{
				String linguaOrig = (scelta==1 ? vc.getListaLingue().get(0) : vc.getListaLingue().get(1));
				String linguaDest = (scelta==2 ? vc.getListaLingue().get(0) : vc.getListaLingue().get(1));
				System.out.println("Inserisci la parola in "+ (scelta==1 ? vc.getListaLingue().get(0) : vc.getListaLingue().get(1)));
				
				sc.nextLine();
				System.out.print(">>> ");
				String strInput = sc.nextLine();
				
				//Risultati
				System.out.println("\n\nRisultati ricerca di "+ strInput);
				
				//Esatta
				System.out.println("\nTraduzione esatta:");
				System.out.println(vc.ricercaParola(linguaOrig, linguaDest, strInput));
				
				//
				System.out.println("\nRicerca parola simile:");
				System.out.println(vc.cercaQuasi(linguaOrig, strInput)+" -> "+vc.ricercaParola(linguaOrig, linguaDest, vc.cercaQuasi(linguaOrig, strInput)));
			}
			
			if (scelta==3||scelta==4)
			{
				System.out.println("Inserisci la parola in "+(scelta==3 ? vc.getListaLingue().get(0) : vc.getListaLingue().get(1)));
				sc.nextLine();
				System.out.print(">>> ");
				String strInput = sc.nextLine();
				System.out.println(vc.iniziaCon(strInput, (scelta==3 ? vc.getListaLingue().get(0) : vc.getListaLingue().get(1))).stream().collect(Collectors.joining("\n")));
				
			}
		}
		while(scelta!=9);

		System.out.println("Uscita dal programma [OK]");
		System.exit(0);
	}
}
