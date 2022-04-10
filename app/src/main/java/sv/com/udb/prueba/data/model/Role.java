package sv.com.udb.prueba.data.model;

import sv.com.udb.prueba.annotations.Column;
import sv.com.udb.prueba.annotations.Entity;
import sv.com.udb.prueba.annotations.Id;

@Entity(name = "role")
public class Role {

    @Id
    private Integer id;

    @Column
    private String role;

}
