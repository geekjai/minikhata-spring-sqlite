package ig.mini.product.khata.ui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ig.mini.product.khata.db.prime.entity.ProManufacture;
import ig.mini.product.khata.db.prime.entity.ProPurchase;
import ig.mini.product.khata.db.prime.entity.ProSell;
import ig.mini.product.khata.service.common.DashboardService;
import ig.mini.product.khata.service.common.ProCommonService;
import ig.mini.product.khata.ui.pojo.CommonResponseEntity;
import ig.mini.product.khata.ui.pojo.SaleChartUI;

@RestController
public class CoreRestController {

	@Autowired
	private ProCommonService proCommonService;
	@Autowired
	private DashboardService dashboardService;

	@RequestMapping(value = "/api/dashboard/saleRecord")
	public ResponseEntity<Object> findPurManufCostData() {

		SaleChartUI saleChartUI = null;
		try {
			saleChartUI = dashboardService.findPurManufCostData();
			return new ResponseEntity<>(saleChartUI, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(value = "/api/purchases/purchaseRecords")
	public ResponseEntity<Object> purchaseRecords() {
		List<ProPurchase> purchases = null;
		try {
			purchases = proCommonService.findPurchasesWithProduct();
			return new ResponseEntity<>(purchases, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(value = "/api/purchases/deletePurchase/{purchaseId}")
	public ResponseEntity<Object> deletePurchase(@PathVariable Long purchaseId) {

		try {
			proCommonService.deleteProductPurchase(purchaseId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/api/manufacture/manufactureRecords")
	public ResponseEntity<Object> manufactureRecords() {
		List<ProManufacture> manufactures = null;
		try {
			manufactures = proCommonService.findManufacturesWithProduct();
			return new ResponseEntity<>(manufactures, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(value = "/api/manufacture/findManufactureCost/{manufactureId}", method = RequestMethod.GET)
	public ResponseEntity<Object> findManufactureCost(@PathVariable Long manufactureId) {
		CommonResponseEntity response = new CommonResponseEntity();
		try {
			Double manufactureCost = null;
			manufactureCost = proCommonService.evaluateManufactureCost(manufactureId);
			String message = "Manufacturing cost is updated successfully!!";
			response.setManufactureCost(manufactureCost);
			response.setResponseMessage(message);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(value = "/api/manufacture/deleteManufacture/{manufactureId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteManufacture(@PathVariable Long manufactureId) {
		try {
			proCommonService.deleteManufacture(manufactureId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/api/manufacture/pushManufactureToPurchase/{manufactureId}", method = RequestMethod.PUT)
	public ResponseEntity<Object> pushManufactureToPurchase(@PathVariable Long manufactureId) {
		try {
			proCommonService.pushManufactureToPurchase(manufactureId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/api/sell/sellRecords")
	public ResponseEntity<Object> sellRecords() {
		List<ProSell> sells = null;
		try {
			sells = proCommonService.findSells();
			return new ResponseEntity<>(sells, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/api/sell/deleteSell/{sellId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteSell(@PathVariable Long sellId) {
		try {
			proCommonService.deleteSell(sellId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
