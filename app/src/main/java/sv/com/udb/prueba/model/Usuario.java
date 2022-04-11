package sv.com.udb.prueba.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@DatabaseTable(tableName = "usuario")
@Data
public class Usuario {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField
    private String nombres;

    @DatabaseField
    private String apellidos;

    @DatabaseField
    private String email;

    @DatabaseField
    private String user;

    @DatabaseField
    private String password;

    @DatabaseField(columnName = "fk_usuario_role_idx",foreign = true)
    private Role role;
}
