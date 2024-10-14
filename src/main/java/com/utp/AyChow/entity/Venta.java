package com.utp.AyChow.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<DetalleVenta> detalles;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private float precioTotal;

    public Venta() {}

    public Venta(Long idVenta, Usuario usuario, List<DetalleVenta> detalles, LocalDate fecha, float precioTotal) {
        this.idVenta = idVenta;
        this.usuario = usuario;
        this.detalles = detalles;
        this.fecha = fecha;
        this.precioTotal = precioTotal;
    }

    public Venta(Usuario usuario, List<DetalleVenta> detalles, LocalDate fecha, float precioTotal) {
        this.usuario = usuario;
        this.detalles = detalles;
        this.fecha = fecha;
        this.precioTotal = precioTotal;
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVenta> detalles) {
        this.detalles = detalles;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }
}
