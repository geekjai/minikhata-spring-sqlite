package ig.mini.product.khata.db.prime.view;

import ig.central.library.FrameworkEntity;
import ig.central.library.TransientColumn;

public class DashboardView extends FrameworkEntity {

	@TransientColumn(name = "customer_count")
	private Long customerCount;

	@TransientColumn(name = "product_count")
	private Long productCount;

	@TransientColumn(name = "sell_count")
	private Long sellCount;

	@TransientColumn(name = "available_product")
	private Long availableProduct;

	public Long getCustomerCount() {
		return customerCount;
	}

	public void setCustomerCount(Long customerCount) {
		this.customerCount = customerCount;
	}

	public Long getProductCount() {
		return productCount;
	}

	public void setProductCount(Long productCount) {
		this.productCount = productCount;
	}

	public Long getSellCount() {
		return sellCount;
	}

	public void setSellCount(Long sellCount) {
		this.sellCount = sellCount;
	}

	public Long getAvailableProduct() {
		return availableProduct;
	}

	public void setAvailableProduct(Long availableProduct) {
		this.availableProduct = availableProduct;
	}

}
