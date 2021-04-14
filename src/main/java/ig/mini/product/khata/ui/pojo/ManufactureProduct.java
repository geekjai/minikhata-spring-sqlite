package ig.mini.product.khata.ui.pojo;

import java.util.ArrayList;
import java.util.List;

import ig.mini.product.khata.db.entity.ProManufacture;
import ig.mini.product.khata.db.entity.ProManufactureProductMap;

public class ManufactureProduct {

	private ProManufacture manufacture;

	private List<ProManufactureProductMap> manufactureProductMaps;

	public ManufactureProduct() {
		manufacture = new ProManufacture();
		manufactureProductMaps = new ArrayList<ProManufactureProductMap>();
	}

	public void addProManufactureProductMap(ProManufactureProductMap entity) {
		this.manufactureProductMaps.add(entity);
	}

	public List<ProManufactureProductMap> getManufactureProductMaps() {
		return manufactureProductMaps;
	}

	public void setManufactureProductMaps(List<ProManufactureProductMap> manufactureProductMaps) {
		this.manufactureProductMaps = manufactureProductMaps;
	}

	public ProManufacture getManufacture() {
		return manufacture;
	}

	public void setManufacture(ProManufacture manufacture) {
		this.manufacture = manufacture;
	}

}
