package com.utp.AyChow.controller;

import com.utp.AyChow.entity.Producto;
import com.utp.AyChow.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public String listarProductos(Model model) {
        List<Producto> mejoresProductos = productoService.getProductosConRatingMayorA(8.0f);
        List<Producto> productosEnDescuento = productoService.getProductosConRatingMenorOIgualA(4.0f);
        model.addAttribute("mejoresProductos", mejoresProductos);
        model.addAttribute("productosEnDescuento", productosEnDescuento);
        return "index";
    }

    @GetMapping("/imagen/{id}")
    public ResponseEntity<byte[]> obtenerImagen(@PathVariable Long id) {
        Producto producto = productoService.getProductoById(id);
        byte[] imagen = producto.getImagen();

        if (imagen == null || imagen.length == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(imagen);
    }

    @GetMapping("/upload")
    public String mostrarFormularioSubida() {
        return "upload";
    }

    @PostMapping("/upload")
    public String subirProducto(@RequestParam("nombre") String nombre, @RequestParam("marca") String marca,
                                @RequestParam("precio") float precio, @RequestParam("detalle") String detalle,
                                @RequestParam("cantidad") int cantidad, @RequestParam("rating") float rating,
                                @RequestParam("imagen") MultipartFile imagen) throws IOException {
        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setMarca(marca);
        producto.setPrecio(precio);
        producto.setDetalle(detalle);
        producto.setCantidad(cantidad);
        producto.setRating(rating);
        producto.setImagen(imagen.getBytes());

        productoService.guardarProducto(producto);
        return "redirect:/productos";
    }
}
