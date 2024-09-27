package za.co.kemtech.agrigate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import za.co.kemtech.agrigate.entity.Farmer;
import za.co.kemtech.agrigate.services.FarmerService;

@Controller
@RequestMapping("/agrigate/v1")
public class FarmerController {

    @Autowired
    private FarmerService farmerService;

    @GetMapping("/farmers")
    public String showFarmers(Model model){
        model.addAttribute("farmers", farmerService.getAll());
        return "farmers";
    }

    @GetMapping("/farmers/view/{farmerId}")
    public String viewFarmer(@PathVariable Long farmerId, Model model){
        model.addAttribute("farmer", farmerService.findById(farmerId));
        return "farmer_view";
    }

    @GetMapping("/farmers/add")
    public String addFarmer(Model model){
        model.addAttribute("farmer", new Farmer());
        return "farmer_add";
    }
    @PostMapping("/farmers/add")
    public String addFarmer(@ModelAttribute Farmer farmer,
                            BindingResult result,
                            RedirectAttributes attributes){
        if(!result.hasErrors()){
            farmerService.add(farmer);
            attributes.addFlashAttribute("message", "Success");
            attributes.addFlashAttribute("alertClass", "alert-success");
            return "redirect:/farmers";
        }
        attributes.addFlashAttribute("message", "Failed");
        attributes.addFlashAttribute("alertClass", "alert-danger");
        return "farmer_add";
    }

}
