package com.tuananhdo.controller.admin;

import com.tuananhdo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/admin/user")
    public String list(Model map) {
        map.addAttribute("listUser", userService.listUser());
        return "admin/admin-home";
    }
}
