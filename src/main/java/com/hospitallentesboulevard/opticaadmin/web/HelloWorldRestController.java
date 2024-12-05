package com.hospitallentesboulevard.opticaadmin.web;

import com.hospitallentesboulevard.opticaadmin.utils.URLConstantsControllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(URLConstantsControllers.HELLO_WORLD_BASE_API)
public class HelloWorldRestController {

    @GetMapping
    public String getGreetings() {
        return "¡Hola mundo!";
    }

}
