package ig.mini.product.khata.db.prime.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ig.mini.product.khata.db.prime.entity.ProCustomer;

@Repository("customerRepository")
public interface CustomerRepository extends JpaRepository<ProCustomer, Long> {

	@Query("SELECT e FROM ProCustomer e WHERE e.customerId = :customerId")
	public Optional<ProCustomer> findByCustomerId(@Param("customerId") Long customerId);

}
