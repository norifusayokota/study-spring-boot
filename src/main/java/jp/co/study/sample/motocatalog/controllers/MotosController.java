package jp.co.study.sample.motocatalog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MotosController {
    
    @RequestMapping("/test")
    public String test() {
        return "test";
    }
}
