package ig.mini.product.khata.db.prime.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ig.mini.product.khata.db.prime.entity.ProSell;

@Repository("sellRepository")
public interface SellRepository extends JpaRepository<ProSell, Long> {

	@Query("SELECT e FROM ProSell e WHERE e.sellId = :sellId")
	public Optional<ProSell> findBySellId(@Param("sellId") Long sellId);

	@Query("SELECT e FROM ProSell e order by e.creationDate desc")
	public List<ProSell> findByAllSells();

}
