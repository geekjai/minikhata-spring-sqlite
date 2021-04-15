package ig.mini.product.khata.services;

import java.util.List;

import ig.mini.product.khata.db.entity.ProManufacture;
import ig.mini.product.khata.db.entity.ProProduct;
import ig.mini.product.khata.db.entity.ProPurchase;
import ig.mini.product.khata.ui.pojo.ManufactureProduct;

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

}
