package ig.mini.product.khata.db.setup.entity;

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

import ig.central.library.FrameworkEntity;
import ig.central.library.annotation.UpdateCacheEntityColumn;

@Entity
@Table(name = "pro_file_execution_entries")
public class ProFileExecutionEntry extends FrameworkEntity implements Serializable {

	private static final long serialVersionUID = 3892057948134135792L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "execution_id", updatable = false, nullable = false)
	private Long executionId;

	@Column(name = "file_path", updatable = false, nullable = false)
	private String filePath;

	@Column(name = "created_by", updatable = false, nullable = false)
	private String createdBy;

	// @Temporal should only be set on a java.util.Date or java.util.Calendar
	// @Temporal(TemporalType.DATE)
	@Column(name = "creation_date", updatable = false, nullable = false)
	private Date creationDate;

	@Column(name = "last_updated_by", nullable = false)
	private String lastUpdatedBy;

	// @Temporal should only be set on a java.util.Date or java.util.Calendar
	// @Temporal(TemporalType.DATE)
	@Column(name = "last_update_date", nullable = false)
	private Date lastUpdateDate;

	@Version
	@UpdateCacheEntityColumn
	@Column(name = "version_id")
	private Long versionId;

	@Transient
	private Boolean isSeedData;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getExecutionId() {
		return executionId;
	}

	public void setExecutionId(Long executionId) {
		this.executionId = executionId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
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

	public Long getVersionId() {
		return versionId;
	}

	public void setVersionId(Long versionId) {
		this.versionId = versionId;
	}

	public Boolean getIsSeedData() {
		return isSeedData;
	}

	public void setIsSeedData(Boolean isSeedData) {
		this.isSeedData = isSeedData;
	}

}
