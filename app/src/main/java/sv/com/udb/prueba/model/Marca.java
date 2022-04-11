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
@DatabaseTable(tableName = "marcas")
public class Marca {

    @DatabaseField(id = true,canBeNull = false)
    private Integer id;

    @NonNull
    @DatabaseField
    private String nombre;
}
