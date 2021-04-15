package ig.mini.product.khata.services;

import ig.mini.product.khata.db.setup.entity.ProFileExecutionEntry;

public interface ProSetupService {

	public ProFileExecutionEntry findByExecutionId(Long executionId);

	public void createFileExecutionEntry(ProFileExecutionEntry entity) throws Exception;

}
