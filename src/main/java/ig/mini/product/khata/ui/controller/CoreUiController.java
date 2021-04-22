package ig.mini.product.khata.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ig.mini.product.khata.db.prime.entity.ProPurchase;
import ig.mini.product.khata.db.prime.entity.ProSellProductMap;
import ig.mini.product.khata.service.common.ProCommonService;
import ig.mini.product.khata.ui.pojo.ManufactureProduct;
import ig.mini.product.khata.ui.pojo.SellForm;

@Controller
public class CoreUiController {

	@Autowired
	private ProCommonService proCommonService;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/purchases/viewPurchases")
	public ModelAndView viewPurchases() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("purchase");
		modelAndView.addObject("fragment", "viewPurchases");
		return modelAndView;
	}

	@RequestMapping("/purchases/createPurchase")
	public ModelAndView createPurchase() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("purchase");
		modelAndView.addObject("fragment", "createPurchase");
		modelAndView.addObject("formTitle", "Create Purchase");
		modelAndView.addObject("products", proCommonService.findAllProduct());
		modelAndView.addObject("purchase", new ProPurchase());
		modelAndView.addObject("isPurchaseQuantityUpdateable", true);
		return modelAndView;
	}

	@RequestMapping("/purchases/editPurchase/{id}")
	public ModelAndView editPurchase(@PathVariable Long id) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("purchase");
		modelAndView.addObject("fragment", "editPurchase");
		modelAndView.addObject("formTitle", "Edit Purchase");
		modelAndView.addObject("products", proCommonService.findAllProduct());
		modelAndView.addObject("purchase", proCommonService.findByPurchaseId(id));
		modelAndView.addObject("isPurchaseQuantityUpdateable", proCommonService.isPurchaseQuantityUpdateable(id));
		return modelAndView;
	}

	@RequestMapping("/manufacture/viewManufactures")
	public ModelAndView viewManufactures() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("manufacture");
		modelAndView.addObject("fragment", "viewManufactures");
		return modelAndView;
	}

	@RequestMapping("/manufacture/createIncense")
	public ModelAndView createIncense() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("manufacture");
		modelAndView.addObject("formTitle", "Create Incense Manufacture Record");
		modelAndView.addObject("fragment", "createManufacture");
		modelAndView.addObject("products", proCommonService.findAllProduct());

		ManufactureProduct formPojo = proCommonService.newManufactureIncenseInstance();
		modelAndView.addObject("form", formPojo);
		return modelAndView;
	}

	@RequestMapping(value = "/manufacture/createManufacture/submit", method = RequestMethod.POST)
	public String createManufactureSubmit(@ModelAttribute ManufactureProduct form) {
		try {
			proCommonService.createManufacture(form);
			return "redirect:/manufacture/viewManufactures";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "redirect:/manufacture/createIncense";
		}
	}

	@RequestMapping("/manufacture/editManufacture/{id}")
	public ModelAndView editManufacture(@PathVariable Long id) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("manufacture");
		modelAndView.addObject("formTitle", "Edit Manufacture Record");
		modelAndView.addObject("fragment", "editManufacture");
		modelAndView.addObject("products", proCommonService.findAllProduct());

		ManufactureProduct formPojo = proCommonService.findManufactureByManufactureId(id);
		modelAndView.addObject("form", formPojo);
		return modelAndView;
	}

	@RequestMapping(value = "/manufacture/editManufacture/submit", method = RequestMethod.POST)
	public String editManufactureSubmit(@ModelAttribute ManufactureProduct form) {
		try {
			proCommonService.updateManufacture(form);
			return "redirect:/manufacture/viewManufactures";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/manufacture/createIncense";
		}
	}

	@RequestMapping("/sell/viewSells")
	public ModelAndView viewSells() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sell");
		modelAndView.addObject("fragment", "viewSells");
		return modelAndView;
	}

	private ModelAndView createSellModelAndView(SellForm sellForm) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sell");
		modelAndView.addObject("fragment", "createSell");
		modelAndView.addObject("formTitle", "Create New Sell");
		if (sellForm != null) {
			sellForm.setProducts(proCommonService.findAllProduct());
			sellForm.setCustomers(proCommonService.findAllCustomers());
		}
		return modelAndView;
	}

	@RequestMapping("/sell/createSell")
	public ModelAndView createSell() {

		// form data
		SellForm formPojo = new SellForm();
		ModelAndView modelAndView = null;
		try {
			modelAndView = createSellModelAndView(formPojo);
			modelAndView.addObject("form", formPojo);
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return modelAndView;
		}

	}

	@RequestMapping(value = "/sell/createSell/submit", params = { "addProduct" }, method = RequestMethod.POST)
	public ModelAndView createSellSubmitAddProduct(@ModelAttribute SellForm sellForm) {
		try {
			ModelAndView modelAndView = createSellModelAndView(sellForm);
			sellForm.getSellProductMaps().add(new ProSellProductMap());
			modelAndView.addObject("form", sellForm);
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/sell/createSell/submit", params = { "deleteProduct" }, method = RequestMethod.POST)
	public ModelAndView createSellSubmitDeleteProduct(@ModelAttribute SellForm sellForm) {
		try {
			ModelAndView modelAndView = createSellModelAndView(sellForm);
			Long index = sellForm.getDeletedProductIndex();
			if (index != null && index >= 0) {
				if (index < sellForm.getSellProductMaps().size()) {
					sellForm.getSellProductMaps().remove(index.intValue());
				}
			}
			modelAndView.addObject("form", sellForm);
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/sell/createSell/submit", method = RequestMethod.POST)
	public String createSellSubmit(@ModelAttribute SellForm sellForm) {
		try {
			proCommonService.createSell(sellForm);
			return "redirect:/sell/viewSells";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "redirect:/sell/createSell";
		}
	}

	private ModelAndView editSellModelAndView(SellForm sellForm) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sell");
		modelAndView.addObject("fragment", "editSell");
		modelAndView.addObject("formTitle", "Edit Sell");
		if (sellForm != null) {
			sellForm.setProducts(proCommonService.findAllProduct());
			sellForm.setCustomers(proCommonService.findAllCustomers());
		}
		return modelAndView;
	}

	@RequestMapping("/sell/editSell/{id}")
	public ModelAndView editSell(@PathVariable Long id) throws Exception {

		SellForm sellForm = proCommonService.findSellBySellId(id);
		ModelAndView modelAndView = editSellModelAndView(sellForm);

		modelAndView.addObject("form", sellForm);
		return modelAndView;
	}

	@RequestMapping(value = "/sell/editSell/submit", method = RequestMethod.POST)
	public String editSellSubmit(@ModelAttribute SellForm form) {
		try {
			proCommonService.updateSell(form);
			return "redirect:/sell/viewSells";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/sell/editSell/" + form.getSell().getSellId();
		}
	}

}