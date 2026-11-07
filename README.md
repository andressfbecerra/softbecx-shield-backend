# SoftBecX Shield

API REST de ciberseguridad empresarial construida con Spring Boot y conectada a MySQL. Permite administrar usuarios, incidentes, análisis de riesgo, evidencias y recomendaciones para apoyar la detección y prevención de fraudes digitales.

## Tecnologías

- Java 17
- Spring Boot 4.1.2
- Spring Web MVC
- Spring Data JPA / Hibernate
- MySQL
- Maven
- BCrypt

## Requisitos

- JDK 17.
- MySQL en ejecución.
- Una base de datos llamada `softbecx_shield` y las tablas compatibles con las entidades del proyecto.
- NetBeans u otro IDE compatible (opcional).

El proyecto incluye Maven Wrapper, por lo que no es necesario tener Apache Maven instalado globalmente.

Hibernate está configurado con `ddl-auto=validate`: comprueba que el esquema de la base de datos coincida con las entidades, pero no crea ni modifica tablas automáticamente.

## Configuración local

1. Verifica que Java esté instalado:

   ```powershell
   java -version
   ```

2. Configura las variables de entorno en PowerShell con los datos de tu instancia MySQL:

   ```powershell
   $env:DB_USERNAME="root"
   $env:DB_PASSWORD="TU_CONTRASEÑA_LOCAL_DE_MYSQL"
   ```

   La contraseña debe reemplazarse por la contraseña local de MySQL. No publiques credenciales reales en GitHub.

3. Comprueba que el proyecto compile:

   ```powershell
   .\mvnw.cmd clean compile
   ```

4. Ejecuta las pruebas:

   ```powershell
   .\mvnw.cmd test
   ```

5. Inicia la aplicación:

   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

Las variables de entorno deben configurarse en la misma sesión de PowerShell desde la que se ejecuta Spring Boot.

La API estará disponible en:

`http://localhost:8080`

## Endpoints disponibles

### Usuarios

La ruta base es `/api/usuarios`.

| Método | Ruta | Descripción | Respuesta exitosa |
|---|---|---|---|
| `GET` | `/api/usuarios` | Lista los usuarios | `200 OK` |
| `GET` | `/api/usuarios/{id}` | Obtiene un usuario | `200 OK` |
| `POST` | `/api/usuarios` | Registra un usuario | `201 Created` |
| `PUT` | `/api/usuarios/{id}` | Actualiza un usuario | `200 OK` |
| `DELETE` | `/api/usuarios/{id}` | Elimina un usuario | `204 No Content` |

### Incidentes

La ruta base es `/api/incidentes`.

| Método | Ruta | Descripción | Respuesta exitosa |
|---|---|---|---|
| `GET` | `/api/incidentes` | Lista los incidentes | `200 OK` |
| `GET` | `/api/incidentes/{id}` | Obtiene un incidente | `200 OK` |
| `POST` | `/api/incidentes` | Registra un incidente | `201 Created` |
| `PUT` | `/api/incidentes/{id}` | Actualiza un incidente | `200 OK` |
| `DELETE` | `/api/incidentes/{id}` | Elimina un incidente | `204 No Content` |

### Análisis de riesgo

La ruta base es `/api/analisis-riesgo`.

| Método | Ruta | Descripción | Respuesta exitosa |
|---|---|---|---|
| `GET` | `/api/analisis-riesgo` | Lista los análisis | `200 OK` |
| `GET` | `/api/analisis-riesgo/{id}` | Obtiene un análisis | `200 OK` |
| `POST` | `/api/analisis-riesgo` | Registra un análisis | `201 Created` |
| `PUT` | `/api/analisis-riesgo/{id}` | Actualiza un análisis | `200 OK` |
| `DELETE` | `/api/analisis-riesgo/{id}` | Elimina un análisis | `204 No Content` |

### Evidencias

La ruta base es `/api/evidencias`.

| Método | Ruta | Descripción | Respuesta exitosa |
|---|---|---|---|
| `GET` | `/api/evidencias` | Lista las evidencias | `200 OK` |
| `GET` | `/api/evidencias/{id}` | Obtiene una evidencia | `200 OK` |
| `POST` | `/api/evidencias` | Registra una evidencia | `201 Created` |
| `PUT` | `/api/evidencias/{id}` | Actualiza una evidencia | `200 OK` |
| `DELETE` | `/api/evidencias/{id}` | Elimina una evidencia | `204 No Content` |

### Recomendaciones

La ruta base es `/api/recomendaciones`.

| Método | Ruta | Descripción | Respuesta exitosa |
|---|---|---|---|
| `GET` | `/api/recomendaciones` | Lista las recomendaciones | `200 OK` |
| `GET` | `/api/recomendaciones/{id}` | Obtiene una recomendación | `200 OK` |
| `POST` | `/api/recomendaciones` | Registra una recomendación | `201 Created` |
| `PUT` | `/api/recomendaciones/{id}` | Actualiza una recomendación | `200 OK` |
| `DELETE` | `/api/recomendaciones/{id}` | Elimina una recomendación | `204 No Content` |

## Respuestas y manejo de errores

La API utiliza códigos HTTP para indicar el resultado de las solicitudes:

| Código | Descripción |
|---|---|
| `200 OK` | Solicitud procesada correctamente |
| `201 Created` | Registro creado |
| `204 No Content` | Operación completada sin contenido |
| `400 Bad Request` | Solicitud incorrecta |
| `404 Not Found` | Recurso no encontrado |
| `409 Conflict` | Conflicto con los datos |
| `500 Internal Server Error` | Error interno del servidor |

Las excepciones se gestionan mediante `GlobalExceptionHandler`, que proporciona respuestas JSON centralizadas.

## Estructura principal

- `controller`: controladores REST que reciben las solicitudes HTTP.
- `service`: lógica y operaciones del sistema.
- `repository`: acceso a datos mediante Spring Data JPA.
- `model`: entidades que representan las tablas de MySQL.
- `dto`: objetos de transferencia de datos.
- `exception`: manejo centralizado de errores.
- `config`: configuración de componentes de la aplicación.
- `src/main/resources/application.properties`: configuración de Spring y conexión a MySQL.
- `.env.example`: plantilla de referencia para las variables de conexión.

## Repositorio

GitHub: https://github.com/andressfbecerra/softbecx-shield-backend

**SoftBecX Shield — Inteligencia que protege tu mundo digital.**