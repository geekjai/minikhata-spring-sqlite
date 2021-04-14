package ig.mini.product.khata.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ig.mini.product.khata.db.entity.ProPurchase;

@Repository("purchaseRepository")
public interface PurchaseRepository extends JpaRepository<ProPurchase, Long> {

	public Optional<ProPurchase> findById(Long purchaseId);

	@Modifying
	@Query("update ProPurchase pur set pur.isConsumed = 'Y' where pur.purchaseId IN (:purchaseIds)")
	public int setIsConsumedToY(@Param("purchaseIds") List<Long> purchaseIds);

	@Query("SELECT pur FROM ProPurchase pur where pur.purchaseId IN (:purchaseIds)")
	public List<ProPurchase> findByPurchaseIds(@Param("purchaseIds") List<Long> purchaseIds);

}
