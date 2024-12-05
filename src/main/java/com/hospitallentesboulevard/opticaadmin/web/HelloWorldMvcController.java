package com.hospitallentesboulevard.opticaadmin.web;

import com.hospitallentesboulevard.opticaadmin.utils.URLConstantsControllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(URLConstantsControllers.HELLO_WORLD_BASE_MVC)
public class HelloWorldMvcController {

    @GetMapping
    public String greetings(Model model) {
        model.addAttribute("message", "¡Hola mundo!");
        return "hello_world";
    }

}
