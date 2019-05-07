package exception;

public class InexistentProductException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InexistentProductException() {};
	
	public InexistentProductException(String msg)
	{
		super(msg);
	}

}
