package za.co.kemtech.agrigate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import za.co.kemtech.agrigate.entity.Client;
import za.co.kemtech.agrigate.services.ClientService;

import java.util.List;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/clients/view/{clientId}")
    public String getClient(@PathVariable final Long clientId, Model model){
        model.addAttribute("client", clientService.findById(clientId));
        return "client_view";
    }

    @GetMapping("/clients")
    public String getClients(Model model){
        List<Client> clientList = clientService.findAll();
        model.addAttribute("clientList", clientList);
        return "clients";
    }

    @GetMapping("/clients/add/")
    public String addClient(Model model){
        model.addAttribute("client", new Client());
        return "client_add";
    }

    @PostMapping("/clients/add")
    public String addClient(@ModelAttribute Client client,
                            BindingResult result,
                            RedirectAttributes attributes){
        if(!result.hasErrors()){
            clientService.add(client);
            attributes.addFlashAttribute("message", "Success");
            attributes.addFlashAttribute("alertClass", "alert-success");
            return "redirect:/clients";
        }
        attributes.addFlashAttribute("message", "Failed");
        attributes.addFlashAttribute("alertClass", "alert-danger");
        return "client_add";
    }
    @GetMapping("/client/edit/{$clientId}")
    public String editClient(@PathVariable Long clientId,
                            Model model){
        model.addAttribute("client", clientService.findById(clientId));
        return "client_edit";
    }
}
