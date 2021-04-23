package ig.mini.product.khata.db.prime.view;

import ig.central.library.FrameworkEntity;
import ig.central.library.TransientColumn;

public class SaleChartView extends FrameworkEntity {

	@TransientColumn(name = "purchase_amount")
	private Double purchaseAmount;

	@TransientColumn(name = "purchase_month")
	private String purchaseMonth;

	@TransientColumn(name = "purchase_year")
	private String purchaseYear;

	@TransientColumn(name = "manufacture_cost")
	private Double manufactureCost;

	@TransientColumn(name = "manufacture_month")
	private String manufactureMonth;

	@TransientColumn(name = "manufacture_year")
	private String manufactureYear;

	public Double getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(Double purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	public String getPurchaseMonth() {
		return purchaseMonth;
	}

	public void setPurchaseMonth(String purchaseMonth) {
		this.purchaseMonth = purchaseMonth;
	}

	public String getPurchaseYear() {
		return purchaseYear;
	}

	public void setPurchaseYear(String purchaseYear) {
		this.purchaseYear = purchaseYear;
	}

	public Double getManufactureCost() {
		return manufactureCost;
	}

	public void setManufactureCost(Double manufactureCost) {
		this.manufactureCost = manufactureCost;
	}

	public String getManufactureMonth() {
		return manufactureMonth;
	}

	public void setManufactureMonth(String manufactureMonth) {
		this.manufactureMonth = manufactureMonth;
	}

	public String getManufactureYear() {
		return manufactureYear;
	}

	public void setManufactureYear(String manufactureYear) {
		this.manufactureYear = manufactureYear;
	}

}
