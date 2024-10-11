package com.utp.AyChow.service;

import com.utp.AyChow.entity.Producto;
import com.utp.AyChow.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getAllProductos(){
        return productoRepository.findAll();

    }
    @Transactional
    public Producto guardarProducto(Producto producto){
        return productoRepository.save(producto);
    }

    public Producto getProductoById(Long id) {
        return productoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }
    @Transactional
    public List<Producto> getProductosConRatingMayorA(float rating) {
        List<Producto> productos = productoRepository.findByRatingGreaterThanEqual(rating);
        System.out.println("Mejores Productos: " + productos);
        return productos;
    }
    @Transactional
    public List<Producto> getProductosConRatingMenorOIgualA(float rating) {
        return productoRepository.findByRatingLessThanEqual(rating);
    }

    public List<Producto> getProductosPorNombre(String nombre) {
        return productoRepository.findByNombre(nombre);
    }

}
