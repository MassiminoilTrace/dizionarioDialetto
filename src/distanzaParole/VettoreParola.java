package distanzaParole;


import java.util.HashMap;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class VettoreParola {
	String parola;
	Map<Character, Integer> vettParola = new HashMap<>();
	public VettoreParola(String parola)
	{
		this.parola=parola;
		for (int i = 0; i < parola.length(); i++)
		{
			char c = parola.charAt(i);
			this.vettParola.put(c, this.vettParola.getOrDefault(c, 0)+1);
		}
	}
	
	public Map<Character, Integer> getVettore()
	{
		return this.vettParola;
	}
	
	public double distanzaA(VettoreParola v2)
	{
		Set<Character> chiavi = new TreeSet<>();
		for (char c : this.vettParola.keySet())
		{
			chiavi.add(c);
		}
		for (char c: v2.getVettore().keySet())
		{
			chiavi.add(c);
		}
		
		double sommaIntermedia = 0;
		for (char c : chiavi)
		{
			sommaIntermedia += Math.pow( v2.getVettore().getOrDefault(c, 0) - this.vettParola.getOrDefault(c, 0)  , 2);
		}
		//System.err.println(Math.sqrt(sommaIntermedia));
		return Math.sqrt(sommaIntermedia);
	}
	
	public String getParola()
	{
		return this.parola;
	}
	
	
	
	
	
}
