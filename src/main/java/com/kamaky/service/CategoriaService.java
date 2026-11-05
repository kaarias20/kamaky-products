package com.kamaky.service;

import com.kamaky.domain.Categoria;
import com.kamaky.repository.CategoriaRepository;
import com.kamaky.repository.ProductoRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;

    @Transactional(readOnly = true)
    public List<Categoria> getCategorias(boolean soloActivas) {
        if (soloActivas) {
            return categoriaRepository.findByActivaTrueOrderByNombreAsc();
        }
        return categoriaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Categoria> getCategoria(Integer idCategoria) {
        return categoriaRepository.findById(idCategoria);
    }

    @Transactional
    public void save(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    @Transactional
    public void delete(Integer idCategoria) {
        if (!categoriaRepository.existsById(idCategoria)) {
            throw new IllegalArgumentException(
                    "La categoría no existe: " + idCategoria);
        }

        if (productoRepository.existsByCategoriaIdCategoria(idCategoria)) {
            throw new IllegalStateException(
                    "No se puede eliminar una categoría que tiene productos relacionados.");
        }

        categoriaRepository.deleteById(idCategoria);
    }
}
