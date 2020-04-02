package ouhk.comps380f.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ouhk.comps380f.dao.UserRepository;
import ouhk.comps380f.model.UserEntry;

@Controller
public class IndexController {

    @GetMapping
    public String index() {
        //return "redirect:/ticket/list";
        return "index";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
