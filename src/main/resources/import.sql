insert INTO roles (nombre) VALUES ("ROLE_ALUMNO"); 
insert INTO roles (nombre) VALUES ("ROLE_ADMIN"); 
insert INTO roles (nombre) VALUES ("ROLE_PROFESOR"); 

insert into usuarios (usuario, apellido, correo, nombre, pass, activo) VALUES ('jesusf', 'Fernandez', 'jesus@gmail.com', 'Jesus', '$2a$10$nC30lb2H7Kw2kP6JcrHe7eX1JK/G3VQACMrTlhTxqHfklR.2gHhTK', 1);
insert into usuarios (usuario, apellido, correo, nombre, pass, activo) VALUES ('lauraf', 'Fernandez', 'laura@gmail.com', 'Laura', '$2a$10$nC30lb2H7Kw2kP6JcrHe7eX1JK/G3VQACMrTlhTxqHfklR.2gHhTK', 1);
insert into usuarios (usuario, apellido, correo, nombre, pass, activo) VALUES ('juanf', 'Fernandez', 'juan@gmail.com', 'Juan', '$2a$10$nC30lb2H7Kw2kP6JcrHe7eX1JK/G3VQACMrTlhTxqHfklR.2gHhTK', 1);
insert into usuarios (usuario, apellido, correo, nombre, pass, activo) VALUES ('pepef', 'Fernandez', 'pepe@gmail.com', 'Pepe', '$2a$10$nC30lb2H7Kw2kP6JcrHe7eX1JK/G3VQACMrTlhTxqHfklR.2gHhTK', 1);
insert into usuarios (usuario, apellido, correo, nombre, pass, activo) VALUES ('ramonf', 'Fernandez', 'ramon@gmail.com', 'Ramon', '$2a$10$nC30lb2H7Kw2kP6JcrHe7eX1JK/G3VQACMrTlhTxqHfklR.2gHhTK', 1);
insert into usuarios (usuario, apellido, correo, nombre, pass, activo) VALUES ('mariaf', 'Fernandez', 'maria@gmail.com', 'Maria', '$2a$10$nC30lb2H7Kw2kP6JcrHe7eX1JK/G3VQACMrTlhTxqHfklR.2gHhTK', 1);

insert into usuarios (usuario, apellido, correo, nombre, pass, activo) VALUES ('axelf', 'Fernandez', 'axel@gmail.com', 'Axel', '$2a$10$3nGpYeEmoobDCg6nrg0JquAT0btp1YIN9StJ2yg4d5lJdh6KYA0ey', 1);
insert into usuarios (usuario, apellido, correo, nombre, pass, activo) VALUES ('josef', 'Fernandez', 'jose@gmail.com', 'Jose', '$2a$10$PnB94Tqe2CpymYO.z3TvXuSdaBfP9KmkNUxormKkU4PbAyCK/dWIO', 1);

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (3, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (4, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (5, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (6, 1);

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (7, 2);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (8, 3);

insert INTO asignaturas (nombre) VALUES ("Desarrollo de Interfaces"); 
insert INTO asignaturas (nombre) VALUES ("Bases de Datos");
insert INTO asignaturas (nombre) VALUES ("Acceso a Datos");
insert INTO asignaturas (nombre) VALUES ("Sistemas y Procesos");

insert INTO asignaturas_alumnos (id_usuario, id_asignatura) VALUES (1,1); 
insert INTO asignaturas_alumnos (id_usuario, id_asignatura) VALUES (2,1); 
insert INTO asignaturas_alumnos (id_usuario, id_asignatura) VALUES (3,1); 
insert INTO asignaturas_alumnos (id_usuario, id_asignatura) VALUES (4,1); 
insert INTO asignaturas_alumnos (id_usuario, id_asignatura) VALUES (5,1); 
insert INTO asignaturas_alumnos (id_usuario, id_asignatura) VALUES (6,1); 

insert INTO asignaturas_alumnos (id_usuario, id_asignatura) VALUES (1,2); 
insert INTO asignaturas_alumnos (id_usuario, id_asignatura) VALUES (2,2); 
insert INTO asignaturas_alumnos (id_usuario, id_asignatura) VALUES (3,2); 
insert INTO asignaturas_alumnos (id_usuario, id_asignatura) VALUES (4,2); 
insert INTO asignaturas_alumnos (id_usuario, id_asignatura) VALUES (5,2); 
insert INTO asignaturas_alumnos (id_usuario, id_asignatura) VALUES (6,2); 

insert INTO asignaturas_alumnos (id_usuario, id_asignatura) VALUES (1,3); 
insert INTO asignaturas_alumnos (id_usuario, id_asignatura) VALUES (2,3); 
insert INTO asignaturas_alumnos (id_usuario, id_asignatura) VALUES (3,3); 
insert INTO asignaturas_alumnos (id_usuario, id_asignatura) VALUES (4,3); 
insert INTO asignaturas_alumnos (id_usuario, id_asignatura) VALUES (5,3); 
insert INTO asignaturas_alumnos (id_usuario, id_asignatura) VALUES (6,3); 

insert INTO asignaturas_alumnos (id_usuario, id_asignatura) VALUES (1,4); 
insert INTO asignaturas_alumnos (id_usuario, id_asignatura) VALUES (2,4); 
insert INTO asignaturas_alumnos (id_usuario, id_asignatura) VALUES (3,4); 
insert INTO asignaturas_alumnos (id_usuario, id_asignatura) VALUES (4,4); 
insert INTO asignaturas_alumnos (id_usuario, id_asignatura) VALUES (5,4); 
insert INTO asignaturas_alumnos (id_usuario, id_asignatura) VALUES (6,4); 

insert INTO asignaturas_profesores (id_usuario, id_asignatura) values (8, 1); 
insert INTO asignaturas_profesores (id_usuario, id_asignatura) values (8, 2); 
insert INTO asignaturas_profesores (id_usuario, id_asignatura) values (8, 3); 
insert INTO asignaturas_profesores (id_usuario, id_asignatura) values (8, 4); 

insert INTO practicas (comentario, titulo) VALUES ("Comentario de texto 1", "Actividad 1"); 
insert INTO practicas (comentario, titulo) VALUES ("Comentario de texto 2", "Actividad 2"); 
insert INTO practicas (comentario, titulo) VALUES ("Comentario de texto 3", "Actividad 3"); 

insert into asignaturas_practicas (id_asignatura, id_practica) VALUES (1,1); 
insert into asignaturas_practicas (id_asignatura, id_practica) VALUES (1,2); 
insert into asignaturas_practicas (id_asignatura, id_practica) VALUES (1,3); 

INSERT INTO alumnos_practicas (practica_id, usuario_id) VALUES (1, 1); 
INSERT INTO alumnos_practicas (practica_id, usuario_id) VALUES (2, 1); 
INSERT INTO alumnos_practicas (practica_id, usuario_id) VALUES (3, 1); 
