package ig.mini.product.khata.service.common;

import ig.mini.product.khata.db.prime.view.DashboardView;
import ig.mini.product.khata.db.prime.view.SaleChartView;
import ig.mini.product.khata.ui.pojo.SaleChartUI;

public interface DashboardService {

	public DashboardView fetchDashboardView() throws Exception;

	public SaleChartUI findPurManufCostData() throws Exception;

	public SaleChartView findTotalCostSummary() throws Exception;

	public SaleChartUI findPurSellCostData() throws Exception;

	public SaleChartUI findManufSellCostData() throws Exception;

}
