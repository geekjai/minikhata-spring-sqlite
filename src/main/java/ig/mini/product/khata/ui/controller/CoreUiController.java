package ig.mini.product.khata.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ig.mini.product.khata.db.entity.ProPurchase;
import ig.mini.product.khata.services.ProCommonService;
import ig.mini.product.khata.ui.pojo.ManufactureProduct;
import ig.mini.product.khata.ui.pojo.User;

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
		return modelAndView;
	}

	@RequestMapping("/purchases/editPurchase/{id}")
	public ModelAndView editPurchase(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("purchase");
		modelAndView.addObject("fragment", "editPurchase");
		modelAndView.addObject("formTitle", "Edit Purchase");
		modelAndView.addObject("products", proCommonService.findAllProduct());
		modelAndView.addObject("purchase", proCommonService.findByPurchaseId(id));
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

	@RequestMapping(value = "manufacture/editManufacture/submit", method = RequestMethod.POST)
	public String editManufactureSubmit(@ModelAttribute ManufactureProduct form) {
		try {
			proCommonService.updateManufacture(form);
			return "redirect:/manufacture/viewManufactures";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/manufacture/createIncense";
		}
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute User user) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user-data");
		modelAndView.addObject("user", user);
		return modelAndView;
	}
}