package sv.com.udb.prueba.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DatabaseTable(tableName = "automovil")
public class Automovil {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField
    private String modelo;

    @DatabaseField(columnName = "numero_vin")
    private String numeroVin;

    @DatabaseField(columnName = "numero_chasis")
    private String numeroChasis;

    @DatabaseField(columnName = "numero_motor")
    private String numeroMotor;

    @DatabaseField(columnName = "numero_asientos")
    private Integer numeroAsientos;

    @DatabaseField(columnName = "anio")
    private Integer anio;

    @DatabaseField(columnName = "capacidad_asientos")
    private Integer capacidadAsientos;

    @DatabaseField
    private Float precio;

    @DatabaseField(columnName = "uri_img")
    private String uri;

    @DatabaseField
    private String descripcion;

    @DatabaseField(columnName = "fk_automovil_marcas_idx")
    private Integer fkMarcas;

    @DatabaseField(columnName = "fk_automovil_tipo_automovil")
    private Integer fkTipoAutomovil;

    @DatabaseField(columnName = "fk_automovil_colores")
    private Integer fkColores;

}
