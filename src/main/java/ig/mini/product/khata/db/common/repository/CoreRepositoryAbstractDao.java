package ig.mini.product.khata.db.common.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ig.central.library.FrameworkEntity;
import ig.mini.product.khata.db.prime.entity.ProManufacture;
import ig.mini.product.khata.db.prime.entity.ProPurchase;
import ig.mini.product.khata.db.prime.entity.ProPurchaseManufactureMap;
import ig.mini.product.khata.db.prime.view.DashboardView;
import ig.mini.product.khata.db.prime.view.ProductPurchaseManufacture;
import ig.mini.product.khata.db.prime.view.ProductPurchaseQuantity;
import ig.mini.product.khata.db.prime.view.SaleChartView;
import ig.mini.product.khata.db.prime.view.StockQuantity;

@SuppressWarnings("deprecation")
public abstract class CoreRepositoryAbstractDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected final List<ProPurchase> populateProPurchaseList(Class<ProPurchase> entityClass, List<Object> data)
			throws InstantiationException, IllegalAccessException {

		List<ProPurchase> entityList = null;
		if (data != null) {
			entityList = new ArrayList<>();
			for (Object object : data) {

				Map row = (Map) object;
				ProPurchase entity = (ProPurchase) entityClass.newInstance();
				FrameworkEntity.setFrameworkEntityData(entity, row);
				entityList.add(entity);
			}
		}

		return entityList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected final List<ProManufacture> populateProManufactureList(Class<ProManufacture> entityClass,
			List<Object> data) throws InstantiationException, IllegalAccessException {

		List<ProManufacture> entityList = null;
		if (data != null) {
			entityList = new ArrayList<>();
			for (Object object : data) {

				Map row = (Map) object;
				ProManufacture entity = (ProManufacture) entityClass.newInstance();
				FrameworkEntity.setFrameworkEntityData(entity, row);
				entityList.add(entity);
			}
		}

		return entityList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected final List<ProductPurchaseQuantity> populateProductPurchaseQuantityList(
			Class<ProductPurchaseQuantity> entityClass, List<Object> data)
			throws InstantiationException, IllegalAccessException {

		List<ProductPurchaseQuantity> entityList = null;
		if (data != null) {
			entityList = new ArrayList<>();
			for (Object object : data) {

				Map row = (Map) object;
				ProductPurchaseQuantity entity = (ProductPurchaseQuantity) entityClass.newInstance();
				FrameworkEntity.setFrameworkEntityData(entity, row);
				entityList.add(entity);
			}
		}

		return entityList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected final List<ProductPurchaseManufacture> populateProductPurchaseManufactureList(
			Class<ProductPurchaseManufacture> entityClass, List<Object> data)
			throws InstantiationException, IllegalAccessException {

		List<ProductPurchaseManufacture> entityList = null;
		if (data != null) {
			entityList = new ArrayList<>();
			for (Object object : data) {

				Map row = (Map) object;
				ProductPurchaseManufacture entity = (ProductPurchaseManufacture) entityClass.newInstance();
				FrameworkEntity.setFrameworkEntityData(entity, row);
				entityList.add(entity);
			}
		}

		return entityList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected final List<ProPurchaseManufactureMap> populateProPurchaseManufactureMapList(
			Class<ProPurchaseManufactureMap> entityClass, List<Object> data)
			throws InstantiationException, IllegalAccessException {

		List<ProPurchaseManufactureMap> entityList = null;
		if (data != null) {
			entityList = new ArrayList<>();
			for (Object object : data) {

				Map row = (Map) object;
				ProPurchaseManufactureMap entity = (ProPurchaseManufactureMap) entityClass.newInstance();
				FrameworkEntity.setFrameworkEntityData(entity, row);
				entityList.add(entity);
			}
		}

		return entityList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected final List<StockQuantity> populateStockQuantityList(Class<StockQuantity> entityClass, List<Object> data)
			throws InstantiationException, IllegalAccessException {

		List<StockQuantity> entityList = null;
		if (data != null) {
			entityList = new ArrayList<>();
			for (Object object : data) {

				Map row = (Map) object;
				StockQuantity entity = (StockQuantity) entityClass.newInstance();
				FrameworkEntity.setFrameworkEntityData(entity, row);
				entityList.add(entity);
			}
		}

		return entityList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected final List<DashboardView> populateDashboardViewList(Class<DashboardView> entityClass, List<Object> data)
			throws InstantiationException, IllegalAccessException {

		List<DashboardView> entityList = null;
		if (data != null) {
			entityList = new ArrayList<>();
			for (Object object : data) {

				Map row = (Map) object;
				DashboardView entity = (DashboardView) entityClass.newInstance();
				FrameworkEntity.setFrameworkEntityData(entity, row);
				entityList.add(entity);
			}
		}

		return entityList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected final List<SaleChartView> populateSaleChartViewList(Class<SaleChartView> entityClass, List<Object> data)
			throws InstantiationException, IllegalAccessException {

		List<SaleChartView> entityList = null;
		if (data != null) {
			entityList = new ArrayList<>();
			for (Object object : data) {

				Map row = (Map) object;
				SaleChartView entity = (SaleChartView) entityClass.newInstance();
				FrameworkEntity.setFrameworkEntityData(entity, row);
				entityList.add(entity);
			}
		}

		return entityList;
	}

}
