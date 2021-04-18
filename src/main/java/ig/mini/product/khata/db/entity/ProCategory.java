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
@Table(name = "PRO_CATEGORIES")
public class ProCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5682634842930285197L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false, updatable = false)
	private Long id;

	@Column(name = "CATEGORY_ID", nullable = false, updatable = false)
	private Long categoryId;

	@Column(name = "CATEGORY_NAME", nullable = false)
	private String categoryName;

	@Column(name = "SUB_CATEGORY_ID", nullable = false, updatable = false)
	private Long subCategoryId;

	@Column(name = "SUB_CATEGORY_NAME")
	private String subCategoryName;

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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Long getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public Long getVersionId() {
		return versionId;
	}

	public void setVersionId(Long versionId) {
		this.versionId = versionId;
	}

}
