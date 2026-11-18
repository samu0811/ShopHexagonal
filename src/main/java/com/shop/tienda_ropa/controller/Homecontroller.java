package com.shop.tienda_ropa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class Homecontroller {

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
