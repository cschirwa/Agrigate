package za.co.kemtech.agrigate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import za.co.kemtech.agrigate.entity.Farm;
import za.co.kemtech.agrigate.services.FarmService;

@Controller
@RequestMapping("/agrigate/v1")
public class FarmController {

    @Autowired
    private FarmService farmService;

    @GetMapping("/farms/view/{farmId}")
    public String viewFarms(final Long farmId, Model model){
        model.addAttribute("farm", farmService.findById(farmId));
        return "farm_view";
    }

    @PostMapping("/farms/add")
    public String addFarm(@ModelAttribute Farm farm,
                          BindingResult result,
                          RedirectAttributes attributes){
        if(!result.hasErrors()){
            farmService.add(farm);
            attributes.addFlashAttribute("message", "Success");
            attributes.addFlashAttribute("alertClass", "alert-success");
            return "redirect:/farms";
        }
        attributes.addFlashAttribute("message", "Failed");
        attributes.addFlashAttribute("alertClass", "alert-danger");
        return "farm_add";
    }
}
