package com.kamaky.controller;

import com.kamaky.domain.SolicitudPersonalizada;
import com.kamaky.service.SolicitudPersonalizadaService;
import jakarta.validation.Valid;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/solicitud")
public class SolicitudPersonalizadaController {

    private final SolicitudPersonalizadaService solicitudService;
    private final MessageSource messageSource;

    //HU-009: formulario para que el cliente pida un producto personalizado
    @GetMapping("/nueva")
    public String nueva() {
        return "solicitud/nueva";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid SolicitudPersonalizada solicitud, BindingResult resultado,
            RedirectAttributes redirectAttributes) {
        if (resultado.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", texto("solicitud.error.datos"));
        } else {
            try {
                solicitudService.registrar(solicitud);
                redirectAttributes.addFlashAttribute("exito", texto("solicitud.enviada"));
            } catch (IllegalArgumentException e) {
                redirectAttributes.addFlashAttribute("error", texto("solicitud.error.datos"));
            }
        }
        return "redirect:/solicitud/nueva";
    }

    //Listado para el administrador (apoyo a HU-019)
    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("solicitudes", solicitudService.getSolicitudes());
        return "solicitud/listado";
    }

    private String texto(String clave) {
        return messageSource.getMessage(clave, null, Locale.getDefault());
    }
}
