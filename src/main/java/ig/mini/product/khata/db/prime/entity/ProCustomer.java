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

import ig.central.library.FrameworkEntity;
import ig.central.library.annotation.UpdateCacheEntityColumn;

@Entity
@Table(name = "pro_customers")
public class ProCustomer extends FrameworkEntity implements Serializable {

	private static final long serialVersionUID = -7430001114907935297L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customer_id", nullable = false, updatable = false)
	private Long customerId;

	@Column(name = "customer_gstin")
	private String customerGstin;

	@Column(name = "customer_name", columnDefinition = "VARCHAR(40) NOT NULL", nullable = false)
	private String customerName;

	@Column(name = "customer_mobile", columnDefinition = "VARCHAR(40) NOT NULL", nullable = false)
	private String customerMobile;

	@Column(name = "customer_email", columnDefinition = "VARCHAR(40) DEFAULT NULL")
	private String customerEmail;

	@Column(name = "customer_phone_home", columnDefinition = "VARCHAR(40) DEFAULT NULL")
	private String customerPhoneHome;

	@Column(name = "address1", columnDefinition = "VARCHAR(100) DEFAULT NULL")
	private String address1;

	@Column(name = "address2", columnDefinition = "VARCHAR(100) DEFAULT NULL")
	private String address2;

	@Column(name = "country", columnDefinition = "INT(15) DEFAULT NULL")
	private Long country;

	@Column(name = "state", columnDefinition = "INT(15) DEFAULT NULL")
	private Long state;

	@Column(name = "city", columnDefinition = "INT(15) DEFAULT NULL")
	private Long city;

	@Column(name = "postcode", columnDefinition = "VARCHAR(40) DEFAULT NULL")
	private String postcode;

	@Column(name = "ship_address1", columnDefinition = "VARCHAR(40) DEFAULT NULL")
	private String shipAddress1;

	@Column(name = "ship_address2", columnDefinition = "VARCHAR(40) DEFAULT NULL")
	private String shipAddress2;

	@Column(name = "ship_country", columnDefinition = "INT(15) DEFAULT NULL")
	private Long shipCountry;

	@Column(name = "ship_state", columnDefinition = "INT(15) DEFAULT NULL")
	private Long shipState;

	@Column(name = "ship_city", columnDefinition = "INT(15) DEFAULT NULL")
	private Long shipCity;

	@Column(name = "ship_postcode", columnDefinition = "VARCHAR(40) DEFAULT NULL")
	private String shipPostcode;

	@Column(name = "isdeleted", columnDefinition = "VARCHAR(1) DEFAULT 'Y'", nullable = false)
	private String isdeleted = "Y";

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
	private Boolean isSeedData;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerGstin() {
		return customerGstin;
	}

	public void setCustomerGstin(String customerGstin) {
		this.customerGstin = customerGstin;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerPhoneHome() {
		return customerPhoneHome;
	}

	public void setCustomerPhoneHome(String customerPhoneHome) {
		this.customerPhoneHome = customerPhoneHome;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public Long getCountry() {
		return country;
	}

	public void setCountry(Long country) {
		this.country = country;
	}

	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	public Long getCity() {
		return city;
	}

	public void setCity(Long city) {
		this.city = city;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getShipAddress1() {
		return shipAddress1;
	}

	public void setShipAddress1(String shipAddress1) {
		this.shipAddress1 = shipAddress1;
	}

	public String getShipAddress2() {
		return shipAddress2;
	}

	public void setShipAddress2(String shipAddress2) {
		this.shipAddress2 = shipAddress2;
	}

	public Long getShipCountry() {
		return shipCountry;
	}

	public void setShipCountry(Long shipCountry) {
		this.shipCountry = shipCountry;
	}

	public Long getShipState() {
		return shipState;
	}

	public void setShipState(Long shipState) {
		this.shipState = shipState;
	}

	public Long getShipCity() {
		return shipCity;
	}

	public void setShipCity(Long shipCity) {
		this.shipCity = shipCity;
	}

	public String getShipPostcode() {
		return shipPostcode;
	}

	public void setShipPostcode(String shipPostcode) {
		this.shipPostcode = shipPostcode;
	}

	public String getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(String isdeleted) {
		this.isdeleted = isdeleted;
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

	public Boolean getIsSeedData() {
		return isSeedData;
	}

	public void setIsSeedData(Boolean isSeedData) {
		this.isSeedData = isSeedData;
	}

}
