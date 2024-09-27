package za.co.kemtech.agrigate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import za.co.kemtech.agrigate.entity.userdomain.User;

@Controller
@RequestMapping
public class HomeController {

    @GetMapping("/login")
    public String home(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user){
        return "redirect:/index";
    }

    @GetMapping("/dashboard")
    public String showDash() {
        return "index";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login?logout";
    }
}
