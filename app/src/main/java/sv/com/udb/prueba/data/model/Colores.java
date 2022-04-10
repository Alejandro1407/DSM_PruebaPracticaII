package sv.com.udb.prueba.data.model;

import sv.com.udb.prueba.annotations.Column;
import sv.com.udb.prueba.annotations.Entity;
import sv.com.udb.prueba.annotations.Id;

@Entity(name = "colores")
public class Colores {

    @Id
    private Integer id;

    @Column
    private String descripcion;

}
