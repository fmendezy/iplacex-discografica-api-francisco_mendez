package org.iplacex.proyectos.discografia.discos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface IDiscoRepository extends MongoRepository<Disco, String> {

    @Query("{ 'titulo': ?0 }")
    Disco findDiscoByTitulo(String titulo);

    @Query("{ 'idArtista': ?0 }")
    List<Disco> findDiscosByArtistaId(String idArtista);

    @Query("{ 'genero': ?0 }")
    List<Disco> findDiscosByGenero(String genero);

    @Query("{ 'anioPublicacion': { $gt: ?0 } }")
    List<Disco> findDiscosByAnioPublicacionGreaterThan(int anioPublicacion);

    @Query("{ 'esEnVivo': ?0 }")
    List<Disco> findDiscosByEsEnVivo(boolean esEnVivo);
}
