package ig.mini.product.khata;

import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ig.mini.product.khata.db.entity.ProPurchase;
import ig.mini.product.khata.services.ProCommonService;
import ig.mini.product.khata.ui.pojo.ManufactureProduct;

@SpringBootApplication
public class MiniProductKhataApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniProductKhataApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner runner(ProCommonService proCommonService) {
		return args -> {
			createProductPurchase(proCommonService);
			createManufacture(proCommonService);
			System.out.println("Initial Setup Completed!!");
		};
	}

	public static void createManufacture(ProCommonService proCommonService) throws Exception {
		// read json and write to db
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<ManufactureProduct> typeReference = new TypeReference<ManufactureProduct>() {};
		InputStream inputStream = TypeReference.class.getResourceAsStream("/__json__/manufacture.spec.json");
		
		ManufactureProduct manufactureProduct = mapper.readValue(inputStream, typeReference);
		proCommonService.createManufacture(manufactureProduct);
		proCommonService.evaluateManufactureCost();
	}
	
	public static void createProductPurchase(ProCommonService proCommonService) throws Exception  {
		// read json and write to db
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<ProPurchase>> typeReference = new TypeReference<List<ProPurchase>>() {};
		InputStream inputStream = TypeReference.class.getResourceAsStream("/__json__/purchase.spec.json");
		
		List<ProPurchase> purchases = mapper.readValue(inputStream, typeReference);
		for (ProPurchase purchase : purchases) {
			proCommonService.createProductPurchase(purchase);
		}
	}
	
}
