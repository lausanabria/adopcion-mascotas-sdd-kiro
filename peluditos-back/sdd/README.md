# Sistema de Adopción de Mascotas - Backend API

Sistema backend desarrollado en Java con Spring Boot para gestionar solicitudes de adopción de mascotas.

## 🚀 Tecnologías

- **Java 21**
- **Spring Boot 4.1.1**
- **Spring Data JPA**
- **PostgreSQL** (Supabase)
- **Lombok**
- **Maven**
- **JUnit 5** para testing

## 📋 Requisitos Previos

- Java 21 o superior
- Maven 3.8+
- PostgreSQL (se proporciona acceso a Supabase)

## 🗂️ Estructura del Proyecto

El proyecto sigue una arquitectura limpia con la siguiente estructura:

```
src/main/java/com/project/sdd/
├── application/
│   ├── dto/
│   │   ├── request/          # DTOs de entrada
│   │   └── response/         # DTOs de salida
│   ├── mapper/               # Conversión Entity <-> DTO
│   └── service/              # Lógica de negocio
├── domain/
│   ├── entities/             # Entidades JPA
│   └── enums/                # Enumeraciones
└── infrastructure/
    ├── exception/            # Manejo de excepciones
    ├── repository/           # Repositorios JPA
    └── web/
        └── controller/       # Controladores REST
```

## ⚙️ Configuración

### 1. Base de Datos

La aplicación está configurada para conectarse a una base de datos PostgreSQL en Supabase.

Las credenciales se encuentran en el archivo `application-local.properties`:

```properties
DB_URL=jdbc:postgresql://db.wnntlrvcajiarahpnjla.supabase.co:5432/postgres
DB_USERNAME=postgres
DB_PASSWORD=SC08oTVql2h5o5IJ
```

### 2. Perfil de Ejecución

Para ejecutar la aplicación con el perfil local:

```bash
# Usando Maven wrapper
./mvnw spring-boot:run -Dspring-boot.run.profiles=local

# Usando Maven
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

En Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run -D"spring-boot.run.profiles=local"
```

## 🔨 Compilación

Para compilar el proyecto:

```bash
# Usando Maven wrapper
./mvnw clean compile

# Usando Maven
mvn clean compile
```

En Windows PowerShell:

```powershell
.\mvnw.cmd clean compile
```

## 🧪 Ejecutar Tests

```bash
# Todos los tests
./mvnw test

# Tests de un servicio específico
./mvnw test -Dtest=PetServiceTest
```

En Windows PowerShell:

```powershell
.\mvnw.cmd test
```

## 📡 Endpoints Disponibles

### Mascotas

#### Listar todas las mascotas
```http
GET /api/pets
```

#### Filtrar mascotas por tipo
```http
GET /api/pets?petType=PERRO
GET /api/pets?petType=GATO
GET /api/pets?petType=OTRO
```

#### Obtener mascota por ID
```http
GET /api/pets/{id}
```

**Respuesta:**
```json
{
  "id": 1,
  "name": "Luna",
  "age": 2,
  "petType": "PERRO",
  "breed": "Criolla / Mestiza",
  "birthdate": "2024-03-15",
  "vaccines": ["RABIA", "MOQUILLO_CANINO"]
}
```

### Solicitudes de Adopción

#### Crear solicitud de adopción
```http
POST /api/applications
Content-Type: application/json

{
  "applicant": {
    "documentType": "CC",
    "documentNumber": "1018123456",
    "name": "Laura Restrepo",
    "email": "laura.restrepo@example.com",
    "phoneNumber": "3001234567"
  },
  "petId": 1,
  "housingType": "APARTMENT",
  "hasOtherPets": true,
  "occupation": "Ingeniera de Software"
}
```

**Respuesta:**
```json
{
  "id": 1,
  "applicant": {
    "id": "uuid",
    "documentType": "CC",
    "documentNumber": "1018123456",
    "name": "Laura Restrepo",
    "email": "laura.restrepo@example.com",
    "phoneNumber": "3001234567"
  },
  "pet": {
    "id": 1,
    "name": "Luna",
    "age": 2,
    "petType": "PERRO",
    "breed": "Criolla / Mestiza",
    "birthdate": "2024-03-15",
    "vaccines": ["RABIA", "MOQUILLO_CANINO"]
  },
  "housingType": "APARTMENT",
  "hasOtherPets": true,
  "occupation": "Ingeniera de Software",
  "status": "PENDING",
  "applicationDate": "2026-09-27T10:30:00"
}
```

## 🔑 Enumeraciones

### PetType (Tipo de Mascota)
- `PERRO`
- `GATO`
- `OTRO`

### VaccineType (Tipo de Vacuna)
- `RABIA`
- `MOQUILLO_CANINO`
- `PARVOVIRUS_CANINO`
- `TRIPLE_FELINA`
- `LEUCEMIA_FELINA`

### DocumentType (Tipo de Documento)
- `CC` (Cédula de Ciudadanía)
- `PASSPORT` (Pasaporte)

### HousingType (Tipo de Vivienda)
- `APARTMENT` (Apartamento)
- `HOUSE` (Casa)

### ApplicationStatus (Estado de Solicitud)
- `PENDING` (Pendiente)
- `APPROVED` (Aprobada)
- `REJECTED` (Rechazada)

## 🛠️ Manejo de Errores

La API utiliza un `GlobalExceptionHandler` que maneja diferentes tipos de errores:

- **404 Not Found**: Cuando un recurso no existe
- **409 Conflict**: Cuando hay conflicto de datos (ej: documento duplicado)
- **400 Bad Request**: Cuando hay errores de validación
- **500 Internal Server Error**: Para errores internos del servidor

Ejemplo de respuesta de error:
```json
{
  "timestamp": "2026-09-27T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Mascota no encontrada con ID: 999",
  "path": "/api/pets/999"
}
```

## 📝 Validaciones

Las siguientes validaciones están implementadas:

- **Applicant (Solicitante):**
  - `documentType`: Obligatorio
  - `documentNumber`: Obligatorio
  - `name`: Obligatorio
  - `email`: Obligatorio y debe ser formato válido
  - `phoneNumber`: Opcional

- **AdoptionApplication (Solicitud de Adopción):**
  - `applicant`: Obligatorio
  - `petId`: Obligatorio
  - `housingType`: Opcional
  - `hasOtherPets`: Opcional
  - `occupation`: Opcional

## 🧩 Características Principales

1. **Arquitectura Limpia**: Separación clara de responsabilidades
2. **DTOs**: Separación entre entidades de dominio y objetos de transferencia
3. **Manejo Global de Excepciones**: Control centralizado de errores
4. **Inyección de Dependencias**: Uso de Spring Framework
5. **Logging**: Trazabilidad con SLF4J y Lombok
6. **Testing**: Tests unitarios con JUnit 5 y Mockito
7. **Validación**: Validación automática con Jakarta Validation

## 📦 Datos de Prueba

La base de datos incluye datos de prueba:

- **6 mascotas** (perros, gatos y otros)
- **3 solicitantes** con diferentes tipos de documentos
- **2 solicitudes de adopción** en diferentes estados

## 🚀 Ejecución Rápida

```bash
# 1. Compilar el proyecto
mvn clean compile

# 2. Ejecutar tests
mvn test

# 3. Ejecutar la aplicación
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

La aplicación estará disponible en: `http://localhost:8080`

## 📞 Contacto

Para más información sobre el proyecto, consulta la documentación en los archivos `.spect/`.
