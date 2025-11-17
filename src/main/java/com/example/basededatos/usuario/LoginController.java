package com.example.basededatos.usuario;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {
        System.out.println("CARGANDO LOGIN...");
        return "login";
    }
}
