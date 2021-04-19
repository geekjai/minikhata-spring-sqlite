package ig.mini.product.khata.db.prime.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ig.mini.product.khata.db.prime.entity.ProSellProductMap;

@Repository("sellProductMapRepository")
public interface SellProductMapRepository extends JpaRepository<ProSellProductMap, Long> {

	@Query("SELECT e FROM ProSellProductMap e WHERE e.sellId = :sellId")
	public Optional<ProSellProductMap> findBySellId(@Param("sellId") Long sellId);

}
