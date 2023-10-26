package net.binarypaper.dockercomposebluegreen.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {

    @Value("${application.body-style}")
    private String bodyStyle;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("bodyStyle", bodyStyle);
        return "index";
    }
}