package com.kamaky.controller;

import com.kamaky.domain.Categoria;
import com.kamaky.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("categorias", categoriaService.getCategorias(false));
        model.addAttribute("categoria", new Categoria());
        return "categoria/listado";
    }

    @GetMapping("/editar/{idCategoria}")
    public String editar(
            @PathVariable Integer idCategoria,
            Model model,
            RedirectAttributes redirectAttributes) {

        Categoria categoria = categoriaService.getCategoria(idCategoria)
                .orElse(null);

        if (categoria == null) {
            redirectAttributes.addFlashAttribute(
                    "error", "La categoría no existe.");
            return "redirect:/categoria/listado";
        }

        model.addAttribute("categoria", categoria);
        model.addAttribute("categorias", categoriaService.getCategorias(false));
        return "categoria/listado";
    }

    @PostMapping("/guardar")
    public String guardar(
            @Valid @ModelAttribute("categoria") Categoria categoria,
            BindingResult resultado,
            RedirectAttributes redirectAttributes) {

        if (resultado.hasErrors()) {
            redirectAttributes.addFlashAttribute(
                    "error", "Revise los datos de la categoría.");
            return "redirect:/categoria/listado";
        }

        try {
            categoriaService.save(categoria);
            redirectAttributes.addFlashAttribute(
                    "exito", "La categoría se guardó correctamente.");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute(
                    "error", "Ya existe una categoría con ese nombre.");
        }

        return "redirect:/categoria/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(
            @RequestParam Integer idCategoria,
            RedirectAttributes redirectAttributes) {

        try {
            categoriaService.delete(idCategoria);
            redirectAttributes.addFlashAttribute(
                    "exito", "La categoría se eliminó correctamente.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/categoria/listado";
    }
}
