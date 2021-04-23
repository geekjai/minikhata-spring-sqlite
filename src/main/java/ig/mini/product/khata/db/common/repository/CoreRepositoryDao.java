package ig.mini.product.khata.db.common.repository;

import java.util.List;
import java.util.Map;

import ig.mini.product.khata.db.prime.entity.ProManufacture;
import ig.mini.product.khata.db.prime.entity.ProPurchase;
import ig.mini.product.khata.db.prime.view.DashboardView;
import ig.mini.product.khata.db.prime.view.ProductPurchaseManufacture;
import ig.mini.product.khata.db.prime.view.ProductPurchaseQuantity;
import ig.mini.product.khata.db.prime.view.SaleChartView;
import ig.mini.product.khata.db.prime.view.StockQuantity;

public interface CoreRepositoryDao {

	public List<ProPurchase> findPurchasesWithProduct() throws Exception;

	public List<ProManufacture> findManufacturesWithProduct() throws Exception;

	public List<ProductPurchaseQuantity> findProductPurchaseQuantity(List<Long> bindProducts) throws Exception;

	public List<ProductPurchaseManufacture> findProductPurchaseManufCostQty(List<Long> bindManufactureIds)
			throws Exception;

	public List<StockQuantity> findStockQuantity(List<Long> bindProducts) throws Exception;

	public Map<Long, List<StockQuantity>> findStockQuantityProductIdMap(List<Long> bindProducts) throws Exception;

	public DashboardView populateDashboardView() throws Exception;

	public List<SaleChartView> findPurManufCostData() throws Exception;

}
