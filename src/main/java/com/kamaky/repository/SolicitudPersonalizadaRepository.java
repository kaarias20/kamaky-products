package com.kamaky.repository;

import com.kamaky.domain.SolicitudPersonalizada;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudPersonalizadaRepository extends JpaRepository<SolicitudPersonalizada, Integer> {

    //Las más recientes primero
    List<SolicitudPersonalizada> findAllByOrderByFechaSolicitudDesc();
}
