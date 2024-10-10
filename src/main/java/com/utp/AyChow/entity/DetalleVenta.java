package com.utp.AyChow.entity;

import jakarta.persistence.*;

@Entity
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleVenta;

    @ManyToOne
    @JoinColumn(name = "id_venta", nullable = false)
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    private float precioPorProducto;

    public DetalleVenta() {
    }

    public DetalleVenta(Long idDetalleVenta, Venta venta, Producto producto, int cantidad, float precioPorProducto) {
        this.idDetalleVenta = idDetalleVenta;
        this.venta = venta;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioPorProducto = precioPorProducto;
    }

    public DetalleVenta(Venta venta, Producto producto, int cantidad, float precioPorProducto) {
        this.venta = venta;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioPorProducto = precioPorProducto;
    }

    public Long getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(Long idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioPorProducto() {
        return precioPorProducto;
    }

    public void setPrecioPorProducto(float precioPorProducto) {
        this.precioPorProducto = precioPorProducto;
    }
}
