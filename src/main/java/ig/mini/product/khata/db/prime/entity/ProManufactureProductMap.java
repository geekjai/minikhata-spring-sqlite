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
@Table(name = "pro_manufacture_product_map")
public class ProManufactureProductMap extends FrameworkEntity implements Serializable, Cloneable {

	private static final long serialVersionUID = 4703784168970284223L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "manufacture_id", updatable = false, nullable = false)
	private Long manufactureId;

	@Column(name = "product_id", updatable = false, nullable = false)
	private Long productId;

	@Column(name = "product_quantity", updatable = false, nullable = false)
	private Double productQuantity;

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

	public Long getManufactureId() {
		return manufactureId;
	}

	public void setManufactureId(Long manufactureId) {
		this.manufactureId = manufactureId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Double getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Double productQuantity) {
		this.productQuantity = productQuantity;
	}

	public int getVersionId() {
		return versionId;
	}

	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
