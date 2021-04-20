package ig.mini.product.khata.db.common.repository;

import java.util.List;
import java.util.Map;

import ig.mini.product.khata.db.prime.entity.ProManufacture;
import ig.mini.product.khata.db.prime.entity.ProPurchase;
import ig.mini.product.khata.db.prime.entity.ProPurchaseManufactureMap;
import ig.mini.product.khata.db.prime.view.ProductPurchaseManufacture;
import ig.mini.product.khata.db.prime.view.ProductPurchaseQuantity;
import ig.mini.product.khata.db.prime.view.StockQuantity;

@SuppressWarnings("deprecation")
public interface CoreRepositoryDao {

	public List<ProPurchase> findPurchasesWithProduct() throws Exception;

	public List<ProManufacture> findManufacturesWithProduct() throws Exception;

	public List<ProductPurchaseQuantity> findProductPurchaseQuantity(List<Long> bindProducts) throws Exception;

	public List<ProductPurchaseManufacture> findProductPurchaseManufCostQty(List<Long> bindManufactureIds)
			throws Exception;

	public List<ProPurchaseManufactureMap> findPurchaseInQty(Long bindPurchaseId) throws Exception;

	public List<ProPurchaseManufactureMap> findManufactureOutQty(Long bindPurchaseId) throws Exception;

	public List<StockQuantity> findStockQuantity(List<Long> bindProducts) throws Exception;

	public Map<Long, List<StockQuantity>> findStockQuantityProductIdMap(List<Long> bindProducts) throws Exception;

}
