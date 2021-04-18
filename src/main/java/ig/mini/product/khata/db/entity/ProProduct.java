package ig.mini.product.khata.db.entity;

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
@Table(name = "pro_products")
public class ProProduct implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6158379670388337846L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id", nullable = false, updatable = false)
	private Long productId;

	@Column(name = "product_code", nullable = false)
	private String productCode;

	@Column(name = "product_barcode")
	private String productBarcode;

	@Column(name = "product_name", nullable = false)
	private String productName;

	@Column(name = "description")
	private String description;

	@Column(name = "product_link")
	private String productLink;

	@Column(name = "product_image")
	private String productImage;

	@Column(name = "category_id", nullable = false)
	private Long categoryId;

	@Column(name = "sub_category_id", nullable = false)
	private Long subCategoryId;

	@Version
	@UpdateCacheEntityColumn
	@Column(name = "version_id")
	private Long versionId;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductBarcode() {
		return productBarcode;
	}

	public void setProductBarcode(String productBarcode) {
		this.productBarcode = productBarcode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProductLink() {
		return productLink;
	}

	public void setProductLink(String productLink) {
		this.productLink = productLink;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public Long getVersionId() {
		return versionId;
	}

	public void setVersionId(Long versionId) {
		this.versionId = versionId;
	}

}
