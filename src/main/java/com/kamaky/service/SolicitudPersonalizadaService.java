package com.kamaky.service;

import com.kamaky.domain.Cliente;
import com.kamaky.domain.SolicitudPersonalizada;
import com.kamaky.repository.ClienteRepository;
import com.kamaky.repository.SolicitudPersonalizadaRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SolicitudPersonalizadaService {

    private final SolicitudPersonalizadaRepository solicitudRepository;
    private final ClienteRepository clienteRepository;

    @Transactional(readOnly = true)
    public List<SolicitudPersonalizada> getSolicitudes() {
        return solicitudRepository.findAllByOrderByFechaSolicitudDesc();
    }

    //HU-009: registra la solicitud; si el correo ya existe se reutiliza ese cliente
    @Transactional
    public void registrar(SolicitudPersonalizada solicitud) {
        //La fecha de entrega debe ser posterior a hoy
        if (!solicitud.getFechaEntrega().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de entrega debe ser posterior a hoy");
        }
        Cliente datosCliente = solicitud.getCliente();
        Cliente cliente;
        Optional<Cliente> clienteExistente = clienteRepository.findByCorreo(datosCliente.getCorreo());
        if (clienteExistente.isPresent()) {
            cliente = clienteExistente.get();
        } else {
            cliente = new Cliente();
        }
        cliente.setNombre(datosCliente.getNombre());
        cliente.setTelefono(datosCliente.getTelefono());
        cliente.setCorreo(datosCliente.getCorreo());
        cliente = clienteRepository.save(cliente);

        solicitud.setIdSolicitud(null);
        solicitud.setCliente(cliente);
        solicitud.setEstado("Pendiente");
        solicitud.setFechaSolicitud(LocalDateTime.now());
        solicitudRepository.save(solicitud);
    }
}
