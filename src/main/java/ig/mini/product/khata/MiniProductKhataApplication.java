package ig.mini.product.khata;

import java.io.IOException;
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

@SpringBootApplication
public class MiniProductKhataApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniProductKhataApplication.class, args);
	}

	public static void createProductPurchase(ProCommonService proCommonService) {
		// read json and write to db
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<ProPurchase>> typeReference = new TypeReference<List<ProPurchase>>() {};
		InputStream inputStream = TypeReference.class.getResourceAsStream("/__json__/purchase.spec.json");
		try {
			List<ProPurchase> purchases = mapper.readValue(inputStream, typeReference);
			for (ProPurchase purchase : purchases) {
				proCommonService.createProductPurchase(purchase);
			}
		} catch (IOException e) {
			System.out.println("Unable to save purchase: " + e.getMessage());
		}
	}

	@Bean
	public CommandLineRunner runner(ProCommonService proCommonService) {
		return args -> {
			createProductPurchase(proCommonService);
		};
	}

}
