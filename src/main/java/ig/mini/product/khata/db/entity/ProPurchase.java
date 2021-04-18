package ig.mini.product.khata.db.entity;

import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

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

@Entity
@Table(name = "pro_purchases")
public class ProPurchase extends FrameworkEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -985801797123962687L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "purchase_id", nullable = false, updatable = false)
	private Long purchaseId;

	@Column(name = "purchase_type_id", nullable = false, updatable = false, columnDefinition = "bigint default 1")
	private Long purchaseTypeId;

	@Column(name = "product_id", nullable = false, updatable = false)
	private Long productId;

	@Column(name = "bill_number", nullable = false)
	private String billNumber;

	@UpdateCacheEntityColumn
	@Column(name = "purchase_quantity")
	private Double purchaseQuantity;

	@Column(name = "purchase_notes")
	private String purchaseNotes;

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

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "purchase_date")
	private Date purchaseDate;

	@UpdateCacheEntityColumn
	@Column(name = "is_amount_settled", nullable = false)
	private String isAmountSettled = "N";

	@UpdateCacheEntityColumn
	@Column(name = "is_consumed", nullable = false)
	private String isConsumed = "N";

	@Transient
	@TransientColumn(name = "product_name")
	private String productName;

	@Version
	@UpdateCacheEntityColumn
	@Column(name = "version_id")
	private int versionId;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
	}

	public Long getPurchaseTypeId() {
		return purchaseTypeId;
	}

	public void setPurchaseTypeId(Long purchaseTypeId) {
		this.purchaseTypeId = purchaseTypeId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	public Double getPurchaseQuantity() {
		return purchaseQuantity;
	}

	public void setPurchaseQuantity(Double purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}

	public String getPurchaseNotes() {
		return purchaseNotes;
	}

	public void setPurchaseNotes(String purchaseNotes) {
		this.purchaseNotes = purchaseNotes;
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

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getIsAmountSettled() {
		return isAmountSettled;
	}

	public void setIsAmountSettled(String isAmountSettled) {
		this.isAmountSettled = isAmountSettled;
	}

	public String getIsConsumed() {
		return isConsumed;
	}

	public void setIsConsumed(String isConsumed) {
		this.isConsumed = isConsumed;
	}

	public int getVersionId() {
		return versionId;
	}

	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}

	public static ProPurchase getInstanceFromMap(Map<String, Object> map) {
		ProPurchase proPurchase = null;
		if (map != null) {
			proPurchase = new ProPurchase();
			Object obj = null;
			obj = map.get("purchaseId");
			if (obj instanceof String) {
				if (obj != null && !"".equals(((String) obj).trim())) {
					proPurchase.setPurchaseId(Long.parseLong(((String) obj)));
				}
			} else {
				proPurchase.setPurchaseId((Long) obj);
			}
			obj = map.get("productId");
			if (obj instanceof String) {
				if (obj != null && !"".equals(((String) obj).trim())) {
					proPurchase.setProductId(Long.parseLong(((String) obj)));
				}
			} else {
				proPurchase.setProductId((Long) obj);
			}
			obj = map.get("billNumber");
			proPurchase.setBillNumber((String) obj);
			// purchaseQuantity
			obj = map.get("purchaseQuantity");
			if (obj instanceof String) {
				if (obj != null && !"".equals(((String) obj).trim())) {
					proPurchase.setPurchaseQuantity(Double.parseDouble(((String) obj)));
				}
			} else {
				proPurchase.setPurchaseQuantity((Double) obj);
			}
			// purchaseNotes
			obj = map.get("purchaseNotes");
			proPurchase.setPurchaseNotes((String) obj);
			// amountBeforeTax
			obj = map.get("amountBeforeTax");
			if (obj instanceof String) {
				if (obj != null && !"".equals(((String) obj).trim())) {
					proPurchase.setAmountBeforeTax(Double.parseDouble(((String) obj)));
				}
			} else {
				proPurchase.setAmountBeforeTax((Double) obj);
			}
			// gstAmount
			obj = map.get("gstAmount");
			if (obj instanceof String) {
				if (obj != null && !"".equals(((String) obj).trim())) {
					proPurchase.setGstAmount(Double.parseDouble(((String) obj)));
				}
			} else {
				proPurchase.setGstAmount((Double) obj);
			}
			// discountAmount
			obj = map.get("discountAmount");
			if (obj instanceof String) {
				if (obj != null && !"".equals(((String) obj).trim())) {
					proPurchase.setDiscountAmount(Double.parseDouble(((String) obj)));
				}
			} else {
				proPurchase.setDiscountAmount((Double) obj);
			}
			// purchaseDate
			obj = map.get("purchaseDate");
			if (obj instanceof String) {
				if (obj != null && !"".equals(((String) obj).trim())) {
					java.util.Date date;
					try {
						date = new SimpleDateFormat("yyyy-MM-dd").parse((String) obj);
						Date sqlDate = new Date(date.getTime());
						proPurchase.setPurchaseDate(sqlDate);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			} else {
				proPurchase.setPurchaseDate((Date) obj);
			}
		}

		return proPurchase;
	}

	public static ProPurchase newInstanceUsingProManufacture(ProManufacture proManufacture) {

		ProPurchase proPurchase = null;
		if (proManufacture == null) {
			return proPurchase;
		}
		proPurchase = new ProPurchase();
		proPurchase.setBillNumber("Manufacture" + proManufacture.getManufactureId());
		proPurchase.setProductId(proManufacture.getProductId());
		proPurchase.setPurchaseDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
		proPurchase.setPurchaseNotes(proManufacture.getManufactureNotes());

		proPurchase.setPurchaseQuantity(proManufacture.getManufactureQuantity());

		proPurchase.setAmountBeforeTax(proManufacture.getManufactureCost());
		proPurchase.setGstAmount(0.0);
		proPurchase.setDiscountAmount(0.0);
		proPurchase.setPayableAmount(proManufacture.getManufactureCost());

		proPurchase.setPurchaseTypeId(2L);
		return proPurchase;
	}

}
