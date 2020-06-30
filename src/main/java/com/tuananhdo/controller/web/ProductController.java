package com.tuananhdo.controller.web;

import com.tuananhdo.model.ProductDTO;
import com.tuananhdo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;
    @GetMapping(value = "/product-detail{id}")
    public String helloController(Model model, @PathVariable(name = "id") int id, ProductDTO productDTO) {
        model.addAttribute("product",productService.getProductById(id));
        model.addAttribute("listProducts",productDTO);
        return "web/product-details";
    }


    @GetMapping(value = "/product-detail")
    public String single() {
        return "web/product-details";
    }
    

}
