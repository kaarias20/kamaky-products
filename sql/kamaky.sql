/*
  Kamaky Products - Script de base de datos
  Se ejecuta completo en MySQL Workbench con un usuario administrador (root).
  Cada integrante agrega sus tablas y datos de prueba en la sección indicada.
*/

-- ================== ADMINISTRACIÓN ==================
drop database if exists kamaky;
drop user if exists 'usuario_kamaky'@'%';

create database kamaky
  default character set utf8mb4
  default collate utf8mb4_unicode_ci;

create user 'usuario_kamaky'@'%' identified by 'Kamaky_2026.';
grant all privileges on kamaky.* to 'usuario_kamaky'@'%';
flush privileges;

use kamaky;

-- ================== TABLAS COMPARTIDAS ==================
create table cliente (
  id_cliente int not null auto_increment,
  nombre varchar(80) not null,
  telefono varchar(20) not null,
  correo varchar(100) not null,
  fecha_creacion timestamp default current_timestamp,
  primary key (id_cliente),
  unique (correo)
) engine = InnoDB;

-- ================== KATHERINE: usuario, informacion_negocio ==================


-- ================== PERSONA 2: categoria, producto ==================


-- ================== PERSONA 3: pedido, detalle_pedido ==================


-- ================== LUIS: promocion, solicitud_personalizada ==================
create table promocion (
  id_promocion int not null auto_increment,
  titulo varchar(60) not null,
  descripcion varchar(255) not null,
  porcentaje_descuento int not null check (porcentaje_descuento between 1 and 100),
  fecha_inicio date not null,
  fecha_fin date not null,
  activa boolean not null default true,
  primary key (id_promocion),
  check (fecha_fin >= fecha_inicio)
) engine = InnoDB;

create table solicitud_personalizada (
  id_solicitud int not null auto_increment,
  id_cliente int not null,
  descripcion varchar(500) not null,
  cantidad int not null check (cantidad > 0),
  fecha_entrega date not null,
  hora_entrega time not null,
  preferencias varchar(255),
  estado varchar(20) not null default 'Pendiente',
  fecha_solicitud timestamp default current_timestamp,
  primary key (id_solicitud),
  foreign key (id_cliente) references cliente(id_cliente)
) engine = InnoDB;


-- ================== DATOS DE PRUEBA ==================
insert into cliente (nombre, telefono, correo) values
('María Fernández', '8888-1234', 'maria.fernandez@correo.com'),
('Andrés Solís', '8777-5678', 'andres.solis@correo.com'),
('Daniela Mora', '8666-9012', 'daniela.mora@correo.com');

insert into promocion (titulo, descripcion, porcentaje_descuento, fecha_inicio, fecha_fin, activa) values
('Mes del chocolate', 'Todos los queques de chocolate con descuento durante el mes.', 15, curdate(), date_add(curdate(), interval 30 day), true),
('Cupcakes 2x1 los viernes', 'Lleve dos cupcakes del mismo sabor y pague uno.', 50, curdate(), date_add(curdate(), interval 60 day), true),
('Pavlovas de temporada', 'Descuento en pavlovas con frutas de temporada.', 10, date_add(curdate(), interval 10 day), date_add(curdate(), interval 40 day), true),
('Navidad dulce', 'Promoción de galletas navideñas (inactiva hasta diciembre).', 20, '2026-12-01', '2026-12-31', false);

insert into solicitud_personalizada (id_cliente, descripcion, cantidad, fecha_entrega, hora_entrega, preferencias) values
(1, 'Pastel de cumpleaños de dos pisos con tema de flores y el nombre Sofía.', 1, date_add(curdate(), interval 7 day), '15:00:00', 'Relleno de fresa, sin nueces'),
(2, 'Caja de 12 cupcakes decorados para un baby shower en tonos celeste.', 12, date_add(curdate(), interval 10 day), '10:30:00', 'Mitad vainilla, mitad chocolate');
