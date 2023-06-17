CREATE DATABASE hotel_alura;

USE hotel_alura;

CREATE TABLE usuarios(
id INT AUTO_INCREMENT PRIMARY KEY,
usuario VARCHAR(50),
clave VARCHAR(100),
tipo_usuario VARCHAR(50))
ENGINE=InnoDB;

CREATE TABLE huespedes(
id INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(100),
apellido VARCHAR(100),
fecha_de_nacimiento DATE,
nacionalidad VARCHAR(50),
telefono VARCHAR(10),
id_reserva INT)
ENGINE=InnoDB;

CREATE TABLE reservas(
id INT AUTO_INCREMENT PRIMARY KEY,
fecha_entrada DATE,
fecha_salida DATE,
valor FLOAT,
forma_de_pago VARCHAR(100))
ENGINE=InnoDB;

ALTER TABLE huespedes ADD FOREIGN KEY(id_reserva) REFERENCES reservas(id);

INSERT INTO usuarios(usuario,clave,tipo_usuario) VALUES ('admin','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','Administrador');