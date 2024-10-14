package com.utp.AyChow.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Admin extends Persona {

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

    public Admin(Rol rol) {
        this.rol = rol;
    }

    public Admin(Long idPersona, String correo, String nombre, String apellido, String password, LocalDate fechaDeRegistro, Rol rol) {
        super(idPersona, correo, nombre, apellido, password, fechaDeRegistro);
        this.rol = rol;
    }

    public Admin(String correo, String nombre, String apellido, String password, LocalDate fechaDeRegistro, Rol rol) {
        super(correo, nombre, apellido, password, fechaDeRegistro);
        this.rol = rol;
    }
}
