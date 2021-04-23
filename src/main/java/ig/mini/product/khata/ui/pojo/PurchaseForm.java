package ig.mini.product.khata.ui.pojo;

import ig.mini.product.khata.db.prime.entity.ProPurchase;

public class PurchaseForm {

	private ProPurchase purchase;

	public PurchaseForm() {

		purchase = new ProPurchase();
	}

	public ProPurchase getPurchase() {
		return purchase;
	}

	public void setPurchase(ProPurchase purchase) {
		this.purchase = purchase;
	}

}
