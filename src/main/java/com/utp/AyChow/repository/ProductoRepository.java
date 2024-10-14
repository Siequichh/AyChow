package com.utp.AyChow.repository;

import com.utp.AyChow.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByRatingGreaterThanEqual(float rating);
    List<Producto> findByRatingLessThanEqual(float rating);
    List<Producto> findByNombre(String nombre);

}
