package ig.mini.product.khata.ui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ig.mini.product.khata.db.prime.entity.ProSell;
import ig.mini.product.khata.services.ProCommonService;

@RestController
public class SellRestController {

	@Autowired
	private ProCommonService proCommonService;

	@RequestMapping(value = "/api/sell/sellRecords")
	public ResponseEntity<Object> manufactureRecords() {
		List<ProSell> sells = null;
		try {
			sells = proCommonService.findSells();
			return new ResponseEntity<>(sells, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
