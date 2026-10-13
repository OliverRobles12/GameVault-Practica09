package com.example.GameVault.service;

import com.example.GameVault.model.Juego;
import com.example.GameVault.repository.JuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class JuegoService {

    @Autowired
    private JuegoRepository juegoRepository;
    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    public List<Juego> obtenerTodos() {
        return juegoRepository.findAll();
    }

    // Recomendable regresar algun tipo de informacion de si el juego fue guardadp
    public void guardarJuego(Juego juego, MultipartFile portada) {

        String nombreArchivo = "default.png";
        if(portada.isEmpty()){
            nombreArchivo = guardarImagenLocal(portada);
            // Cloudinary
            // Storage de firebase
        }

        juego.setPortadaUrl(nombreArchivo);
        juegoRepository.save(juego);

    }

    private String guardarImagenLocal(MultipartFile portada) {

        try{
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // generamos un nombre único para evitar sobreescribir archivos con el mismo nombre
            String nombreArchivo = UUID.randomUUID().toString() + "_" + portada.getOriginalFilename();
            Path filePath = uploadPath.resolve(nombreArchivo);

            // Guardamos el archivos fisicamente en disco
            Files.copy(portada.getInputStream(), filePath);

            return nombreArchivo;

        } catch (IOException e) {
            e.printStackTrace();
            return "default.png";
            // En un proyecto real, manejamos una exepción apropiadamente (ej. redirigir con error)
        }

    }

    public List<Juego> buscarPorTitulo(String palabra) {
        if (palabra == null || palabra.trim().isEmpty()) {
            return obtenerTodos();
        }
        return juegoRepository.findByTituloContainingIgnoreCase(palabra);
    }

}
