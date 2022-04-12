package sv.com.udb.prueba.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@DatabaseTable(tableName = "favoritos_automovil")
public class Favoritos {

    @DatabaseField(id = true)
    private Integer id;

    @NonNull
    @DatabaseField(columnName = "fk_favoritos_automovil_usuario_idx",
            foreign = true,foreignAutoRefresh = true)
    private Usuario usuario;

    @NonNull
    @DatabaseField(columnName = "fk_favoritos_automovil_automovil_idx",
            foreign = true,foreignAutoRefresh = true)
    private Automovil automovil;

    @NonNull
    @DatabaseField(columnName = "fecha_agregado")
    private Date date;

}
