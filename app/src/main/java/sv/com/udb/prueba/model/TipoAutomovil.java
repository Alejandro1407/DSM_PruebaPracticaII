package sv.com.udb.prueba.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@DatabaseTable(tableName = "tipo_automovil")
public class TipoAutomovil {

    @DatabaseField(id = true)
    private Integer id;

    @NonNull
    @DatabaseField
    private String descripcion;
}
