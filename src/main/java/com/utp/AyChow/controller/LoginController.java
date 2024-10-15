package com.utp.AyChow.controller;

import com.utp.AyChow.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private PersonaService personaService;

    @PostMapping("/login")
    public String login(@RequestParam String correo, @RequestParam String password, Model model) {
        if (personaService.validarCredenciales(correo, password)) {
            // Redirigir a la página de inicio
            return "redirect:/productos";
        } else {
            // Devolver mensaje de error en el login
            model.addAttribute("error", "Credenciales incorrectas");
            return "login"; // Vista Thymeleaf para la página de login
        }
    }
}
