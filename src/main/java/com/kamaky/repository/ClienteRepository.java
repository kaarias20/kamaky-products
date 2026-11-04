package com.kamaky.repository;

import com.kamaky.domain.Cliente;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    //Permite reutilizar un cliente que ya hizo un pedido o una solicitud antes
    Optional<Cliente> findByCorreo(String correo);
}
