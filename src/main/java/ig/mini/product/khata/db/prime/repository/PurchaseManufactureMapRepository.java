package ig.mini.product.khata.db.prime.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ig.mini.product.khata.db.prime.entity.ProPurchaseManufactureMap;

//@Repository("purchaseManufactureMapRepository")
@SuppressWarnings("deprecation")
public interface PurchaseManufactureMapRepository extends JpaRepository<ProPurchaseManufactureMap, Long> {

	@Query("SELECT e FROM ProPurchaseManufactureMap e WHERE e.manufactureId = :manufactureId")
	public List<ProPurchaseManufactureMap> findByManufactureId(@Param("manufactureId") Long manufactureId);

	@Query("SELECT e FROM ProPurchaseManufactureMap e WHERE e.inQuantity is NOT NULL and e.outQuantity is NULL and e.purchaseId = :purchaseId")
	public Optional<ProPurchaseManufactureMap> findPurchaseInQty(@Param("purchaseId") Long purchaseId);

	@Query("SELECT e FROM ProPurchaseManufactureMap e WHERE e.inQuantity is NULL and e.outQuantity is NOT NULL and e.purchaseId = :purchaseId")
	public List<ProPurchaseManufactureMap> findManufactureOutQty(@Param("purchaseId") Long purchaseId);

}
