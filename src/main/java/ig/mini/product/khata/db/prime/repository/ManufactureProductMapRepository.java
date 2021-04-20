package ig.mini.product.khata.db.prime.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ig.mini.product.khata.db.prime.entity.ProManufactureProductMap;

@Repository("manufactureProductMapRepository")
public interface ManufactureProductMapRepository extends JpaRepository<ProManufactureProductMap, Long> {

	@Query("SELECT mpm FROM ProManufactureProductMap mpm WHERE mpm.manufactureId = :manufactureId")
	public List<ProManufactureProductMap> findByManufactureId(@Param("manufactureId") Long manufactureId);
	
}
