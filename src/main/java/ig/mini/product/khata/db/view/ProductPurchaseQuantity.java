package ig.mini.product.khata.db.view;

import ig.mini.product.khata.db.api.FrameworkEntity;
import ig.mini.product.khata.db.api.TransientColumn;

public class ProductPurchaseQuantity extends FrameworkEntity {

	@TransientColumn(name = "purchase_id")
	private Long purchaseId;

	@TransientColumn(name = "product_id")
	private Long productId;

	@TransientColumn(name = "in_quantity")
	private Double inQuantity;

	@TransientColumn(name = "out_quantity")
	private Double outQuantity;

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

	public Double getInQuantity() {
		return inQuantity;
	}

	public void setInQuantity(Double inQuantity) {
		this.inQuantity = inQuantity;
	}

	public Double getOutQuantity() {
		return outQuantity;
	}

	public void setOutQuantity(Double outQuantity) {
		this.outQuantity = outQuantity;
	}

}
