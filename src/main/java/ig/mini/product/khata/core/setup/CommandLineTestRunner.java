package ig.mini.product.khata.core.setup;

import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ig.mini.product.khata.db.prime.entity.ProCustomer;
import ig.mini.product.khata.db.prime.entity.ProPurchase;
import ig.mini.product.khata.db.setup.entity.ProFileExecutionEntry;
import ig.mini.product.khata.services.ProCommonService;
import ig.mini.product.khata.services.ProSetupService;
import ig.mini.product.khata.ui.pojo.ManufactureProduct;

public class CommandLineTestRunner {

	private static final Long CreateProductPurchase = 500L;
	private static final Long CreateManufacture = 501L;
	private static final Long CreateCustomer = 502L;

	public static void createCustomer(ProSetupService proSetupService, ProCommonService proCommonService)
			throws Exception {
		ProFileExecutionEntry entry = proSetupService.findByExecutionId(CreateCustomer);
		if (entry != null) {
			return;
		}
		// read json and write to db
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<ProCustomer>> typeReference = new TypeReference<List<ProCustomer>>() {
		};
		InputStream inputStream = TypeReference.class.getResourceAsStream("/__json__/customer.spec.json");

		List<ProCustomer> customers = mapper.readValue(inputStream, typeReference);
		for (ProCustomer customer : customers) {
			customer.setIsSeedData(true);
			proCommonService.createCustomer(customer);
		}

		entry = new ProFileExecutionEntry();
		entry.setExecutionId(CreateCustomer);
		entry.setFilePath("classpath:/__json__/customer.spec.json");
		entry.setIsSeedData(true);
		proSetupService.createFileExecutionEntry(entry);
	}

	public static void createManufacture(ProSetupService proSetupService, ProCommonService proCommonService)
			throws Exception {
		ProFileExecutionEntry entry = proSetupService.findByExecutionId(CreateManufacture);
		if (entry != null) {
			return;
		}
		// read json and write to db
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<ManufactureProduct> typeReference = new TypeReference<ManufactureProduct>() {
		};
		InputStream inputStream = TypeReference.class.getResourceAsStream("/__json__/manufacture.spec.json");

		ManufactureProduct manufactureProduct = mapper.readValue(inputStream, typeReference);
		proCommonService.createManufacture(manufactureProduct);
		proCommonService.evaluateManufactureCost();

		entry = new ProFileExecutionEntry();
		entry.setExecutionId(CreateManufacture);
		entry.setFilePath("classpath:/__json__/manufacture.spec.json");
		entry.setIsSeedData(true);
		proSetupService.createFileExecutionEntry(entry);
	}

	public static void createProductPurchase(ProSetupService proSetupService, ProCommonService proCommonService)
			throws Exception {
		ProFileExecutionEntry entry = proSetupService.findByExecutionId(CreateProductPurchase);
		if (entry != null) {
			return;
		}
		// read json and write to db
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<ProPurchase>> typeReference = new TypeReference<List<ProPurchase>>() {
		};
		InputStream inputStream = TypeReference.class.getResourceAsStream("/__json__/purchase.spec.json");

		List<ProPurchase> purchases = mapper.readValue(inputStream, typeReference);
		for (ProPurchase purchase : purchases) {
			proCommonService.createProductPurchase(purchase);
		}

		entry = new ProFileExecutionEntry();
		entry.setExecutionId(CreateProductPurchase);
		entry.setFilePath("classpath:/__json__/purchase.spec.json");
		entry.setIsSeedData(true);
		proSetupService.createFileExecutionEntry(entry);
	}

}
