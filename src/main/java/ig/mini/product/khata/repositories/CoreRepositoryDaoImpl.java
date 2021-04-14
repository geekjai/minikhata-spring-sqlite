package ig.mini.product.khata.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import ig.mini.product.khata.db.entity.ProManufacture;
import ig.mini.product.khata.db.entity.ProPurchase;
import ig.mini.product.khata.db.view.ProductPurchaseManufacture;
import ig.mini.product.khata.db.view.ProductPurchaseQuantity;

@Repository("coreRepositoryDao")
public class CoreRepositoryDaoImpl extends CoreRepositoryAbstractDao implements CoreRepositoryDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<ProPurchase> findPurchasesWithProduct() throws Exception {

		@SuppressWarnings("deprecation")
		Query query = entityManager.createNamedQuery("ProPurchase.findAllWithProduct")
				.unwrap(org.hibernate.query.Query.class).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

		@SuppressWarnings("unchecked")
		List<Object> objResults = query.getResultList();
		List<ProPurchase> results = populateProPurchaseList(ProPurchase.class, objResults);
		return results;
	}

	@Override
	public List<ProManufacture> findManufacturesWithProduct() throws Exception {

		@SuppressWarnings("deprecation")
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
		@SuppressWarnings("deprecation")
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
		@SuppressWarnings("deprecation")
		Query query = entityManager.createNamedQuery("findProductPurchaseManufCostQty")
				.unwrap(org.hibernate.query.Query.class).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		query.setParameter("bindManufactureIds", bindManufactureIds);

		@SuppressWarnings("unchecked")
		List<Object> objResults = query.getResultList();
		List<ProductPurchaseManufacture> results = populateProductPurchaseManufactureList(
				ProductPurchaseManufacture.class, objResults);
		return results;
	}

}
