package exception;

public class WrongIdException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WrongIdException() {};
	
	public WrongIdException(String msg)
	{
		super(msg);
	}

}
