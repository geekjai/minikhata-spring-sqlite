package ig.mini.product.khata.ui.controller;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ig.mini.product.khata.db.entity.ProManufacture;
import ig.mini.product.khata.db.entity.ProPurchase;
import ig.mini.product.khata.services.ProCommonService;

@RestController
public class CoreRestController {

	@Autowired
	private ProCommonService proCommonService;

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

	@RequestMapping(value = "/api/purchases/createPurchase")
	public ResponseEntity<Object> createPurchase(@RequestBody String payload) {

		Gson gson = new Gson();
		Type mapType = new TypeToken<Map<String, Object>>() {
		}.getType();

		Map<String, Object> map = gson.fromJson(payload, mapType);
		ProPurchase proPurchase = ProPurchase.getInstanceFromMap(map);
		proCommonService.createProductPurchase(proPurchase);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/api/purchases/editPurchase")
	public ResponseEntity<Object> editPurchase(@RequestBody String payload) {

		Gson gson = new Gson();
		Type mapType = new TypeToken<Map<String, Object>>() {
		}.getType();

		Map<String, Object> map = gson.fromJson(payload, mapType);
		ProPurchase proPurchase = ProPurchase.getInstanceFromMap(map);
		try {
			proCommonService.updateProductPurchase(proPurchase);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
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
		Double manufactureCost = null;
		try {
			manufactureCost = proCommonService.evaluateManufactureCost(manufactureId);
			return new ResponseEntity<>(manufactureCost, HttpStatus.OK);
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

}
