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


-- ================== DATOS DE PRUEBA ==================
insert into cliente (nombre, telefono, correo) values
('María Fernández', '8888-1234', 'maria.fernandez@correo.com'),
('Andrés Solís', '8777-5678', 'andres.solis@correo.com'),
('Daniela Mora', '8666-9012', 'daniela.mora@correo.com');
