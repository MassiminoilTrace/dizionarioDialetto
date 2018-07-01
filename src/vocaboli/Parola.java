package vocaboli;

public class Parola {
	private String traduzioneLingua1;
	private String traduzioneLingua2;

	Parola(String traduzioneLingua1, String traduzioneLingua2)
	{
		this.traduzioneLingua1 = traduzioneLingua1;
		this.traduzioneLingua2 = traduzioneLingua2;
	}

	String getTraduzioneLingua1() {
		return traduzioneLingua1;
	}

	String getTraduzioneLingua2() {
		return traduzioneLingua2;
	}
	
}
