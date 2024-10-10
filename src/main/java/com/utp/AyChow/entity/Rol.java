package com.utp.AyChow.entity;

import jakarta.persistence.*;
import jakarta.persistence.GenerationType;

import java.util.List;


@Entity
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRol;
    private String nombreRol;
    @OneToMany(mappedBy = "rol")
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "rol")
    private List<Admin> admins;

    public Rol() {}

    public Rol(int idRol, String nombreRol, List<Usuario> usuarios, List<Admin> admins) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
        this.usuarios = usuarios;
        this.admins = admins;
    }

    public Rol(String nombreRol, List<Usuario> usuarios, List<Admin> admins) {
        this.nombreRol = nombreRol;
        this.usuarios = usuarios;
        this.admins = admins;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(List<Admin> admins) {
        this.admins = admins;
    }
}
