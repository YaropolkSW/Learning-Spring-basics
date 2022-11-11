package com.spring.springboot.springbootapplication.controller;

import com.spring.springboot.springbootapplication.dto.ShopDTO;
import com.spring.springboot.springbootapplication.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ShopController {
    private final static String SHOW_ALL_SHOPS_HTML = "show-all-shops";

    @Autowired
    private ShopService shopService;

    @RequestMapping("/")
    public String getAllShops(final Model model) {
        final List<ShopDTO> shops = shopService.getAllShops();

        model.addAttribute("shops", shops);

        return SHOW_ALL_SHOPS_HTML;
    }
}
