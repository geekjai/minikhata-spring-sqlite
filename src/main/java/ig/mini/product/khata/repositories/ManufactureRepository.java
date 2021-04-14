package ig.mini.product.khata.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ig.mini.product.khata.db.entity.ProManufacture;

@Repository("manufactureRepository")
public interface ManufactureRepository extends JpaRepository<ProManufacture, Long> {

	@Query("SELECT manuf FROM ProManufacture manuf WHERE manuf.manufactureCost IS NULL")
	public List<ProManufacture> findAllNullManufactureCost();

}