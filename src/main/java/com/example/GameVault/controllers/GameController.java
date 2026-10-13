package com.example.GameVault.controllers;

import com.example.GameVault.model.Juego;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class GameController {

    private static List<Juego> juegosDb = new ArrayList<>();
    private static long idCounter = 1;


    static {
        // juegosDb.add(new Juego(idCounter++, "The Legend of Zelda", "Aventura épica", "zelnda.jpg"));
    }

    @GetMapping("/fragments-demo")
    public String fragmentDemo(){
        return "fragments-demo";
    }

    @GetMapping({"/", "/juegos"})
    public String listarJuegos(Model model) {
        model.addAttribute("juegos", juegosDb);
        return "juegos";
    }

    @GetMapping("/juegos/nuevo")
    public String mostrarFormulario() {
        return "formulario";
    }

    @PostMapping("/juegos")
    public String guardarJuego(@RequestParam("titulo") String titulo,
                               @RequestParam("descripcion") String descripcion,
                               @RequestParam("portada")MultipartFile portada) {
        // Imagen por defento si no suben nada


        // Verificamos si el archivo no está vacio
        if(!portada.isEmpty()){

        }

        // Juego nuevoJuego = new Juego(idCounter++, titulo, descripcion, nombreArchivo);
        // juegosDb.add(nuevoJuego);

        return "redirect:/juegos";

    }

}
