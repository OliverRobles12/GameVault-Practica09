package com.example.GameVault.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {

    @GetMapping("/fragments-demo")
    public String fragmentDemo(){
        return "fragments-demo";
    }

}
