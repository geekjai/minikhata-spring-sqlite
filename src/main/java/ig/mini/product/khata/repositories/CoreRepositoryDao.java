package ig.mini.product.khata.repositories;

import java.util.List;

import ig.mini.product.khata.db.entity.ProManufacture;
import ig.mini.product.khata.db.entity.ProPurchase;
import ig.mini.product.khata.db.view.ProductPurchaseManufacture;
import ig.mini.product.khata.db.view.ProductPurchaseQuantity;

public interface CoreRepositoryDao {

	public List<ProPurchase> findPurchasesWithProduct() throws Exception;

	public List<ProManufacture> findManufacturesWithProduct() throws Exception;

	public List<ProductPurchaseQuantity> findProductPurchaseQuantity(List<Long> bindProducts) throws Exception;

	public List<ProductPurchaseManufacture> findProductPurchaseManufCostQty(List<Long> bindManufactureIds)
			throws Exception;

}
