package sv.com.udb.prueba.model;

import androidx.annotation.NonNull;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@DatabaseTable(tableName = "colores")
public class Color {

    @DatabaseField(id = true)
    private Integer id;

    @NonNull
    @DatabaseField
    private String descripcion;

}
