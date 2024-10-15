package com.utp.AyChow.service;

import com.utp.AyChow.entity.Usuario;
import com.utp.AyChow.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistroService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void registrarUsuario(Usuario usuario) {
        // Aquí puedes agregar lógica de validación si es necesario
        // Por ejemplo, verificar si el correo ya está en uso

        // Guardar el usuario en la base de datos
        usuarioRepository.save(usuario);
    }
}
