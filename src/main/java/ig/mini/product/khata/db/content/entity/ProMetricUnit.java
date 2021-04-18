package ig.mini.product.khata.db.content.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import ig.central.library.annotation.UpdateCacheEntityColumn;

@Entity
@Table(name = "PRO_METRIC_UNITS")
public class ProMetricUnit implements Serializable {

	private static final long serialVersionUID = 7428995649960343751L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "METRIC_ID", nullable = false, updatable = false)
	private Long metricId;

	@Column(name = "UNIT_TYPE", nullable = false, updatable = false)
	private String unitType;

	@Column(name = "UNIT_NAME", nullable = false, updatable = false)
	private String unitName;

	@Column(name = "UNIT_SYMBOL", nullable = false, updatable = false)
	private String unitSymbol;

	@Version
	@UpdateCacheEntityColumn
	@Column(name = "version_id")
	private int versionId;

	public Long getMetricId() {
		return metricId;
	}

	public void setMetricId(Long metricId) {
		this.metricId = metricId;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitSymbol() {
		return unitSymbol;
	}

	public void setUnitSymbol(String unitSymbol) {
		this.unitSymbol = unitSymbol;
	}

	public int getVersionId() {
		return versionId;
	}

	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}

}
