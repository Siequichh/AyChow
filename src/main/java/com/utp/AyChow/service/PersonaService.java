package com.utp.AyChow.service;

import com.utp.AyChow.entity.Usuario;
import com.utp.AyChow.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean validarCredenciales(String correo, String password) {
        Usuario usuario = usuarioRepository.findByCorreo(correo);

        if (usuario != null) {
            // Comparación directa de contraseñas
            return usuario.getPassword().equals(password);
        }

        return false;
    }
}

