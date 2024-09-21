package org.iplacex.proyectos.discografia.artistas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ArtistaController {

    @Autowired
    private IArtistaRepository artistaRepository;

    @PostMapping(
        value = "/artista",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Artista> handleInsertArtistaRequest(@RequestBody Artista artista) {

        Artista savedArtista = artistaRepository.save(artista);
        return new ResponseEntity<>(savedArtista, HttpStatus.CREATED);

    }

    @GetMapping(
        value = "/artistas",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Artista>> handleGetArtistasRequest() {

        List<Artista> artistas = artistaRepository.findAll();
        return new ResponseEntity<>(artistas, HttpStatus.OK);

    }

    @GetMapping(
        value = "/artista/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Artista> handleGetArtistaRequest(@PathVariable String id) {

        Optional<Artista> artista = artistaRepository.findById(id);
        return artista.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(
        value = "/artista/{id}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Artista> handleUpdateArtistaRequest(
        @PathVariable("id") String id,
        @RequestBody Artista artista
    ) {
        if (!artistaRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        artista._id = id; 
        Artista updatedArtista = artistaRepository.save(artista);

        return new ResponseEntity<>(updatedArtista, HttpStatus.OK);
    }

    @DeleteMapping(
        value = "/artista/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> handleDeleteArtistaRequest(@PathVariable String id) {
        if (artistaRepository.existsById(id)) {

            artistaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }
}
