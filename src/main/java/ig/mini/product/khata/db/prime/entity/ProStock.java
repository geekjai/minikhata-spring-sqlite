package ig.mini.product.khata.db.prime.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import ig.central.library.FrameworkEntity;
import ig.central.library.annotation.UpdateCacheEntityColumn;

@Entity
@Table(name = "pro_stocks")
public class ProStock extends FrameworkEntity implements Serializable {

	private static final long serialVersionUID = -4369564141152188301L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "stock_id", nullable = false, updatable = false)
	private Long stockId;

	@Column(name = "product_id", updatable = false, nullable = false)
	private Long productId;

	@Column(name = "purchase_id", updatable = false)
	private Long purchaseId;

	@Column(name = "purchase_quantity", updatable = false)
	private Double purchaseQuantity;

	@Column(name = "manufacture_id", updatable = false)
	private Long manufactureId;

	@Column(name = "manufacture_quantity", updatable = false)
	private Double manufactureQuantity;

	@Column(name = "sell_id ", updatable = false)
	private Long sellId;

	@Column(name = "sell_quantity", updatable = false)
	private Double sellQuantity;

	@Version
	@UpdateCacheEntityColumn
	@Column(name = "version_id")
	private int versionId;

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
	}

	public Double getPurchaseQuantity() {
		return purchaseQuantity;
	}

	public void setPurchaseQuantity(Double purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}

	public Long getManufactureId() {
		return manufactureId;
	}

	public void setManufactureId(Long manufactureId) {
		this.manufactureId = manufactureId;
	}

	public Double getManufactureQuantity() {
		return manufactureQuantity;
	}

	public void setManufactureQuantity(Double manufactureQuantity) {
		this.manufactureQuantity = manufactureQuantity;
	}

	public Long getSellId() {
		return sellId;
	}

	public void setSellId(Long sellId) {
		this.sellId = sellId;
	}

	public Double getSellQuantity() {
		return sellQuantity;
	}

	public void setSellQuantity(Double sellQuantity) {
		this.sellQuantity = sellQuantity;
	}

	public int getVersionId() {
		return versionId;
	}

	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}

}
