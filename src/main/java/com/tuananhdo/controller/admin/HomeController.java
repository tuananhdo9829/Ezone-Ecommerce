package com.tuananhdo.controller.admin;

import com.tuananhdo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;


    @GetMapping(value = "/admin/home")
    public String list(Model map) {
        map.addAttribute("listProducts", productService.finAll());
        return "admin/admin-home";
    }

}
