package exception;

public class StockTooLowException extends Exception {
		private static final long serialVersionUID = 1L;

		public StockTooLowException() {};
		
		public StockTooLowException(String msg)
		{
			super(msg);
		}

	
}
