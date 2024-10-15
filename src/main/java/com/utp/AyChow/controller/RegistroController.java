package com.utp.AyChow.controller;

import com.utp.AyChow.entity.Rol;
import com.utp.AyChow.entity.Usuario;
import com.utp.AyChow.service.RegistroService;
import com.utp.AyChow.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;

@Controller
public class RegistroController {

    @Autowired
    private RegistroService registroService;

    @Autowired
    private RolRepository rolRepository;

    @PostMapping("/registro")
    public ResponseEntity<String> registrarUsuario(@RequestParam String nombres,
                                                   @RequestParam String apellidos,
                                                   @RequestParam String correo,
                                                   @RequestParam String password) {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombres);
        nuevoUsuario.setApellido(apellidos);
        nuevoUsuario.setCorreo(correo);
        nuevoUsuario.setPassword(password);
        nuevoUsuario.setFechaDeRegistro(LocalDate.now());

        // Obtener el rol con ID 1
        Rol rol = rolRepository.findById(1).orElse(null);
        if (rol != null) {
            nuevoUsuario.setRol(rol); // Asignar rol al usuario
        } else {
            return new ResponseEntity<>("Error: Rol por defecto no encontrado.", HttpStatus.BAD_REQUEST);
        }

        // Guardar el usuario utilizando el servicio
        registroService.registrarUsuario(nuevoUsuario);

        return new ResponseEntity<>("Cuenta creada satisfactoriamente.", HttpStatus.OK);
    }
}
