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
create table categoria (
  id_categoria int not null auto_increment,
  nombre varchar(60) not null,
  descripcion varchar(255),
  activa boolean not null default true,
  primary key (id_categoria),
  unique key uk_categoria_nombre (nombre)
) engine = InnoDB;

create table producto (
  id_producto int not null auto_increment,
  nombre varchar(100) not null,
  descripcion varchar(500),
  precio decimal(10,2) not null,
  imagen varchar(1024),
  disponible boolean not null default true,
  id_categoria int not null,
  primary key (id_producto),
  key idx_producto_categoria (id_categoria),
  constraint fk_producto_categoria
    foreign key (id_categoria) references categoria (id_categoria)
    on update cascade
    on delete restrict
) engine = InnoDB;

insert into categoria (nombre, descripcion, activa) values
('Pasteles', 'Pasteles para celebraciones y ocasiones especiales', true),
('Postres', 'Postres preparados artesanalmente', true),
('Galletas', 'Galletas y bocadillos dulces', true);

insert into producto
  (nombre, descripcion, precio, imagen, disponible, id_categoria)
values
('Pastel de chocolate', 'Pastel de chocolate para compartir', 13500.00, null, true, 1),
('Red velvet', 'Pastel red velvet con cubierta cremosa', 15500.00, null, true, 1),
('Flan casero', 'Flan elaborado artesanalmente', 2800.00, null, true, 2),
('Galleta con chispas', 'Galleta con chispas de chocolate', 1200.00, null, true, 3);

-- ================== PERSONA 3: pedido, detalle_pedido ==================


-- ================== LUIS: promocion, solicitud_personalizada ==================


-- ================== DATOS DE PRUEBA ==================
insert into cliente (nombre, telefono, correo) values
('María Fernández', '8888-1234', 'maria.fernandez@correo.com'),
('Andrés Solís', '8777-5678', 'andres.solis@correo.com'),
('Daniela Mora', '8666-9012', 'daniela.mora@correo.com');
