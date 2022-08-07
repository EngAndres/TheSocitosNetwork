USE socitos_net;

CREATE TABLE IF NOT EXISTS pais(
	id_pais INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(20) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS zona_horaria(
	id_zona INT AUTO_INCREMENT PRIMARY KEY, 
    nombre VARCHAR(20) UNIQUE NOT NULL, 
    variacion INT NOT NULL
);

CREATE TABLE IF NOT EXISTS hobby(
	id_hobby INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS ubicacion(
	id_ubicacion INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    pais_fk INT NOT NULL,
    FOREIGN KEY(pais_fk) REFERENCES pais(id_pais)
);

CREATE TABLE IF NOT EXISTS usuario(
	id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    nickname VARCHAR(30) UNIQUE NOT NULL,
    contrasenia VARCHAR(30) NOT NULL,
    fec_nacimiento DATE NOT NULL,
    descripcion VARCHAR(150) DEFAULT "",
    email VARCHAR(40) UNIQUE NOT NULL, 
    ubicacion_fk INT NOT NULL,
    zona_horaria_fk INT NOT NULL,
    FOREIGN KEY(ubicacion_fk) REFERENCES ubicacion(id_ubicacion),
    FOREIGN KEY(zona_horaria_fk) REFERENCES zona_horaria(id_zona)
);

CREATE TABLE IF NOT EXISTS hobby_usuario(
	id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_fk INT NOT NULL,
    hobby_fk INT NOT NULL,
    FOREIGN KEY(usuario_fk) REFERENCES usuario(id_usuario),
    FOREIGN KEY(hobby_fk) REFERENCES hobby(id_hobby)
);

CREATE TABLE IF NOT EXISTS novedad(
	id_novedad INT AUTO_INCREMENT PRIMARY KEY,
    contenido VARCHAR(200) NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	usuario_fk INT NOT NULL,
    FOREIGN KEY(usuario_fk) REFERENCES usuario(id_usuario)
);

CREATE TABLE IF NOT EXISTS respuesta(
	id_respuesta INT AUTO_INCREMENT PRIMARY KEY,
    contenido VARCHAR(200) NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	novedad_fk INT NOT NULL,
    usuario_fk INT NOT NULL,
    FOREIGN KEY(novedad_fk) REFERENCES novedad(id_novedad),
    FOREIGN KEY(usuario_fk) REFERENCES usuario(id_usuario)
);
