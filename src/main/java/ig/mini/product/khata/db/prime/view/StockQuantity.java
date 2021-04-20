package ig.mini.product.khata.db.prime.view;

import ig.central.library.FrameworkEntity;
import ig.central.library.TransientColumn;

public class StockQuantity extends FrameworkEntity {

	@TransientColumn(name = "purchase_id")
	private Long purchaseId;

	@TransientColumn(name = "product_id")
	private Long productId;

	@TransientColumn(name = "purchase_quantity")
	private Double purchaseQuantity;

	@TransientColumn(name = "manufacture_quantity")
	private Double manufactureQuantity;

	@TransientColumn(name = "sell_quantity")
	private Double selltQuantity;

	public Long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Double getPurchaseQuantity() {
		return purchaseQuantity;
	}

	public void setPurchaseQuantity(Double purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}

	public Double getManufactureQuantity() {
		return manufactureQuantity;
	}

	public void setManufactureQuantity(Double manufactureQuantity) {
		this.manufactureQuantity = manufactureQuantity;
	}

	public Double getSelltQuantity() {
		return selltQuantity;
	}

	public void setSelltQuantity(Double selltQuantity) {
		this.selltQuantity = selltQuantity;
	}

}
