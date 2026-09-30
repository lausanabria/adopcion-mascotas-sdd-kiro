# Requerimientos

## 1. Descripción
Página web de adopción de mascotas, los usuarios podrán postularse para adoptar una mascota, las mascotas serán publicadas para poder ver su información y poder realizar la postulación.

## 2. Stack
- **Backend:** Java con Spring Boot, Java 17/21. Uso de JPA, librería Lombok, arquitectura limpia, GlobalHandlerException para control de excepciones, JUnit para testing, uso de inyección de dependencias, DTOs request y DTOs response, JWT para autenticación.
- **Database:** PostgreSQL alojado en Supabase con conexión JDBC.
    - **URL de conexión:** `jdbc:postgresql://postgres:[SC08oTVql2h5o5IJ]@db.wnntlrvcajiarahpnjla.supabase.co:5432/postgres` (o vía Transaction Pooler en puerto `6543`).
    - **Modelo de Datos:** Tablas `pets`, `applicants` y `adoption_applications` mapeadas mediante JPA/Hibernate.
    - **Tipos de Datos:** Manejo de tipos personalizados `ENUM` para especies (`PERRO`, `GATO`, `OTRO`), vacunas en español (`RABIA`, `TRIPLE_FELINA`, etc.) y UUIDs para identificadores de solicitantes.
- **Frontend:** React con Vite.

## 3. Modelo de datos
### 3.1 Pet Entity
- `id`: Long (Primary Key, Auto-generated)
- `name`: String (Required)
- `age`: Integer
- `vaccines`: List<Enum> (RABIES, DISTEMPER, PARVOVIRUS)
- `petType`: Enum (DOG, CAT, OTHER)
- `breed`: String
- `birthdate`: LocalDate

### 3.2 Applicant Entity
- `id`: UUID (Primary Key, Auto-generated)
- `documentType`: Enum (CC, PASSPORT)
- `documentNumber`: String (Required, Unique)
- `name`: String (Required)
- `email`: String (Required)
- `phoneNumber`: String

### 3.3 AdoptionApplication Entity
- `id`: Long (Primary Key, Auto-generated)
- `applicant`: Applicant (ManyToOne relation)
- `pet`: Pet (ManyToOne relation)
- `housingType`: Enum (APARTMENT, HOUSE)
- `hasOtherPets`: Boolean
- `occupation`: String
- `status`: Enum (PENDING, APPROVED, REJECTED) — Default: PENDING
- `applicationDate`: LocalDateTime — Default: NOW

## 4. Rest API endpoints

### 4.1 Pet Controller (`/api/pets`)
- `GET /api/pets`: Listar mascotas disponibles (permite filtro opcional por tipo `?petType=DOG` o `?petType=CAT`).
- `GET /api/pets/{id}`: Obtener el detalle de una mascota por su ID.

### 4.2 AdoptionApplication Controller (`/api/applications`)
- `POST /api/applications`: Registrar una nueva solicitud de adopción (recibe los datos del adoptante, la mascota seleccionada y el formulario).

## 5. Requerimientos para el agente Kiro
- Generar clases de SpringBoot de acuerdo a la arquitectura limpia (Entity,Controller,Service,Repository,DTO)
- Configurar Application.properties con las variables y el application-local.properties con los valores de los secretos
- Crear los test de los servicios
- Inicialmente la API de backend debe permitir: consultar mascotas disponibles, filtrar por tipo de mascota y registrar una solicitud de adopción.