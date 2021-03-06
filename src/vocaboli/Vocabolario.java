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
	public Collection<Parola> listaParole= new LinkedList<>();
	private Collection<String> listaLingue = new LinkedList<>(); // TUTTO IN MAIUSCOLO

	public Vocabolario(Collection<String> nomiLingue) {
		for (String e:nomiLingue)
		{
			this.listaLingue.add(e.toUpperCase());
		}
	}
	
	public Vocabolario(FileReader f)
	{
		this.caricaDati(f);
		
	}
	
	public List<String> getListaLingue()
	{
		return this.listaLingue.stream().collect(Collectors.toList());
	}
	
	public void aggiungiParola(String linguaOrigineParolAarg, String parolaOrigineArg, String linguaDestinazioneArg, String traduzione)
	{
		String linguaOrigineParola = linguaOrigineParolAarg.toUpperCase();
		String parolaOrigine=parolaOrigineArg.toUpperCase().trim();
		String linguaDestinazione=linguaDestinazioneArg.toUpperCase();


		if (!this.listaLingue.contains(linguaOrigineParola.toUpperCase()) || !this.listaLingue.contains(linguaDestinazione.toUpperCase()))
		{
			System.err.println("Lingua non trovata");
			return;
		}

		String parolaDestinazioneArg = traduzione;
		String parolaDestinazione = parolaDestinazioneArg.toUpperCase().trim();
		if (this.listaParole.stream()
				.filter(p -> p.ottieniTraduzione(linguaOrigineParola).equals(parolaOrigine)).count()==0
				||
				this.listaParole.stream().filter(p -> p.ottieniTraduzione(linguaDestinazione).equals(parolaDestinazione)).count()==0)
		{
			Parola p = new Parola();
			this.listaParole.add(p);
			p.aggiungiTraduzione(linguaOrigineParola, parolaOrigine);
			p.aggiungiTraduzione(linguaDestinazione, parolaDestinazione);
		}
	}

	public boolean aggiungiParole(String linguaOrigineParolAarg, String parolaOrigineArg[], String linguaDestinazioneArg, String[] traduzioni)
	{
		String linguaOrigineParola = linguaOrigineParolAarg.toUpperCase();
		String linguaDestinazione=linguaDestinazioneArg.toUpperCase();
		
		
		if (!this.listaLingue.contains(linguaOrigineParola.toUpperCase()) || !this.listaLingue.contains(linguaDestinazione.toUpperCase()))
		{
			System.err.println("Lingua non trovata");
			return false;
		}
		
		for (String parolaOrigineLow:parolaOrigineArg)
		{
			String parolaOrigine= parolaOrigineLow.toUpperCase().trim();
			for (String parolaDestinazioneLow:traduzioni)
			{
				String parolaDestinazione = parolaDestinazioneLow.toUpperCase().trim();
				
				//Associo ogni coppia di parole
				
				//Se l'associazione non esiste ancora
				if (this.listaParole.stream()
						.filter(p -> 
						p.ottieniTraduzione(linguaOrigineParola).equals(parolaOrigine)
						&& p.ottieniTraduzione(linguaDestinazione).equals(parolaDestinazione)
						).count()==0)
				{
					Parola p = new Parola();
					this.listaParole.add(p);
					p.aggiungiTraduzione(linguaOrigineParola, parolaOrigine);
					p.aggiungiTraduzione(linguaDestinazione, parolaDestinazione);
				}
				
			}
		}
		
		
		return true;
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
				.distinct()
				.collect(Collectors.toList());
	}
	
	public String cercaQuasi(String linguaArg,String parolaDaCercareArg)
	{
		String lingua=linguaArg.toUpperCase();
		String parolaDaCercare = parolaDaCercareArg.toUpperCase();
		if (!this.listaLingue.contains(lingua))
		{
			System.err.println("Lingua non trovata");
			return "";
		}
		VettoreParola vP = new VettoreParola(parolaDaCercare);
		return this.listaParole.stream().map(p -> new VettoreParola(p.ottieniTraduzione(lingua)))
				.distinct()
				.sorted(Comparator.comparing(v -> v.distanzaA(vP)))
				.map(VettoreParola::getParola).findFirst().orElse("");
	}
	
	public void nuovoProverbio(String linguaOrigine, String proverbioOrigine, String linguaDestinazione, String proverbioDestinazione)
	{
		//DA FARE
	}
	
	public void caricaDati(FileReader f)//IMPORTAZIONE FILE CSV
	{
		//funziona <3
		BufferedReader br = new BufferedReader(f);
		List<String> listaLinee = br.lines().collect(Collectors.toList());
		String[] lingue = listaLinee.remove(0).split(";");
		if (this.listaLingue.size()==0)
		{
			for (String e:lingue)
			{
				this.listaLingue.add(e.toUpperCase());
			}
		}
		
//		System.err.println(lingue[0]+" "+lingue[1]);
		for (String linea : listaLinee)
		{
			String[] traduzioni = linea.split(";");
			this.aggiungiParole(lingue[0], traduzioni[0].split("\b*,\b*"), lingue[1], traduzioni[1].split("\b*,\b*"));
		}
		//br.close();
	}
	public List<String> cercaPezzoParola(String stralcio, String linguaRicerca)
	{
		return this.listaParole.stream()
				.map(v -> v.ottieniTraduzione(linguaRicerca.toUpperCase()))
				.filter(s -> !s.equals(""))
				.filter(p -> p.toUpperCase().contains(stralcio.toUpperCase()))
				.distinct()
				.sorted()
				.collect(Collectors.toList());
	}
	
	public List<String> iniziaCon(String stralcio, String linguaRicerca)
	{
		String stralcio2= stralcio.toUpperCase();
		return this.listaParole.stream()
				.map(p -> p.ottieniTraduzione(linguaRicerca))
				.filter(s -> s.matches(stralcio2+".*"))
				.distinct()
				.sorted()
				.collect(Collectors.toList());
	}
	
}
