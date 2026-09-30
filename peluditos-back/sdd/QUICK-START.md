# 🚀 Quick Start Guide

Guía rápida para poner en marcha el backend de Pet Adoption API.

## ✅ Pre-requisitos

- **Java 21** instalado
- **Maven 3.8+** instalado (o usar el wrapper incluido)
- Conexión a Internet (para descargar dependencias)

## 🏃‍♂️ Inicio Rápido (3 pasos)

### 1️⃣ Compilar el proyecto

```powershell
mvn clean compile
```

O usando Maven Wrapper:
```powershell
.\mvnw.cmd clean compile
```

### 2️⃣ Ejecutar los tests

```powershell
mvn test
```

### 3️⃣ Ejecutar la aplicación

```powershell
mvn spring-boot:run -D"spring-boot.run.profiles=local"
```

O usar el script PowerShell:
```powershell
.\run-local.ps1
```

## 🌐 Verificar que funciona

Una vez iniciada la aplicación, abre tu navegador o Postman:

### Test 1: Listar mascotas
```
GET http://localhost:8080/api/pets
```

### Test 2: Obtener una mascota
```
GET http://localhost:8080/api/pets/1
```

### Test 3: Crear solicitud de adopción
```
POST http://localhost:8080/api/applications
Content-Type: application/json

{
  "applicant": {
    "documentType": "CC",
    "documentNumber": "1234567890",
    "name": "Test User",
    "email": "test@example.com",
    "phoneNumber": "3001234567"
  },
  "petId": 1,
  "housingType": "HOUSE",
  "hasOtherPets": false,
  "occupation": "Developer"
}
```

## 📊 Respuesta Esperada

Si ves algo como esto, ¡todo funciona! ✅

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

## 🛠️ Solución de Problemas

### Error: Java no encontrado
```powershell
# Verificar versión de Java
java -version

# Debe mostrar Java 21 o superior
```

### Error: Maven no encontrado
```powershell
# Usar el wrapper incluido
.\mvnw.cmd --version
```

### Error: No se puede conectar a la base de datos
- Verificar que `application-local.properties` existe
- Verificar que las credenciales de Supabase son correctas
- Verificar conexión a Internet

### Error: Puerto 8080 en uso
```powershell
# Cambiar el puerto en application.properties
server.port=8081
```

## 📚 Siguiente Paso

Lee la documentación completa en:
- `README.md` - Documentación general
- `API-EXAMPLES.md` - Ejemplos de todas las APIs
- `IMPLEMENTATION-SUMMARY.md` - Detalles técnicos

## 💡 Tips

1. **Desarrollo continuo**: Spring Boot Dev Tools recarga automáticamente
2. **Ver logs**: Los logs aparecen en la consola
3. **Detener servidor**: Presiona `Ctrl+C`
4. **Cambiar perfil**: Modifica `-Dspring-boot.run.profiles=local`

## 🎯 Endpoints Principales

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/pets` | Lista todas las mascotas |
| GET | `/api/pets?petType=PERRO` | Filtra por tipo |
| GET | `/api/pets/{id}` | Obtiene mascota por ID |
| POST | `/api/applications` | Crea solicitud de adopción |

## 🔥 Todo listo!

Tu API REST está funcionando en: **http://localhost:8080** 🎉
