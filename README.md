# webflux-crud-auth
##  :: Spring Boot ::                (v3.4.1)

Este proyecto es una aplicación simple utilizando **Spring WebFlux** para implementar la dinamica de un CRUD (Create, Read, Update, Delete). Además, incluye manejo de excepciones estándar,
una excepción personalizada, persistencia de datos, una capa de APIs e implementacion de Spring Security para el manejo de Authentication y Authorization.

## Características principales

- **CRUD completo**: APIs para crear, leer, actualizar y eliminar recursos.
- **Spring Security**: Manejo de seguridad en las apis con jwt. (Anthentication & Authorization)
- **Manejo de excepciones**: Validaciones, captura y manejo de errores estándar y personalizados.
- **Excepción personalizada**: Define y gestiona situaciones específicas con una excepción propia.
- **Spring WebFlux**: Implementación reactiva para mayor rendimiento y escalabilidad.
- **MariaDB**: Persistencia de datos en una SQL Database.
- **Docker-Compose**: Configuración para facilitar la ejecución del proyecto en entornos Dockerizados.
- **Makefile**: (Opcional) Agiliza comandos comunes como iniciar o detener servicios.

## APIs disponibles

Las APIs siguen una estructura REST para interactuar con los recursos:

### Login (POST)
- **URL**: `/auth/login`
- **Descripción**: Devuelve token para apis que lo requiera por seguridad.

### Registrar (POST)
- **URL**: `/auth/register`
- **Descripción**: Registra usuario con un rol de acceso.

### Crear (POST)
- **URL**: `/products`
- **Descripción**: Crea un nuevo producto.

### Leer todos (GET)
- **URL**: `/products`
- **Descripción**: Devuelve la lista de todos los recursos disponibles.

### Leer uno (GET)
- **URL**: `/api/resource/{id}`
- **Descripción**: Devuelve los detalles de un recurso específico.

### Actualizar (PUT)
- **URL**: `/api/resource/{id}`
- **Descripción**: Actualiza un recurso existente.

### Eliminar (DELETE)
- **URL**: `/api/resource/{id}`
- **Descripción**: Elimina un recurso específico.

## Requisitos previos
- **Java 17**
- **Maven 3.8** o superior
- **io.jsonwebtoken 0.12.6**
- **Docker** y **Docker Compose**
- (Opcional) **Make**

## Cómo levantar el proyecto

### Usando Docker Compose
1. Levanta los servicios dependientes (MariaDB):
   ```bash
   docker-compose up
   ```
   o
    ```bash
   make up
   ```

1. Levanta el proyecto spring webflux:
   ```bash
   mvn spring-boot:run
   ```

3. La aplicación estará disponible en `http://localhost:8080`.
