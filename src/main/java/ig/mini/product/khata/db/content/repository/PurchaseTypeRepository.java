package ig.mini.product.khata.db.content.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ig.mini.product.khata.db.content.entity.ProPurchaseType;

@Repository("purchaseTypeRepository")
public interface PurchaseTypeRepository extends JpaRepository<ProPurchaseType, Long> {

}
