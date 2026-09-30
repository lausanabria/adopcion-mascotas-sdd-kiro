# Estructura del Proyecto

```
sdd/
├── .spect/
│   ├── database.md                         # Especificación de base de datos
│   └── requirements.md                     # Requerimientos del proyecto
│
├── src/
│   ├── main/
│   │   ├── java/com/project/sdd/
│   │   │   ├── SddApplication.java         # Clase principal Spring Boot
│   │   │   │
│   │   │   ├── application/                # Capa de Aplicación
│   │   │   │   ├── dto/
│   │   │   │   │   ├── request/           # DTOs de entrada
│   │   │   │   │   │   ├── AdoptionApplicationRequestDTO.java
│   │   │   │   │   │   └── ApplicantRequestDTO.java
│   │   │   │   │   └── response/          # DTOs de salida
│   │   │   │   │       ├── AdoptionApplicationResponseDTO.java
│   │   │   │   │       ├── ApplicantResponseDTO.java
│   │   │   │   │       └── PetResponseDTO.java
│   │   │   │   │
│   │   │   │   ├── mapper/                # Conversión Entity ↔ DTO
│   │   │   │   │   ├── AdoptionApplicationMapper.java
│   │   │   │   │   ├── ApplicantMapper.java
│   │   │   │   │   └── PetMapper.java
│   │   │   │   │
│   │   │   │   └── service/               # Lógica de negocio
│   │   │   │       ├── AdoptionApplicationService.java
│   │   │   │       ├── ApplicantService.java
│   │   │   │       └── PetService.java
│   │   │   │
│   │   │   ├── domain/                    # Capa de Dominio
│   │   │   │   ├── entities/              # Entidades JPA
│   │   │   │   │   ├── AdoptionApplication.java
│   │   │   │   │   ├── Applicant.java
│   │   │   │   │   └── Pet.java
│   │   │   │   │
│   │   │   │   └── enums/                 # Enumeraciones
│   │   │   │       ├── ApplicationStatus.java
│   │   │   │       ├── DocumentType.java
│   │   │   │       ├── HousingType.java
│   │   │   │       ├── PetType.java
│   │   │   │       └── VaccineType.java
│   │   │   │
│   │   │   └── infrastructure/            # Capa de Infraestructura
│   │   │       ├── exception/             # Manejo de excepciones
│   │   │       │   ├── DuplicateResourceException.java
│   │   │       │   ├── ErrorResponse.java
│   │   │       │   ├── GlobalExceptionHandler.java
│   │   │       │   └── ResourceNotFoundException.java
│   │   │       │
│   │   │       ├── repository/            # Repositorios JPA
│   │   │       │   ├── AdoptionApplicationRepository.java
│   │   │       │   ├── ApplicantRepository.java
│   │   │       │   └── PetRepository.java
│   │   │       │
│   │   │       └── web/
│   │   │           └── controller/        # Controladores REST
│   │   │               ├── AdoptionApplicationController.java
│   │   │               └── PetController.java
│   │   │
│   │   └── resources/
│   │       ├── application.properties              # Configuración principal
│   │       └── application-local.properties        # Configuración local (Supabase)
│   │
│   └── test/
│       └── java/com/project/sdd/
│           ├── SddApplicationTests.java            # Test principal
│           └── application/service/                # Tests de servicios
│               ├── AdoptionApplicationServiceTest.java
│               ├── ApplicantServiceTest.java
│               └── PetServiceTest.java
│
├── .gitignore                             # Archivos ignorados por Git
├── API-EXAMPLES.md                        # Ejemplos de uso de la API
├── IMPLEMENTATION-SUMMARY.md              # Resumen de implementación
├── mvnw                                   # Maven Wrapper (Linux/Mac)
├── mvnw.cmd                               # Maven Wrapper (Windows)
├── pom.xml                                # Configuración Maven
├── PROJECT-STRUCTURE.md                   # Este archivo
├── README.md                              # Documentación principal
├── run-local.ps1                          # Script para ejecutar en local
└── verify-database.sql                    # Script de verificación de BD
```

## 📊 Resumen por Capas

### Domain Layer (Dominio)
**5 Enums + 3 Entities = 8 archivos**
- Enumeraciones que representan conceptos del negocio
- Entidades JPA que mapean las tablas de PostgreSQL

