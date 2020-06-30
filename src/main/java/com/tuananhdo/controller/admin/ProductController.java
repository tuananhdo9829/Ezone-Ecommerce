package com.tuananhdo.controller.admin;

import com.tuananhdo.entity.Product;
import com.tuananhdo.model.ProductDTO;
import com.tuananhdo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/addProduct")
    public String addProduct(Model map) {
        map.addAttribute("product", new ProductDTO());
        return "admin/addProduct";
    }

    @GetMapping(value = "/403")
    public String access() {
        return "admin/403";
    }

    @PostMapping(value = "/save")
    public String addProduct(@Valid @ModelAttribute(name = "product") MultipartFile file, Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/addProduct";
        } else {
            try {
                product.setImageUrl(file.getOriginalFilename());
                File newFile = new File("/Users/TuanAnhDo/Documents/MyProject/src/main/resources/static/web/img/product/" + file.getOriginalFilename());
                FileOutputStream fileOutputStream = new FileOutputStream(newFile);
                fileOutputStream.write(file.getBytes());
                fileOutputStream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            productService.saveProduct(product);
        }
        return "redirect:/admin/home";
    }


    @GetMapping(value = "/editProduct/{id}")
    public String editProduct(ModelMap map, @PathVariable(name = "id") int id, ProductDTO productDTO) {
        map.addAttribute("product", productService.getProductById(id));
        map.addAttribute("product",productDTO);
        return "admin/editProduct";
    }

    @PostMapping(value = "/editProduct")
    public String editProduct(@ModelAttribute(name = "product")Product product,MultipartFile file) {
        try {
            product.setImageUrl(file.getOriginalFilename());
            File newFile = new File("/Users/TuanAnhDo/Documents/MyProject/src/main/resources/static/web/img/product/" + file.getOriginalFilename());
            FileOutputStream fileOutputStream = new FileOutputStream(newFile);
            fileOutputStream.write(file.getBytes());
            fileOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        productService.saveProduct(product);

        return "redirect:/admin/home";
    }



    @GetMapping(value = "/deleteProduct/{id}")
    public String delete(@PathVariable(name = "id") int id) {
        productService.deleteProduct(id);
        return "redirect:/admin/home";
    }

    @GetMapping(value = "/logout-admin")
    public String logoutAdmin(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }
}
