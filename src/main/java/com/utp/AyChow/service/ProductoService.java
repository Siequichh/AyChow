package com.utp.AyChow.service;

import com.utp.AyChow.entity.Producto;
import com.utp.AyChow.repository.ProductoRepository;
import jakarta.persistence.EntityNotFoundException;
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

    @Transactional
    public Producto editarProducto(Long id, Producto productoActualizado) {
        // Verifica si el producto existe
        if (!productoRepository.existsById(id)) {
            throw new EntityNotFoundException("Producto no encontrado con ID: " + id);
        }

        productoActualizado.setIdProducto(id);
        return productoRepository.save(productoActualizado);
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

    public void eliminarProducto(Long idProducto) {
        // Verifica si el producto existe antes de eliminarlo
        if (productoRepository.existsById(idProducto)) {
            productoRepository.deleteById(idProducto);
        } else {
            throw new EntityNotFoundException("Producto no encontrado con ID: " + idProducto);
        }
    }

    // Método para obtener productos por marca
    @Transactional
    public List<Producto> getProductosPorMarca(String marca) {
        List<Producto> productos = productoRepository.findByMarca(marca);
        System.out.println(productos);  // Para ver qué productos se están devolviendo
        return productos;
    }

}
