package ig.mini.product.khata.service.common;

import java.util.List;

import ig.mini.product.khata.db.content.entity.ProProduct;
import ig.mini.product.khata.db.prime.entity.ProCustomer;
import ig.mini.product.khata.db.prime.entity.ProManufacture;
import ig.mini.product.khata.db.prime.entity.ProPurchase;
import ig.mini.product.khata.db.prime.entity.ProSell;
import ig.mini.product.khata.ui.pojo.ManufactureProduct;
import ig.mini.product.khata.ui.pojo.SellForm;

public interface ProCommonService {

	public Iterable<ProProduct> findAllProduct();

	public List<ProPurchase> findPurchasesWithProduct() throws Exception;

	public void createProductPurchase(ProPurchase proPurchase);

	public ProPurchase findByPurchaseId(Long purchaseId);

	public void updateProductPurchase(ProPurchase proPurchase) throws Exception;

	public void deleteProductPurchase(Long purchaseId) throws Exception;

	public List<ProManufacture> findManufacturesWithProduct() throws Exception;

	public ManufactureProduct newManufactureIncenseInstance() throws Exception;

	public void createManufacture(ManufactureProduct manufactureProduct) throws Exception;

	public ManufactureProduct findManufactureByManufactureId(Long manufactureId) throws Exception;

	public Double evaluateManufactureCost(Long manufactureId) throws Exception;

	public void evaluateManufactureCost() throws Exception;

	public void updateManufacture(ManufactureProduct manufactureProduct) throws Exception;

	public void deleteManufacture(Long manufactureId) throws Exception;

	public boolean isPurchaseQuantityUpdateable(Long bindPurchaseId) throws Exception;

	public void pushManufactureToPurchase(Long manufactureId) throws Exception;

	public void pushManufactureToPurchase() throws Exception;

	public List<ProSell> findSells() throws Exception;

	public void createCustomer(ProCustomer proCustomer) throws Exception;

	public Iterable<ProCustomer> findAllCustomers() throws Exception;

	public void createSell(SellForm sellForm) throws Exception;

	public SellForm findSellBySellId(Long sellId) throws Exception;

	public void updateSell(SellForm sellForm) throws Exception;

	public void deleteSell(Long sellId) throws Exception;

}
