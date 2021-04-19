package ig.mini.product.khata.ui.pojo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import ig.mini.product.khata.db.content.entity.ProProduct;
import ig.mini.product.khata.db.prime.entity.ProCustomer;
import ig.mini.product.khata.db.prime.entity.ProSell;
import ig.mini.product.khata.db.prime.entity.ProSellProductMap;

public class SellForm {

	@JsonProperty("sell")
	private ProSell sell;

	@JsonProperty("sellProductMaps")
	private List<ProSellProductMap> sellProductMaps;

	private Iterable<ProProduct> products;

	private Iterable<ProCustomer> customers;

	public SellForm() {

		sell = new ProSell();
		sellProductMaps = new ArrayList<ProSellProductMap>();
	}

	public ProSell getSell() {
		return sell;
	}

	public void setSell(ProSell sell) {
		this.sell = sell;
	}

	public List<ProSellProductMap> getSellProductMaps() {
		return sellProductMaps;
	}

	public void setSellProductMaps(List<ProSellProductMap> sellProductMaps) {
		this.sellProductMaps = sellProductMaps;
	}

	public Iterable<ProProduct> getProducts() {
		return products;
	}

	public void setProducts(Iterable<ProProduct> products) {
		this.products = products;
	}

	public Iterable<ProCustomer> getCustomers() {
		return customers;
	}

	public void setCustomers(Iterable<ProCustomer> customers) {
		this.customers = customers;
	}

}
