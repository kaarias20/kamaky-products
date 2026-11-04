# Kamaky Products

Aplicación web para la gestión de productos y pedidos de repostería artesanal.
Proyecto del curso **Desarrollo de Aplicaciones Web y Patrones** – Universidad Fidélitas.

## Descripción
Permite a los clientes de Kamaky Products consultar el catálogo, ver precios y promociones, gestionar un carrito de compras, realizar pedidos y solicitar productos personalizados. El administrador gestiona productos, categorías, promociones, pedidos e información de contacto.

## Integrantes
- Katherine Arias Molina
- Luis Sebastián Caravaca Rojas
- Roberto Arias Badilla
- Minor Gabriel Segura Solano

## Tecnologías
- Java 21 y Spring Boot 4.1.1 (Spring Web MVC, Thymeleaf, Spring Data JPA/Hibernate, Validation)
- MySQL 8
- Bootstrap 5.3 y Font Awesome (WebJars)
- Firebase Storage para las imágenes
- Lombok

## Requisitos de ejecución
1. **JDK 21** y **NetBeans** (o cualquier IDE con Maven).
2. **MySQL 8** con MySQL Workbench.
3. Ejecutar completo el script `sql/kamaky.sql` con el usuario `root`. Crea la base `kamaky`, el usuario `usuario_kamaky` y las tablas con datos de prueba.
4. Colocar la llave de Firebase del grupo en `src/main/resources/firebase/llave-firebase.json` y completar `firebase.bucket.name` en `application.properties`. **La llave no se sube a GitHub** (está en el `.gitignore`); se comparte de forma privada entre los integrantes.
5. Ejecutar el proyecto (Run en NetBeans) y abrir **http://localhost**.

## Estructura
```
com.kamaky
├── KamakyApplication.java
├── ProjectConfig.java          (idioma es-CR y página de inicio)
├── StorageConfig.java          (conexión a Firebase Storage)
├── controller/
├── domain/                     (entidades JPA)
├── repository/
└── service/                    (incluye FirebaseStorageService)
templates/
├── plantilla/fragmentos.html   (head, encabezado, pie, mensajes, modalEliminar)
└── index.html
sql/kamaky.sql
```

## Forma de trabajo en GitHub
- Nadie trabaja directo en `main`.
- Cada integrante trabaja en su rama `feature/...` y abre un **Pull Request** hacia `main`.
- Otro integrante revisa y aprueba el PR antes de unirlo.
- Los textos de pantalla van en `messages.properties`, cada uno con su prefijo.

| Integrante | Rama | Módulo |
|---|---|---|
| Luis | `feature/promociones` | Promoción, Solicitud personalizada, Cliente |
| Katherine | `feature/contacto-admin` | Usuario, Información del negocio, panel admin |
| Minor | `feature/productos` | Producto, Categoría, inicio |
| Roberto | `feature/pedidos` | Carrito, Pedido, Detalle del pedido |

## Estado del avance 2
| Historia | Descripción | Responsable | Estado |
|---|---|---|---|
| HU-001 | Visualizar catálogo de productos | Minor | Pendiente |
| HU-004 | Agregar productos al carrito | Roberto | Pendiente |
| HU-007 | Consultar resumen del carrito | Roberto | Pendiente |
| HU-008 | Realizar un pedido | Roberto | Pendiente |
| HU-009 | Solicitar producto personalizado | Luis | Implementada |
| HU-010 | Consultar promociones | Luis | Implementada |
| HU-011 | Consultar información de contacto | Katherine | Pendiente |
| HU-012 | Consultar información del negocio | Katherine | Pendiente |
| HU-013 | Agregar productos al catálogo | Minor | Pendiente |
| HU-015 | Gestionar categorías | Minor | Pendiente |
| HU-016 | Gestionar promociones | Luis | Implementada |
| HU-020 | Gestionar información de contacto | Katherine | Pendiente |

Base del proyecto (estructura, configuración, plantilla, script SQL y entidad Cliente): **completada**.
