package vocaboli;

import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Vocabolario {
	private Collection<Parola> listaParole= new LinkedList<>();
	private Collection<String> listaLingue = new LinkedList<>();;

	public Vocabolario(Collection<String> nomiLingue) {
		for (String e:nomiLingue)
		{
			this.listaLingue.add(e);
		}
	}

	public void aggiungiParola(String linguaOrigineParola, String parolaOrigine, String linguaDestinazione, Collection<String> traduzioni) throws NoSuchLanguage
	{
		if (!this.listaLingue.contains(linguaOrigineParola) || !this.listaLingue.contains(linguaDestinazione))
		{
			throw new NoSuchLanguage();
		}
		for (String parolaDestinazione:traduzioni)
		{
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

	public Collection<String> ricercaParola(String lingua, String parolaDaCercare)
	{
		return this.listaParole.stream().filter(p -> p.ottieniTraduzione(lingua).equals(parolaDaCercare)).map(p -> p.ottieniTraduzione(lingua)).collect(Collectors.toList());
	}

}
