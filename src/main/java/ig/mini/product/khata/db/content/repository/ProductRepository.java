package ig.mini.product.khata.db.content.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ig.mini.product.khata.db.content.entity.ProProduct;

@Repository("productRepository")
public interface ProductRepository extends JpaRepository<ProProduct, Long> {

	@Query("SELECT pd FROM ProProduct pd WHERE pd.productCode IN (:codes)")
	public List<ProProduct> findByProductCodes(@Param("codes") List<String> codes);

	@Query("SELECT pd FROM ProProduct pd WHERE pd.productCode = :code")
	public List<ProProduct> findByProductCode(@Param("code") String code);

}
