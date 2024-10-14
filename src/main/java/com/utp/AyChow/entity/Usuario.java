package com.utp.AyChow.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Usuario extends Persona {

    @ManyToOne
    @JoinColumn(name = "id_rol",nullable = false)
    private Rol rol;

    private String direccion;
    private String telefono;

    @OneToMany(mappedBy = "usuario")
    private List<Venta> ventas;


    public Usuario(Rol rol, String direccion, String telefono, List<Venta> ventas) {
        this.rol = rol;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ventas = ventas;
    }

    public Usuario(Long idPersona, String correo, String nombre, String apellido, String password, LocalDate fechaDeRegistro, Rol rol, String direccion, String telefono, List<Venta> ventas) {
        super(idPersona, correo, nombre, apellido, password, fechaDeRegistro);
        this.rol = rol;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ventas = ventas;
    }

    public Usuario(String correo, String nombre, String apellido, String password, LocalDate fechaDeRegistro, Rol rol, String direccion, String telefono, List<Venta> ventas) {
        super(correo, nombre, apellido, password, fechaDeRegistro);
        this.rol = rol;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ventas = ventas;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }
}
