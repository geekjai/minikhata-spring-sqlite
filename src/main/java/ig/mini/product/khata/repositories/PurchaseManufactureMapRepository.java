package ig.mini.product.khata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ig.mini.product.khata.db.entity.ProPurchaseManufactureMap;

@Repository("purchaseManufactureMapRepository")
public interface PurchaseManufactureMapRepository extends JpaRepository<ProPurchaseManufactureMap, Long> {

}