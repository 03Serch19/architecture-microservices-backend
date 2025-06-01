# Architecture-microservices-backend
🏡 Sistema de Gestión Inmobiliaria Basado en Microservicios
API REST basada en microservicios para la compra y mantenimiento de inmuebles, así como el registro y gestión de usuarios. Desarrollado con Spring Boot, el sistema consta de 4 microservicios que utilizan Spring Security para la autenticación y autorización, Eureka Server para el descubrimiento de servicios y Spring Feign para la comunicación interna. La persistencia de datos se maneja con MySQL, PostgreSQL y H2 (para desarrollo).

🚀 Microservicios
El proyecto está modularizado en los siguientes 4 microservicios clave:

eureka-server:Permite que los demás microservicios se registren y se localicen entre sí.
ms-inmuebles: Encargado de toda la lógica de negocio relacionada con los inmuebles (registro, consulta, actualización, eliminación, estado de compra y mantenimiento).
ms-compras: Gestiona las transacciones y la lógica asociada a la compra de inmuebles, interactuando con ms-inmuebles.
api-gateway: Sirve como la puerta de entrada principal para todas las solicitudes externas.
Es responsable de:
Autenticación y Autorización: Aplica la seguridad más robusta, gestionando la autenticación de usuarios y las reglas de acceso.
Gestión de Usuarios: Contiene la lógica para el registro y mantenimiento de usuarios.
Balanceo de Carga y Enrutamiento: Redirige las solicitudes a los microservicios apropiados y utiliza Spring Feign para comunicarse internamente con ellos.
🛠️ Tecnologías Utilizadas
Spring Boot: Framework principal para el desarrollo de microservicios.
Spring Security: Manejo de autenticación y autorización (posiblemente JWT).
Spring Feign: Cliente REST declarativo para la comunicación inter-microservicios.
Eureka Server: Servidor de descubrimiento de servicios.
Bases de Datos:
MySQL: Base de datos relacional para entornos de producción.
PostgreSQL: Base de datos relacional adicional para entornos de producción (o según configuración específica).
H2 Database: Base de datos en memoria para desarrollo y pruebas.

Mayo  2025
