package com.tuananhdo.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    @GetMapping(value = "/checkout")
    public String checkout() {
        return "web/checkout";
    }
}
