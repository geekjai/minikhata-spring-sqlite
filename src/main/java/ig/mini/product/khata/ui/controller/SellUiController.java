package ig.mini.product.khata.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ig.mini.product.khata.db.prime.entity.ProSellProductMap;
import ig.mini.product.khata.service.common.ProCommonService;
import ig.mini.product.khata.ui.pojo.SellForm;

@Controller
public class SellUiController {

	@Autowired
	private ProCommonService proCommonService;

	@RequestMapping("/sell/viewSells")
	public ModelAndView viewPurchases() {
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
	public ModelAndView createManufactureSubmit(@ModelAttribute SellForm form) {
		try {
			ModelAndView modelAndView = createSellModelAndView(form);
			form.getSellProductMaps().add(new ProSellProductMap());
			modelAndView.addObject("form", form);
			return modelAndView;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/sell/createSell/submit", method = RequestMethod.POST)
	public String createSellSubmit(@ModelAttribute SellForm form) {
		try {
			form.getSellProductMaps().add(new ProSellProductMap());
			System.out.println("+++++++++++++++++ createSellSubmit");
			return "sell";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "sell";
		}
	}

}
