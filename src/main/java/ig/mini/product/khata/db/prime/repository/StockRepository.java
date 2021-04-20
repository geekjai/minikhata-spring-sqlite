package ig.mini.product.khata.db.prime.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ig.mini.product.khata.db.prime.entity.ProStock;

@Repository("stockRepository")
public interface StockRepository extends JpaRepository<ProStock, Long> {

	@Query("SELECT e FROM ProStock e WHERE e.stockId = :stockId")
	public Optional<ProStock> findByStockId(@Param("stockId") Long stockId);

	@Query("SELECT e FROM ProStock e WHERE e.manufactureId = :manufactureId")
	public List<ProStock> findByManufactureId(@Param("manufactureId") Long manufactureId);

	@Query("SELECT e FROM ProStock e WHERE e.purchaseId = :purchaseId AND (e.manufactureId is not null OR e.sellId is not null)")
	public List<ProStock> findInUsePurchasesByPurchaseId(@Param("purchaseId") Long purchaseId);

	@Query("SELECT e FROM ProStock e WHERE e.purchaseId = :purchaseId AND (e.manufactureId is null AND e.sellId is null)")
	public Optional<ProStock> findPurchaseRecordByPurchaseId(@Param("purchaseId") Long purchaseId);

}
