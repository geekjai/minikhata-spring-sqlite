package ig.mini.product.khata.service.util;

import java.util.List;
import java.util.Map;

import ig.mini.product.khata.db.prime.entity.ProManufactureProductMap;
import ig.mini.product.khata.db.prime.entity.ProStock;
import ig.mini.product.khata.db.prime.view.ManufactureWrapper;
import ig.mini.product.khata.db.prime.view.StockQuantity;

public class CommonServiceUtil {

	public static ManufactureWrapper processStock(Long manufactureId,
			List<ProManufactureProductMap> manufactureProductMapList, Map<Long, List<StockQuantity>> stockQuantityMap) {

		ManufactureWrapper wrapper = new ManufactureWrapper();
		while (manufactureProductMapList != null && manufactureProductMapList.size() > 0) {
			// read 0 index
			ProManufactureProductMap manufactureProduct = manufactureProductMapList.get(0);
			Long productId = manufactureProduct.getProductId();
			if (stockQuantityMap.containsKey(productId)) {

				List<StockQuantity> stockQuantityList = stockQuantityMap.get(productId);
				StockQuantity element = stockQuantityList.get(0);

				double xfactor = element.getPurchaseQuantity()
						- (element.getManufactureQuantity() + element.getSellQuantity());
				if (xfactor <= 0) {
					stockQuantityList.remove(0);
					if (stockQuantityList.size() == 0) {
						stockQuantityMap.remove(productId);
					}
				}

				double curQuantity = 0.0;
				if (xfactor > manufactureProduct.getProductQuantity()) {
					curQuantity = manufactureProduct.getProductQuantity();
					manufactureProduct.setProductQuantity(manufactureProduct.getProductQuantity() - curQuantity);
				} else {
					curQuantity = xfactor;
					manufactureProduct.setProductQuantity(manufactureProduct.getProductQuantity() - curQuantity);
				}

				ProStock proStock = new ProStock();
				proStock.setManufactureId(manufactureId);
				proStock.setProductId(productId);
				proStock.setPurchaseId(element.getPurchaseId());
				proStock.setManufactureQuantity(curQuantity);
				wrapper.addProStock(proStock);

				if (curQuantity == xfactor) {
					wrapper.addConsumedPurchaseId(element.getPurchaseId());
					stockQuantityList.remove(0);
					if (stockQuantityList.size() == 0) {
						stockQuantityMap.remove(productId);
					}
				}
				if (manufactureProduct.getProductQuantity() <= 0) {
					// remove 0 index
					manufactureProductMapList.remove(0);
				}
			} // end of if block
				// ....
		} // end of while loop

		return wrapper;
	}

}
