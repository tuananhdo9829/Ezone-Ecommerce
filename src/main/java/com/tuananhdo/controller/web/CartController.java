package com.tuananhdo.controller.web;

import com.tuananhdo.entity.Product;
import com.tuananhdo.model.OrderDTO;
import com.tuananhdo.model.OrderItemDTO;
import com.tuananhdo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/cart")
    public String cart(ModelMap map,HttpSession session){
        map.put("totalItem",totalItem(session));
        return "web/cart";
    }

    @GetMapping(value = "/cart/{productId}")
    public String productCart(@PathVariable(name = "productId") int productId, HttpSession session){
            Product product = productService.getProductById(productId);
            if (session.getAttribute("cart")==null){
                OrderDTO orderDTO = new OrderDTO();
                OrderItemDTO orderItemDTO = new OrderItemDTO();
                orderItemDTO.setNumber(1);
                orderItemDTO.setProductDTO(product);
                List<OrderItemDTO> orderItemDTOS = new ArrayList<>();
                orderItemDTOS.add(orderItemDTO);
                orderDTO.setItemDTOS(orderItemDTOS);
                session.setAttribute("cart",orderDTO);
                session.setAttribute("order",orderDTO);
            }else {
                OrderDTO orderDTO = (OrderDTO) session.getAttribute("cart");
                List<OrderItemDTO> orderItemDTOS = orderDTO.getItemDTOS();
                    boolean checkItem = false;
                for (OrderItemDTO orderItemDTO : orderItemDTOS){
                    if (orderItemDTO.getProductDTO().getId() == productId){
                        orderItemDTO.setNumber(orderItemDTO.getNumber()+1);
                        checkItem=true;
                    }
                }
                if (!checkItem){
                    OrderItemDTO orderItemDTO = new OrderItemDTO();
                    orderItemDTO.setNumber(1);
                    orderItemDTO.setProductDTO(product);
                    orderItemDTOS.add(orderItemDTO);
                    session.setAttribute("cart",orderDTO);
                    session.setAttribute("order",orderDTO);
                }
            }
        return "redirect:/cart";
    }

    @GetMapping(value = "/viewCart")
    public String removeCart(HttpSession session){
        if (session.getAttribute("cart")!=null) {
            OrderDTO orderDTO = (OrderDTO) session.getAttribute("cart");
            session.setAttribute("order", orderDTO);
        }
        return "redirect:/cart";
    }

    @GetMapping(value = "/removeCart/{productId}")
    public String removeCart(@PathVariable(name = "productId") int productId, HttpSession session){
        if (session.getAttribute("cart")!=null){
            OrderDTO orderDTO = (OrderDTO) session.getAttribute("cart");
            List<OrderItemDTO> orderItemDTOS = orderDTO.getItemDTOS();
            orderItemDTOS.removeIf(orderItemDTO -> orderItemDTO.getProductDTO().getId() == productId);
            session.setAttribute("order",orderDTO);
        }
        return "redirect:/cart";
    }


    private long totalItem(HttpSession session) {
        OrderDTO orderDTO = (OrderDTO) session.getAttribute("cart");
        List<OrderItemDTO> orderItemDTOS = orderDTO.getItemDTOS();
        long totalItem = 0;
        for (OrderItemDTO orderItemDTO : orderItemDTOS){
            totalItem += orderItemDTO.getNumber() * orderItemDTO.getProductDTO().getPrice();
        }
        return totalItem;
    }


}
