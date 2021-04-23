package ig.mini.product.khata.ui.pojo;

public class SaleChartUI {

	private String[] labels;
	private Double[] purchaseCosts;
	private Double[] manufactureCosts;

	public SaleChartUI(int size) {

		labels = new String[size];
		purchaseCosts = new Double[size];
		manufactureCosts = new Double[size];
	}

	public String[] getLabels() {
		return labels;
	}

	public void addLabel(int index, String label) {
		this.labels[index] = label;
	}

	public Double[] getPurchaseCosts() {
		return purchaseCosts;
	}

	public void addPurchaseCost(int index, Double purchaseCost) {
		purchaseCost = purchaseCost == null ? 0.0 : purchaseCost;
		this.purchaseCosts[index] = purchaseCost;
	}

	public Double[] getManufactureCosts() {
		return manufactureCosts;
	}

	public void addManufactureCost(int index, Double manufactureCost) {
		manufactureCost = manufactureCost == null ? 0.0 : manufactureCost;
		this.manufactureCosts[index] = manufactureCost;
	}

}
