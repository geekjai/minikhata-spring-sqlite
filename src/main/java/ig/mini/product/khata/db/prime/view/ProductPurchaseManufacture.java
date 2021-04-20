package ig.mini.product.khata.db.prime.view;

import ig.central.library.FrameworkEntity;
import ig.central.library.TransientColumn;

public class ProductPurchaseManufacture extends FrameworkEntity {

	@TransientColumn(name = "purchase_id")
	private Long purchaseId;

	@TransientColumn(name = "product_id")
	private Long productId;

	@TransientColumn(name = "purchase_quantity")
	private Double purchaseQuantity;

	@TransientColumn(name = "amount_before_tax")
	private Double amountBeforeTax;

	@TransientColumn(name = "gst_amount")
	private Double gstAmount;

	@TransientColumn(name = "discount_amount")
	private Double discountAmount;

	@TransientColumn(name = "payable_amount")
	private Double payableAmount;

	@TransientColumn(name = "manufacture_quantity")
	private Double manufactureQuantity;

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

	public Double getAmountBeforeTax() {
		return amountBeforeTax;
	}

	public void setAmountBeforeTax(Double amountBeforeTax) {
		this.amountBeforeTax = amountBeforeTax;
	}

	public Double getGstAmount() {
		return gstAmount;
	}

	public void setGstAmount(Double gstAmount) {
		this.gstAmount = gstAmount;
	}

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Double getPayableAmount() {
		return payableAmount;
	}

	public void setPayableAmount(Double payableAmount) {
		this.payableAmount = payableAmount;
	}

	public Double getManufactureQuantity() {
		return manufactureQuantity;
	}

	public void setManufactureQuantity(Double manufactureQuantity) {
		this.manufactureQuantity = manufactureQuantity;
	}

}
