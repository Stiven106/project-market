package com.proyecto.market.persistence.crud;

import java.util.List;
import java.util.Optional;

import com.proyecto.market.persistence.entity.Producto;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer>{

    //Query de forma nativa
    //@Query(value = "SELECT * FROM productos WHERE id_categoria = ?", nativeQuery = true)
    //List<Producto> buscarPorCategoria(int idCategoria);

    //QueryMethods
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);
}
