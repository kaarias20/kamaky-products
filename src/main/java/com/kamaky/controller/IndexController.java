package com.kamaky.controller;

import com.kamaky.service.CategoriaService;
import com.kamaky.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final CategoriaService categoriaService;
    private final ProductoService productoService;

    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("categorias", categoriaService.getCategorias(true));
        model.addAttribute("productos", productoService.getProductosDestacados());
        model.addAttribute("idCategoriaSeleccionada", null);
        return "index";
    }
}
