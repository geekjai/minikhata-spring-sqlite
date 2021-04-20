package ig.mini.product.khata.db.prime.view;

import java.util.ArrayList;
import java.util.List;

import ig.mini.product.khata.db.prime.entity.ProStock;

public class SellWrapper {

	private List<Long> consumedPurchaseList;
	private List<ProStock> stockList;

	public List<Long> getConsumedPurchaseList() {
		return consumedPurchaseList;
	}

	public void addConsumedPurchaseId(Long purchaseId) {

		if (this.consumedPurchaseList == null) {
			this.consumedPurchaseList = new ArrayList<Long>();
		}

		this.consumedPurchaseList.add(purchaseId);
	}

	public List<ProStock> getStockList() {
		return stockList;
	}

	public void addProStock(ProStock proStock) {

		if (this.stockList == null) {
			this.stockList = new ArrayList<ProStock>();
		}

		this.stockList.add(proStock);
	}

}
