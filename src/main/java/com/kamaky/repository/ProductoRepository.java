package com.kamaky.repository;

import com.kamaky.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> findByDisponibleTrueOrderByNombreAsc();

    List<Producto> findByCategoriaIdCategoriaAndDisponibleTrueOrderByNombreAsc(
            Integer idCategoria);

    List<Producto> findTop3ByDisponibleTrueOrderByIdProductoDesc();

    boolean existsByCategoriaIdCategoria(Integer idCategoria);
}
