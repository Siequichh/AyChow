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

    @GetMapping("/productosAdmin")
    public String mostrarproductos(Model model){
        List<Producto> productos = productoService.getAllProductos();
        model.addAttribute("listaProductos", productos);
        return "productosAdmin";
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
        return "redirect:/productos/productosAdmin";
    }

    @GetMapping("/editar/{idProducto}")
    public String mostrarFormularioEditar(@PathVariable Long idProducto, Model model) {
        Producto producto = productoService.getProductoById(idProducto);
        if (producto == null) {
            return "redirect:/productos";
        }
        model.addAttribute("producto", producto);
        return "actualizarProducto";
    }



    @PostMapping("/editar/{idProducto}")
    public String editarProducto(@PathVariable Long idProducto,
                                 @RequestParam("nombre") String nombre,
                                 @RequestParam("marca") String marca,
                                 @RequestParam("precio") float precio,
                                 @RequestParam("detalle") String detalle,
                                 @RequestParam("cantidad") int cantidad,
                                 @RequestParam("rating") float rating,
                                 @RequestParam(value = "imagen", required = false) MultipartFile imagen) throws IOException {

        Producto productoExistente = productoService.getProductoById(idProducto);
        productoExistente.setNombre(nombre);
        productoExistente.setMarca(marca);
        productoExistente.setPrecio(precio);
        productoExistente.setDetalle(detalle);
        productoExistente.setCantidad(cantidad);
        productoExistente.setRating(rating);


        if (imagen != null && !imagen.isEmpty()) {
            productoExistente.setImagen(imagen.getBytes());
        }

        productoService.editarProducto(idProducto, productoExistente);

        return "redirect:/productos/productosAdmin";
    }

    @PostMapping("/eliminar/{idProducto}")
    public String eliminarProducto(@PathVariable Long idProducto) {
        productoService.eliminarProducto(idProducto);
        return "redirect:/productos/productosAdmin";
    }

    @GetMapping("/por-marca")
    @ResponseBody
    public ResponseEntity<List<Producto>> listarProductosPorMarca(@RequestParam("marca") String marca) {
        List<Producto> productos = productoService.getProductosPorMarca(marca);
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build(); // Devuelve un 204 si no hay productos
        }
        return ResponseEntity.ok(productos); // Devuelve un 200 y la lista de productos
    }



}
