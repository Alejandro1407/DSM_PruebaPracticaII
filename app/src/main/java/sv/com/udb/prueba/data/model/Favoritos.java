package sv.com.udb.prueba.data.model;

import java.time.LocalDate;

import sv.com.udb.prueba.annotations.Column;
import sv.com.udb.prueba.annotations.Entity;
import sv.com.udb.prueba.annotations.Id;

@Entity(name = "favoritos_automovil")
public class Favoritos {
    @Id
    private Integer id;

    @Column(name = "fk_favoritos_automovil_usuario_idx")
    private Integer fkUsuario;

    @Column(name = "fk_favoritos_automovil_automovil_idx")
    private Integer fkAutomovil;

    @Column(name = "fecha_agregado")
    private LocalDate localDate;

}
