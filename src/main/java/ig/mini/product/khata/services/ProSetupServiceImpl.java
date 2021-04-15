package ig.mini.product.khata.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ig.central.library.FrameworkEntity;
import ig.mini.product.khata.db.setup.entity.ProFileExecutionEntry;
import ig.mini.product.khata.db.setup.repository.FileExecutionEntryRepository;

@Service("proSetupService")
public class ProSetupServiceImpl implements ProSetupService {

	@Autowired
	private FileExecutionEntryRepository fileExecutionEntryRepository;

	@Override
	public ProFileExecutionEntry findByExecutionId(Long executionId) {

		Optional<ProFileExecutionEntry> optional = fileExecutionEntryRepository.findByExecutionId(executionId);
		if (optional.isPresent()) {
			return optional.get();
		}

		return null;
	}

	@Override
	@Transactional
	public void createFileExecutionEntry(ProFileExecutionEntry entity) throws Exception {

		if (entity.getIsSeedData() == null) {
			entity.setIsSeedData(false);
		}
		FrameworkEntity.createWhoColumnData(entity, entity.getIsSeedData());
		fileExecutionEntryRepository.save(entity);
	}

}
