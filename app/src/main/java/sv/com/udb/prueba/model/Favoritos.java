package sv.com.udb.prueba.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DatabaseTable(tableName = "favoritos_automovil")
public class Favoritos {

    @DatabaseField(id = true)
    private Integer id;

    @DatabaseField(columnName = "fk_favoritos_automovil_usuario_idx",foreign = true)
    private Usuario usuario;

    @DatabaseField(columnName = "fk_favoritos_automovil_automovil_idx",foreign = true)
    private Automovil automovil;

    @DatabaseField(columnName = "fecha_agregado")
    private LocalDate localDate;

}
