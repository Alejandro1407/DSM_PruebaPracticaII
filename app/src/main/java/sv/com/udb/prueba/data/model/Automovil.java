package sv.com.udb.prueba.data.model;

import sv.com.udb.prueba.annotations.Column;
import sv.com.udb.prueba.annotations.Entity;
import sv.com.udb.prueba.annotations.Id;

@Entity(name = "automovil")
public class Automovil {

    @Id
    private Integer id;

    @Column
    private String modelo;

    @Column(name = "numero_vin")
    private String numeroVin;

    @Column(name = "numero_chasis")
    private String numeroChasis;

    @Column(name = "numero_motor")
    private String numeroMotor;

    @Column(name = "numero_asientos")
    private Integer numeroAsientos;

    @Column(name = "anio")
    private Integer anio;

    @Column(name = "capacidad_asientos")
    private Integer capacidadAsientos;

    @Column
    private Float precio;

    @Column(name = "uri_img")
    private String uri;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fk_automovil_marcas_idx")
    private Integer fkMarcas;

    @Column(name = "fk_automovil_tipo_automovil")
    private Integer fkTipoAutomovil;

    @Column(name = "fk_automovil_colores")
    private Integer fkColores;

}