### Application Layer (Aplicación)
**5 DTOs Request + 3 DTOs Response + 3 Mappers + 3 Services = 14 archivos**
- DTOs para entrada y salida de datos
- Mappers para conversión entre entidades y DTOs
- Servicios con lógica de negocio

### Infrastructure Layer (Infraestructura)
**4 Exception + 3 Repositories + 2 Controllers = 9 archivos**
- Manejo global de excepciones
- Repositorios JPA para acceso a datos
- Controladores REST para API

### Tests
**1 Application Test + 3 Service Tests = 4 archivos**
- Tests unitarios con JUnit 5 y Mockito

### Total
**35 archivos Java** distribuidos en una arquitectura limpia

## 🎯 Mapeo de Capas a Paquetes

```
┌─────────────────────────────────────────────────────┐
│                   Controllers                       │
│            (infrastructure.web.controller)          │
│                  REST Endpoints                     │
└────────────────────┬────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────┐
│                   Services                          │
│            (application.service)                    │
│               Business Logic                        │
└────────────────────┬────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────┐
│                  Repositories                       │
│           (infrastructure.repository)               │
│               Data Access                           │
└────────────────────┬────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────┐
│                   Entities                          │
│              (domain.entities)                      │
│              Database Tables                        │
└─────────────────────────────────────────────────────┘
```

## 📦 Flujo de Conversión de Datos

```
[HTTP Request] 
    → [Controller] 
    → [Request DTO] 
    → [Service] 
    → [Mapper] 
    → [Entity] 
    → [Repository] 
    → [Database]

[Database] 
    → [Repository] 
    → [Entity] 
    → [Mapper] 
    → [Response DTO] 
    → [Controller] 
    → [HTTP Response]
```

## 🔄 Dependencias entre Componentes

```
Controller
    ↓ depende de
Service
    ↓ depende de
Repository + Mapper
    ↓ dependen de
Entity + DTO
    ↓ dependen de
Enum
```

## 📁 Convenciones de Nomenclatura

### Paquetes
- `domain` - Núcleo del negocio (independiente de frameworks)
- `application` - Casos de uso y lógica de aplicación
- `infrastructure` - Detalles técnicos y frameworks

### Clases
- **Entities**: `<Nombre>.java` (ej: `Pet.java`)
- **DTOs Request**: `<Nombre>RequestDTO.java`
- **DTOs Response**: `<Nombre>ResponseDTO.java`
- **Services**: `<Nombre>Service.java`
- **Repositories**: `<Nombre>Repository.java`
- **Controllers**: `<Nombre>Controller.java`
- **Mappers**: `<Nombre>Mapper.java`
- **Exceptions**: `<Nombre>Exception.java`

### Tests
- **Service Tests**: `<Nombre>ServiceTest.java`
- Ubicados en el mismo paquete que la clase bajo prueba

## 🎨 Patrones de Diseño Utilizados

1. **Repository Pattern** - Abstracción de acceso a datos
2. **DTO Pattern** - Transferencia de datos entre capas
3. **Service Layer Pattern** - Encapsulación de lógica de negocio
4. **Mapper Pattern** - Conversión entre objetos
5. **Dependency Injection** - Inyección de dependencias con Spring
6. **Exception Handler Pattern** - Manejo centralizado de errores

## 🚀 Puntos de Entrada

### Aplicación
- **Clase**: `SddApplication.java`
- **Ubicación**: `src/main/java/com/project/sdd/`
- **Propósito**: Iniciar aplicación Spring Boot

### API REST
- **Base URL**: `http://localhost:8080`
- **Endpoints**:
  - `/api/pets` - Gestión de mascotas
  - `/api/applications` - Gestión de solicitudes

### Tests
- **Comando**: `mvn test`
- **Ubicación**: `src/test/java/com/project/sdd/`

## 📝 Archivos de Configuración

- `pom.xml` - Dependencias Maven
- `application.properties` - Configuración general
- `application-local.properties` - Configuración local
- `.gitignore` - Exclusiones de Git

## 📚 Documentación

- `README.md` - Documentación principal
- `API-EXAMPLES.md` - Ejemplos de uso
- `IMPLEMENTATION-SUMMARY.md` - Resumen técnico
- `PROJECT-STRUCTURE.md` - Estructura (este archivo)
- `verify-database.sql` - Verificación de BD
