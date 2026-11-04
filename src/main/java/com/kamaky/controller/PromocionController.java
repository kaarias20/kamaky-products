package com.kamaky.controller;

import com.kamaky.domain.Promocion;
import com.kamaky.service.PromocionService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/promocion")
public class PromocionController {

    private final PromocionService promocionService;
    private final MessageSource messageSource;

    //HU-016: administración de promociones
    @GetMapping("/listado")
    public String listado(Model model) {
        var promociones = promocionService.getPromociones();
        model.addAttribute("promociones", promociones);
        model.addAttribute("totalPromociones", promociones.size());
        return "promocion/listado";
    }

    //HU-010: promociones vigentes para el cliente
    @GetMapping("/vigentes")
    public String vigentes(Model model) {
        model.addAttribute("promociones", promocionService.getPromocionesVigentes());
        return "promocion/vigentes";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Promocion promocion, BindingResult resultado,
            RedirectAttributes redirectAttributes) {
        if (resultado.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", texto("promocion.error.datos"));
        } else {
            try {
                promocionService.guardar(promocion);
                redirectAttributes.addFlashAttribute("exito", texto("promocion.guardada"));
            } catch (IllegalArgumentException e) {
                redirectAttributes.addFlashAttribute("error", texto("promocion.error.fechas"));
            }
        }
        return "redirect:/promocion/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer idPromocion, RedirectAttributes redirectAttributes) {
        try {
            promocionService.eliminar(idPromocion);
            redirectAttributes.addFlashAttribute("exito", texto("promocion.eliminada"));
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", texto("promocion.error.noExiste"));
        }
        return "redirect:/promocion/listado";
    }

    //Busca el texto en messages.properties según el idioma actual
    private String texto(String clave) {
        return messageSource.getMessage(clave, null, Locale.getDefault());
    }
}
