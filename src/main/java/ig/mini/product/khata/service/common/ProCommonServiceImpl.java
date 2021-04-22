package ig.mini.product.khata.service.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ig.central.library.FrameworkEntity;
import ig.mini.product.khata.db.common.repository.CoreRepositoryDao;
import ig.mini.product.khata.db.content.entity.ProProduct;
import ig.mini.product.khata.db.content.repository.ProductRepository;
import ig.mini.product.khata.db.prime.entity.ProCustomer;
import ig.mini.product.khata.db.prime.entity.ProManufacture;
import ig.mini.product.khata.db.prime.entity.ProManufactureProductMap;
import ig.mini.product.khata.db.prime.entity.ProPurchase;
import ig.mini.product.khata.db.prime.entity.ProSell;
import ig.mini.product.khata.db.prime.entity.ProSellProductMap;
import ig.mini.product.khata.db.prime.entity.ProStock;
import ig.mini.product.khata.db.prime.repository.CustomerRepository;
import ig.mini.product.khata.db.prime.repository.ManufactureProductMapRepository;
import ig.mini.product.khata.db.prime.repository.ManufactureRepository;
import ig.mini.product.khata.db.prime.repository.PurchaseRepository;
import ig.mini.product.khata.db.prime.repository.SellProductMapRepository;
import ig.mini.product.khata.db.prime.repository.SellRepository;
import ig.mini.product.khata.db.prime.repository.StockRepository;
import ig.mini.product.khata.db.prime.view.ManufactureWrapper;
import ig.mini.product.khata.db.prime.view.ProductPurchaseManufacture;
import ig.mini.product.khata.db.prime.view.SellWrapper;
import ig.mini.product.khata.db.prime.view.StockQuantity;
import ig.mini.product.khata.service.util.CommonServiceUtil;
import ig.mini.product.khata.ui.pojo.ManufactureProduct;
import ig.mini.product.khata.ui.pojo.SellForm;

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
	private ManufactureProductMapRepository manufactureProductMapRepository;
	@Autowired
	private SellRepository sellRepository;
	@Autowired
	private SellProductMapRepository sellProductMapRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private StockRepository stockRepository;

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
		// purchased from vendor
		proPurchase.setPurchaseTypeId(1L);
		proPurchase = purchaseRepository.save(proPurchase);

		// now create entry inside stock;
		ProStock stock = new ProStock();
		stock.setProductId(proPurchase.getProductId());
		stock.setPurchaseId(proPurchase.getPurchaseId());
		stock.setPurchaseQuantity(proPurchase.getPurchaseQuantity());
		stockRepository.save(stock);
	}

	@Transactional
	private void updateProductPurchase(ProPurchase proPurchase, boolean isPurchaseQuantityUpdateable) throws Exception {

		Long purchaseId = proPurchase.getPurchaseId();
		Optional<ProPurchase> purchase = purchaseRepository.findById(purchaseId);
		if (purchase.isPresent()) {
			ProPurchase cachedEntity = purchase.get();
			FrameworkEntity.updateCachedEntity(cachedEntity, proPurchase);
			if (isPurchaseQuantityUpdateable) {

				cachedEntity.setPurchaseQuantity(proPurchase.getPurchaseQuantity());
				cachedEntity.setAmountBeforeTax(proPurchase.getAmountBeforeTax());
				cachedEntity.setGstAmount(proPurchase.getGstAmount());
				cachedEntity.setDiscountAmount(proPurchase.getDiscountAmount());
				cachedEntity.setPayableAmount(proPurchase.getPayableAmount());

				Optional<ProStock> optional = stockRepository.findPurchaseRecordByPurchaseId(purchaseId);
				if (optional.isPresent()) {
					ProStock stock = optional.get();
					stock.setPurchaseQuantity(cachedEntity.getPurchaseQuantity());
					stockRepository.save(stock);
				}
			}
			purchaseRepository.save(cachedEntity);
		}
	}

	@Override
	public void updateProductPurchase(ProPurchase proPurchase) throws Exception {

		Double payableAmount = calculatePayableAmount(proPurchase);
		proPurchase.setPayableAmount(payableAmount);
		boolean isPurchaseQuantityUpdateable = isPurchaseQuantityUpdateable(proPurchase.getPurchaseId());

		updateProductPurchase(proPurchase, isPurchaseQuantityUpdateable);
	}

	@Override
	@Transactional
	public void deleteProductPurchase(Long purchaseId) throws Exception {

		// if a manufacture recorded again purchase then deletion not allowed
		if (purchaseId != null && isPurchaseQuantityUpdateable(purchaseId)) {
			Optional<ProStock> stOptional = stockRepository.findPurchaseRecordByPurchaseId(purchaseId);
			if (stOptional.isPresent()) {
				ProStock stock = stOptional.get();
				stockRepository.delete(stock);
			}
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

	private Map<Long, List<StockQuantity>> readStockQuantity(List<Long> productIds) throws Exception {

		Map<Long, List<StockQuantity>> stockQuantities = coreRepositoryDao.findStockQuantityProductIdMap(productIds);
		return stockQuantities;
	}

	private void executeCreateManufacture(ProManufacture manufacture,
			List<ProManufactureProductMap> manufactureProductMaps, Map<Long, List<StockQuantity>> stockQuantities)
			throws Exception {
		// 1. Create entry in manufacture table
		if (manufacture.getProductId() == null) {
			return;
		}
		manufacture.setManufactureCost(null);
		manufacture = manufactureRepository.save(manufacture);
		// 2. Create entry inside ProManufactureProductMap...product-manufacture mapping
		List<ProManufactureProductMap> clonedProManufactureProductMaps = new ArrayList<ProManufactureProductMap>();
		for (ProManufactureProductMap productMap : manufactureProductMaps) {
			if (productMap.getProductId() != null && productMap.getProductQuantity() != null
					&& productMap.getProductQuantity() > 0) {
				productMap.setManufactureId(manufacture.getManufactureId());
				manufactureProductMapRepository.save(productMap);
				clonedProManufactureProductMaps.add((ProManufactureProductMap) productMap.clone());
			}
		}

		ManufactureWrapper wrapper = CommonServiceUtil.processManufactureStock(manufacture.getManufactureId(),
				clonedProManufactureProductMaps, stockQuantities);

		for (ProStock proStock : wrapper.getStockList()) {
			stockRepository.save(proStock);
		}

		// purchaseRepository.setIsConsumedToY(consumedPurchaseList);
		List<ProPurchase> purchases = purchaseRepository.findByPurchaseIds(wrapper.getConsumedPurchaseList());
		for (ProPurchase purchase : purchases) {
			purchase.setIsConsumed("Y");
			purchaseRepository.save(purchase);
		}

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void createManufacture(ManufactureProduct manufactureProduct) throws Exception {

		if (manufactureProduct == null || manufactureProduct.getManufacture() == null
				|| manufactureProduct.getManufactureProductMaps() == null) {
			return;
		}

		ProManufacture manufacture = manufactureProduct.getManufacture();
		manufacture.processManufactureDate();
		List<ProManufactureProductMap> manufactureProductMaps = manufactureProduct.getManufactureProductMaps();
		List<Long> productIds = new ArrayList<Long>();
		for (ProManufactureProductMap productMap : manufactureProductMaps) {
			if (productMap.getProductId() != null && productMap.getProductQuantity() != null
					&& productMap.getProductQuantity() > 0) {
				productIds.add(productMap.getProductId());
			}
		}

		Map<Long, List<StockQuantity>> stockQuantities = readStockQuantity(productIds);
		executeCreateManufacture(manufacture, manufactureProductMaps, stockQuantities);
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

	private Double evaluateManufactureCost(Long manufactureId, List<ProductPurchaseManufacture> purchaseCostList) {

		Double totalManufCost = 0.0;
		for (ProductPurchaseManufacture element : purchaseCostList) {
			Double payableAmount = element.getPayableAmount() != null ? element.getPayableAmount() : 0.0;
			Double purchaseQuantity = element.getPurchaseQuantity() != null ? element.getPurchaseQuantity() : 0.0;
			Double manufactureQuantity = element.getManufactureQuantity() != null ? element.getManufactureQuantity()
					: 0.0;

			Double manufCost = (payableAmount / purchaseQuantity) * manufactureQuantity;
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
	@Transactional(rollbackFor = Exception.class)
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

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateManufacture(ManufactureProduct manufactureProduct) throws Exception {

		if (manufactureProduct == null || manufactureProduct.getManufacture() == null) {
			return;
		}

		ProManufacture manufacture = manufactureProduct.getManufacture();
		manufacture.processManufactureDate();
		Optional<ProManufacture> optional = manufactureRepository.findById(manufacture.getManufactureId());
		if (optional.isPresent()) {
			ProManufacture cachedEntity = optional.get();
			FrameworkEntity.updateCachedEntity(cachedEntity, manufacture);
			manufactureRepository.save(cachedEntity);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteManufacture(Long manufactureId) throws Exception {

		Optional<ProManufacture> optional = manufactureRepository.findById(manufactureId);
		if (optional.isPresent()) {
			ProManufacture manufactureEntity = optional.get();
			if (!"Y".equals(manufactureEntity.getIsDeleteAllowed())) {
				return;
			}
			List<ProManufactureProductMap> mpMaps = manufactureProductMapRepository.findByManufactureId(manufactureId);
			if (mpMaps != null && mpMaps.size() > 0) {
				manufactureProductMapRepository.deleteAll(mpMaps);
			}

			List<ProStock> stocks = stockRepository.findByManufactureId(manufactureId);
			if (stocks != null && stocks.size() > 0) {
				List<Long> purchaseIds = new ArrayList<Long>();
				for (ProStock element : stocks) {
					purchaseIds.add(element.getPurchaseId());
				}
				List<ProPurchase> purchaseList = purchaseRepository.findByPurchaseIds(purchaseIds);
				for (ProPurchase element : purchaseList) {
					element.setIsConsumed("N");
					purchaseRepository.save(element);
				}
				stockRepository.deleteAll(stocks);
			}

			manufactureRepository.delete(manufactureEntity);
		} // end of optional.isPresent()
	}

	@Override
	public boolean isPurchaseQuantityUpdateable(Long bindPurchaseId) throws Exception {

		if (bindPurchaseId == null) {
			return true;
		}

		// sellId is not null OR manufactureId is not null
		List<ProStock> stocks = stockRepository.findInUsePurchasesByPurchaseId(bindPurchaseId);
		if (stocks != null && stocks.size() > 0) {
			return false;
		}

		return true;
	}

	public void pushManufactureToPurchase(ProManufacture proManufacture, ProPurchase proPurchase) throws Exception {

		proPurchase.setPurchaseId(null);
		proPurchase = purchaseRepository.save(proPurchase);

		ProStock stock = new ProStock();
		stock.setProductId(proPurchase.getProductId());
		stock.setPurchaseId(proPurchase.getPurchaseId());
		stock.setPurchaseQuantity(proPurchase.getPurchaseQuantity());
		stockRepository.save(stock);

		proManufacture.setRelatedPurchaseId(proPurchase.getPurchaseId());
		proManufacture.setIsDeleteAllowed("N");
		manufactureRepository.save(proManufacture);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void pushManufactureToPurchase(Long manufactureId) throws Exception {

		if (manufactureId == null) {
			return;
		}
		Optional<ProManufacture> optional = manufactureRepository.findById(manufactureId);
		if (optional.isPresent()) {
			ProManufacture manufacture = optional.get();
			if (manufacture.getManufactureQuantity() == null || manufacture.getManufactureQuantity() <= 0.0) {
				return;
			}
			if (manufacture.getManufactureCost() == null || manufacture.getManufactureCost() <= 0.0) {
				return;
			}
			if (manufacture.getRelatedPurchaseId() != null) {
				return;
			}
			ProPurchase proPurchase = ProPurchase.newInstanceUsingProManufacture(manufacture);
			pushManufactureToPurchase(manufacture, proPurchase);
		}

	}

	@Override
	public void pushManufactureToPurchase() throws Exception {

		List<ProManufacture> manufactures = manufactureRepository.findAll();
		if (manufactures != null && manufactures.size() > 0) {
			for (ProManufacture manufacture : manufactures) {
				if (manufacture.getRelatedPurchaseId() == null) {
					if (manufacture.getManufactureCost() != null && manufacture.getManufactureQuantity() != null) {
						this.pushManufactureToPurchase(manufacture.getManufactureId());
					}
				}
			}
		}
	}

	@Override
	public List<ProSell> findSells() throws Exception {

		return sellRepository.findByAllSells();
	}

	@Override
	@Transactional
	public void createCustomer(ProCustomer proCustomer) throws Exception {

		if (proCustomer.getIsSeedData() == null) {
			proCustomer.setIsSeedData(false);
		}
		FrameworkEntity.createWhoColumnData(proCustomer, proCustomer.getIsSeedData());
		proCustomer = customerRepository.save(proCustomer);
	}

	@Override
	public Iterable<ProCustomer> findAllCustomers() throws Exception {

		return customerRepository.findAllCustomers();
	}

	private void executeCreateSell(ProSell sell, List<ProSellProductMap> sellProductMaps,
			Map<Long, List<StockQuantity>> stockQuantities) throws Exception {
		// 1. Create entry in sell table
		if (sell == null) {
			return;
		}
		if (sell.getIsSeedData() == null) {
			sell.setIsSeedData(false);
		}
		FrameworkEntity.createWhoColumnData(sell, sell.getIsSeedData());
		sell = sellRepository.save(sell);

		// 2. Create entry inside ProManufactureProductMap...product-manufacture mapping
		List<ProSellProductMap> clonedSellProductMaps = new ArrayList<ProSellProductMap>();
		for (ProSellProductMap productMap : sellProductMaps) {
			if (productMap.getProductId() != null && productMap.getSellQuantity() != null
					&& productMap.getSellQuantity() > 0) {
				productMap.setSellId(sell.getSellId());
				sellProductMapRepository.save(productMap);
				clonedSellProductMaps.add((ProSellProductMap) productMap.clone());
			}
		}

		SellWrapper wrapper = CommonServiceUtil.processSellStock(sell.getSellId(), clonedSellProductMaps,
				stockQuantities);

		for (ProStock proStock : wrapper.getStockList()) {
			stockRepository.save(proStock);
		}

		// purchaseRepository.setIsConsumedToY(consumedPurchaseList);
		List<ProPurchase> purchases = purchaseRepository.findByPurchaseIds(wrapper.getConsumedPurchaseList());
		for (ProPurchase purchase : purchases) {
			purchase.setIsConsumed("Y");
			purchaseRepository.save(purchase);
		}

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void createSell(SellForm sellForm) throws Exception {

		if (sellForm == null || sellForm.getSell() == null || sellForm.getSellProductMaps() == null) {
			return;
		}

		ProSell sell = sellForm.getSell();
		sell.processSellDate();
		List<ProSellProductMap> sellProductMaps = sellForm.getSellProductMaps();
		List<Long> productIds = new ArrayList<Long>();
		for (ProSellProductMap productMap : sellProductMaps) {
			if (productMap.getProductId() != null && productMap.getSellQuantity() != null
					&& productMap.getSellQuantity() > 0) {
				productIds.add(productMap.getProductId());
			}
		}

		Map<Long, List<StockQuantity>> stockQuantities = readStockQuantity(productIds);
		executeCreateSell(sell, sellProductMaps, stockQuantities);
	}

	@Override
	public SellForm findSellBySellId(Long sellId) throws Exception {

		SellForm sellForm = new SellForm();

		Optional<ProSell> sellOptional = sellRepository.findById(sellId);
		if (sellOptional.isPresent()) {
			sellForm.setSell(sellOptional.get());
		}

		sellForm.setSellProductMaps(sellProductMapRepository.findBySellId(sellId));
		return sellForm;
	}

}
