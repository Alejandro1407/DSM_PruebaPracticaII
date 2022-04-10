package sv.com.udb.prueba.data.model;

import sv.com.udb.prueba.annotations.Column;
import sv.com.udb.prueba.annotations.Entity;
import sv.com.udb.prueba.annotations.Id;

@Entity(name = "marcas")
public class Marcas {

    @Id
    private Integer id;

    @Column
    private String nombre;

    public Marcas(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

}
