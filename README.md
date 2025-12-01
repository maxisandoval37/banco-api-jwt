# Banco Rest API JWT

Este proyecto implementa un servicio REST que gestiona entidades bancarias (**CRUD completo**), utilizando Spring Boot, Spring Security (JWT), JPA/Hibernate, base de datos en memoria H2 y Caché.
Además, cumple con el requisito: **consumir un endpoint de la misma aplicación**, reutilizando el token JWT vigente.

Utiliza arquitectura en capas (entity -> repository -> service -> controller), manejo de excepciones y validaciones de duplicidad en la creación de entidades.
Además la app cuenta con seguridad con JWT (login, registro y logout), uso de roles y un endpoint de administración de usuarios, disponible solo para el rol Administrador.

---

## Tecnologías Utilizadas
- **Spring Boot Versión 3.5.3** 
- **Spring Boot Starter Data JPA**
- **Spring Boot Starter Test / Junit**
- **H2 Database:**
- **Spring Boot Starter Validation**
- **Lombok**
- **Spring Boot Starter Security**
- **Spring Boot Starter Cache y Caffeine**
- **DataFaker:** Utilizado para generar datos para los test unitarios.
- **Swagger**

## Endpoint swagger

El endpoint de swagger se encuentra en:

`/swagger-ui/index.html`

<img width="1949" height="964" alt="image" src="https://github.com/user-attachments/assets/21c585ed-d557-403f-a64a-70b83b1ac665" />


## Live Demo

### [Click Here! 🖱️](https://banco-api-jwt.onrender.com/swagger-ui/index.html)

#### Usuarios de prueba (pass: *password*):
- admin
- user1

## Información Adicional
Para cualquier información adicional o consultas: <maxisandoval98@gmail.com>

¡Muchas gracias! 🦔
