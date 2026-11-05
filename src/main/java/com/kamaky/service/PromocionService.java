package com.kamaky.service;

import com.kamaky.domain.Promocion;
import com.kamaky.repository.PromocionRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PromocionService {

    private final PromocionRepository promocionRepository;

    //HU-016: el administrador ve todas las promociones
    @Transactional(readOnly = true)
    public List<Promocion> getPromociones() {
        return promocionRepository.findAllByOrderByFechaInicioDesc();
    }

    //HU-010: el cliente solo ve las activas y vigentes hoy
    @Transactional(readOnly = true)
    public List<Promocion> getPromocionesVigentes() {
        LocalDate hoy = LocalDate.now();
        return promocionRepository
                .findByActivaTrueAndFechaInicioLessThanEqualAndFechaFinGreaterThanEqualOrderByFechaFinAsc(hoy, hoy);
    }

    @Transactional(readOnly = true)
    public Optional<Promocion> getPromocion(Integer idPromocion) {
        return promocionRepository.findById(idPromocion);
    }

    //Inserta si idPromocion es null y actualiza si trae valor
    public void guardar(Promocion promocion) {
        if (promocion.getFechaFin().isBefore(promocion.getFechaInicio())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la de inicio");
        }
        promocionRepository.save(promocion);
    }

    public void eliminar(Integer idPromocion) {
        if (!promocionRepository.existsById(idPromocion)) {
            throw new IllegalArgumentException("No existe la promoción " + idPromocion);
        }
        promocionRepository.deleteById(idPromocion);
    }
}
