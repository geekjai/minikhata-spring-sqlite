package ig.mini.product.khata.db.content.entity;

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
@Table(name = "pro_purchase_types")
public class ProPurchaseType extends FrameworkEntity implements Serializable {

	private static final long serialVersionUID = -2022686736886061794L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "purchase_type_id", nullable = false, updatable = false)
	private Long purchaseTypeId;

	@Column(name = "purchase_type_code", nullable = false, updatable = false)
	private String purchaseTypeCode;

	@Column(name = "description")
	private String description;

	@Version
	@UpdateCacheEntityColumn
	@Column(name = "version_id")
	private int versionId;

	public Long getPurchaseTypeId() {
		return purchaseTypeId;
	}

	public void setPurchaseTypeId(Long purchaseTypeId) {
		this.purchaseTypeId = purchaseTypeId;
	}

	public String getPurchaseTypeCode() {
		return purchaseTypeCode;
	}

	public void setPurchaseTypeCode(String purchaseTypeCode) {
		this.purchaseTypeCode = purchaseTypeCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getVersionId() {
		return versionId;
	}

	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}

}
