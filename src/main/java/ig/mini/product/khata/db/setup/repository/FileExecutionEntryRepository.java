package ig.mini.product.khata.db.setup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ig.mini.product.khata.db.setup.entity.ProFileExecutionEntry;

@Repository("fileExecutionEntryRepository")
public interface FileExecutionEntryRepository extends JpaRepository<ProFileExecutionEntry, Long> {

	@Query("SELECT e FROM ProFileExecutionEntry e WHERE e.executionId = :executionId")
	public Optional<ProFileExecutionEntry> findByExecutionId(@Param("executionId") Long executionId);

}
