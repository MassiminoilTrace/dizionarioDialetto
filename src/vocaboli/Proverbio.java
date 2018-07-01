package vocaboli;

class Proverbio {
	private String traduzioneLingua1;
	private String traduzioneLingua2;

	Proverbio(String tradLingua1, String tradLingua2)
	{
		this.traduzioneLingua1 = tradLingua1;
		this.traduzioneLingua2 = tradLingua2;
	}

	String getTraduzioneLingua1() {
		return traduzioneLingua1;
	}

	String getTraduzioneLingua2() {
		return traduzioneLingua2;
	}
}
