package ig.mini.product.khata.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ig.central.library.FrameworkEntity;
import ig.mini.product.khata.db.entity.ProManufacture;
import ig.mini.product.khata.db.entity.ProManufactureProductMap;
import ig.mini.product.khata.db.entity.ProProduct;
import ig.mini.product.khata.db.entity.ProPurchase;
import ig.mini.product.khata.db.entity.ProPurchaseManufactureMap;
import ig.mini.product.khata.db.view.ProductPurchaseManufacture;
import ig.mini.product.khata.db.view.ProductPurchaseQuantity;
import ig.mini.product.khata.repositories.CoreRepositoryDao;
import ig.mini.product.khata.repositories.ManufactureProductMapRepository;
import ig.mini.product.khata.repositories.ManufactureRepository;
import ig.mini.product.khata.repositories.ProductRepository;
import ig.mini.product.khata.repositories.PurchaseManufactureMapRepository;
import ig.mini.product.khata.repositories.PurchaseRepository;
import ig.mini.product.khata.ui.pojo.ManufactureProduct;

@Repository("proCommonService")
public class ProCommonServiceImpl implements ProCommonService {

	@Autowired
	private CoreRepositoryDao coreRepositoryDao;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private PurchaseRepository purchaseRepository;
	@Autowired
	private ManufactureRepository manufactureRepository;
	@Autowired
	private PurchaseManufactureMapRepository purchaseManufactureMapRepository;
	@Autowired
	private ManufactureProductMapRepository manufactureProductMapRepository;

	@Override
	public Iterable<ProProduct> findAllProduct() {

		return productRepository.findAll();
	}

	private Double calculatePayableAmount(ProPurchase proPurchase) {

		Double payableAmount = 0.0;
		if (proPurchase == null) {
			return payableAmount;
		}

		if (proPurchase.getAmountBeforeTax() == null)
			proPurchase.setAmountBeforeTax(0.0);
		if (proPurchase.getGstAmount() == null)
			proPurchase.setGstAmount(0.0);
		if (proPurchase.getDiscountAmount() == null)
			proPurchase.setDiscountAmount(0.0);

		payableAmount += proPurchase.getAmountBeforeTax() + proPurchase.getGstAmount()
				- proPurchase.getDiscountAmount();
		return payableAmount;
	}

	@Override
	public List<ProPurchase> findPurchasesWithProduct() throws Exception {
		// TODO Auto-generated method stub
		return coreRepositoryDao.findPurchasesWithProduct();
	}

	@Override
	public ProPurchase findByPurchaseId(Long purchaseId) {
		Optional<ProPurchase> purchase = purchaseRepository.findById(purchaseId);
		if (purchase.isPresent()) {
			return purchase.get();
		}

		return new ProPurchase();
	}

	@Override
	@Transactional
	public void createProductPurchase(ProPurchase proPurchase) {

		Double payableAmount = calculatePayableAmount(proPurchase);
		proPurchase.setPayableAmount(payableAmount);
		proPurchase = purchaseRepository.save(proPurchase);
		// create ProPurchaseManufactureMap entry
		ProPurchaseManufactureMap pmmap = new ProPurchaseManufactureMap();
		pmmap.setProductId(proPurchase.getProductId());
		pmmap.setPurchaseId(proPurchase.getPurchaseId());
		pmmap.setInQuantity(proPurchase.getPurchaseQuantity());
		purchaseManufactureMapRepository.save(pmmap);
	}

	@Override
	@Transactional
	public void updateProductPurchase(ProPurchase proPurchase) throws Exception {

		Double payableAmount = calculatePayableAmount(proPurchase);
		proPurchase.setPayableAmount(payableAmount);
		Optional<ProPurchase> purchase = purchaseRepository.findById(proPurchase.getPurchaseId());
		if (purchase.isPresent()) {
			ProPurchase cachedEntity = purchase.get();
			FrameworkEntity.updateCachedEntity(cachedEntity, proPurchase);
			purchaseRepository.save(cachedEntity);
		}
	}

	@Override
	@Transactional
	public void deleteProductPurchase(Long purchaseId) throws Exception {

		if (purchaseId != null) {
			purchaseRepository.deleteById(purchaseId);
		}
	}

