package cn.com.hinova.ruvs.common.exception;

public class CFException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CFException() {
		super();
	}

	public CFException(String message, Throwable cause) {
		super(message, cause);
	}

	public CFException(String message) {
		super(message);
	}

	public CFException(Throwable cause) {
		super(cause);
	}

}
