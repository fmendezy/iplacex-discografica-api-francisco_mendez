package org.iplacex.proyectos.discografia.discos;

import org.iplacex.proyectos.discografia.artistas.IArtistaRepository;
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
public class DiscoController {

    @Autowired
    private IDiscoRepository discoRepository;

    @Autowired
    private IArtistaRepository artistaRepository;

    @PostMapping(
        value = "/disco",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Disco> handleInsertDiscoRequest(@RequestBody Disco disco) {
        if (!artistaRepository.existsById(disco.idArtista)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Disco savedDisco = discoRepository.save(disco);
        return new ResponseEntity<>(savedDisco, HttpStatus.CREATED);
    }

    @GetMapping(
        value = "/discos",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Disco>> handleGetDiscosRequest() {
        List<Disco> discos = discoRepository.findAll();
        return new ResponseEntity<>(discos, HttpStatus.OK);
    }

    @GetMapping(
        value = "/disco/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Disco> handleGetDiscoRequest(@PathVariable String id) {

        Optional<Disco> disco = discoRepository.findById(id);

        return disco.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(

        value = "/disco/{id}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Disco> handleUpdateDiscoRequest(

        @PathVariable("id") String id,
        @RequestBody Disco disco

    ) {
        if (!discoRepository.existsById(id)) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        disco._id = id;
        Disco updatedDisco = discoRepository.save(disco);

        return new ResponseEntity<>(updatedDisco, HttpStatus.OK);
    }

    @DeleteMapping(

        value = "/disco/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE

    )
    public ResponseEntity<Void> handleDeleteDiscoRequest(@PathVariable String id) {

        if (discoRepository.existsById(id)) {
            discoRepository.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }
}
