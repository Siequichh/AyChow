package com.utp.AyChow.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

import java.util.List;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private float precio;

    @Column(length = 500)
    private String detalle;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] imagen;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    @DecimalMin(value = "1.0")
    @DecimalMax(value = "10.0")
    private float rating;

    @OneToMany(mappedBy = "producto")
    private List<DetalleVenta> detalles;

    public Producto() {}

    public Producto(Long idProducto, String nombre, String marca, float precio, String detalle, byte[] imagen, int cantidad, float rating, List<DetalleVenta> detalles) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.detalle = detalle;
        this.imagen = imagen;
        this.cantidad = cantidad;
        this.rating = rating;
        this.detalles = detalles;
    }

    public Producto(String nombre, String marca, float precio, String detalle, byte[] imagen, int cantidad, float rating, List<DetalleVenta> detalles) {
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.detalle = detalle;
        this.imagen = imagen;
        this.cantidad = cantidad;
        this.rating = rating;
        this.detalles = detalles;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVenta> detalles) {
        this.detalles = detalles;
    }
}
