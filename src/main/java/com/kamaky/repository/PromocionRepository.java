package com.kamaky.repository;

import com.kamaky.domain.Promocion;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromocionRepository extends JpaRepository<Promocion, Integer> {

    //Para el administrador: todas, de la más reciente a la más antigua
    List<Promocion> findAllByOrderByFechaInicioDesc();

    //Para el cliente: activas y cuya fecha de hoy esté entre fechaInicio y fechaFin
    List<Promocion> findByActivaTrueAndFechaInicioLessThanEqualAndFechaFinGreaterThanEqualOrderByFechaFinAsc(
            LocalDate hoyInicio, LocalDate hoyFin);
}
