package hu.nsmdmp.matrix.operation;

public class MatrixMathException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MatrixMathException() {
		super();
	}

	public MatrixMathException(String message) {
		super(message);
	}

	public MatrixMathException(String message, Throwable cause) {
		super(message, cause);
	}

	public MatrixMathException(Throwable cause) {
		super(cause);
	}
}
