package ig.mini.product.khata.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ig.mini.product.khata.db.entity.ProPurchaseManufactureMap;

@Repository("purchaseManufactureMapRepository")
public interface PurchaseManufactureMapRepository extends JpaRepository<ProPurchaseManufactureMap, Long> {

	@Query("SELECT e FROM ProPurchaseManufactureMap e WHERE e.manufactureId = :manufactureId")
	public List<ProPurchaseManufactureMap> findByManufactureId(@Param("manufactureId") Long manufactureId);

}