	@Override
	public List<ProManufacture> findManufacturesWithProduct() throws Exception {

		return coreRepositoryDao.findManufacturesWithProduct();
	}

	@Override
	public ManufactureProduct newManufactureIncenseInstance() throws Exception {

		ManufactureProduct lManufactureProduct = new ManufactureProduct();
		List<String> codes = new ArrayList<>();
		codes.add("BAMBOO_INCENSE_STICK");
		codes.add("AGARBATTI_PREMIX_POWDER");
		codes.add("BROWN_JOSH_POWDER");
		codes.add("PREMIX_AND_JOSH_POWDER");

		ProManufactureProductMap mpmap = null;
		List<ProProduct> products = productRepository.findByProductCodes(codes);
		for (ProProduct product : products) {
			mpmap = new ProManufactureProductMap();
			mpmap.setProductId(product.getProductId());
			lManufactureProduct.addProManufactureProductMap(mpmap);
		}

		products = productRepository.findByProductCode("UNSCENTED_INCENSE_STICK");
		lManufactureProduct.getManufacture().setProductId(products.get(0).getProductId());
		return lManufactureProduct;
	}

	private List<ProductPurchaseQuantity> readProductPurchaseQuantity(List<Long> productIds) throws Exception {

		List<ProductPurchaseQuantity> purchaseQuantities = coreRepositoryDao.findProductPurchaseQuantity(productIds);
		return purchaseQuantities;
	}

	@Transactional
	private void executeCreateManufacture(ProManufacture manufacture,
			List<ProManufactureProductMap> manufactureProductMaps, List<ProductPurchaseQuantity> purchaseQuantities) {
		if (manufacture.getProductId() == null) {
			return;
		}
		manufacture.setManufactureCost(null);
		manufacture = manufactureRepository.save(manufacture);

		for (ProManufactureProductMap productMap : manufactureProductMaps) {
			if (productMap.getProductId() != null && productMap.getProductQuantity() != null
					&& productMap.getProductQuantity() > 0) {
				productMap.setManufactureId(manufacture.getManufactureId());
				manufactureProductMapRepository.save(productMap);
			}
		}

		List<Long> consumedPurchaseList = new ArrayList<Long>();
		List<ProPurchaseManufactureMap> purchaseManufactureMaps = new ArrayList<ProPurchaseManufactureMap>();

		for (ProductPurchaseQuantity element : purchaseQuantities) {
			Double xfactor = element.getInQuantity() - element.getOutQuantity();
			if (xfactor <= 0) {
				return;
			}
			for (int i = 0; i < manufactureProductMaps.size(); i++) {

				ProManufactureProductMap curRow = manufactureProductMaps.get(i);
				if (curRow.getProductId() == element.getProductId()) {
					Double curQuantity = 0.0;
					Double remain = element.getInQuantity() - element.getOutQuantity();
					if (remain >= curRow.getProductQuantity()) {
						curQuantity = curRow.getProductQuantity();
						curRow.setProductQuantity(curRow.getProductQuantity() - curQuantity);
					} else {
						curQuantity = remain;
						curRow.setProductQuantity(curRow.getProductQuantity() - curQuantity);
					}

					ProPurchaseManufactureMap purchaseManufactureMap = new ProPurchaseManufactureMap();
					purchaseManufactureMap.setManufactureId(manufacture.getManufactureId());
					purchaseManufactureMap.setProductId(curRow.getProductId());
					purchaseManufactureMap.setPurchaseId(element.getPurchaseId());
					purchaseManufactureMap.setOutQuantity(curQuantity);

					purchaseManufactureMaps.add(purchaseManufactureMap);
					if (curQuantity == remain) {
						consumedPurchaseList.add(element.getPurchaseId());
					}
					if (curRow.getProductQuantity() <= 0) {
						manufactureProductMaps.remove(i);
						break;
					}
				}
			}
		} // end of building purchaseManufactureMaps and consumedPurchaseList

		for (ProPurchaseManufactureMap purchaseManufactureMap : purchaseManufactureMaps) {
			purchaseManufactureMapRepository.save(purchaseManufactureMap);
		}

		// purchaseRepository.setIsConsumedToY(consumedPurchaseList);
		List<ProPurchase> purchases = purchaseRepository.findByPurchaseIds(consumedPurchaseList);
		for (ProPurchase purchase : purchases) {
			purchase.setIsConsumed("Y");
			purchaseRepository.save(purchase);
		}
	}

