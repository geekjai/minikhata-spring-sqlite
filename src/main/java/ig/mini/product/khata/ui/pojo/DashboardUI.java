package ig.mini.product.khata.ui.pojo;

import ig.mini.product.khata.db.prime.view.DashboardView;
import ig.mini.product.khata.db.prime.view.SaleChartView;

public class DashboardUI {

	private DashboardView dashboardView;

	private SaleChartView saleChartView;

	public DashboardView getDashboardView() {
		return dashboardView;
	}

	public void setDashboardView(DashboardView dashboardView) {
		this.dashboardView = dashboardView;
	}

	public SaleChartView getSaleChartView() {
		return saleChartView;
	}

	public void setSaleChartView(SaleChartView saleChartView) {
		this.saleChartView = saleChartView;
	}

}
