package ig.mini.product.khata.db.entity;

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

@Entity
@Table(name = "pro_manufactures")
public class ProManufacture extends FrameworkEntity implements Serializable {

	private static final long serialVersionUID = 2623473953010212903L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "manufacture_id", nullable = false, updatable = false)
	private Long manufactureId;

	@Column(name = "product_id", nullable = false, updatable = false)
	private Long productId;

	@Column(name = "manufacture_quantity")
	private Double manufactureQuantity;

	@Column(name = "manufacture_cost")
	private Double manufactureCost;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "manufacture_date")
	private Date manufactureDate;

	@Transient
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@TransientColumn(name = "manufacture_date")
	private java.util.Date manufactureDateUi;

	@Column(name = "manufacture_notes")
	private String manufactureNotes;

	@Version
	@Column(name = "version_id")
	private Long versionId;

	@Transient
	@TransientColumn(name = "product_name")
	private String productName;

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

	public Double getManufactureQuantity() {
		return manufactureQuantity;
	}

	public void setManufactureQuantity(Double manufactureQuantity) {
		this.manufactureQuantity = manufactureQuantity;
	}

	public Double getManufactureCost() {
		return manufactureCost;
	}

	public void setManufactureCost(Double manufactureCost) {
		this.manufactureCost = manufactureCost;
	}

	public Date getManufactureDate() {
		return manufactureDate;
	}

	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}

	public String getManufactureNotes() {
		return manufactureNotes;
	}

	public void setManufactureNotes(String manufactureNotes) {
		this.manufactureNotes = manufactureNotes;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public java.util.Date getManufactureDateUi() {
		return manufactureDateUi;
	}

	public void setManufactureDateUi(java.util.Date manufactureDateUi) {
		this.manufactureDateUi = manufactureDateUi;
	}

	public Long getVersionId() {
		return versionId;
	}

	public void setVersionId(Long versionId) {
		this.versionId = versionId;
	}

}
