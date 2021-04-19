package ig.mini.product.khata.db.prime.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import ig.central.library.FrameworkEntity;
import ig.central.library.TransientColumn;
import ig.central.library.annotation.UpdateCacheEntityColumn;

@Entity
@Table(name = "pro_sell_product_maps")
public class ProSellProductMap extends FrameworkEntity implements Serializable {

	private static final long serialVersionUID = -4873550272982747872L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "sell_id", nullable = false, updatable = false)
	private Long sellId;

	@Column(name = "product_id", nullable = false, updatable = false)
	private Long productId;

	@UpdateCacheEntityColumn
	@Column(name = "sell_quantity")
	private Double sellQuantity;

	@UpdateCacheEntityColumn
	@Column(name = "amount_before_tax")
	private Double amountBeforeTax;

	@UpdateCacheEntityColumn
	@Column(name = "gst_amount")
	private Double gstAmount;

	@UpdateCacheEntityColumn
	@Column(name = "discount_amount")
	private Double discountAmount;

	@UpdateCacheEntityColumn
	@Column(name = "payable_amount")
	private Double payableAmount;

	@Transient
	@TransientColumn(name = "product_name")
	private String productName;

	@Version
	@UpdateCacheEntityColumn
	@Column(name = "version_id")
	private int versionId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSellId() {
		return sellId;
	}

	public void setSellId(Long sellId) {
		this.sellId = sellId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Double getSellQuantity() {
		return sellQuantity;
	}

	public void setSellQuantity(Double sellQuantity) {
		this.sellQuantity = sellQuantity;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getVersionId() {
		return versionId;
	}

	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}

}
