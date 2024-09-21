package org.iplacex.proyectos.discografia.discos;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("disco")
public class Disco {
    @Id
    public String _id;
    public String titulo;
    public String idArtista;
    public int anioPublicacion;
    public String genero;
    public boolean esEnVivo;
    
}