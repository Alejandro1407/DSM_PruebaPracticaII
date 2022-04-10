package sv.com.udb.prueba.data.model;

import sv.com.udb.prueba.annotations.Column;
import sv.com.udb.prueba.annotations.Entity;
import sv.com.udb.prueba.annotations.Id;

@Entity(name = "usuario")
public class Usuario {

    @Id
    private Integer id;

    @Column
    private String nombres;

    @Column
    private String apellidos;

    @Column
    private String email;

    @Column
    private String user;

    @Column
    private String password;

    @Column(name = "fk_usuario_role_idx")
    private Integer type;

    public Usuario(){}

    public Usuario(Integer id, String nombres, String apellidos, String email, String user, String password, Integer type) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.user = user;
        this.password = password;
        this.type = type;
    }

}
