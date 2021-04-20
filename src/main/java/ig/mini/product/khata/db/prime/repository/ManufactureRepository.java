package ig.mini.product.khata.db.prime.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ig.mini.product.khata.db.prime.entity.ProManufacture;

@Repository("manufactureRepository")
public interface ManufactureRepository extends JpaRepository<ProManufacture, Long> {

	public Optional<ProManufacture> findById(Long manufactureId);

	@Query("SELECT manuf FROM ProManufacture manuf WHERE manuf.manufactureCost IS NULL")
	public List<ProManufacture> findAllNullManufactureCost();

}
