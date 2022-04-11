CREATE TABLE marcas (
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    nombre VARCHAR (45) NOT NULL UNIQUE MIN 3
);

CREATE TABLE colores (
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    descripcion VARCHAR (45) NOT NULL UNIQUE
);

CREATE TABLE tipo_automovil (
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    descripcion VARCHAR (45)
);

INSERT INTO tipo_automovil (descripcion) VALUES ("SEDAN"),("CAMION"),("PICK UP"),("CAMIONETA");

CREATE TABLE role (
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    role VARCHAR (45) NOT NULL UNIQUE
);

INSERT INTO ROLE (role) VALUES ("ADMIN"),("CLIENT");

CREATE TABLE usuario (
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    nombres VARCHAR (45) NOT NULL,
    apellidos VARCHAR (45) NOT NULL,
    email VARCHAR (45) NOT NULL,
    user VARCHAR (45) UNIQUE NOT NULL,
    password VARCHAR (45) NOT NULL,
    fk_usuario_role_idx INT,
    FOREIGN KEY (fk_usuario_role_idx) REFERENCES role (id)
);

CREATE TABLE automovil (
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    modelo VARCHAR (45),
    numero_vin VARCHAR (45),
    numero_chasis VARCHAR (45),
    numero_motor VARCHAR (45),
    numero_asientos INTEGER,
    anio INTEGER,
    capacidad_asientos INT,
    precio FLOAT (10,2),
    uri_img VARCHAR (45),
    descripcion VARCHAR (45),
    fk_automovil_marcas_idx INT,
    fk_automovil_tipo_automovil INT,
    fk_automovil_colores INT,
    FOREIGN KEY (fk_automovil_marcas_idx) REFERENCES marcas (id),
    FOREIGN KEY (fk_automovil_tipo_automovil) REFERENCES tipo_automovil (id),
    FOREIGN KEY (fk_automovil_colores) REFERENCES colores (id)
);

CREATE TABLE favoritos_automovil (
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    fk_favoritos_automovil_usuario_idx INT,
    fk_favoritos_automovil_automovil_idx INT,
    fecha_agregado TIMESTAMP,
    FOREIGN KEY (fk_favoritos_automovil_usuario_idx) REFERENCES usuario (id),
    FOREIGN KEY (fk_favoritos_automovil_automovil_idx) REFERENCES automovil (id)
);
