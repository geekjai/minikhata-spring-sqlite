package ig.mini.product.khata.service.exception;

public class OutOfStockException extends Exception {

	private static final long serialVersionUID = -1881740860365031396L;

	public OutOfStockException() {
		super("Provided Quantity is out of Stock.");
	}
}
