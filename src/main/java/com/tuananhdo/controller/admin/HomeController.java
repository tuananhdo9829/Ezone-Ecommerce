package com.tuananhdo.controller.admin;

import com.tuananhdo.entity.Product;
import com.tuananhdo.model.ProductDTO;
import com.tuananhdo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;


    @GetMapping(value = "/admin/home")
    public String list(Model map) {
        map.addAttribute("listProduct",productService.finAll());
        return "admin/list";
    }

    @GetMapping(value = "/chitietProduct/{id}")
    public String chitietProduct(Model map, @PathVariable(name = "id") int id) {
        map.addAttribute("products", productService.getProductById(id));
        return "admin/chitiet";
    }

    @GetMapping(value = "/addProduct")
    public String addProduct(Model map) {
        map.addAttribute("product", new ProductDTO());
        return "admin/add";
    }
    @GetMapping(value = "/403")
    public String access() {
        return "admin/403";
    }

    @PostMapping(value = "/save")
    public String addProduct(@ModelAttribute(name = "product")MultipartFile file, Product product) {

        try {
            product.setImageUrl(file.getOriginalFilename());
            File newFile = new File("/Users/TuanAnhDo/Documents/MyProject/src/main/resources/static/web/img/product/"+file.getOriginalFilename());
            FileOutputStream fileOutputStream = new FileOutputStream(newFile);
            fileOutputStream.write(file.getBytes());
            fileOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        productService.saveProduct(product);
        return "redirect:/admin/home";
    }


    @GetMapping(value = "/editProduct/{id}")
    public String editProduct(ModelMap map, @PathVariable(name = "id") int id ) {
        map.addAttribute("product", productService.getProductById(id));
        return "admin/edit";
    }



    @GetMapping(value = "/deleteProduct/{id}")
    public String delete(@PathVariable(name = "id") int id) {
        productService.deleteProduct(id);
        return "redirect:/admin/home";
    }
}
