# webflux-simple-crud

Este proyecto es una aplicación simple utilizando **Spring WebFlux** para implementar un CRUD (Create, Read, Update, Delete). Además, incluye manejo de excepciones estándar, una excepción personalizada, persistencia de datos y una capa de APIs.

## Características principales

- **CRUD completo**: APIs para crear, leer, actualizar y eliminar recursos.
- **Manejo de excepciones**: Validaciones, captura y manejo de errores estándar y personalizados.
- **Excepción personalizada**: Define y gestiona situaciones específicas con una excepción propia.
- **Spring WebFlux**: Implementación reactiva para mayor rendimiento y escalabilidad.
- **MariaDB**: Persistencia de datos en una SQL Database.
- **Docker-Compose**: Configuración para facilitar la ejecución del proyecto en entornos Dockerizados.
- **Makefile**: (Opcional) Agiliza comandos comunes como iniciar o detener servicios.

## APIs disponibles

Las APIs siguen una estructura REST para interactuar con los recursos:

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
