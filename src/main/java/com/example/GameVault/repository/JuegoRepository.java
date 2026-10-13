package com.example.GameVault.repository;

import com.example.GameVault.model.Juego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JuegoRepository extends JpaRepository<Juego, Long> {
    // Metodos gratis como insert(save), update, delete, find

    List<Juego> findByTituloContainingIgnoreCase();

    List<Juego> findByPrecioLessThan(Double precio);

    @Query("SELECT j FROM Juegos j ORDER BY j.id DESC")
    List<Juego> obtenerJuegosRecientes();

    @Query("SELECT j FROM Juegos j WHERE LOWER(j.descripcion) LIKE LOWER(CONCAT('%', :palabra, '%'))")
    List<Juego> buscarPorDescripcion(String palabra);

}
