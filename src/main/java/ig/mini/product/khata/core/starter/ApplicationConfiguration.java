package ig.mini.product.khata.core.starter;

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

	private static final Long DataSourceInitializer_CreateSchema = 100L;
	private static final Long DataSourceInitializer_SeedData = 101L;

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

		boolean createSchema = false;
		boolean seedData = false;
		ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
		ProFileExecutionEntry entry = proSetupService.findByExecutionId(DataSourceInitializer_CreateSchema);
		if (entry == null) {
			createSchema = true;
			resourceDatabasePopulator.addScript(new ClassPathResource("/META-INF/sql-script/create-schema.sql"));
		}

		entry = proSetupService.findByExecutionId(DataSourceInitializer_SeedData);
		if (entry == null) {
			seedData = true;
			resourceDatabasePopulator.addScript(new ClassPathResource("/META-INF/sql-script/SeedData.sql"));
		}
		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
		dataSourceInitializer.setDataSource(dataSource);
		dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
		if (createSchema) {
			entry = new ProFileExecutionEntry();
			entry.setExecutionId(DataSourceInitializer_CreateSchema);
			entry.setFilePath("classpath:/META-INF/sql-script/create-schema.sql");
			entry.setIsSeedData(true);
			proSetupService.createFileExecutionEntry(entry);
		}
		if (seedData) {
			entry = new ProFileExecutionEntry();
			entry.setExecutionId(DataSourceInitializer_SeedData);
			entry.setFilePath("classpath:/META-INF/sql-script/SeedData.sql");
			entry.setIsSeedData(true);
			proSetupService.createFileExecutionEntry(entry);
		}
		return dataSourceInitializer;
	}

}
