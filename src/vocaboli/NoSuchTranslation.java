package vocaboli;

public class NoSuchTranslation extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NoSuchTranslation ()
	{
		super("Traduzione non trovata nella parola");
	}
	public NoSuchTranslation(String msg)
	{
		super(msg);
	}

}
