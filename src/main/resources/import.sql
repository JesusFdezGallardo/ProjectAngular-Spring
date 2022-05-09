insert INTO roles (nombre) VALUES ("Alumno"); 
insert INTO roles (nombre) VALUES ("Administrador"); 
insert INTO roles (nombre) VALUES ("Profesor"); 

insert into usuarios (rol_id, apellido, correo, nombre, pass) VALUES (1, 'Fernandez', 'jesus@gmail.com', 'Jesus', 'jesus123');
insert into usuarios (rol_id, apellido, correo, nombre, pass) VALUES (1, 'Fernandez', 'pepe@gmail.com', 'Pepe', 'pepe123');
insert into usuarios (rol_id, apellido, correo, nombre, pass) VALUES (1, 'Fernandez', 'laura@gmail.com', 'Laura', 'laura123');
insert into usuarios (rol_id, apellido, correo, nombre, pass) VALUES (1, 'Fernandez', 'ramon@gmail.com', 'Ramon', 'ramon123');
insert into usuarios (rol_id, apellido, correo, nombre, pass) VALUES (1, 'Fernandez', 'alex@gmail.com', 'Alex', 'alex123');
insert into usuarios (rol_id, apellido, correo, nombre, pass) VALUES (1, 'Fernandez', 'izan@gmail.com', 'Izan', 'izan123');
insert into usuarios (rol_id ,apellido, correo, nombre, pass) VALUES (2, 'Fernandez', 'carla@gmail.com', 'Carla', 'carla123');
insert into usuarios (rol_id, apellido, correo, nombre, pass) VALUES (2, 'Fernandez', 'leyre@gmail.com', 'Leyre', 'leyre123');
insert into usuarios (rol_id, apellido, correo, nombre, pass) VALUES (2, 'Fernandez', 'axel@gmail.com', 'Axel', 'axel123');
insert into usuarios (rol_id, apellido, correo, nombre, pass) VALUES (3, 'Fernandez', 'jose@gmail.com', 'Jose', 'jose123');

insert INTO asignaturas (nombre) VALUES ("Desarrollo de Interfaces"); 
insert INTO asignaturas (nombre) VALUES ("Bases de Datos");
insert INTO asignaturas (nombre) VALUES ("Acceso a Datos");
insert INTO asignaturas (nombre) VALUES ("Sistemas y Procesos");

insert INTO asignaturas_alumnos (id_usuario, id_asignatura) VALUES (1,1); 
insert INTO asignaturas_alumnos (id_usuario, id_asignatura) VALUES (2,1); 
insert INTO asignaturas_alumnos (id_usuario, id_asignatura) VALUES (3,1); 
insert INTO asignaturas_alumnos (id_usuario, id_asignatura) VALUES (4,1); 
insert INTO asignaturas_alumnos (id_usuario, id_asignatura) VALUES (1,2); 
insert INTO asignaturas_alumnos (id_usuario, id_asignatura) VALUES (1,3); 
insert INTO asignaturas_alumnos (id_usuario, id_asignatura) VALUES (1,4); 

insert INTO asignaturas_profesores (id_usuario, id_asignatura) values (10, 1); 
insert INTO asignaturas_profesores (id_usuario, id_asignatura) values (10, 2); 
insert INTO asignaturas_profesores (id_usuario, id_asignatura) values (10, 3); 
insert INTO asignaturas_profesores (id_usuario, id_asignatura) values (10, 4); 

