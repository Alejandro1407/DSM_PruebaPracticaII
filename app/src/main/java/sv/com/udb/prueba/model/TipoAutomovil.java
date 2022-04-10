package sv.com.udb.prueba.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tipo_automovil")
public class TipoAutomovil {

    @DatabaseField(id = true)
    private Integer id;

    @DatabaseField
    private String descripcion;
}
