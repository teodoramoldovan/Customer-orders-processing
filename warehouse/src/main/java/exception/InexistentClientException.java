package exception;

public class InexistentClientException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InexistentClientException() {};
	
	public InexistentClientException(String msg)
	{
		super(msg);
	}

}
