# Ejemplos de Uso de la API

## Base URL
```
http://localhost:8080
```

## 1. Obtener todas las mascotas

### Request
```http
GET /api/pets HTTP/1.1
Host: localhost:8080
Accept: application/json
```

### cURL
```bash
curl -X GET http://localhost:8080/api/pets
```

### Respuesta Esperada
```json
[
  {
    "id": 1,
    "name": "Luna",
    "age": 2,
    "petType": "PERRO",
    "breed": "Criolla / Mestiza",
    "birthdate": "2024-03-15",
    "vaccines": ["RABIA", "MOQUILLO_CANINO"]
  },
  {
    "id": 2,
    "name": "Mishu",
    "age": 1,
    "petType": "GATO",
    "breed": "Siamés",
    "birthdate": "2025-01-10",
    "vaccines": ["RABIA", "TRIPLE_FELINA"]
  }
]
```

## 2. Filtrar mascotas por tipo (PERRO)

### Request
```http
GET /api/pets?petType=PERRO HTTP/1.1
Host: localhost:8080
Accept: application/json
```

### cURL
```bash
curl -X GET "http://localhost:8080/api/pets?petType=PERRO"
```

### Respuesta Esperada
```json
[
  {
    "id": 1,
    "name": "Luna",
    "age": 2,
    "petType": "PERRO",
    "breed": "Criolla / Mestiza",
    "birthdate": "2024-03-15",
    "vaccines": ["RABIA", "MOQUILLO_CANINO"]
  },
  {
    "id": 3,
    "name": "Max",
    "age": 3,
    "petType": "PERRO",
    "breed": "Golden Retriever",
    "birthdate": "2023-06-20",
    "vaccines": ["RABIA", "MOQUILLO_CANINO", "PARVOVIRUS_CANINO"]
  }
]
```

## 3. Filtrar mascotas por tipo (GATO)

### Request
```http
GET /api/pets?petType=GATO HTTP/1.1
Host: localhost:8080
Accept: application/json
```

### cURL
```bash
curl -X GET "http://localhost:8080/api/pets?petType=GATO"
```

## 4. Obtener una mascota específica por ID

### Request
```http
GET /api/pets/1 HTTP/1.1
Host: localhost:8080
Accept: application/json
```

### cURL
```bash
curl -X GET http://localhost:8080/api/pets/1
```

### Respuesta Esperada
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

## 5. Crear una solicitud de adopción

### Request
```http
POST /api/applications HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: application/json

{
  "applicant": {
    "documentType": "CC",
    "documentNumber": "1030567890",
    "name": "María Fernanda López",
    "email": "maria.lopez@example.com",
    "phoneNumber": "3102345678"
  },
  "petId": 1,
  "housingType": "HOUSE",
  "hasOtherPets": false,
  "occupation": "Profesora"
}
```

### cURL
```bash
curl -X POST http://localhost:8080/api/applications \
  -H "Content-Type: application/json" \
  -d '{
    "applicant": {
      "documentType": "CC",
      "documentNumber": "1030567890",
      "name": "María Fernanda López",
      "email": "maria.lopez@example.com",
      "phoneNumber": "3102345678"
    },
    "petId": 1,
    "housingType": "HOUSE",
    "hasOtherPets": false,
    "occupation": "Profesora"
  }'
```

### Respuesta Esperada
```json
{
  "id": 3,
  "applicant": {
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "documentType": "CC",
    "documentNumber": "1030567890",
    "name": "María Fernanda López",
    "email": "maria.lopez@example.com",
    "phoneNumber": "3102345678"
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
  "housingType": "HOUSE",
  "hasOtherPets": false,
  "occupation": "Profesora",
  "status": "PENDING",
  "applicationDate": "2026-09-27T10:30:00"
}
```

## 6. Crear solicitud con solicitante que ya existe

Si un solicitante ya existe (mismo número de documento), el sistema lo reutiliza:

### Request
```http
POST /api/applications HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: application/json

{
  "applicant": {
    "documentType": "CC",
    "documentNumber": "1030567890",
    "name": "María Fernanda López",
    "email": "maria.lopez@example.com",
    "phoneNumber": "3102345678"
  },
  "petId": 2,
  "housingType": "HOUSE",
  "hasOtherPets": true,
  "occupation": "Profesora"
}
```

## 7. Ejemplo de solicitud con PASSPORT

### Request
```http
POST /api/applications HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: application/json

{
  "applicant": {
    "documentType": "PASSPORT",
    "documentNumber": "B98765432",
    "name": "John Smith",
    "email": "john.smith@example.com",
    "phoneNumber": "3209876543"
  },
  "petId": 4,
  "housingType": "APARTMENT",
  "hasOtherPets": false,
  "occupation": "Engineer"
}
```

## 8. Ejemplo de solicitud mínima (campos opcionales omitidos)

### Request
```http
POST /api/applications HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: application/json

{
  "applicant": {
    "documentType": "CC",
    "documentNumber": "1040123456",
    "name": "Pedro Gómez",
    "email": "pedro.gomez@example.com"
  },
  "petId": 5
}
```

## Errores Comunes

### 1. Mascota no encontrada (404)

#### Request
```http
GET /api/pets/999 HTTP/1.1
```

#### Respuesta
```json
{
  "timestamp": "2026-09-27T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Mascota no encontrada con ID: 999",
  "path": "/api/pets/999"
}
```

### 2. Error de validación (400)

#### Request
```http
POST /api/applications HTTP/1.1
Content-Type: application/json

{
  "applicant": {
    "documentNumber": "1030567890",
    "email": "email-invalido"
  },
  "petId": 1
}
```

#### Respuesta
```json
{
  "timestamp": "2026-09-27T10:30:00",
  "status": 400,
  "error": "Validation Error",
  "message": "Error en la validación de los datos",
  "path": "/api/applications",
  "validationErrors": {
    "applicant.name": "El nombre es obligatorio",
    "applicant.email": "El email debe ser válido",
    "applicant.documentType": "El tipo de documento es obligatorio"
  }
}
```

## Valores Válidos para Enumeraciones

### PetType
- `PERRO`
- `GATO`
- `OTRO`

### DocumentType
- `CC`
- `PASSPORT`

### HousingType
- `APARTMENT`
- `HOUSE`

### ApplicationStatus (solo lectura)
- `PENDING` (valor por defecto)
- `APPROVED`
- `REJECTED`

## Testing con Postman

1. Importa estas peticiones en Postman
2. Crea una colección llamada "Pet Adoption API"
3. Agrega una variable de entorno `base_url` con valor `http://localhost:8080`
4. Usa `{{base_url}}` en lugar de la URL completa

## Testing con HTTPie

```bash
# Instalar HTTPie
pip install httpie

# Listar mascotas
http GET localhost:8080/api/pets

# Filtrar por tipo
http GET localhost:8080/api/pets petType==PERRO

# Crear solicitud
http POST localhost:8080/api/applications \
  applicant:='{"documentType":"CC","documentNumber":"1030567890","name":"Test User","email":"test@example.com"}' \
  petId:=1 \
  housingType=HOUSE \
  hasOtherPets:=false \
  occupation="Developer"
```
