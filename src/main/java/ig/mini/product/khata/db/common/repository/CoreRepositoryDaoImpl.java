package ig.mini.product.khata.db.common.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import ig.mini.product.khata.db.prime.entity.ProManufacture;
import ig.mini.product.khata.db.prime.entity.ProPurchase;
import ig.mini.product.khata.db.prime.view.DashboardView;
import ig.mini.product.khata.db.prime.view.ProductPurchaseManufacture;
import ig.mini.product.khata.db.prime.view.ProductPurchaseQuantity;
import ig.mini.product.khata.db.prime.view.SaleChartView;
import ig.mini.product.khata.db.prime.view.StockQuantity;

@Repository("coreRepositoryDao")
@SuppressWarnings("deprecation")
public class CoreRepositoryDaoImpl extends CoreRepositoryAbstractDao implements CoreRepositoryDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<ProPurchase> findPurchasesWithProduct() throws Exception {

		Query query = entityManager.createNamedQuery("ProPurchase.findAllWithProduct")
				.unwrap(org.hibernate.query.Query.class).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

		@SuppressWarnings("unchecked")
		List<Object> objResults = query.getResultList();
		List<ProPurchase> results = populateProPurchaseList(ProPurchase.class, objResults);
		return results;
	}

	@Override
	public List<ProManufacture> findManufacturesWithProduct() throws Exception {

		Query query = entityManager.createNamedQuery("ProManufacture.findAllWithProduct")
				.unwrap(org.hibernate.query.Query.class).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

		@SuppressWarnings("unchecked")
		List<Object> objResults = query.getResultList();
		List<ProManufacture> results = populateProManufactureList(ProManufacture.class, objResults);
		return results;
	}

	@Override
	public List<ProductPurchaseQuantity> findProductPurchaseQuantity(List<Long> bindProducts) throws Exception {

		if (bindProducts == null) {
			return null;
		}

		Query query = entityManager.createNamedQuery("findProductPurchaseQuantity")
				.unwrap(org.hibernate.query.Query.class).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		query.setParameter("bindProducts", bindProducts);

		@SuppressWarnings("unchecked")
		List<Object> objResults = query.getResultList();
		List<ProductPurchaseQuantity> results = populateProductPurchaseQuantityList(ProductPurchaseQuantity.class,
				objResults);
		return results;
	}

	@Override
	public List<ProductPurchaseManufacture> findProductPurchaseManufCostQty(List<Long> bindManufactureIds)
			throws Exception {

		if (bindManufactureIds == null) {
			return null;
		}

		Query query = entityManager.createNamedQuery("findProductPurchaseManufCostQty")
				.unwrap(org.hibernate.query.Query.class).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		query.setParameter("bindManufactureIds", bindManufactureIds);

		@SuppressWarnings("unchecked")
		List<Object> objResults = query.getResultList();
		List<ProductPurchaseManufacture> results = populateProductPurchaseManufactureList(
				ProductPurchaseManufacture.class, objResults);
		return results;
	}

	@Override
	public List<StockQuantity> findStockQuantity(List<Long> bindProducts) throws Exception {

		if (bindProducts == null || bindProducts.size() == 0) {
			return null;
		}

		Query query = entityManager.createNamedQuery("findStockQuantity").unwrap(org.hibernate.query.Query.class)
				.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		query.setParameter("bindProducts", bindProducts);

		@SuppressWarnings("unchecked")
		List<Object> objResults = query.getResultList();
		List<StockQuantity> results = populateStockQuantityList(StockQuantity.class, objResults);
		return results;
	}

	@Override
	public Map<Long, List<StockQuantity>> findStockQuantityProductIdMap(List<Long> bindProducts) throws Exception {

		if (bindProducts == null || bindProducts.size() == 0) {
			return null;
		}

		Map<Long, List<StockQuantity>> stockMap = new HashMap<Long, List<StockQuantity>>();
		List<StockQuantity> stockQuantities = findStockQuantity(bindProducts);
		if (stockQuantities != null && stockQuantities.size() > 0) {
			for (StockQuantity stockQuantity : stockQuantities) {
				Long productId = stockQuantity.getProductId();
				if (stockMap.containsKey(productId)) {
					List<StockQuantity> list = stockMap.get(productId);
					list.add(stockQuantity);
				} else {
					List<StockQuantity> list = new ArrayList<StockQuantity>();
					list.add(stockQuantity);
					stockMap.put(productId, list);
				}
			}
		}

		return stockMap;
	}

	@Override
	public DashboardView populateDashboardView() throws Exception {

		Query query = entityManager.createNamedQuery("populateDashboardView").unwrap(org.hibernate.query.Query.class)
				.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

		@SuppressWarnings("unchecked")
		List<Object> objResults = query.getResultList();
		List<DashboardView> results = populateDashboardViewList(DashboardView.class, objResults);
		if (results != null && results.size() == 1) {
			return results.get(0);
		}

		return null;
	}

	@Override
	public List<SaleChartView> findPurManufCostData() throws Exception {

		Query query = entityManager.createNamedQuery("findPurManufCostData").unwrap(org.hibernate.query.Query.class)
				.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

		@SuppressWarnings("unchecked")
		List<Object> objResults = query.getResultList();
		List<SaleChartView> results = populateSaleChartViewList(SaleChartView.class, objResults);

		return results;
	}

}
