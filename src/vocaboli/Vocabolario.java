package vocaboli;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import distanzaParole.VettoreParola;

public class Vocabolario {
	private Collection<Parola> listaParole= new LinkedList<>();
	private Collection<String> listaLingue = new LinkedList<>(); // TUTTO IN MAIUSCOLO

	public Vocabolario(Collection<String> nomiLingue) {
		for (String e:nomiLingue)
		{
			this.listaLingue.add(e.toUpperCase());
		}
	}

	public void aggiungiParola(String linguaOrigineParolAarg, String parolaOrigineArg, String linguaDestinazioneArg, String[] traduzioni)
	{
		String linguaOrigineParola = linguaOrigineParolAarg.toUpperCase();
		String parolaOrigine=parolaOrigineArg.toUpperCase();
		String linguaDestinazione=linguaDestinazioneArg.toUpperCase();
		
		
		if (!this.listaLingue.contains(linguaOrigineParola.toUpperCase()) || !this.listaLingue.contains(linguaDestinazione.toUpperCase()))
		{
			System.err.println("Lingua non trovata");
			return;
		}
		for (String parolaDestinazioneArg:traduzioni)
		{
			String parolaDestinazione = parolaDestinazioneArg.toUpperCase();
			if (this.listaParole.stream()
					.filter(p -> p.ottieniTraduzione(linguaOrigineParola).equals(parolaOrigine) && p.ottieniTraduzione(linguaDestinazione).equals(parolaDestinazione)).count()==0)
			{
				Parola p = new Parola();
				this.listaParole.add(p);
				p.aggiungiTraduzione(linguaOrigineParola, parolaOrigine);
				p.aggiungiTraduzione(linguaDestinazione, parolaDestinazione);
			}
		}
	}

	public List<String> ricercaParola(String linguaOrigineArg, String linguaTraduzioneArg, String parolaDaCercareArg) ///DEVE RITORNARE UNA MAPPA CON LE TRADUZIONI OTTENUTE; QUINDI GROUPINGBY; YEEE
	{
		String linguaOrigine = linguaOrigineArg.toUpperCase();
		String linguaTraduzione=linguaTraduzioneArg.toUpperCase();
		String parolaDaCercare = parolaDaCercareArg.toUpperCase();
		if (!this.listaLingue.contains(linguaOrigine) || !this.listaLingue.contains(linguaTraduzione))
		{
			System.err.println("Lingua non trovata");
			return new LinkedList<String>();
		}
		return this.listaParole.stream().filter(p -> p.ottieniTraduzione(linguaOrigine).equals(parolaDaCercare))
				.map(p-> p.ottieniTraduzione(linguaTraduzione))
				.collect(Collectors.toList());
	}
	
	public List<String> cercaQuasi(String linguaArg, String parolaDaCercareArg)
	{
		String lingua=linguaArg.toUpperCase();
		String parolaDaCercare = parolaDaCercareArg.toUpperCase();
		if (!this.listaLingue.contains(lingua))
		{
			System.err.println("Lingua non trovata");
			return new LinkedList<String>();
		}
		VettoreParola vP = new VettoreParola(parolaDaCercare);
		return this.listaParole.stream().map(p -> new VettoreParola(p.ottieniTraduzione(lingua))).sorted(Comparator.comparing(v -> v.distanzaA(vP))).map(VettoreParola::getParola).limit(10).collect(Collectors.toList());
	}
	
	public void nuovoProverbio(String linguaOrigine, String proverbioOrigine, String linguaDestinazione, String proverbioDestinazione)
	{
		//DA FARE
	}
	
	public void caricaDati(FileReader f)//IMPORTAZIONE FILE CSV
	{
		//DA FARE
		BufferedReader br = new BufferedReader(f);
	}
	
	
}
