package org.iplacex.proyectos.discografia.artistas;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

public interface IArtistaRepository extends MongoRepository<Artista, String> {

    @Query("{ 'nombre': ?0 }")
    Artista findArtistaByNombre(String nombre);

    @Query("{ 'estilos': ?0 }")
    List<Artista> findArtistasByEstilo(String estilo);

    @Query("{ 'anioFundacion': { $gt: ?0 } }")
    List<Artista> findArtistasByAnioFundacionGreaterThan(int anioFundacion);

    @Query("{ 'estaActivo': ?0 }")
    List<Artista> findArtistasByEstaActivo(boolean estaActivo);

    @Query("{ 'anioFundacion': { $gte: ?0, $lte: ?1 } }")
    List<Artista> findArtistasByAnioFundacionBetween(int anioInicio, int anioFin);
}