	@Override
	public void createManufacture(ManufactureProduct manufactureProduct) throws Exception {

		if (manufactureProduct == null || manufactureProduct.getManufacture() == null
				|| manufactureProduct.getManufactureProductMaps() == null) {
			return;
		}

		ProManufacture manufacture = manufactureProduct.getManufacture();
		List<ProManufactureProductMap> manufactureProductMaps = manufactureProduct.getManufactureProductMaps();
		List<Long> productIds = new ArrayList<Long>();
		for (ProManufactureProductMap productMap : manufactureProductMaps) {
			if (productMap.getProductId() != null && productMap.getProductQuantity() != null
					&& productMap.getProductQuantity() > 0) {
				productIds.add(productMap.getProductId());
			}
		}
		List<ProductPurchaseQuantity> purchaseQuantities = readProductPurchaseQuantity(productIds);
		executeCreateManufacture(manufacture, manufactureProductMaps, purchaseQuantities);
	}

	@Override
	public ManufactureProduct findManufactureByManufactureId(Long manufactureId) throws Exception {

		ManufactureProduct lManufactureProduct = new ManufactureProduct();

		Optional<ProManufacture> manufactureObj = manufactureRepository.findById(manufactureId);
		if (manufactureObj.isPresent()) {
			lManufactureProduct.setManufacture(manufactureObj.get());
		}

		lManufactureProduct
				.setManufactureProductMaps(manufactureProductMapRepository.findByManufactureId(manufactureId));

		return lManufactureProduct;
	}

	private List<ProductPurchaseManufacture> readProductPurchaseManufCostQty(Long manufactureId) throws Exception {

		List<Long> manufactureIds = new ArrayList<Long>();
		manufactureIds.add(manufactureId);
		List<ProductPurchaseManufacture> list = null;
		list = coreRepositoryDao.findProductPurchaseManufCostQty(manufactureIds);
		return list;
	}

	@Transactional
	private Double evaluateManufactureCost(Long manufactureId, List<ProductPurchaseManufacture> purchaseCostList) {

		Double totalManufCost = 0.0;
		for (ProductPurchaseManufacture element : purchaseCostList) {
			Double payableAmount = element.getPayableAmount() != null ? element.getPayableAmount() : 0.0;
			Double purchaseQuantity = element.getPurchaseQuantity() != null ? element.getPurchaseQuantity() : 0.0;
			Double outQuantity = element.getOutQuantity() != null ? element.getOutQuantity() : 0.0;

			Double manufCost = (payableAmount / purchaseQuantity) * outQuantity;
			totalManufCost = totalManufCost + manufCost;
		}

		Optional<ProManufacture> optionalEntity = manufactureRepository.findById(manufactureId);
		if (optionalEntity.isPresent()) {
			ProManufacture entity = optionalEntity.get();
			entity.setManufactureCost(totalManufCost);
			manufactureRepository.save(entity);
		}

		return totalManufCost;
	}

	@Override
	public Double evaluateManufactureCost(Long manufactureId) throws Exception {

		if (manufactureId == null) {
			return null;
		}
		Optional<ProManufacture> optionalEntity = manufactureRepository.findById(manufactureId);
		if (optionalEntity.isPresent()) {
			ProManufacture entity = optionalEntity.get();
			if (entity.getManufactureCost() != null) {
				return entity.getManufactureCost();
			}
		}
		List<ProductPurchaseManufacture> purchaseCostList = readProductPurchaseManufCostQty(manufactureId);
		return evaluateManufactureCost(manufactureId, purchaseCostList);
	}

	@Override
	public void evaluateManufactureCost() throws Exception {

		List<ProManufacture> manufactures = manufactureRepository.findAllNullManufactureCost();
		if (manufactures == null || manufactures.size() == 0) {
			return;
		}
		for (ProManufacture manufacture : manufactures) {
			evaluateManufactureCost(manufacture.getManufactureId());
		}
	}

}