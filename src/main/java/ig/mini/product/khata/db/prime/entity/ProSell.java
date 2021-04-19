package ig.mini.product.khata.db.prime.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.springframework.format.annotation.DateTimeFormat;

import ig.central.library.FrameworkEntity;
import ig.central.library.TransientColumn;
import ig.central.library.annotation.UpdateCacheEntityColumn;

//TODO.. payment_mode 
@Entity
@Table(name = "pro_sells")
public class ProSell extends FrameworkEntity implements Serializable {

	private static final long serialVersionUID = -5791670870890184848L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sell_id", nullable = false, updatable = false)
	private Long sellId;

	@Column(name = "customer_id", nullable = false, updatable = false)
	private Long customerId;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "sell_date")
	private Date sellDate;

	@Column(name = "sell_notes")
	private String sellNotes;

	@UpdateCacheEntityColumn
	@Column(name = "is_amount_settled", nullable = false)
	private String isAmountSettled = "N";

	@Column(name = "created_by", columnDefinition = "VARCHAR(60) NOT NULL DEFAULT 'SEED_DATA_FROM_APPLICATION'", updatable = false, nullable = false)
	private String createdBy;

	// @Temporal should only be set on a java.util.Date or java.util.Calendar
	// @Temporal(TemporalType.DATE)//my-sql TIMESTAMP
	@Column(name = "creation_date", columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP", updatable = false, nullable = false)
	private Date creationDate;

	@Column(name = "last_updated_by", columnDefinition = "VARCHAR(60) NOT NULL DEFAULT 'SEED_DATA_FROM_APPLICATION'", nullable = false)
	private String lastUpdatedBy;

	// @Temporal should only be set on a java.util.Date or java.util.Calendar
	// @Temporal(TemporalType.DATE)
	@Column(name = "last_update_date", columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP", nullable = false)
	private Date lastUpdateDate;

	@Version
	@UpdateCacheEntityColumn
	@Column(name = "version_id")
	private int versionId;

	@Transient
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@TransientColumn(name = "sell_date")
	private java.util.Date sellDateUi;

	public Long getSellId() {
		return sellId;
	}

	public void setSellId(Long sellId) {
		this.sellId = sellId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Date getSellDate() {
		return sellDate;
	}

	public void setSellDate(Date sellDate) {
		this.sellDate = sellDate;
	}

	public String getSellNotes() {
		return sellNotes;
	}

	public void setSellNotes(String sellNotes) {
		this.sellNotes = sellNotes;
	}

	public String getIsAmountSettled() {
		return isAmountSettled;
	}

	public void setIsAmountSettled(String isAmountSettled) {
		this.isAmountSettled = isAmountSettled;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public int getVersionId() {
		return versionId;
	}

	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}

	public java.util.Date getSellDateUi() {
		return sellDateUi;
	}

	public void setSellDateUi(java.util.Date sellDateUi) {
		this.sellDateUi = sellDateUi;
	}

}
