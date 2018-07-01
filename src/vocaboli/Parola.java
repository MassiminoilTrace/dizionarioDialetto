package vocaboli;

import java.util.HashMap;
import java.util.Map;

public class Parola {
	private Map<String, String> dizLinguaTraduzione = new HashMap<>();
	
	void aggiungiTraduzione(String lingua, String parola)
	{
		this.dizLinguaTraduzione.put(lingua, parola);
	}
	
	String ottieniTraduzione(String linguaRichiesta)
	{
		if (this.dizLinguaTraduzione.get(linguaRichiesta)!=null)
		{
			return this.dizLinguaTraduzione.get(linguaRichiesta);
		}
		else
		{
			return "";
		}
	}
	
	boolean traduzioneEsistente(String lingua)
	{
		return this.dizLinguaTraduzione.containsKey(lingua);
	}
	
}
