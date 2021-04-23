package ig.mini.product.khata.service.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ig.mini.product.khata.db.common.repository.CoreRepositoryDao;
import ig.mini.product.khata.db.prime.view.DashboardView;
import ig.mini.product.khata.db.prime.view.SaleChartView;
import ig.mini.product.khata.ui.pojo.SaleChartUI;

@Repository("dashboardService")
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	private CoreRepositoryDao coreRepositoryDao;

	@Override
	public DashboardView fetchDashboardView() throws Exception {

		return coreRepositoryDao.populateDashboardView();
	}

	@Override
	public SaleChartUI findPurManufCostData() throws Exception {

		List<SaleChartView> list = coreRepositoryDao.findPurManufCostData();
		SaleChartUI chartUI = null;
		if (list != null && list.size() > 0) {
			chartUI = new SaleChartUI(list.size());
			int index = 0;
			for (SaleChartView element : list) {
				chartUI.addLabel(index, element.getPurchaseYear() + "-" + element.getPurchaseMonth());
				chartUI.addPurchaseCost(index, element.getPurchaseAmount());
				chartUI.addManufactureCost(index, element.getManufactureCost());
				index += 1;
			}
		}

		return chartUI;
	}

}
