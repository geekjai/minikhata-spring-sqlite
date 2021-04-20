package ig.mini.product.khata.core.setup;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import ig.mini.product.khata.db.setup.entity.ProFileExecutionEntry;
import ig.mini.product.khata.service.common.ProCommonService;
import ig.mini.product.khata.service.common.ProSetupService;

@Configuration
public class ApplicationConfiguration implements CommandLineRunner {

	private static final Long DataSourceInitializer_SeedData = 100L;

	@Autowired
	private ProSetupService proSetupService;
	@Autowired
	private ProCommonService proCommonService;

	@Override
	public void run(String... args) throws Exception {

		CommandLineTestRunner.createCustomer(proSetupService, proCommonService);
		CommandLineTestRunner.createProductPurchase(proSetupService, proCommonService);
		CommandLineTestRunner.createManufacture(proSetupService, proCommonService);
		CommandLineTestRunner.createSell(proSetupService, proCommonService);
		System.out.println("Initial Setup Completed!!");
	}

	@Bean
	DataSourceInitializer dataSourceInitializer(final DataSource dataSource) throws Exception {

		boolean createEntry = false;
		ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
		ProFileExecutionEntry entry = proSetupService.findByExecutionId(DataSourceInitializer_SeedData);

		if (entry == null) {
			createEntry = true;
			resourceDatabasePopulator.addScript(new ClassPathResource("/META-INF/sql-script/SeedData.sql"));
		}
		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
		dataSourceInitializer.setDataSource(dataSource);
		dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
		if (createEntry) {
			entry = new ProFileExecutionEntry();
			entry.setExecutionId(DataSourceInitializer_SeedData);
			entry.setFilePath("classpath:/META-INF/sql-script/SeedData.sql");
			entry.setIsSeedData(true);
			proSetupService.createFileExecutionEntry(entry);
		}
		return dataSourceInitializer;
	}

}
