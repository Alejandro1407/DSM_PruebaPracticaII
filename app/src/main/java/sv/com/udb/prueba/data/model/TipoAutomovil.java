package sv.com.udb.prueba.data.model;

import sv.com.udb.prueba.annotations.Column;
import sv.com.udb.prueba.annotations.Entity;
import sv.com.udb.prueba.annotations.Id;

@Entity(name = "tipo_automovil")
public class TipoAutomovil {

    @Id
    private Integer id;

    @Column
    private String descripcion;
}
