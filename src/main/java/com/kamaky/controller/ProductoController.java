package com.kamaky.controller;

import com.kamaky.domain.Categoria;
import com.kamaky.domain.Producto;
import com.kamaky.service.CategoriaService;
import com.kamaky.service.ProductoService;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/producto")
public class ProductoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    @GetMapping("/catalogo")
    public String catalogo(
            @RequestParam(required = false) Integer idCategoria,
            Model model) {

        model.addAttribute("categorias", categoriaService.getCategorias(true));
        model.addAttribute(
                "productos", productoService.getProductosPorCategoria(idCategoria));
        model.addAttribute("idCategoriaSeleccionada", idCategoria);

        return "producto/catalogo";
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        Producto producto = new Producto();
        producto.setCategoria(new Categoria());

        model.addAttribute("producto", producto);
        model.addAttribute("productos", productoService.getProductos(false));
        model.addAttribute("categorias", categoriaService.getCategorias(true));

        return "producto/listado";
    }

    @PostMapping("/guardar")
    public String guardar(
            @Valid @ModelAttribute("producto") Producto producto,
            BindingResult resultado,
            @RequestParam(value = "imagenFile", required = false)
                    MultipartFile imagenFile,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (resultado.hasErrors()) {
            model.addAttribute("productos", productoService.getProductos(false));
            model.addAttribute("categorias", categoriaService.getCategorias(true));
            return "producto/listado";
        }

        try {
            productoService.save(producto, imagenFile);
            redirectAttributes.addFlashAttribute(
                    "exito", "El producto se agregó correctamente.");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute(
                    "error", "No se pudo guardar el producto.");
        }

        return "redirect:/producto/listado";
    }
}