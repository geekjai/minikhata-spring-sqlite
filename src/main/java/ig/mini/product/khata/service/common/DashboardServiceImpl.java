package ig.mini.product.khata.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ig.mini.product.khata.db.common.repository.CoreRepositoryDao;
import ig.mini.product.khata.db.prime.view.DashboardView;

@Repository("dashboardService")
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	private CoreRepositoryDao coreRepositoryDao;

	@Override
	public DashboardView fetchDashboardView() throws Exception {

		return coreRepositoryDao.populateDashboardView();
	}
	
}
