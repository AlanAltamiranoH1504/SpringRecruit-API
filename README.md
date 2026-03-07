<p align="center">
  <img src="/docs/imgs/Logo_SpringRecruit-API.png" alt="Admin Veterinaria Logo" width="200"/>
</p>

# SpringRecruit API: Sistema de Gestión de Reclutamiento

## 🚀 Descripción del Proyecto
SpringRecruit-API es un backend robusto desarrollado con **Spring Boot** diseñado para gestionar el ciclo de vida de los procesos de selección de personal. La API permite la interacción entre reclutadores y candidatos, facilitando desde la publicación de vacantes hasta la postulación y gestión de estados de los procesos.

El sistema implementa una arquitectura segura y escalable, integrando servicios en la nube para el manejo de archivos multimedia y notificaciones automatizadas.

## 🏗️ Estado del Proyecto: En Desarrollo 🚧
Este proyecto se encuentra actualmente en fase de desarrollo activo.
- **Backend:** Funcionalidad principal completada (Seguridad, persistencia, lógica de negocio e integraciones).
- **Frontend:** **En planificación/desarrollo.** Se tiene previsto implementar una interfaz cliente utilizando **Angular** para consumir esta API, permitiendo una experiencia de usuario completa y reactiva.

## 🛠️ Tecnologías Utilizadas
- **Framework:** Spring Boot 3.x
- **Seguridad:** Spring Security + JSON Web Token (JWT)
- **Persistencia:** Spring Data JPA + Hibernate (MySQL)
- **Almacenamiento de Archivos:** Cloudinary SDK
- **Notificaciones:** JavaMailSender + Mailtrap

- **Validación:** Hibernate Validator (Bean Validation)

## 🔑 Características Principales
* **Gestión de Identidad:** Sistema de roles (ADMIN, RECRUITER, CANDIDATE) con autenticación basada en JWT.
* **Flujo de Reclutamiento:** - Creación y publicación de vacantes.
    - Registro de candidatos y gestión de perfiles.
    - Proceso de postulación con manejo de estados.
* **Gestión de Multimedia:** Carga y almacenamiento optimizado de imágenes de perfil y documentos (CVs) en Cloudinary.
* **Sistema de Notificaciones:** Envío automatizado de correos electrónicos al actualizar el estado de una postulación (integración con Mailtrap).
* **Arquitectura Limpia:** Organización en capas (Controller, Service, Repository) para facilitar el mantenimiento.

## ⚙️ Configuración del Entorno
Para ejecutar el proyecto, asegúrate de configurar las siguientes propiedades en tu archivo `application.properties` o `application.yml`:

```properties
# Zona Horaria de Proyecto
spring.jackson.time-zone=America/Mexico_City

# Configuracion de uploads
url.imgs=C:/Imagenes_Proyectos/SpringBoot
spring.servlet.multipart.enabled=true
spring.servlet.multipart.location=c:/tmp
spring.servlet.multipart.max-file-size=2MB

# Configuracion base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/DB_Plataforma_Trabajos_SpringBoot
spring.datasource.username=root
spring.datasource.password=admin
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# Configuracion de Spring Security y JWT
JWT_SECRET=V0p6Q1RHaE5uWm1LQ0F6TnZxQ2RzR2JkR3hQeVhYb1F5V1dYcVZkTnRZbXhRZ0tXb1RZc1pGQ3hUeVJ4Qm1Qd1Z5V2pOa1pR

# Configuracion de mails
spring.mail.host=sandbox.smtp.mailtrap.io
spring.mail.port=2525
spring.mail.username=390abaef5937a8
spring.mail.password=0094e429760032