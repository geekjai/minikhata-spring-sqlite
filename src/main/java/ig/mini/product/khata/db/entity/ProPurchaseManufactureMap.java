package ig.mini.product.khata.db.entity;

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
@Table(name = "pro_purchase_manufacture_map")
public class ProPurchaseManufactureMap extends FrameworkEntity implements Serializable {

	private static final long serialVersionUID = -6962587442107119396L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "product_id", updatable = false, nullable = false)
	private Long productId;

	@Column(name = "purchase_id", updatable = false)
	private Long purchaseId;

	@Column(name = "in_quantity", updatable = false)
	private Double inQuantity;

	@Column(name = "manufacture_id", updatable = false)
	private Long manufactureId;

	@Column(name = "out_quantity", updatable = false)
	private Double outQuantity;

	@Version
	@UpdateCacheEntityColumn
	@Column(name = "version_id")
	private Long versionId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Double getInQuantity() {
		return inQuantity;
	}

	public void setInQuantity(Double inQuantity) {
		this.inQuantity = inQuantity;
	}

	public Long getManufactureId() {
		return manufactureId;
	}

	public void setManufactureId(Long manufactureId) {
		this.manufactureId = manufactureId;
	}

	public Double getOutQuantity() {
		return outQuantity;
	}

	public void setOutQuantity(Double outQuantity) {
		this.outQuantity = outQuantity;
	}

	public Long getVersionId() {
		return versionId;
	}

	public void setVersionId(Long versionId) {
		this.versionId = versionId;
	}

}
